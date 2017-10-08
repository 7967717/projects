package ua.miratech.rudenko.docstore.textIndex;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import ua.miratech.rudenko.docstore.domain.ArticleStatus;
import ua.miratech.rudenko.docstore.pdfParser.ReadPdf;
import ua.miratech.rudenko.docstore.service.ArticlesService;

import java.io.*;
import java.util.Date;

/**
 * Created by RomanR on 2/10/14.
 */

public class IndexFiles implements Runnable  {

    public static final Logger LOG = Logger.getLogger("rootLogger");
    public static final String TEMP_DIR = "/STORAGE/TEMP_FILES/";
    private static final String ARTICLE_INDEX_STATUS_Y = "INDEXED";
    private static Integer counter = 0;

    private String filePath;
    private String indexPath;
    private boolean createNewIndex;
    private Integer articleId;
    private ArticlesService articlesService;

    private ReadPdf readPdf;

    public IndexFiles(String filePath, String indexPath, boolean createNewIndex, Integer articleId,
                      ArticlesService articlesService) {
        this.filePath = filePath;
        this.indexPath = indexPath;
        this.createNewIndex = createNewIndex;
        this.articleId = articleId;
        this.articlesService = articlesService;
        readPdf = new ReadPdf();

    }

    @Override
    public void run() {
        LOG.info(Thread.currentThread().getName() + " started new index job");
        startIndexing();
    }

    /**
     * Creates new index or updates existing to indexPath directory.
     */
    public void startIndexing() {

        LOG.info("entered startIndexing");

        Date start = new Date();

        try {
            LOG.info("Indexing to directory '" + indexPath + "'...");

            Directory dir = FSDirectory.open(new File(indexPath));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//            Analyzer analyzer = new RussianAnalyzer(Version.LUCENE_46);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_46, analyzer);

            if (createNewIndex) {
                // Create a new index in the directory, removing any
                // previously indexed documents:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
                LOG.info("Creating new index in '" + indexPath + "'...");
            } else {
                // Add new documents to an existing index:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
                LOG.info("Updating index in '" + indexPath + "'...");
            }

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, filePath);

            writer.close();

            Date end = new Date();
            LOG.info("Finished indexing, it took ..........................");
            LOG.info(end.getTime() - start.getTime() + " total milliseconds");


            LOG.info("go to change status");

            ArticleStatus article = new ArticleStatus();
            article.setId(articleId);
            article.setStatus(ARTICLE_INDEX_STATUS_Y);
            LOG.info("id " + article.getId());
            LOG.info("status " + article.getStatus());
            articlesService.updateNewArticleStatus(article);


        } catch (IOException e) {
            LOG.info("failed to index file " + filePath);
            LOG.error(" caught a " + e.getClass() +
                    "\n with message: " + e.getMessage());
        }
    }

    /**
     * Indexes the given file using the given writer
     *
     * @param writer   Writer to the index where the given file/dir info will be stored
     * @param filePath The file to index
     * @throws IOException If there is a low-level I/O error
     */
    private void indexDocs(IndexWriter writer, String filePath)
            throws IOException {

        LOG.info("entered indexDocs");

        String fileName = getResultFileName();
        readPdf.parsePdfLocationStrategy(filePath, fileName);

        LOG.info("parsed pdf source to " + fileName);

        File file = new File(fileName);
        File pdfFile = new File(filePath);

        // do not try to index files that cannot be read
        if (file.canRead()) {

            FileInputStream fis;
            FileInputStream pdfFis;
            try {
                fis = new FileInputStream(file);
                pdfFis = new FileInputStream(pdfFile);
            } catch (FileNotFoundException fnfe) {
                // at least on windows, some temporary files raise this exception with an "access denied" message
                // checking if the file can be read doesn't help
                LOG.error("Access to the file is denied");
                return;
            }

            try {

                // make a new, empty document
                Document doc = new Document();
                readPdf.init(pdfFis);

                // Add the path of the file as a field named "path".  Use a
                // field that is indexed (i.e. searchable), but don't tokenize
                // the field into separate words and don't index term frequency
                // or positional information:
                Field pathField = new StringField("path", filePath, Field.Store.YES);
                doc.add(pathField);

                // Add the last modified date of the file a field named "modified".
                // Use a LongField that is indexed (i.e. efficiently filterable with
                // NumericRangeFilter).  This indexes to milli-second resolution, which
                // is often too fine.  You could instead create a number based on
                // year/month/day/hour/minutes/seconds, down the resolution you require.
                // For example the long value 2011021714 would mean
                // February 17, 2011, 2-3 PM.
//                doc.add(new LongField("modified", file.lastModified(), Field.Store.NO)); TODO check if I need the field?

                // Add the author info from metadata a field named "author".
                doc.add(new TextField("author", readPdf.getMetaInfo("Author"), Field.Store.YES));

                // Add the subject info from metadata a field named "subject".
                doc.add(new TextField("subject", readPdf.getMetaInfo("Subject"), Field.Store.YES));

                // Add the title info from metadata a field named "title".
                doc.add(new TextField("title", readPdf.getMetaInfo("Title"), Field.Store.YES));

                // Add the keywords info from metadata a field named "keywords".
                doc.add(new TextField("keywords", readPdf.getMetaInfo("Keywords"), Field.Store.YES));

                // Add the contents of the file to a field named "contents".  Specify a Reader,
                // so that the text of the file is tokenized and indexed, but not stored.
                // Note that FileReader expects the file to be in UTF-8 encoding.
                // If that's not the case searching for special characters will fail.
                doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(fis, "UTF-8"))));

                if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                    // New index, so we just add the document (no old document can be there):
                    LOG.info("adding to index file " + file);
                    writer.addDocument(doc);
                } else {
                    // Existing index (an old copy of this document may have been indexed) so
                    // we use updateDocument instead to replace the old one matching the exact
                    // path, if present:
                    LOG.info("updating to index file " + file);
                    writer.updateDocument(new Term("path", file.getPath()), doc);
                }

            } finally {
                fis.close();
            }
        }
    }

    /**
     * Creates temp txt file name.
     */
    private static String getResultFileName() {
        counter++;
        String fileName = TEMP_DIR + counter + ".txt";
        LOG.info("created name for txt file");
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public void setCreateNewIndex(boolean createNewIndex) {
        this.createNewIndex = createNewIndex;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }


}






























































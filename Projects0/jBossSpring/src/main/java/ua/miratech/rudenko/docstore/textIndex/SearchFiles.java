package ua.miratech.rudenko.docstore.textIndex;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RomanR on 2/10/14.
 */
public class SearchFiles {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    List<String> searchIndex(String indexDir, String queryStr, Integer maxHits) {

        LOG.info("entered searchIndex");

        IndexReader reader = null;
        try {
            reader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        } catch (IOException e) {
            LOG.error("could not open index directory");
            e.printStackTrace();
        }

        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
        QueryParser parser = new QueryParser(Version.LUCENE_46,
                "contents", analyzer);
        Query query = null;
        LOG.info("started query parsing");

        List<String> docList = new ArrayList<String>();
        try {
            query = parser.parse(queryStr);

            LOG.info("started top docs search");
            TopDocs topDocs = null;
            try {
                topDocs = searcher.search(query, maxHits);
            } catch (IOException e) {
                LOG.error(e);
                e.printStackTrace();
            }
            LOG.info("adding top docs to list");
            ScoreDoc[] hits = topDocs.scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                int docId = hits[i].doc;
                Document d = null;
                try {
                    d = searcher.doc(docId);
                } catch (IOException e) {
                    LOG.error("could not add doc # " + docId);
                    e.printStackTrace();
                }
                docList.add(d.get("path"));
                LOG.info("added to list doc - " + d.get("path"));
            }

            LOG.info("Found " + hits.length + " docs.");
            return docList;

        } catch (ParseException e) {
            LOG.error("could not parse the query");
        }
        return docList;
    }

    List<String> extSearchIndex(String indexDir, String stringAuthor, String stringSubject, String stringTitle,
                                String stringKeywords, String stringContent, Integer maxHits) {

        LOG.info("entered extSearchIndex");

        IndexReader reader = null;
        try {
            reader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        } catch (IOException e) {
            LOG.error("could not open index directory");
            e.printStackTrace();
        }

        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);

        BooleanQuery booleanQuery = new BooleanQuery();
        LOG.info("started query parsing");

        List<String> docList = new ArrayList<String>();
        try {
            if (!stringAuthor.isEmpty()) {
                QueryParser authorParser = new QueryParser(Version.LUCENE_46, "author", analyzer);
                Query authorQuery = authorParser.parse(stringAuthor);
                booleanQuery.add(authorQuery, BooleanClause.Occur.MUST);
            }
            if (!stringSubject.isEmpty()) {
                QueryParser subjectParser = new QueryParser(Version.LUCENE_46, "subject", analyzer);
                Query subjectQuery = subjectParser.parse(stringSubject);
                booleanQuery.add(subjectQuery, BooleanClause.Occur.MUST);
            }
            if (!stringTitle.isEmpty()) {
                QueryParser titleParser = new QueryParser(Version.LUCENE_46, "title", analyzer);
                Query titleQuery = titleParser.parse(stringTitle);
                booleanQuery.add(titleQuery, BooleanClause.Occur.MUST);
            }
            if (!stringKeywords.isEmpty()) {
                QueryParser keywordsParser = new QueryParser(Version.LUCENE_46, "keywords", analyzer);
                Query keywordsQuery = keywordsParser.parse(stringKeywords);
                booleanQuery.add(keywordsQuery, BooleanClause.Occur.MUST);
            }
            if (!stringContent.isEmpty()) {
                QueryParser contentParser = new QueryParser(Version.LUCENE_46, "contents", analyzer);
                Query contentQuery = contentParser.parse(stringContent);
                booleanQuery.add(contentQuery, BooleanClause.Occur.MUST);
            }

            LOG.info("started top docs search");
            TopDocs topDocs = null;
            try {
                topDocs = searcher.search(booleanQuery, maxHits);
            } catch (IOException e) {
                LOG.error(e);
                e.printStackTrace();
            }
            LOG.info("adding top docs to list");
            ScoreDoc[] hits = topDocs.scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                int docId = hits[i].doc;
                Document d = null;
                try {
                    d = searcher.doc(docId);
                } catch (IOException e) {
                    LOG.error("could not add doc # " + docId);
                    e.printStackTrace();
                }
                docList.add(d.get("path"));
                LOG.info("added to list doc - " + d.get("path"));
            }

            LOG.info("Found " + hits.length + " docs.");
            return docList;

        } catch (ParseException e) {
            LOG.error("could not parse the query");
        }
        return docList;
    }
}

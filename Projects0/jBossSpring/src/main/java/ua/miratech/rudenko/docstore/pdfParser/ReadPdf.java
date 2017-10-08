package ua.miratech.rudenko.docstore.pdfParser;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by RomanR on 2/3/14.
 */
@Component
public class ReadPdf {

    public static final Logger LOG = Logger.getLogger("rootLogger");
    public static final String NO_INFO = "!-no info-!";
    private PdfReader reader;
    Map<String, String> map;

    public void init(InputStream inputStream) {
        try {
            reader = new PdfReader(inputStream);
        } catch (IOException e) {
            LOG.error("processing PDF exception");
            e.printStackTrace();
        }
        map = reader.getInfo();
    }

    public String getMetaInfo(String key){
        String value = NO_INFO;
        if (map.get(key) != null) {
            value = map.get(key);
        } else {
            return value;
        }
        return value;
    }

    public String getPageSize() {
        return String.valueOf(reader.getPageSize(1));
    }

    public Integer getPagesN() {
        return reader.getNumberOfPages();
    }

    /**
     * Parses a specific area of a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            out.println(PdfTextExtractor.getTextFromPage(reader, i));
        }
        out.flush();
        out.close();
        reader.close();
    }

    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdfSimpleStrategy(String pdf, String txt) throws IOException {

        PrintWriter out = new PrintWriter(new FileOutputStream(txt));

        try {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        } catch (IOException e) {
            LOG.error("parsing PDF exception");
            throw e;
        } finally {
            out.flush();
            out.close();
            reader.close();
        }

    }


    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdfLocationStrategy(String pdf, String txt) throws IOException {
        LOG.info("entered parsePdfLocationStrategy");

        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        LOG.info("parsing " + pdf);
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new LocationTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        out.flush();
        out.close();
        reader.close();
    }
}

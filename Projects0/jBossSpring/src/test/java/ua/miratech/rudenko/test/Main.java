package ua.miratech.rudenko.test;

import java.io.IOException;

/**
 * Created by RomanR on 2/3/14.
 */
public class Main {

    public static final String SOURCE = "/STORAGE/TEMP_FILES/One Size Fits All – Part 2 Benchmarking Results.pdf";
//    public static final String RESULT = "/STORAGE/TEMP_FILES/xmp.xml";
    public static final String RESULT = "/STORAGE/TEMP_FILES/res3.txt";

    public static final String RESULT_PATH = "/STORAGE/TEMP_FILES/";
    private static Integer counter = 1;




    public static void main(String[] args) throws IOException {

//        ReadPdf readPdf = new ReadPdf();
//        readPdf.parsePdfLocationStrategy(SOURCE, RESULT);
//        readPdf.parsePdfSimpleStrategy(SOURCE, RESULT);
//        readPdf.parsePdf(SOURCE, RESULT);

        counter++;
        String fileName = RESULT_PATH + counter + ".txt";
        System.out.println(fileName);




//
////        create a new DocumentBuilderFactory
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//        try {
//            // use the factory to create a documentbuilder
//            DocumentBuilder builder = factory.newDocumentBuilder();
////
//            File file = new File(RESULT);
//        InputStream in = new FileInputStream(file);
//////            FileInputStream in = new FileInputStream(SOURCE);
////
//            // create a new document from input stream
//            Document doc = builder.parse(in);
////            in.close();
//
//            NodeList nodes = doc.getElementsByTagName("xmp:CreateDate");
//
//            System.out.println(nodes.item(0).getTextContent());
//
//            // get the first element
//            Element element = doc.getDocumentElement();
////
////            System.out.println(element.getTagName());
////            System.out.println(element.("title"));
//
//            // get all child nodes
////            NodeList nodes = element.getChildNodes();
//
////            String node = nodes.("title");
////            String node = element.getAttribute("title");
//
////            System.out.println(node);
//
//            // print the text content of each child
////            for (int i = 0; i < nodes.getLength(); i++) {
////                if (nodes.item(i).getTextContent())
////                System.out.println("" + nodes.item(i).getTextContent());
//            } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
////    } catch (Exception ex) {
////            ex.printStackTrace();
////        }
//
////        XPathFactory xpathFactory = XPathFactory.newInstance();
////        XPath xpath = xpathFactory.newXPath();
//////        xpath.setNamespaceContext(new XFDLNamespaceContext());
////
////        InputSource source = new InputSource(in);
////        String title = "string";
////        try {
////            title = xpath.evaluate("/xmpmeta", source);
////            System.out.println("hfg" + title);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }

    }
}

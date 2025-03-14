package in.hridayan.xmlforge.utils;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlFormatter {

  public static String formatXml(String inputXml) {
    try {
      Document document = parseXml(inputXml);
      document.getDocumentElement().normalize();

      String originalXml = prettyPrintXml(document);
      XmlAttributeSorter.sortAttributes(document.getDocumentElement());
      String formattedXml = prettyPrintXml(document);

      return validateXmlStructure(originalXml, formattedXml) ? formattedXml : null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Document parseXml(String xml) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    return builder.parse(new InputSource(new StringReader(xml)));
  }

  private static String prettyPrintXml(Document document) throws Exception {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");

    StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(writer));
    return writer.toString().trim();
  }

  private static boolean validateXmlStructure(String originalXml, String formattedXml) {
    try {
      Document originalDoc = parseXml(originalXml);
      Document formattedDoc = parseXml(formattedXml);
      return originalDoc.isEqualNode(formattedDoc);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
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
      // Remove extra blank lines and ensure at least one empty line between attribute blocks
      inputXml = inputXml.replaceAll("(?m)^\\s*$[\n\r]{1,}", "").trim();

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
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();

    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");

    StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(writer));

    String formattedXml = writer.toString().trim();

    return formatLines(formattedXml);
  }

  private static String formatLines(String formattedXml) {
    // Remove duplicate XML declarations if any
    formattedXml = formattedXml.replaceAll("(<\\?xml[^>]+\\?>)(\\s*<\\?xml[^>]+\\?>)+", "$1");

    // Ensure attributes inside a tag are aligned properly
    formattedXml =
        formattedXml.replaceAll("(<[^!?/>\\s]+)\\s+", "$1 "); // Fixes first space after <TextView

    // Indent attributes properly (but avoid breaking self-closing tags)
    formattedXml = formattedXml.replaceAll("(\\S+?=\"[^\"]+\")\\s*", "\n    $1");

    // Remove unnecessary newlines before '>' or '/>' to avoid misalignment
    formattedXml = formattedXml.replaceAll("\"\\s*\n\\s*([/>])", "\"$1");

    // Ensure a newline after '>', but keep '/>' on the same line
    formattedXml = formattedXml.replaceAll("([^/])>", "$1>\n");
    formattedXml = formattedXml.replaceAll("/>", "/>\n");
    // Ensure the XML declaration is always in one line with no extra spaces or newlines
    formattedXml =
        formattedXml.replaceAll(
            "\\s*<\\?xml\\s+version=\"1.0\"\\s+encoding=\"UTF-8\"\\s*\\?>\\s*",
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    return formattedXml;
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

package in.hridayan.xmlforge.utils;

import in.hridayan.xmlforge.config.Const;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlAttributeSorter {

  public static void sortAttributes(Element element) {
    if (!element.hasAttributes()) return;

    NamedNodeMap attributes = element.getAttributes();
    List<Node> priorityAttrs = new ArrayList<>();
    List<Node> layoutAttrs = new ArrayList<>();
    List<Node> paddingAttrs = new ArrayList<>();
    List<Node> otherAttrs = new ArrayList<>();

    for (int i = 0; i < attributes.getLength(); i++) {
      Node attr = attributes.item(i);
      String attrName = attr.getNodeName();

      if (Const.ATTR_PRIORITY_ORDER.contains(attrName)) {
        priorityAttrs.add(attr);
      } else if (attrName.startsWith("android:layout_")) {
        layoutAttrs.add(attr);
      } else if (attrName.startsWith("android:padding")) {
        paddingAttrs.add(attr);
      } else {
        otherAttrs.add(attr);
      }
    }

    priorityAttrs.sort(
        Comparator.comparingInt(attr -> Const.ATTR_PRIORITY_ORDER.indexOf(attr.getNodeName())));
    layoutAttrs.sort(Comparator.comparing(Node::getNodeName));
    paddingAttrs.sort(Comparator.comparing(Node::getNodeName));
    otherAttrs.sort(Comparator.comparing(Node::getNodeName));

    List<Node> sortedAttrs = new ArrayList<>();
    sortedAttrs.addAll(priorityAttrs);
    sortedAttrs.addAll(layoutAttrs);
    sortedAttrs.addAll(paddingAttrs);
    sortedAttrs.addAll(otherAttrs);

    for (Node attr : sortedAttrs) {
      element.removeAttribute(attr.getNodeName());
      element.setAttribute(attr.getNodeName(), attr.getNodeValue());
    }

    NodeList children = element.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeType() == Node.ELEMENT_NODE) {
        sortAttributes((Element) child);
      }
    }
  }
}

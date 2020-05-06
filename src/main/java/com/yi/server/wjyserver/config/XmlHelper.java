package com.yi.server.wjyserver.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Collections;
import java.util.List;

/**
 * @author wujingyi
 */
public class XmlHelper {
    public static Element getElementByTagName(Element element, String tagName) {
        return element.element(tagName);
    }

    public static List<Element> getElementsByTagName(Element element, String tagName) {
        List<Element> targetElementList  = element.elements(tagName);
        return targetElementList == null ? Collections.emptyList() : targetElementList;
    }

    public static String getAttributeAsString(Element element, String attributeName) {
        return element.attributeValue(attributeName);
    }

    public static int getAttributeAsInt(Element element, String attributeName) {
        return Integer.parseInt(element.attributeValue(attributeName));
    }

    public static Element mergeXmlRoot(Element rootSrc, Element rootDst) {
        for (Object obj : rootSrc.elements()) {
            rootDst.add(((org.dom4j.Element) obj).detach());
        }
        return rootDst;
    }

    public static Element loadXmlConfig(String fileName) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(fileName);
            return document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(String.format("<XmlHelper> load xml config error. fileName:%s", fileName), e);
        }
    }
}

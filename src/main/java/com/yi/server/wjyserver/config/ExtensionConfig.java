package com.yi.server.wjyserver.config;

import org.dom4j.Element;

/**
 * @author wujingyi
 */
public class ExtensionConfig {
    private int id;
    private String className;

    public ExtensionConfig(Element eleExtension) {
        this.id = XmlHelper.getAttributeAsInt(eleExtension, "id");
        this.className = XmlHelper.getAttributeAsString(eleExtension, "class");
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}

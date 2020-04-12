package com.yi.server.wjyserver.config;


import com.yi.server.wjyserver.logger.WJYServerLogger;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wujingyi
 */
public class KeyValueConfigBatch {
    private Map<String, String> keyValueBatchMap;

    public void loadKeyValueBatch(Element root) {
        this.keyValueBatchMap = new HashMap<>();
        Element eleKeyValueBatch = XmlHelper.getElementByTagName(root, "KeyValueBatch");
        if (eleKeyValueBatch == null) {
            WJYServerLogger.LOGGER.warn("<KeyValueConfigBatch> there is no element named 'eleKeyValueBatch'");
            return;
        }
        List<Element> eleKeyValueList = XmlHelper.getElementsByTagName(eleKeyValueBatch, "KeyValue");
        for (Element eleKeyValue: eleKeyValueList) {
            keyValueBatchMap.put(eleKeyValue.attribute("key").getValue(), eleKeyValue.attribute("value").getValue());
        }
    }
}

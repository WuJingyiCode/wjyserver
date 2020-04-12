package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.config.ExtensionConfig;
import com.yi.server.wjyserver.config.KeyValueConfigBatch;
import com.yi.server.wjyserver.config.ServerSystemProperty;
import com.yi.server.wjyserver.config.XmlHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wujingyi
 *
 * 配置的获取
 */
public class WJYServerConfig extends Command {

    private static final String SERVER_CONFIG_FILE_NAME = "serverConfig.xml";
    private static final String SERVER_WORKSPACE_CONFIG_FILE_NAME = "serverWorkspaceConfig.xml";

    private String serverConfigName;
    private String serverWorkspaceConfigName;

    private KeyValueConfigBatch keyValueConfigBatch;
    private List<ExtensionConfig> extensionConfigList;

    public WJYServerConfig() {
        this.serverConfigName = getConfigName(ServerSystemProperty.SERVER_CONFIG_PATH, SERVER_CONFIG_FILE_NAME);
        this.serverWorkspaceConfigName = getConfigName(ServerSystemProperty.SERVER_WORKSPACE_CONFIG_PATH, SERVER_WORKSPACE_CONFIG_FILE_NAME);
    }

    private String getConfigName(String path,String fileName) {
        String serverConfigPath = System.getProperty(path);
        return serverConfigPath + File.separator + fileName;
    }

    /**
     * 加载配置文件
     */
    @Override
    protected void executeOnce() {
        XmlHelper.loadXmlConfig(serverConfigName);
        Element configRoot = XmlHelper.loadXmlConfig(serverConfigName);
        Element workspaceConfigRoot = XmlHelper.loadXmlConfig(serverWorkspaceConfigName);
        Element mergeRoot = XmlHelper.mergeXmlRoot(configRoot, workspaceConfigRoot);
        List<Element> eleExtensionConfigList = XmlHelper.getElementsByTagName(mergeRoot, "ExtensionConfig");
        List<ExtensionConfig> extensionConfigList = new ArrayList<>(eleExtensionConfigList.size());
        for (Element eleExtensionConfig: eleExtensionConfigList) {
            extensionConfigList.add(new ExtensionConfig(eleExtensionConfig));
        }
        this.extensionConfigList = extensionConfigList;
    }

    @Override
    protected void rollbackOnce() {

    }

    public List<ExtensionConfig> getExtensionConfigList() {
        return extensionConfigList;
    }
}

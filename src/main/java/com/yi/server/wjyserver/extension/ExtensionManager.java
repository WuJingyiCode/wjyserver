package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.RequestType;
import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.command.ListCommand;
import com.yi.server.wjyserver.command.WJYServerConfig;
import com.yi.server.wjyserver.config.ExtensionConfig;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.lang.reflect.Constructor;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


/**
 * @author wujingyi
 *
 * Extension统一管理。
 * 1.读取配置文件，利用反射，创建Extension。
 * 2.将Extension将入commandList中，这样子就会初始化
 */
public class ExtensionManager extends ListCommand {

    private WJYServer server;
    private EnumMap<RequestType, RequestDispatcher> requestDispatcherMap = new EnumMap<>(RequestType.class);

    public ExtensionManager(WJYServer server) {
        this.server = server;
    }

    /**
     * 初始化所有的Extension
     */
    @Override
    protected void beforeExecuteOnce() {
        WJYServerConfig config = server.getServerConfig();
        List<ExtensionConfig> extensionConfigList = config.getExtensionConfigList();
        for (ExtensionConfig extensionConfig: extensionConfigList) {
            addCommand(createExtension(extensionConfig));
        }
    }

    public void addListeners(Extension extension) {
        requestDispatcherMap.forEach((type, dispatcher) -> {
            dispatcher.addListeners(extension);
        });
    }

    private Extension createExtension(ExtensionConfig extensionConfig) {
        try {
            Class<?> clazz = Class.forName(extensionConfig.getClassName());
            Constructor<?> constructor = clazz.getConstructor(int.class);
            constructor.setAccessible(true);
            return (Extension) constructor.newInstance(extensionConfig.getId());
        } catch (Throwable t) {
            WJYServerLogger.LOGGER.error(String.format("<ExtensionManager> create Extension fail. className=%s, id=%s", extensionConfig.getClassName(), extensionConfig.getId()));
            return null;
        }

    }

}

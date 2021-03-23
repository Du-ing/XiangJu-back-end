//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xiangju.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.util.*;

/**
 * 引用ServerEndpointExporter类总是报错
 * 因此复制一个自己使用
 */
public class ServerEndpointExporterUtil extends WebApplicationObjectSupport implements InitializingBean, SmartInitializingSingleton {
    @Nullable
    private List<Class<?>> annotatedEndpointClasses;
    @Nullable
    private ServerContainer serverContainer;

    public ServerEndpointExporterUtil() {
    }

    public void setAnnotatedEndpointClasses(Class<?>... annotatedEndpointClasses) {
        this.annotatedEndpointClasses = Arrays.asList(annotatedEndpointClasses);
    }

    public void setServerContainer(@Nullable ServerContainer serverContainer) {
        this.serverContainer = serverContainer;
    }

    @Nullable
    protected ServerContainer getServerContainer() {
        return this.serverContainer;
    }

    protected void initServletContext(ServletContext servletContext) {
        if (this.serverContainer == null) {
            this.serverContainer = (ServerContainer)servletContext.getAttribute("javax.websocket.server.ServerContainer");
        }

    }

    protected boolean isContextRequired() {
        return false;
    }

    public void afterPropertiesSet() {
        Assert.state(this.getServerContainer() != null, "javax.websocket.server.ServerContainer not available");
    }

    public void afterSingletonsInstantiated() {
        this.registerEndpoints();
    }

    protected void registerEndpoints() {
        Set<Class<?>> endpointClasses = new LinkedHashSet();
        if (this.annotatedEndpointClasses != null) {
            endpointClasses.addAll(this.annotatedEndpointClasses);
        }

        ApplicationContext context = this.getApplicationContext();
        if (context != null) {
            String[] endpointBeanNames = context.getBeanNamesForAnnotation(ServerEndpoint.class);
            String[] var4 = endpointBeanNames;
            int var5 = endpointBeanNames.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String beanName = var4[var6];
                endpointClasses.add(context.getType(beanName));
            }
        }

        Iterator var8 = endpointClasses.iterator();

        while(var8.hasNext()) {
            Class<?> endpointClass = (Class)var8.next();
            this.registerEndpoint(endpointClass);
        }

        if (context != null) {
            Map<String, ServerEndpointConfig> endpointConfigMap = context.getBeansOfType(ServerEndpointConfig.class);
            Iterator var11 = endpointConfigMap.values().iterator();

            while(var11.hasNext()) {
                ServerEndpointConfig endpointConfig = (ServerEndpointConfig)var11.next();
                this.registerEndpoint(endpointConfig);
            }
        }

    }

    private void registerEndpoint(Class<?> endpointClass) {
        ServerContainer serverContainer = this.getServerContainer();
        Assert.state(serverContainer != null, "No ServerContainer set. Most likely the server's own WebSocket ServletContainerInitializer has not run yet. Was the Spring ApplicationContext refreshed through a org.springframework.web.context.ContextLoaderListener, i.e. after the ServletContext has been fully initialized?");

        try {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Registering @ServerEndpoint class: " + endpointClass);
            }

            serverContainer.addEndpoint(endpointClass);
        } catch (DeploymentException var4) {
            throw new IllegalStateException("Failed to register @ServerEndpoint class: " + endpointClass, var4);
        }
    }

    private void registerEndpoint(ServerEndpointConfig endpointConfig) {
        ServerContainer serverContainer = this.getServerContainer();
        Assert.state(serverContainer != null, "No ServerContainer set");

        try {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Registering ServerEndpointConfig: " + endpointConfig);
            }

            serverContainer.addEndpoint(endpointConfig);
        } catch (DeploymentException var4) {
            throw new IllegalStateException("Failed to register ServerEndpointConfig: " + endpointConfig, var4);
        }
    }
}

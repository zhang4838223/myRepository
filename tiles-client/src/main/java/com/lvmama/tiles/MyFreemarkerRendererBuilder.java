package com.lvmama.tiles;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.freemarker.FreemarkerRequestException;
import org.apache.tiles.request.freemarker.render.AttributeValueFreemarkerServlet;
import org.apache.tiles.request.freemarker.render.FreemarkerRenderer;
import org.apache.tiles.request.freemarker.render.FreemarkerRendererBuilder;
import org.apache.tiles.request.freemarker.render.InitParamsServletConfig;
/**
 * 自定义远程加载模板的渲染器（重写build方法）
 * @author zhangxiaojun
 *
 */
public class MyFreemarkerRendererBuilder {


    /**
     * The initialization parameters.
     */
    private Map<String, String> params = new HashMap<String, String>();

    /**
     * The application context.
     */
    private ApplicationContext applicationContext;

    /**
     * Constructor.
     */
    private MyFreemarkerRendererBuilder() {
    }

    /**
     * Creates a new instance of this class.
     *
     * @return A new instance of the builder.
     */
    public static MyFreemarkerRendererBuilder createInstance() {
        return new MyFreemarkerRendererBuilder();
    }

    /**
     * Sets a parameter for the internal servlet.
     *
     * @param key The name of the parameter.
     * @param value The value of the parameter.
     * @return This object.
     */
    public MyFreemarkerRendererBuilder setParameter(String key, String value) {
        params.put(key, value);
        return this;
    }

    /**
     * Sets the application context.
     *
     * @param applicationContext The application context.
     * @return This object.
     */
    public MyFreemarkerRendererBuilder setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        return this;
    }

    /**
     * Creates a new {@link FreemarkerRenderer} with the given configuration.
     *引用自定义的MyAttributeValueFreemarkerServlet
     * @return A new Freemarker renderer.
     */
    public FreemarkerRenderer build() {
        AttributeValueFreemarkerServlet servlet = new MyAttributeValueFreemarkerServlet();
        try {
            servlet.init(new InitParamsServletConfig(params, applicationContext));
            return new FreemarkerRenderer(servlet);
        } catch (ServletException e) {
            throw new FreemarkerRequestException(
                    "Cannot initialize internal servlet", e);
        }

    }



}

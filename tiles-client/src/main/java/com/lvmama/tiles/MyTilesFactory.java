package com.lvmama.tiles;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.evaluator.AttributeEvaluatorFactory;
import org.apache.tiles.extras.complete.CompleteAutoloadTilesContainerFactory;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.freemarker.TilesSharedVariableFactory;
import org.apache.tiles.impl.mgmt.CachingTilesContainer;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.ApplicationResource;
import org.apache.tiles.request.freemarker.render.FreemarkerRenderer;
import org.apache.tiles.request.freemarker.render.FreemarkerRendererBuilder;
import org.apache.tiles.request.freemarker.servlet.SharedVariableLoaderFreemarkerServlet;
import org.apache.tiles.request.mustache.MustacheRenderer;
import org.apache.tiles.request.render.BasicRendererFactory;
import org.apache.tiles.request.velocity.render.VelocityRenderer;
import org.apache.tiles.request.velocity.render.VelocityRendererBuilder;
/**
 * 自定义我们的Tiles容器工厂
 * @author zhangxiaojun
 *
 */
public class MyTilesFactory extends CompleteAutoloadTilesContainerFactory {



    /**
     * The freemarker renderer name.
     */
    private static final String FREEMARKER_RENDERER_NAME = "freemarker";

    /**
     * The velocity renderer name.
     */
    private static final String VELOCITY_RENDERER_NAME = "velocity";

    /**
     * The mustache renderer name.
     */
    private static final String MUSTACHE_RENDERER_NAME = "mustache";

    /** {@inheritDoc} */
    @Override
    public TilesContainer createDecoratedContainer(TilesContainer originalContainer,
            ApplicationContext applicationContext) {
        return new CachingTilesContainer(originalContainer);
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAttributeRenderers(
            final BasicRendererFactory rendererFactory,
            final ApplicationContext applicationContext,
            final TilesContainer container,
            final AttributeEvaluatorFactory attributeEvaluatorFactory) {
        super.registerAttributeRenderers(rendererFactory, applicationContext, container, attributeEvaluatorFactory);

        FreemarkerRenderer freemarkerRenderer = MyFreemarkerRendererBuilder
                .createInstance()
                .setApplicationContext(applicationContext)
                .setParameter("TemplatePath", "/")
                .setParameter("NoCache", "true")
                .setParameter("ContentType", "text/html")
                .setParameter("template_update_delay", "0")
                .setParameter("default_encoding", "utf-8")
                .setParameter("number_format", "0.##########")
                .setParameter(SharedVariableLoaderFreemarkerServlet.CUSTOM_SHARED_VARIABLE_FACTORIES_INIT_PARAM,
                        "tiles," + TilesSharedVariableFactory.class.getName()).build();
        rendererFactory.registerRenderer(FREEMARKER_RENDERER_NAME, freemarkerRenderer);

        VelocityRenderer velocityRenderer = VelocityRendererBuilder.createInstance()
                .setApplicationContext(applicationContext).build();
        rendererFactory.registerRenderer(VELOCITY_RENDERER_NAME, velocityRenderer);

        MustacheRenderer mustacheRenderer = new MustacheRenderer();
        mustacheRenderer.setAcceptPattern(Pattern.compile(".+\\.mustache"));
        rendererFactory.registerRenderer(MUSTACHE_RENDERER_NAME, mustacheRenderer);
    }
    /**
     * 该方法用于加载远程配置文件tiles1.xml
     */
    @Override
    protected List<ApplicationResource> getSources(
    		ApplicationContext applicationContext) {
    	// TODO Auto-generated method stub
//    	Collection<ApplicationResource> resources = applicationContext.getResources("http://localhost:8081/tiles-serverN/remote/tiles1.xml");
    	Collection<ApplicationResource> resources = applicationContext.getResources(ResourceBundle.getBundle("const").getString("tilesDefinition"));
    	return (List<ApplicationResource>) resources;
    }

}

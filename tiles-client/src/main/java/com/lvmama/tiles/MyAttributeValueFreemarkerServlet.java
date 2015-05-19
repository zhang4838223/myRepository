package com.lvmama.tiles;

import org.apache.tiles.request.freemarker.render.AttributeValueFreemarkerServlet;

import freemarker.cache.TemplateLoader;
/**
 * 引用自定义的远程模板加载器，来读取远程服务器上的tiles属性文件
 * @author zhangxiaojun
 *
 */
public class MyAttributeValueFreemarkerServlet extends
		AttributeValueFreemarkerServlet {
	@Override
	protected TemplateLoader createTemplateLoader(String templatePath) {
		// TODO Auto-generated method stub
		return new MyURLTemplateLoader();
	}
}

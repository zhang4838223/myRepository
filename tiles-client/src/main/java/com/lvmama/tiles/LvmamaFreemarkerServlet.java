package com.lvmama.tiles;

import org.apache.tiles.request.freemarker.servlet.SharedVariableLoaderFreemarkerServlet;

import freemarker.cache.TemplateLoader;
/**
 * 该类配置到web.xml中，可以用自定义的的模板加载器读取远程的定义模板
 * @author zhangxiaojun
 *
 */
public class LvmamaFreemarkerServlet extends
		SharedVariableLoaderFreemarkerServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected TemplateLoader createTemplateLoader(String templatePath) {
		// TODO Auto-generated method stub
		return new MyURLTemplateLoader();
	}
}

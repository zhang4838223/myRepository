package com.lvmama.tiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import freemarker.cache.TemplateLoader;
import freemarker.cache.URLTemplateLoader;
/**
 * 自定义模板加载器，主要用于读取远程服务器上的模板文件
 * @author zhangxiaojun
 *
 */
public class MyURLTemplateLoader extends URLTemplateLoader implements
		TemplateLoader {

	@Override
	protected URL getURL(String name) {
		String url = ResourceBundle.getBundle("const").getString("url");
		try {
			return  new URL(url+name);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("remotePath is null");
		}
	}

}

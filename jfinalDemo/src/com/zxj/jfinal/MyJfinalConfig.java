package com.zxj.jfinal;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.zxj.jfinal.model.GameServer;

/**
 * Created by xiaojun.zhang on 2015/11/4.
 */
public class MyJfinalConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/hello",MyJfinalController.class);
    }

    @Override
    public void configPlugin(Plugins plugins) {

        Prop prop = PropKit.use("db.txt");
        String jdbcUrl = prop.get("jdbcUrl");//"jdbc:mysql://localhost:3306/xsg_center";
        String user = prop.get("user");//"root";
        String password = prop.get("password");//"root";
        C3p0Plugin cp = new C3p0Plugin(jdbcUrl,user,password);
        plugins.add(cp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        plugins.add(arp);
        String tableName="game_server";
        arp.addMapping(tableName,GameServer.class);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}

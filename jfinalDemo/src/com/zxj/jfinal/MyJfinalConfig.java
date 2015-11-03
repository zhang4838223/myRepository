package com.zxj.jfinal;

import com.jfinal.config.*;

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

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}

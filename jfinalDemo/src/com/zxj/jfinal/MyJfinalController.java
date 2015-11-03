package com.zxj.jfinal;

import com.jfinal.core.Controller;

/**
 * Created by xiaojun.zhang on 2015/11/4.
 */
public class MyJfinalController extends Controller {

    public void index(){
        renderText("hello jfinal!!");
    }
}

package com.zxj.jfinal;

import com.jfinal.core.Controller;
import com.zxj.jfinal.model.GameServer;

import java.util.List;

/**
 * Created by xiaojun.zhang on 2015/11/4.
 */
public class MyJfinalController extends Controller {

    public void index(){
        renderText("hello jfinal!!");
        //保存数据
        new GameServer().set("show_id",2)
                .set("host","127.0.0.2")
                .set("is_new",true)
                .set("name","叶公好龙")
                .set("cp_show_only",0)
                .set("cp_enter_only",0)
                .set("online_limit",1).save();
    }

    public void findAll(){
        List<GameServer> serverList = GameServer.DAO.find("select * from game_server");
        setAttr("servers",serverList);
        renderJsp("/WEB-INF/servers.jsp");
    }
}

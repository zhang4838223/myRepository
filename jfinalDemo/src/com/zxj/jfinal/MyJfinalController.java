package com.zxj.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zxj.jfinal.model.GameServer;

import java.util.List;

/**
 * Created by xiaojun.zhang on 2015/11/4.
 */
public class MyJfinalController extends Controller {

    public void index(){
        renderText("hello jfinal!!");
        //保存数据
        /*new GameServer().set("show_id",2)
                .set("host","127.0.0.2")
                .set("is_new",true)
                .set("name","叶公好龙")
                .set("cp_show_only",0)
                .set("cp_enter_only",0)
                .set("online_limit",1).save();*/
    }

    @Before(CacheInterceptor.class)
    @CacheName("serverList")//缓存数据
    public void findAll(){
        List<GameServer> serverList = GameServer.DAO.find("select * from game_server");
        setAttr("servers",serverList);
        renderJsp("/WEB-INF/servers.jsp");
    }

    @Before(EvictInterceptor.class)
    @CacheName("serverList")//会更新该缓存数据
    public void save(){
        GameServer server = getModel(GameServer.class,"server");
        server.save();
        redirect("/hello/findAll");
    }

    private void queryAll() {
        List<GameServer> serverList = GameServer.DAO.find("select * from game_server");
        setAttr("servers", serverList);
        redirect("/hello/findAll");
    }

    @Before(EvictInterceptor.class)
    @CacheName("serverList")//会更新该缓存数据
    public void delete(){
        int id = getParaToInt("show_id");
        GameServer.DAO.deleteById(id);
        redirect("/hello/findAll");
    }
}

package com.zxj.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by xiaojun.zhang on 2015/11/18.
 */
public class GameServer extends Model<GameServer>{

    public static final GameServer DAO = new GameServer();

    public static void main(String[] args){
        new GameServer().set("show_id",1)
                .set("host","127.0.0.1")
                .set("is_new",1)
                .set("name","¸»¿ÉµÐ¹ú")
                .set("cp_show_only",1)
                .set("cp_enter_only",1)
                .set("online_limit",1).save();
    }
}

package com.zxj.controller;

import com.zxj.client.DubboClient;
import com.zxj.dubbo.service.DubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhang4838223 on 2015/10/24.
 */
public class DubboController {

    private static ClassPathXmlApplicationContext ctx = DubboClient.getContext();

    public static void main(String[] args){
        DubboService dubboService = (DubboService)ctx.getBean("dubboService");
        String result = dubboService.getRpcResult("xx");
        System.out.println("client get result: "+ result);
    }
}

package com.zxj.serviceImpl;

import com.zxj.dubbo.service.DubboService;

/**
 * Created by zhang4838223 on 2015/10/24.
 */
public class DubboServiceImpl implements DubboService {
    public String getRpcResult(String param) {
        System.out.println("server is called!!!!");
        return "RPC RESULT";
    }

    public String getName(String name) {
        return "hello "+name+" !";
    }
}

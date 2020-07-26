package com.atguigu.springcloud.service.impl;


import com.alibaba.fastjson.JSON;
import com.atguigu.springcloud.mapper.GatewayDefineMapper;
import com.atguigu.springcloud.redis.CacheHelper;
import com.atguigu.springcloud.service.GatewayDefineService;
import com.atguigu.springcloud.vo.GatewayDefine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GatewayDefineServiceImpl implements GatewayDefineService {

    @Resource
    GatewayDefineMapper gatewayDefineMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Title reset
     * @Description: 更新redis缓存，同时触发监听，更新路由缓存
     * @Date: 2019/10/23 9:40
     */
    public void reset() throws Exception{
        List<GatewayDefine> gatewayDefineServiceAll = gatewayDefineMapper.findAll();
        CacheHelper.saveCache("gateway_routes", JSON.toJSONString(gatewayDefineServiceAll));
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public static void main(String[] args){
       System.out.println(3&60);
    }


}

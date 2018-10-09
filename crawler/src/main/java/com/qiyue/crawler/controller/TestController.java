package com.qiyue.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "services:" + discoveryClient.getServices();
    }
}

package com.wang.kettle.controller;

import com.wang.kettle.service.KettleService;
import com.wang.kettle.utils.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KettleController {
    @Autowired
    public  KettleService kettleService;
    @Value("${kettle.trans}")
    private  String transFileName;
    @Value("${kettle.job}")
    private  String jobFileName;
    //执行一次作业
   // @Scheduled(cron="0 */30 * * * ?")
    @RequestMapping("/runJob")
    public Map<String,Object> runJob (){
        System.out.println(jobFileName);
        kettleService.runJob(jobFileName);
        Map<String,Object> mapResult = new HashMap<>();
        ResultMapUtils.makeResultMap(mapResult,ResultMapUtils.SUCCESS,"本次作业执行成功","data","");
        return mapResult;
    }
    //执行一次转换
    @RequestMapping("/runTrans")
    public Map<String,Object> runTrans (){
        kettleService.runTrans(transFileName);
        Map<String,Object> mapResult = new HashMap<>();
        ResultMapUtils.makeResultMap(mapResult,ResultMapUtils.SUCCESS,"本次转换执行成功","data","");
        return mapResult;
    }
}

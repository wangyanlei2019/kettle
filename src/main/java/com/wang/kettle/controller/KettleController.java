package com.wang.kettle.controller;

import com.wang.kettle.service.KettleService;
import com.wang.kettle.utils.ResultMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KettleController {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public  KettleService kettleService;
    @Value("${kettle.trans}")
    private  String transFileName;
    @Value("${kettle.job}")
    private  String jobFileName;

    /**
     * @author:  wangyanlei
     * @methodsName: runJob
     * @description: 作业类型，定时任务为凌晨两点执行
     * 因RequestMapping未指定类型  故 post 和get 请求都可以接收
     * @param:
     * @return:  Map<String,Object>
     * @date 2019年12月3日 14:06:03
     */
    @RequestMapping("/runJob")
    @Scheduled(cron="0 0 2 * * ?")
    public Map<String,Object> runJob (){
        logger.info("++++++++++++++执行作业类型定时任务开始++++++++++++++++++++");
        Boolean flag=kettleService.runJob(jobFileName);
        logger.info("获取文件路径为{}",jobFileName);
        Map<String,Object> mapResult = new HashMap<>();
        String result=null;
        String resultMessage=null;
        if(flag){
            logger.info("本次作业执行成功，单并不意味作业内部每个步骤都执行了TURE路线，以实际数据为准");
            result= ResultMapUtils.SUCCESS;
            resultMessage="本次作业执行成功，单并不意味作业内部每个步骤都执行了TURE路线，以实际数据为准";
        }else{
            logger.error("本次作业执行失败");
            result= ResultMapUtils.FAIL;
            resultMessage="本次作业执行失败";
        }
        ResultMapUtils.makeResultMap(mapResult,result,resultMessage,"data","");
        return mapResult;
    }
    /**
     * @author:  wangyanlei
     * @methodsName: runJob
     * @description: 执行一次转换
     * @param:
     * @return:  Map<String,Object>
     * @date 2019年12月3日 14:06:03
     */
    //@GetMapping("/runTrans")
    @RequestMapping("/runTrans")
    public Map<String,Object> runTrans (){
        logger.info("++++++++++++++执行转换类型定时任务开始++++++++++++++++++++");
        Boolean flag= kettleService.runTrans(transFileName);
        logger.info("获取文件路径为{}",transFileName);
        Map<String,Object> mapResult = new HashMap<>();
        String result=null;
        String resultMessage=null;
        if(flag){
            logger.info("本次调用转换成功");
            result= ResultMapUtils.SUCCESS;
            resultMessage="本次调用转换成功";
        }else{
            logger.error("本次转换执行失败");
            result= ResultMapUtils.FAIL;
            resultMessage="本次转换执行失败";
        }
        ResultMapUtils.makeResultMap(mapResult,result,resultMessage,"data","");
        return mapResult;
    }
}

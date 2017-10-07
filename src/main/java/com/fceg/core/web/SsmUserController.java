package com.fceg.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fceg.core.domain.SsmUser;
import com.fceg.core.service.ISsmUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class SsmUserController {

    @Resource
    private ISsmUserService ssmUserService;

    @RequestMapping("/ui/list")
    private String user(){
        return "user/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String listPage(SsmUser ssmUser, int page, int rows){
        //PageHelper.startPage(page,rows);
        //System.out.println( JSON.toJSONStringWithDateFormat( ssmUserService.listPage(ssmUser,page,rows),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat) );
        return JSON.toJSONStringWithDateFormat( ssmUserService.listPage(ssmUser,page,rows),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat) ;
    }

    public void save(){

    }

    public void delete(){

    }
}

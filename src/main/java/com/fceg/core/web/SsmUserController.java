package com.fceg.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fceg.core.domain.SsmUser;
import com.fceg.core.service.ISsmUserService;
import com.fceg.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class SsmUserController {

    @Resource
    private ISsmUserService ssmUserService;

    @RequestMapping("/ui/{ui}")
    private String user(@PathVariable("ui") String ui ){
        return "user/"+ui;
    }

    @RequestMapping("/list")
    @ResponseBody
    public String listPage(SsmUser ssmUser, int page, int rows){
        //PageHelper.startPage(page,rows);
        //System.out.println( JSON.toJSONStringWithDateFormat( ssmUserService.listPage(ssmUser,page,rows),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat) );
        return JSON.toJSONStringWithDateFormat( ssmUserService.listPage(ssmUser,page,rows),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat) ;
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult save(SsmUser entity){

        return ssmUserService.saveOrUpdate(entity);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public BaseResult get(@PathVariable("id") Long id){
        return ssmUserService.get(id);
    }
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids){
        return ssmUserService.deleteByIds(ids);
    }
}

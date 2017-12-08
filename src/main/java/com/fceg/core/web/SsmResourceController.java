package com.fceg.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmResource;
import com.fceg.core.service.ISsmOrgService;
import com.fceg.core.service.ISsmResourceService;
import com.fceg.core.service.ISsmRoleResourceService;
import com.fceg.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class SsmResourceController {

    @Resource
    private ISsmResourceService ssmResourceService;



    @RequestMapping("/ui/{ui}")
    private String ui(@PathVariable("ui") String ui){
        return "resource/"+ui;
    }


    @RequestMapping("/list")
    @ResponseBody
    public String listPage(SsmResource ssmResource){
        //System.out.println(JSON.toJSONStringWithDateFormat(ssmRoleService.listPage(ssmRole,page,rows),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        return JSON.toJSONStringWithDateFormat(ssmResourceService.listPage(ssmResource),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult save(SsmResource ssmResource){
        if (ssmResource.getId()!=null){
            ssmResource.setUpdateTime(new Date());
        }else{
            ssmResource.setCreateTime(new Date());
        }
        ssmResourceService.saveOrUpdate(ssmResource);
        return BaseResult.ok("保存成功");
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public BaseResult delete(@PathVariable("ids") String ids){

        ssmResourceService.deleteByIds(ids);
        return BaseResult.ok("删除成功");
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public BaseResult get(@PathVariable("id")Long id){
        SsmResource ssmResource=ssmResourceService.selectByKey(id);
        return BaseResult.ok("查询成功",ssmResource);
    }


    @RequestMapping("/tree")
    @ResponseBody
    public List<SsmResource> tree(){
        return ssmResourceService.tree();
    }



}

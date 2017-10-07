package com.fceg.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmRole;
import com.fceg.core.service.ISsmOrgService;
import com.fceg.core.service.ISsmRoleService;
import com.fceg.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/org")
public class SsmOrgController {

    @Resource
    private ISsmOrgService ssmOrgService;

    @RequestMapping("/ui/{ui}")
    private String ui(@PathVariable("ui") String ui){
        return "org/"+ui;
    }


    @RequestMapping("/list")
    @ResponseBody
    public String listPage(SsmOrg ssmOrg, int page, int rows){
        //System.out.println(JSON.toJSONStringWithDateFormat(ssmRoleService.listPage(ssmRole,page,rows),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        return JSON.toJSONStringWithDateFormat(ssmOrgService.listPage(ssmOrg,page,rows),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult save(SsmOrg ssmOrg){
        ssmOrgService.saveOrUpdate(ssmOrg);
        return BaseResult.ok("保存成功");
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public BaseResult delete(@PathVariable("ids") String ids){

        ssmOrgService.deleteByIds(ids);
        return BaseResult.ok("删除成功");
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public BaseResult get(@PathVariable("id")Long id){
        SsmOrg ssmOrg=ssmOrgService.selectByKey(id);
        return BaseResult.ok("查询成功",ssmOrg);
    }

}

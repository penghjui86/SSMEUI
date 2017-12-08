package com.fceg.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fceg.core.domain.SsmRole;
import com.fceg.core.service.ISsmRoleResourceService;
import com.fceg.core.service.ISsmRoleService;
import com.fceg.result.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/role")
public class SsmRoleController {

    @Resource
    private ISsmRoleService ssmRoleService;

    @Resource
    private ISsmRoleResourceService ssmRoleResourceService;

    @RequestMapping("/ui/{ui}")
    private String ui(@PathVariable("ui") String ui){
        return "role/"+ui;
    }


    @RequestMapping("/list")
    @ResponseBody
    public String listPage(SsmRole ssmRole,int page,int rows){
        System.out.println(JSON.toJSONStringWithDateFormat(ssmRoleService.listPage(ssmRole,page,rows),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        return JSON.toJSONStringWithDateFormat(ssmRoleService.listPage(ssmRole,page,rows),"yyyy-MM-dd hh:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    @RequestMapping("/save")
    @ResponseBody
    public BaseResult save(SsmRole ssmRole){
        ssmRoleService.saveOrUpdate(ssmRole);
        return BaseResult.ok("保存成功");
    }

    @RequestMapping("/delete/{ids}")
    @ResponseBody
    public BaseResult delete(@PathVariable("ids") String ids){
        ssmRoleService.deleteByIds(ids);
        return BaseResult.ok("删除成功");
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public BaseResult get(@PathVariable("id")Long id){
        SsmRole ssmRole=ssmRoleService.selectByKey(id);
        return BaseResult.ok("查询成功",ssmRole);
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<SsmRole> all(){
        Example example=new Example(SsmRole.class);
        return  ssmRoleService.selectByExample(example);
    }

    @RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
    @ResponseBody
    public Object getResources(@PathVariable("id") Long id) {
        return BaseResult.ok("查询成功",ssmRoleResourceService.getResourcesIdsByRoleId(id));
    }

    @RequestMapping(value = "/{id}/resources", method = RequestMethod.POST)
    @ResponseBody
    public Object saveResources(@PathVariable("id") Long id, String ids) {
        return BaseResult.ok("保存成功",ssmRoleResourceService.saveRoleResources(id, ids));
    }

}

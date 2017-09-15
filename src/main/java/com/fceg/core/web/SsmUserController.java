package com.fceg.core.web;

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

    @RequestMapping("/list.htm")
    @ResponseBody
    public Object listPage(SsmUser ssmUser, int page, int rows){
        return ssmUserService.listPage(ssmUser,page,rows);
    }

    public void save(){

    }

    public void delete(){

    }
}

package com.fceg.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IconController {

    @RequestMapping(value = "/icons",method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request){
        String path="/resources/images/32x32/";
        String imgPath=request.getSession().getServletContext().getRealPath(path);
        File dirs=new File(imgPath);
        List<String> imageList=new ArrayList<>();
        for (String name:dirs.list()){
            imageList.add(path+name);
        }
        return imageList;
    }
}

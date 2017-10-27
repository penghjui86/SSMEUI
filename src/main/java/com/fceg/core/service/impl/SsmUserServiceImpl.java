package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserMapper;
import com.fceg.core.dao.SsmUserOrgMapper;
import com.fceg.core.domain.SsmUser;
import com.fceg.core.domain.SsmUserOrg;
import com.fceg.core.domain.SsmUserOrgExample;
import com.fceg.core.service.ISsmUserService;
import com.fceg.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import sun.swing.BakedArrayList;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SsmUserServiceImpl extends BaseServiceImpl<SsmUser> implements ISsmUserService{

    @Resource(name="ssmUserMapper")
    private SsmUserMapper mapper;

    @Resource(name="ssmUserOrgMapper")
    private SsmUserOrgMapper ssmUserOrgMapper;

    @Override
    public SsmUserMapper getMapper() {
        return mapper;
    }

    @Override
    public Object listPage(SsmUser ssmUser, int page, int rows) {
        Example example=new Example(SsmUser.class);
        Example.Criteria criteria = example.createCriteria();
        BaseResult baseResult=new BaseResult();
        if(ssmUser.getOrgId()!=null){
            SsmUserOrgExample exampleUserOrg = new SsmUserOrgExample();

            PageHelper.startPage(page, rows);
            SsmUserOrgExample.Criteria userOrgCriteria=exampleUserOrg.createCriteria();
            userOrgCriteria.andOrgIdEqualTo(ssmUser.getOrgId());

            List<SsmUserOrg> userOrgs=ssmUserOrgMapper.selectByExample(exampleUserOrg);
            if(userOrgs!=null&&userOrgs.size()>0){
                criteria.andIn("id",getUserIdList(userOrgs));
            }else{
                return baseResult;
            }

        }
        //分页查询
        PageHelper.startPage(page, rows);
        List<SsmUser> list=selectByExample(example);


        PageInfo<SsmUser> pageInfo=new PageInfo<>(list);
        baseResult.setRows(list);
        baseResult.setTotal((int)pageInfo.getTotal());
        return baseResult;
    }

    private List<Long> getUserIdList(List<SsmUserOrg> userOrgs) {
        List<Long> idList=new ArrayList<>();
        for (SsmUserOrg ssmUserOrg: userOrgs) {
            idList.add(ssmUserOrg.getUserId());
        }
        return idList;
    }


}

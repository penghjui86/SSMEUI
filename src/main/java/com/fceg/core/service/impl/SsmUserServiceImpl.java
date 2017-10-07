package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserMapper;
import com.fceg.core.domain.SsmUser;
import com.fceg.core.service.ISsmUserService;
import com.fceg.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SsmUserServiceImpl extends BaseServiceImpl<SsmUser> implements ISsmUserService{

    @Resource(name="ssmUserMapper")
    private SsmUserMapper mapper;

    @Override
    public SsmUserMapper getMapper() {
        return mapper;
    }

    @Override
    public Object listPage(SsmUser ssmUser, int page, int rows) {
        Example example=new Example(SsmUser.class);
        Example.Criteria criteria = example.createCriteria();

        //分页查询
        PageHelper.startPage(page, rows);
        List<SsmUser> list=selectByExample(example);
        BaseResult baseResult=new BaseResult();
        PageInfo<SsmUser> pageInfo=new PageInfo<>(list);
        baseResult.setRows(list);
        baseResult.setTotal((int)pageInfo.getTotal());
        return baseResult;
    }


}

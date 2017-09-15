package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserMapper;
import com.fceg.core.domain.SsmUser;
import com.fceg.core.service.ISsmUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class SsmUserServiceImpl extends BaseServiceImpl<SsmUser> implements ISsmUserService {

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
        return selectByExample(example);
    }


}

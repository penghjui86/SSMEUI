package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmOrgMapper;
import com.fceg.core.dao.SsmRoleMapper;
import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmRole;
import com.fceg.core.service.ISsmOrgService;
import com.fceg.core.service.ISsmRoleService;
import com.fceg.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SsmOrgServiceImpl extends BaseServiceImpl<SsmOrg> implements ISsmOrgService {

    @Resource(name="ssmOrgMapper")
    private SsmOrgMapper mapper;

    public SsmOrgServiceImpl() {
        super.entity=new SsmOrg();
    }

    @Override
    public SsmOrgMapper getMapper() {
        return this.mapper;
    }

    @Override
    public BaseResult listPage(SsmOrg ssmOrg, int page, int rows) {
        Example example=new Example(SsmRole.class);
        Example.Criteria criteria=example.createCriteria();

        PageHelper.startPage(page,rows);
        List<SsmOrg> list=selectByExample(example);
        PageInfo<SsmOrg> pageInfo=new PageInfo<>(list);
        BaseResult baseResult=new BaseResult();
        baseResult.setTotal((int)pageInfo.getTotal());
        baseResult.setRows(list);

        return baseResult;
    }
}

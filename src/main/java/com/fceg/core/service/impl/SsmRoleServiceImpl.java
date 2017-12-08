package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmRoleMapper;
import com.fceg.core.domain.SsmRole;
import com.fceg.core.service.ISsmRoleService;
import com.fceg.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SsmRoleServiceImpl extends BaseServiceImpl<SsmRole> implements ISsmRoleService {

    @Resource(name="ssmRoleMapper")
    private SsmRoleMapper mapper;

    public SsmRoleServiceImpl() {
        super.entity=new SsmRole();
    }

    @Override
    public SsmRoleMapper getMapper() {
        return this.mapper;
    }

    @Override
    public BaseResult listPage(SsmRole ssmRole, int page, int rows) {
        Example example=new Example(SsmRole.class);
        Example.Criteria criteria=example.createCriteria();

        PageHelper.startPage(page,rows);
        List<SsmRole> list=selectByExample(example);
        PageInfo<SsmRole> pageInfo=new PageInfo<>(list);
        BaseResult baseResult=new BaseResult();
        baseResult.setTotal((int)pageInfo.getTotal());
        baseResult.setRows(list);

        return baseResult;
    }


}

package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserRoleMapper;
import com.fceg.core.domain.SsmUserRole;
import com.fceg.core.service.ISsmUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SsmUserRoleServiceImpl extends BaseServiceImpl<SsmUserRole> implements ISsmUserRoleService {

    @Resource(name="ssmUserRoleMapper")
    private SsmUserRoleMapper mapper;

    @Override
    public SsmUserRoleMapper getMapper() {
        return mapper;
    }
}

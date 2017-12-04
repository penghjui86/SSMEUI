package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmRoleResourceMapper;
import com.fceg.core.domain.SsmRoleResource;
import com.fceg.core.domain.SsmUserOrg;
import com.fceg.core.service.ISsmRoleResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SsmRoleResourceServiceImpl extends BaseServiceImpl<SsmRoleResource> implements ISsmRoleResourceService {

    @Resource(name="ssmRoleResourceMapper")
    private SsmRoleResourceMapper mapper;

    @Override
    public SsmRoleResourceMapper getMapper() {
        return mapper;
    }
}

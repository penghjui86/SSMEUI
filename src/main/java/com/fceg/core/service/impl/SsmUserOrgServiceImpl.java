package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserOrgMapper;
import com.fceg.core.domain.SsmUserOrg;
import com.fceg.core.service.ISsmUserOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SsmUserOrgServiceImpl extends BaseServiceImpl<SsmUserOrg> implements ISsmUserOrgService {

    @Resource(name="ssmUserOrgMapper")
    private SsmUserOrgMapper mapper;

    @Override
    public SsmUserOrgMapper getMapper() {
        return mapper;
    }



}

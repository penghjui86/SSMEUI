package com.fceg.core.service;

import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmResource;

import java.util.List;

public interface ISsmResourceService extends IBaseService<SsmResource> {
    public Object listPage(SsmResource ssmResource);
    public List<SsmResource> tree();
}

package com.fceg.core.service;

import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmUser;

public interface ISsmOrgService extends IBaseService<SsmOrg> {
    public Object listPage(SsmOrg ssmOrg, int page, int rows);
}

package com.fceg.core.service;

import com.fceg.core.domain.SsmRole;
import com.fceg.result.BaseResult;

public interface ISsmRoleService extends IBaseService<SsmRole> {

    public BaseResult listPage(SsmRole ssmRole,int page,int rows);


}

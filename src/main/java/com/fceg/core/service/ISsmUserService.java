package com.fceg.core.service;

import com.fceg.core.domain.SsmUser;
import com.fceg.result.BaseResult;

public interface ISsmUserService extends IBaseService<SsmUser> {
    public Object listPage(SsmUser ssmUser, int page,int rows);

    public BaseResult get(Long id);
}

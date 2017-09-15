package com.fceg.core.service;

import com.fceg.core.domain.SsmUser;

public interface ISsmUserService extends IBaseService<SsmUser> {


    public Object listPage(SsmUser ssmUser, int page,int rows);
}

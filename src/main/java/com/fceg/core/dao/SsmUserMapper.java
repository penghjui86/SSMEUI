package com.fceg.core.dao;

import com.fceg.core.domain.SsmUser;
import com.fceg.util.IBaseMapper;
import org.springframework.stereotype.Repository;

@Repository("ssmUserMapper")
public interface SsmUserMapper extends IBaseMapper<SsmUser> {

}
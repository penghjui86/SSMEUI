package com.fceg.core.dao;

import com.fceg.core.domain.SsmUser;
import com.fceg.core.domain.SsmUserExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmUserMapper")
public interface SsmUserMapper extends IBaseMapper<SsmUser> {

}
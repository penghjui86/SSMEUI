package com.fceg.core.dao;

import com.fceg.core.domain.SsmRole;
import com.fceg.core.domain.SsmRoleExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmRoleMapper")
public interface SsmRoleMapper extends IBaseMapper<SsmRole> {

}
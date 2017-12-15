package com.fceg.core.dao;

import com.fceg.core.domain.SsmUserRole;
import com.fceg.core.domain.SsmUserRoleExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmUserRoleMapper")
public interface SsmUserRoleMapper extends IBaseMapper<SsmUserRole> {

}
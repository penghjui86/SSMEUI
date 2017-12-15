package com.fceg.core.dao;

import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmOrgExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmOrgMapper")
public interface SsmOrgMapper extends IBaseMapper<SsmOrg> {

    List<SsmOrg> querySsmOrgTreeList();
}
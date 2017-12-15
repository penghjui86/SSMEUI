package com.fceg.core.dao;

import com.fceg.core.domain.SsmUserOrg;
import com.fceg.core.domain.SsmUserOrgExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmUserOrgMapper")
public interface SsmUserOrgMapper extends IBaseMapper<SsmUserOrg>{

}
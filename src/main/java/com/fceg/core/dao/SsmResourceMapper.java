package com.fceg.core.dao;

import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmResource;
import com.fceg.core.domain.SsmResourceExample;
import java.util.List;

import com.fceg.util.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ssmResourceMapper")
public interface SsmResourceMapper extends IBaseMapper<SsmResource> {

    List<SsmResource> querySsmResourceTreeList();

}
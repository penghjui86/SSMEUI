package com.fceg.core.service;

import com.fceg.core.domain.SsmResource;
import com.fceg.core.domain.SsmRoleResource;
import com.fceg.core.domain.SsmRoleResourceExample;
import com.fceg.result.BaseResult;

import java.util.List;

public interface ISsmRoleResourceService extends IBaseService<SsmRoleResource> {


    void deleteByRoleId(Long roleId);

    void deleteByRoleIds(List<Long> ids);

    List<Long> getResourcesIdsByRoleId(Long id);

    BaseResult saveRoleResources(Long roleId, String ids);
}

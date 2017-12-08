package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmRoleResourceMapper;
import com.fceg.core.domain.SsmRoleResource;
import com.fceg.core.domain.SsmRoleResourceExample;
import com.fceg.core.domain.SsmUserOrg;
import com.fceg.core.service.ISsmRoleResourceService;
import com.fceg.result.BaseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SsmRoleResourceServiceImpl extends BaseServiceImpl<SsmRoleResource> implements ISsmRoleResourceService {

    @Resource(name="ssmRoleResourceMapper")
    private SsmRoleResourceMapper mapper;

    @Override
    public SsmRoleResourceMapper getMapper() {
        return mapper;
    }

    @Override
    public void deleteByRoleId(Long roleId){
        SsmRoleResourceExample ssmRoleResourceExample=new SsmRoleResourceExample();
        ssmRoleResourceExample.createCriteria().andRoleIdEqualTo(roleId);
        this.getMapper().deleteByExample(ssmRoleResourceExample);
    }

    @Override
    public void deleteByRoleIds(List<Long> ids){
        SsmRoleResourceExample ssmRoleResourceExample=new SsmRoleResourceExample();
        ssmRoleResourceExample.createCriteria().andRoleIdIn(ids);
        this.getMapper().deleteByExample(ssmRoleResourceExample);
    }

    @Override
    public List<Long> getResourcesIdsByRoleId(Long id){
        SsmRoleResourceExample ssmRoleResourceExample=new SsmRoleResourceExample();
        ssmRoleResourceExample.createCriteria().andRoleIdEqualTo(id);
        List<SsmRoleResource> ssmRoleResources=this.getMapper().selectByExample(ssmRoleResourceExample);
        return resourcesIdList(ssmRoleResources);
    }

    @Override
    public BaseResult saveRoleResources(Long roleId, String ids) {
        //删除以前的数据
        this.deleteByRoleId(roleId);

        if (ids != null && !"".equals(ids)) {
            //保存新数据
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                SsmRoleResource roleResource = new SsmRoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(Long.valueOf(idArr[i]));
                this.saveOrUpdate(roleResource);
            }
        }
        return BaseResult.ok("保存成功");
    }

    private List<Long> resourcesIdList(List<SsmRoleResource> ssmRoleResources) {
        List<Long> idList = new ArrayList<>();
        if (ssmRoleResources != null && ssmRoleResources.size() > 0) {
            for (SsmRoleResource s : ssmRoleResources) {
                idList.add(s.getResourceId());
            }
        }
        return idList;
    }

}

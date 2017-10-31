package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmUserMapper;
import com.fceg.core.dao.SsmUserOrgMapper;
import com.fceg.core.dao.SsmUserRoleMapper;
import com.fceg.core.domain.*;
import com.fceg.core.service.ISsmUserService;
import com.fceg.result.BaseResult;
import com.fceg.util.UtilMd5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SsmUserServiceImpl extends BaseServiceImpl<SsmUser> implements ISsmUserService{

    @Resource(name="ssmUserMapper")
    private SsmUserMapper mapper;

    @Resource(name="ssmUserOrgMapper")
    private SsmUserOrgMapper ssmUserOrgMapper;

    @Resource(name="ssmUserRoleMapper")
    private SsmUserRoleMapper ssmUserRoleMapper;

    @Override
    public SsmUserMapper getMapper() {
        return mapper;
    }

    @Override
    public Object listPage(SsmUser ssmUser, int page, int rows) {
        Example example=new Example(SsmUser.class);
        Example.Criteria criteria = example.createCriteria();
        BaseResult baseResult=new BaseResult();
        if(ssmUser.getOrgId()!=null){
            SsmUserOrgExample exampleUserOrg = new SsmUserOrgExample();

            PageHelper.startPage(page, rows);
            SsmUserOrgExample.Criteria userOrgCriteria=exampleUserOrg.createCriteria();
            userOrgCriteria.andOrgIdEqualTo(ssmUser.getOrgId());

            List<SsmUserOrg> userOrgs=ssmUserOrgMapper.selectByExample(exampleUserOrg);
            if(userOrgs!=null&&userOrgs.size()>0){
                criteria.andIn("id",getUserIdList(userOrgs));
            }else{
                return baseResult;
            }

        }
        //分页查询
        PageHelper.startPage(page, rows);
        List<SsmUser> list=selectByExample(example);


        PageInfo<SsmUser> pageInfo=new PageInfo<>(list);
        baseResult.setRows(list);
        baseResult.setTotal((int)pageInfo.getTotal());
        return baseResult;
    }

    @Override
    public BaseResult get(Long id) {
        SsmUser ssmUser=mapper.selectByPrimaryKey(id);

        //查询User-org中间表
        SsmUserOrgExample ssmUserOrgExample=new SsmUserOrgExample();
        ssmUserOrgExample.createCriteria().andUserIdEqualTo(ssmUser.getId());
        List<SsmUserOrg> ssmUserOrgs=ssmUserOrgMapper.selectByExample(ssmUserOrgExample);
        if (ssmUserOrgs!=null&&ssmUserOrgs.size()>0){
            Long[] orgIds=new Long[ssmUserOrgs.size()];
            for (int i=0;i<ssmUserOrgs.size();i++) {
                orgIds[i]=ssmUserOrgs.get(i).getOrgId();
            }
            ssmUser.setOrgIds(orgIds);
        }

        //查询user-role中间表

        SsmUserRoleExample ssmUserRoleExample=new SsmUserRoleExample();
        ssmUserRoleExample.createCriteria().andUserIdEqualTo(ssmUser.getId());
        List<SsmUserRole> ssmUserRoles=ssmUserRoleMapper.selectByExample(ssmUserRoleExample);

        if (ssmUserRoles!=null&&ssmUserRoles.size()>0){
            Long[] roleIds=new Long[ssmUserRoles.size()];
            for (int i=0;i<ssmUserRoles.size();i++){
                roleIds[i]=ssmUserRoles.get(i).getRoleId();
            }
            ssmUser.setRoleIds(roleIds);
        }

        return BaseResult.ok("查询成功",ssmUser);
    }

    private List<Long> getUserIdList(List<SsmUserOrg> userOrgs) {
        List<Long> idList=new ArrayList<>();
        for (SsmUserOrg ssmUserOrg: userOrgs) {
            idList.add(ssmUserOrg.getUserId());
        }
        return idList;
    }

    @Override
    public BaseResult saveOrUpdate(SsmUser entity){
        BaseResult baseResult=new BaseResult();
        //
        if(entity.getId()!=null){

            SsmUser oldEntity=mapper.selectByPrimaryKey(entity.getId());
            entity.setUpdateTime(new Date());
            entity.setPassword(UtilMd5.md5(entity.getPassword(),String.valueOf(oldEntity.getCreateTime().getTime())));
            mapper.updateByPrimaryKeySelective(entity);
            //删除user-org中间表数据
            SsmUserOrgExample ssmUserOrgExample=new SsmUserOrgExample();
            ssmUserOrgExample.createCriteria().andUserIdEqualTo(entity.getId());
            ssmUserOrgMapper.deleteByExample(ssmUserOrgExample);

            //删除user-role中间表数据
            SsmUserRoleExample ssmUserRoleExample=new SsmUserRoleExample();
            ssmUserOrgExample.createCriteria().andUserIdEqualTo(entity.getId());
            ssmUserRoleMapper.deleteByExample(ssmUserOrgExample);
        }else{
            //判断用户名是否存在
            SsmUser ssmUser=getByLoginName(entity.getLoginName());
            if (ssmUser!=null){
                return BaseResult.fail("用户名已存在！");
            }
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            //用创建时间作为盐给密码加密
            entity.setPassword(UtilMd5.md5(entity.getPassword(),String.valueOf(entity.getCreateTime().getTime())));
            mapper.insert(entity);
        }
        //保存user-org中间表
        if(entity.getOrgIds()!=null&&entity.getOrgIds().length>0){
            for (Long orgId:entity.getOrgIds()){
                SsmUserOrg ssmUserOrg=new SsmUserOrg();
                ssmUserOrg.setOrgId(orgId);
                ssmUserOrg.setUserId(entity.getId());
                ssmUserOrgMapper.insert(ssmUserOrg);
            }
        }

        //保存user-role中间表
        if (entity.getRoleIds()!=null&&entity.getRoleIds().length>0){
            for (Long roleId: entity.getRoleIds()) {
                SsmUserRole ssmUserRole=new SsmUserRole();
                ssmUserRole.setRoleId(roleId);
                ssmUserRole.setUserId(entity.getId());
                ssmUserRoleMapper.insert(ssmUserRole);
            }
        }
        return BaseResult.ok("保存成功");
    }

    @Override
    public  BaseResult deleteByIds(String ids){
        //删除用户
        List<Long> idList=idsToList(ids);
        SsmUserExample ssmUserExample=new SsmUserExample();
        ssmUserExample.createCriteria().andIdIn(idList);
        mapper.deleteByExample(ssmUserExample);
        //删除用户组织机构中间表
        SsmUserOrgExample ssmUserOrgExample=new SsmUserOrgExample();
        ssmUserOrgExample.createCriteria().andUserIdIn(idList);
        ssmUserOrgMapper.deleteByExample(ssmUserOrgExample);
        //删除用户角色中间表

        SsmUserRoleExample ssmUserRoleExample=new SsmUserRoleExample();
        ssmUserRoleExample.createCriteria().andUserIdIn(idList);
        ssmUserRoleMapper.deleteByExample(ssmUserRoleExample);

        return BaseResult.ok("删除成功");
    }

    private SsmUser getByLoginName(String loginName) {
        SsmUserExample ssmUserExample=new SsmUserExample();
        ssmUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SsmUser> ssmUsers=mapper.selectByExample(ssmUserExample);
        if (ssmUsers!=null&&ssmUsers.size()==1){
            return ssmUsers.get(0);
        }
        return null;
    }


}

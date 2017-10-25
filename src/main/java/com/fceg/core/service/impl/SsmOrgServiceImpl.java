package com.fceg.core.service.impl;

import com.fceg.core.dao.SsmOrgMapper;
import com.fceg.core.dao.SsmRoleMapper;
import com.fceg.core.domain.SsmOrg;
import com.fceg.core.domain.SsmRole;
import com.fceg.core.service.ISsmOrgService;
import com.fceg.core.service.ISsmRoleService;
import com.fceg.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SsmOrgServiceImpl extends BaseServiceImpl<SsmOrg> implements ISsmOrgService {

    @Resource(name="ssmOrgMapper")
    private SsmOrgMapper mapper;

    public SsmOrgServiceImpl() {
        super.entity=new SsmOrg();
    }

    @Override
    public SsmOrgMapper getMapper() {
        return this.mapper;
    }

    @Override
    public BaseResult listPage(SsmOrg ssmOrg, int page, int rows) {
        Example example=new Example(SsmRole.class);
        Example.Criteria criteria=example.createCriteria();

        PageHelper.startPage(page,rows);
        List<SsmOrg> list=selectByExample(example);
        PageInfo<SsmOrg> pageInfo=new PageInfo<>(list);
        BaseResult baseResult=new BaseResult();
        baseResult.setTotal((int)pageInfo.getTotal());
        baseResult.setRows(list);

        return baseResult;
    }

    public List<SsmOrg> tree(){
//        Example example=new Example(SsmRole.class);
//        Example.Criteria criteria=example.createCriteria();
//        example.setOrderByClause("seq ASC");
//        List<SsmOrg> ssmOrgList=selectByExample(example);
        List<SsmOrg> ssmOrgList=mapper.querySsmOrgTreeList();
        return ssmOrgList;
        //return prepareTree(ssmOrgList);
    }

    /**
     * 获取父菜单
     * @param ssmOrgList
     * @return
     */
    private List<SsmOrg> prepareTree(List<SsmOrg> ssmOrgList) {
        List<SsmOrg> topList=new ArrayList<>();
        for (SsmOrg ssmOrg:ssmOrgList) {
            //遍历所有父节点，将父节点与传过来的子节点id比较
            if(ssmOrg.getPid()==null){
                ssmOrg.setChildren(prepareTreeChild(ssmOrg.getId(),ssmOrgList));
            }
        }
        return topList;
    }

    /**
     * 获取子菜单
     * @param id
     * @param ssmOrgList
     * @return
     */
    private List<SsmOrg> prepareTreeChild(Long id, List<SsmOrg> ssmOrgList) {
        List<SsmOrg> childList=new ArrayList<>();

        return childList;
    }
}

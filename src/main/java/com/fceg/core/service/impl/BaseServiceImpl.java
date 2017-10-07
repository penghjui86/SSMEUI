
package com.fceg.core.service.impl;
import com.fceg.core.domain.Entity;
import com.fceg.core.service.IBaseService;
import com.fceg.result.BaseResult;
import com.fceg.util.IBaseMapper;
import com.sun.xml.internal.rngom.parse.host.Base;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	

    public IBaseMapper<T> mapper;

    public T entity;

    public IBaseMapper<T> getMapper() {
		return mapper;
	}

    public T selectByKey(Object key) {
        return this.getMapper().selectByPrimaryKey(Long.parseLong(key.toString()));
    }

    public int save(T entity) {
        return this.getMapper().insert(entity);
    }

    @Override
    public BaseResult saveOrUpdate(T entity){
        Entity obj=(Entity)entity;
        if (obj.getId()!=null){
            this.getMapper().updateByPrimaryKey(entity);
        }else{
            this.getMapper().insert(entity);
        }

        return BaseResult.ok("保存成功");
    }

    public int delete(Object key) {
        return this.getMapper().deleteByPrimaryKey(key);
    }

    @Override
    public BaseResult deleteByIds(String ids){
	    List<Long> idList=idsToList(ids);
        Example example=new Example(entity.getClass());
	    example.createCriteria().andIn("id",idList);
        this.getMapper().deleteByExample(example);
        return BaseResult.ok("删除成功");
    }

    public int updateAll(T entity) {
        return this.getMapper().updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity) {
        return this.getMapper().updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return this.getMapper().selectByExample(example);
    }

    public List<Long> idsToList(String ids){
        String[] idsArr=ids.split(",");
        List<Long> idList=new ArrayList<>();
        for(int i=0;i<idsArr.length;i++){
            idList.add(Long.parseLong(idsArr[i]));
        }
        return idList;
    }
}

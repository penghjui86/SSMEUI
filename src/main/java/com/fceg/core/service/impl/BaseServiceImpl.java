
package com.fceg.core.service.impl;
import com.fceg.core.service.IBaseService;
import com.fceg.util.IBaseMapper;

import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	

    public IBaseMapper<T> mapper;

	public IBaseMapper<T> getMapper() {
		return mapper;
	}

    public T selectByKey(Object key) {
        return this.getMapper().selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return this.getMapper().insert(entity);
    }

    public int delete(Object key) {
        return this.getMapper().deleteByPrimaryKey(key);
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
}

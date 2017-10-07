package com.fceg.core.service;

import com.fceg.result.BaseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBaseService<T> {

    public T selectByKey(Object key);

    public int save(T entity);

    BaseResult saveOrUpdate(T entity);

    public int delete(Object key);

    public BaseResult deleteByIds(String ids);

    public int updateAll(T entity);

    public int updateNotNull(T entity);

    public List<T> selectByExample(Object example);
}

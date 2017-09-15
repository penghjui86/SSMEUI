package com.fceg.core.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBaseService<T> {

    public T selectByKey(Object key);

    public int save(T entity);

    public int delete(Object key);

    public int updateAll(T entity);

    public int updateNotNull(T entity);

    public List<T> selectByExample(Object example);
}

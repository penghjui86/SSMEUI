package com.fceg.util;

import com.fceg.core.domain.Entity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

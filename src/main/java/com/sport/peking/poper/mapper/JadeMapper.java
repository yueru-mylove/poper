package com.sport.peking.poper.mapper;

import com.sport.peking.poper.bean.Jade;
import com.sport.peking.poper.bean.JadeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface JadeMapper {
    long countByExample(JadeExample example);

    int deleteByExample(JadeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jade record);

    int insertSelective(Jade record);

    List<Jade> selectByExample(JadeExample example);

    Jade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jade record, @Param("example") JadeExample example);

    int updateByExample(@Param("record") Jade record, @Param("example") JadeExample example);

    int updateByPrimaryKeySelective(Jade record);

    int updateByPrimaryKey(Jade record);
}
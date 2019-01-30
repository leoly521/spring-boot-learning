package com.example.springbootlearning.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.springbootlearning.entity.Account;

@Mapper
public interface AccountMapper {

    @Insert("insert into TB_ACC(ACC_NAME, ACC_MONEY) values(#{name}, #{money})")
    int add(@Param("name") String name, @Param("money") double money);

    @Update("update TB_ACC set ACC_NAME = #{name}, ACC_MONEY = #{money} where ACC_ID = #{id}")
    int update(@Param("id") int id, @Param("name") String name, @Param("money") double money);

    @Delete("delete from TB_ACC where ACC_ID = #{id}")
    int delete(int id);

    @Select("select ACC_ID as id, ACC_NAME as name, ACC_MONEY as money from TB_ACC where ACC_ID = #{id}")
    Account getById(@Param("id") int id);

    @Select("select ACC_ID as id, ACC_NAME as name, ACC_MONEY as money from TB_ACC")
    List<Account> findAll();
}

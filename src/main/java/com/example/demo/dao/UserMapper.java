package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Eden
 * @Date: 2019/4/17 16:49
 * @Version 1.0
 */
@Repository
public interface UserMapper {
    //查询所有 分页
    @Select("select * from `user`")
    /*
    * @Results({
    @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
    @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
    @Result(column="class_id", property="classId", jdbcType=JdbcType.INTEGER)
})
    * */
    List<User> findAll();

    //查询个人 登录
    @Select("select * from `user` where logname = #{logname} and password = #{pwd}")
    User findOne(@Param("logname") String logname, @Param("pwd") String pwd);

    //添加 注册
    int addOne(User user);

    //根据ID删除
    @Delete("delete from `user` where uid = #{uid}")
    int deleteOne(int uid);

    //模糊查询
    List<User> findLike(String username);

    //修改
    int modifyOne(User user);
}

package com.key.api.mapper.login;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.key.api.model.login.LoginUser;

@Mapper
public interface LoginUserMapper {
	
	@Select("select * from t_userinfo")
	List<LoginUser> queryUserInfoAll();

	@Select("select * from t_userinfo where id = #{id}")
	LoginUser findUserInfoById(@Param("id") int id);
	
	@Insert("insert t_userinfo(id,user_name,user_password) values(#{id},#{userName},#{userPassword})")
	int insert(LoginUser loginUser);
	
	@Delete("delete from t_userinfo")
	int deleteAll();
	
//	//用xml文件执行查询方式。注：方法名和要UserMapper.xml中的id一致
//	List<User> query(@Param("userName")String userName);
}
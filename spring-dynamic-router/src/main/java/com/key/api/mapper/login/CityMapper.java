package com.key.api.mapper.login;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.key.api.model.login.City;

@Mapper
public interface CityMapper {
	
	@Select("select * from t_a")
	List<City> queryCityAll();

	@Select("select * from t_a where id = #{id}")
	City findCityById(@Param("id") int id);
	
	@Insert("insert t_a(id,name) values(#{id},#{name})")
	int insert(City city);
	
	@Delete("delete from t_a")
	int deleteAll();
	
	@Update("update t_a set name = #{name} " +
			"where id = #{id}")
	int update(City city);
//	//用xml文件执行查询方式。注：方法名和要UserMapper.xml中的id一致
//	List<User> query(@Param("userName")String userName);
}
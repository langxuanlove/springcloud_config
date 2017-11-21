package com.key.api.service.login;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.key.api.mapper.login.CityMapper;
import com.key.api.model.login.City;


@Service
public class CityService {
	
	public Logger logger = LoggerFactory.getLogger(CityService.class);
	
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询city
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<City> queryPage(int pageNum,int pageSize){
    	PageHelper.startPage(pageNum, pageSize);
		//PageHelper会自动拦截到下面这查询sql,只执行紧接着的sql语句。
    	List<City> cityList = cityMapper.queryCityAll();
    	return new PageInfo<City>(cityList);
	}

    /**
     * 方法说明
     * @param id
     * @return
     */
    public City getById(int id) {
        return cityMapper.findCityById(id);
    }
    
    /**
     * city保存
     * @param city
     * @return
     */
    @Transactional
    public int save(City city) {
    	return cityMapper.insert(city);
    }
    
    /**
     * city删除
     * @return
     */
    @Transactional
    public int deleteAll() {
    	return cityMapper.deleteAll();
    }

    /**
     * city更新
     * @return
     */
    @Transactional
    public int update(City city) {
    	return cityMapper.update(city);
    }
    
}

package com.key.api.service.login;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.key.api.mapper.login.LoginUserMapper;
import com.key.api.model.login.LoginUser;
@Service
public class LoginUserService {
	
	public Logger logger = LoggerFactory.getLogger(LoginUserService.class);
	
    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 查询city
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<LoginUser> queryPage(int pageNum, int pageSize, String orderBy){
    	PageHelper.startPage(pageNum, pageSize, orderBy);
		//PageHelper会自动拦截到下面这查询sql,只执行紧接着的sql语句。
    	List<LoginUser> LoginUserList = loginUserMapper.queryUserInfoAll();
    	return new PageInfo<LoginUser>(LoginUserList);
	}

    /**
     * 方法说明
     * @param id
     * @return
     */
    public LoginUser getById(int id) {
        return loginUserMapper.findUserInfoById(id);
    }
    
    /**
     * 保存
     * @param loginUser
     * @return
     */
    @Transactional
    public int save(LoginUser loginUser) {
    	return loginUserMapper.insert(loginUser);
    }
    
    /**
     * city删除
     * @return
     */
    @Transactional
    public int deleteAll() {
    	return loginUserMapper.deleteAll();
    }

}

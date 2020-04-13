package com.book.dao.user;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.User;


public interface BookUserMapper {
	//登录
	User loginValidate(@Param("userId") String userId,@Param("userPsw") String userPsw);
	//注册
	int saveUser(User user);
}

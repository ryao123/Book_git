package com.book.service;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.User;

public interface BookUserService {
	
	User loginValidate(String userId,String userPsw);
	boolean saveUser(User user);
}

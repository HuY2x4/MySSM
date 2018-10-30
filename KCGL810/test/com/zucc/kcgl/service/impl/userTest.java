package com.zucc.kcgl.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zucc.kcgl.model.MdUser;

public class userTest {

	@Test
	public void test() {
		UserServiceImpl userser=new UserServiceImpl();
		MdUser user=new MdUser();
		user.setUserName("56");
		user.setLoginName("hyxzucc");
		user.setPassword("123456");
		user.setPhone("135");
		userser.saveUser(user);
	}

}

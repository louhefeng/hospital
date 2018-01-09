package com.hospi.server;

import java.util.List;

import com.hospi.model.User;

public interface UserService {

	long saveUser( User user);
	
	List<User> search (String name,String phone);
}

package com.hospi.server.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospi.dao.BaseDaoI;
import com.hospi.model.User;
import com.hospi.server.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BaseDaoI<User> dao;

	@Override
	public long saveUser(User user) {
		user.setCreatetime(new Date());
		dao.save(user);
		return 1;
	}

	@Override
	public List<User> search(String name, String phone) {
		
		String sql="select *  from User  where 1=1 ";
		List param=new ArrayList<>();
		
		if(name!=null&&!name.equals("")) {
			sql+=" and name like ?  ";
			param.add("%"+name+"%");
		}
		
		if(phone!=null&&!phone.equals("")) {
			sql+=" and phone = ?  ";
			param.add(phone);
			
		}
		
		List<User> users = dao.findList(sql, param, new User());
		return users;
	}

}

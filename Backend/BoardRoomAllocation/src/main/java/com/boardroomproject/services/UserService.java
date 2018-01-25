package com.boardroomproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.UserDao;
import com.boardroomproject.model.User;

@Service 
public class UserService implements IUserService {
	@Autowired
	UserDao userDao;
	
	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}

	public void saveUser(User user) {
		userDao.saveUser(user);

	}

	public void deleteUserById(int userId) {
		userDao.deleteUserById(userId);
	}

	public List<User> getUserByLocation(int lId) {
		return userDao.getUserByLocation(lId);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	public void changeUserType(int userId) {
		userDao.changeUserType(userId);
	}

}
package com.boardroomproject.services;

import java.util.List;

import com.boardroomproject.model.User;

public interface IUserService {
	
	public boolean isUserExist(User user); 
	public void saveUser(User user);
	public void deleteUserById(int userId);
	public List<User> getUserByLocation(int lId);
	public void updateUser(User user);
	public User getUserById(int userId);
	public void changeUserType(int userId);
}

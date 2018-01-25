package com.boardroomproject.dao;

import java.util.List;
import com.boardroomproject.model.User;

public interface UserDao {
	/** 
     * This is the method to be used to create
     * a record in the user table.
    */
	public void saveUser(User user);
	/** 
     * This is the method to be used to delete
     * a record from the user table.
    */
	public void deleteUserById(int id);
	 /** 
     * This is the method to be used to update
     * a record into the user table.
     */
	public void updateUser(User user);
	 /** 
     * This is the method to be used to check if
     * a user entry exists in user table.
     */
	public boolean isUserExist(User user);
	/** 
     * This is the method to be used to return list
     * of users based on their location.
     */
	public List<User> getUserByLocation(int lid);
	/** 
     * This is the method to be used to get
     * a user object based on the user Id.
     */
	public User getUserById(int userId);
	public void changeUserType(int userId);
}

package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.boardroomproject.model.User;


public class UserDaoImpl implements UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	}  
	public void saveUser(User user){
		String saveUser = "insert into User (fName,lName,userName,dob,password,contact,address,gender,location,type,isArchived) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		user.setType("USER");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(user.getPassword());
	    jdbcTemplateObject.update( saveUser, user.getfName(), user.getlName(), user.getUserName(),user.getDob(), user.getPassword(), user.getContact(), user.getAddress(), Character.toString(user.getGender()), user.getLocation(), user.getType(),"N");
	}
	public void deleteUserById(int id){
		String deleteUser = "update user set isArchived = ? where userId  = ?";
	    jdbcTemplateObject.update(deleteUser, "Y", id);
	}
	public void updateUser(User user) {
		 String updateUser = "update user set age = ? where userId  = ?";
	     jdbcTemplateObject.update(updateUser, user.getUserId());
	}
	public boolean isUserExist(User user) {
		String checkExist = "select * from user where userName = ? and isArchived = ?";
		
		User u;
		try {
			u = jdbcTemplateObject.queryForObject(checkExist, new Object[] { user.getUserName(), "N" },
					new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}
	public List<User> getUserByLocation(int lid) {
		 return jdbcTemplateObject.query("select * from user where lid = ? and isArchived = ?",new RowMapper<User>(){  
		        public User mapRow(ResultSet rs, int row) throws SQLException {  
		            User u=new User();    
		            u.setfName(rs.getString(2));  
		            u.setlName(rs.getString(3));  
		            u.setUserName(rs.getString(4));
		            return u;  
		        }  
		    },lid,"N");  
	}
	public User getUserById(int userId) {
		String checkExist = "select 1 from user where userId  = ? and isArchived = ?";
		return jdbcTemplateObject.queryForObject(checkExist, new Object[]{userId,"N"},new BeanPropertyRowMapper<User>(User.class));
	}
	public void changeUserType(int userId) {
		String updateUser = "update user set type = ? where userId  = ?";
	    jdbcTemplateObject.update(updateUser, "ADMIN", userId);
	}
	@Override
	public User validateUser(User user) {
		String validateUser = "select * from user where userName=? and password= ? and isArchived = ?";
		User ValidUser;
		try {
			ValidUser =jdbcTemplateObject.queryForObject(validateUser, new Object[]{user.getUserName(),user.getPassword(),"N"},new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return ValidUser;
	}
	
}

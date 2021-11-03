package com.icin.bankapplication.service;

import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.entity.UserSearchCriteria;

public interface UserService {
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Response registerUser(User user);
	/**
	 * 
	 * @param emailId
	 * @param password
	 * @param mobileNum
	 * @return
	 */
	public Response userLogin(String emailId, String password, String mobileNum);
	/**
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public Response changePassword(Long userId, String oldPassword, String newPassword);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Response updateContactDetails(User user);
	/**
	 * 
	 * @param userId
	 * @param userSearchCriteria
	 * @return
	 */
	public Response getUserDetails(User user);
}

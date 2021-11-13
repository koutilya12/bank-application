package com.icin.bankapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icin.bankapplication.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE (u.emailId = :emailId OR u.mobileNum = :mobileNum) AND u.password =  :password")
	User validateLogin(String emailId, String password, String mobileNum);
	
	@Modifying
	@Query("UPDATE User u set u.password = :newPassword WHERE u.userId = :userId")
	int changePassword(Long userId, String newPassword);

	@Modifying
	@Query("UPDATE User u set u.address = if(:addressFlag, :address, u.address), u.emailId = if(:emailIdFlag, :emailId, u.emailId) WHERE u.userId = :userId ") 
	int updateContactDetails(Long userId, String address, String emailId, int addressFlag, int emailIdFlag);

	User findByName(String username);
	
}

package com.icin.bankapplication.constants.helpers;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.constants.AccountType;
import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.constants.TransactionStatus;
import com.icin.bankapplication.constants.TransactionType;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.ChequeBookSearchCriteria;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.entity.UserSearchCriteria;

public class Validator {
	public static String validateUser(User user) {
		if(user == null) {
			return "Invalid User"; 
		}
		if(user.getName() == null) {
			return "Invalid User Name";
		}
		if(user.getEmailId() == null) {
			return "empty email Id";
		}
		if(user.getPassword() == null) {
			return "Password is empty";
		}
		if(user.getMobileNum() == null) {
			return "Ivalid Mobile num";
		}
		if(user.getAddress() == null) {
			return "Invalid Address";
		}
		if(user.getDateOfBirth() == null) {
			return "Invalid Date of birth";
		}
		return null;
	}

	public static String validateUserLogin(String emailId, String password, String mobileNum) {
		if(emailId == null && mobileNum == null) {
			return "Invalid email Id";
		}
		if(password == null) {
			return "Password is empty";
		}
		return null;
	}

	public static String validateChangePassword(Long userId, String oldPassword, String newPassword) {
		if(userId == null) {
			return "empty user Id"; 
		}
		if(oldPassword == null) {
			return "empty old password";
		}
		if(newPassword == null) {
			return "empty new password";
		}
		return null;
	}

	public static String validateContactDetails(User user) {
		if(user== null) {
			return "Invalid user";
		}
		return null;
	}

	public static String validateUserSearchCriteria(User user) {
		if(user == null) {
			return "Invalid user search";
		}
		return null;
	}

	public static String validateTransaction(Transaction transaction) {
		if(transaction == null) {
			return "transaction empty";
		}
		if(transaction.getTransactionAmount() == null) {
			return "transaction amount empty";
		}
		if(transaction.getTransactionType() == null) {
			return "transaction type empty";
		}
		if(transaction.getStatus() == null) {
			return "transaction status empty";
		}
		if(transaction.getCreatedBy() == null) {
			return "empty created by";
		}
		return null;
	}

	public static String validateAccount(Account account) {	
		if(account == null) {
			return "account is empty";
		}
		if(account.getUser() == null) {
			return "user is empty";
		}
		if(account.getUser().getUserId() == null) {
			return "user Id empty";
		}
		if(account.getAccountType() == null) {
			return "account type empty";
		}
		return null;
	}

	public static String validateAccountStatus(Long accountId, AccountStatus status) {
		if(accountId == null) {
			return "account Id empty";
		}
		if(status == null) {
			return "status is empty";
		}
		return null;
	}

	public static String validateUpdateAccountBalance(Long id, Double amount) {
		if(id == null) {
			return "id empty";
		}
		if(amount == null) {
			return "balance empty";
		}
		return null;
	}

	public static String validateTransactionDetails(TransactionSearchCriteria transactionSearchCriteria) {
		if(transactionSearchCriteria == null) {
			return "transaction search details empty";
		}
		if(transactionSearchCriteria.getFromAccount() == null && transactionSearchCriteria.getToAccount() == null) {
			return "Invalid account details";
		}
		if(transactionSearchCriteria.getFromAccount().getAccountId() == null && transactionSearchCriteria.getToAccount().getAccountId() == null) {
			return "empty account details";
		}
		return null;
	}

	public static String validateAccountSearchCriteria(Account account) {
		if(account == null) {
			return "Invalid account search";
		}
		return null;
	}

	public static String validateChequeBookRequest(ChequeBookRequest chequeBookRequest) {
		if(chequeBookRequest == null) {
			return "empty cheque book request";
		}
		if(chequeBookRequest.getAccount() == null) {
			return "invalid account";
		}
		if(chequeBookRequest.getAccount().getAccountId() == null) {
			return "empty account id";
		}
		return null;
	}

	public static String validateChequeBookStatus(Long chequeBookId, ChequeBookStatus status) {
		if(chequeBookId == null) {
			return "cheque book Id empty";
		}
		if(status == null) {
			return "cheque book status empty";
		}
		return null;
	}

	public static String validateChequeBookSearch(ChequeBookRequest chequeBookRequest) {
		if(chequeBookRequest == null) {
			return "cheque book search request empty";
		}
		return null;
	}
}

package utils;

import java.util.ArrayList;

import model.BankAccount;
import model.User;

public class UsersList {
	private ArrayList<User> usersArrayList = new ArrayList<User>();
	public static User loggedInUser = null;

	public void initUsers() {
		User demoUser = new User();
		demoUser.setUserName("demoUser");
		demoUser.setPassword("demoUser");
		BankAccount demoUserBankAccount = new BankAccount();
		demoUserBankAccount.setBalance(10000);
		demoUser.setBankAccount(demoUserBankAccount);
		usersArrayList.add(demoUser);

		User freeUser = new User();
		freeUser.setUserName("freeUser");
		freeUser.setPassword("freeUser");
		BankAccount freeUserBankAccount = new BankAccount();
		freeUserBankAccount.setBalance(10000);
		usersArrayList.add(freeUser);
	}

	public ArrayList<User> getUsers() {
		return usersArrayList;
	}

	public void addUser(User newUser) {
		usersArrayList.add(newUser);
	}

	public static void debitBalance(int amount) {
		int currentBalance = UsersList.loggedInUser.getBankAccount().getBalance();
		UsersList.loggedInUser.getBankAccount().setBalance(currentBalance - amount);
	}
}

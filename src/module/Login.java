package module;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.BankAccount;
import model.User;
import utils.UsersList;
import utils.Utils;

public class Login {
	UsersList usersList;
	Scanner scanner;

	public Login(Scanner scanner) {
		this.scanner = scanner;
		usersList = new UsersList();
		usersList.initUsers();
	}

	public void init() {
		Utils.printMessage("...Welcom to PayTM...");
		boolean isLoggedIn = false;
		do {
			Utils.printStars();
			Utils.printMessage("1. Login");
			Utils.printMessage("2. Reset Password");
			Utils.printMessage("3. Reset Username");
			Utils.printMessage("4. Register User");
			int choice = scanner.nextInt();
			try {
				if (choice == 1) {
					isLoggedIn = login();
				} else if (choice == 2) {
					resetPassword();
				} else if (choice == 3) {
					resetUserName();
				} else if (choice == 4) {
					registerUser();
				} else {
					Utils.printMessage("Invalid option!!! Please enter a valid option");
				}
			} catch (InputMismatchException e) {
				Utils.printMessage("Invalid option!!! Please enter a valid option");
			}
		} while (!isLoggedIn);
	}

	private void resetUserName() {
		String userName;
		Utils.printMessage("Enter your username");
		userName = scanner.next();
		if (Utils.isAlphabets(userName)) {
			if (isExisting(userName)) {
				for (User user : usersList.getUsers()) {
					if (user.getUserName().equals(userName)) {
						String password, newUserName;
						Utils.printMessage("Enter your password");
						password = scanner.next();
						if (Utils.isAlphabets(password) && user.getPassword().equals(password)) {
							Utils.printMessage("Enter your new username");
							newUserName = scanner.next();
							if (Utils.isAlphabets(newUserName)) {
								user.setUserName(newUserName);
							} else {
								Utils.printMessage("Invalid user name!!!");
							}
						} else {
							Utils.printMessage("Invalid Password!!");
						}
						break;
					}
				}
			} else {
				Utils.printMessage("Invalid username");
			}
		}
	}

	private void registerUser() {
		String userName, password, confirmPassword;
		Utils.printMessage("Enter your user name");
		userName = scanner.next();
		if (Utils.isAlphabets(userName)) {
			if (!isExisting(userName)) {
				Utils.printMessage("Please enter your password");
				password = scanner.next();
				Utils.printMessage("Please enter your password again");
				confirmPassword = scanner.next();
				if (password.equals(confirmPassword)) {
					User newUser = new User();
					newUser.setUserName(userName);
					newUser.setPassword(password);

					Utils.printMessage("Please enter deposit amount");
					int amount = scanner.nextInt();
					BankAccount bankAccount = new BankAccount();
					bankAccount.setBalance(amount);
					newUser.setBankAccount(bankAccount);
					
					usersList.addUser(newUser);
				} else {
					Utils.printMessage("Password and confrim password did not match");
				}
			} else {
				Utils.printMessage("Username already existing, please choose another username");
			}
		}
	}

	private boolean isExisting(String userName) {
		for (User user : usersList.getUsers()) {
			if (userName.equals(user.getUserName())) {
				return true;
			}
		}
		return false;
	}

	private void resetPassword() {
		String userName, oldPassword;
		Utils.printMessage("Can you please enter your username?");
		userName = scanner.next();
		if (Utils.isAlphabets(userName)) {
			for (User user : usersList.getUsers()) {
				if (user.getUserName().equals(userName)) {
					Utils.printMessage("Please enter your old password");
					oldPassword = scanner.next();
					if (Utils.isAlphabets(oldPassword) && user.getPassword().equals(oldPassword)) {
						String newPassword, confrimPassword;
						Utils.printMessage("Please enter your new password");
						newPassword = scanner.next();
						Utils.printMessage("Please enter your new password again");
						confrimPassword = scanner.next();
						if (newPassword.equals(confrimPassword)) {
							user.setPassword(newPassword);
							Utils.printMessage("Password reset successfull");
						} else {
							Utils.printMessage("Password and confrim password did not match");
						}
					} else {
						Utils.printMessage("Invalid password!!!");
					}
					break;
				}
			}
		} else {
			Utils.printMessage("Invalid user name!!!");
		}
	}

	private boolean login() {
		String userName, password;
		User validUser = new User();

		Utils.printMessage("Can you please enter your userName?");
		boolean isUserNamePresent = false;
		boolean isPasswordValid = false;

		do {
			userName = scanner.next();
			for (User user : usersList.getUsers()) {
				if (user.getUserName().equals(userName)) {
					validUser = user;
					isUserNamePresent = true;
					break;
				}
			}
			if (isUserNamePresent) {
				Utils.printMessage("Can you please enter your password?");
				password = scanner.next();
				if (validUser != null && validUser.getPassword().equals(password)) {
					isPasswordValid = true;
					UsersList.loggedInUser = validUser;
					Utils.printMessage("Login Successful!!!");
				} else {
					Utils.printMessage("Invalid password!!!");
				}
			} else {
				Utils.printMessage("Invalid Username!!!");
			}
		} while (!isUserNamePresent || !isPasswordValid);
		return isUserNamePresent && isPasswordValid;
	}

}

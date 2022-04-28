package model;

import java.util.ArrayList;

public class User {

	private String userName;
	private String password;
	private BankAccount bankAccount;
	private ArrayList<PaymentHistory> paymentHistoriesArrayList = new ArrayList<PaymentHistory>();

	public ArrayList<PaymentHistory> getPaymentHistoriesArrayList() {
		return paymentHistoriesArrayList;
	}

	public void setPaymentHistoriesArrayList(ArrayList<PaymentHistory> paymentHistoriesArrayList) {
		this.paymentHistoriesArrayList = paymentHistoriesArrayList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}

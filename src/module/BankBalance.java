package module;

import java.util.Scanner;

import model.PaymentHistory;
import utils.UsersList;
import utils.Utils;

public class BankBalance {

	public void handleBankBalance() {
		Utils.printMessage("Your balance is Rs." + UsersList.loggedInUser.getBankAccount().getBalance());
	}

	public void showPaymentHistory() {
		if (UsersList.loggedInUser.getPaymentHistoriesArrayList().size() > 0) {
			for (PaymentHistory history : UsersList.loggedInUser.getPaymentHistoriesArrayList()) {
				Utils.printMessage(history.getTime() + " - Rs." + history.getAmount());
			}
		} else {
			Utils.printMessage("User hasn't made any payment so far");
		}
	}

}

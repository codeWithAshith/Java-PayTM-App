package module;

import java.util.Scanner;

import utils.UsersList;
import utils.Utils;

public class PayTM {

	private Scanner scanner;
	private Login login;
	private Recharge recharge;
	private BankBalance bankBalance;
	private TicketBooking ticketBooking;

	public void initApp() {
		scanner = new Scanner(System.in);
		login = new Login(scanner);
		recharge = new Recharge(scanner);
		bankBalance = new BankBalance();
		ticketBooking = new TicketBooking();
		login.init();
		showServices();
	}

	private void handleUserOption() {
		switch (getUserOption()) {
		case 1:
			recharge.handleRecharge();
			showServices();
			break;
		case 2:
			bankBalance.handleBankBalance();
			showServices();
			break;
		case 3:
			ticketBooking.handleTicketBooking(scanner);
			showServices();
			break;
		case 4:
			bankBalance.showPaymentHistory();
			showServices();
			break;
		case 5:
			Utils.printMessage("Logged out!!!");
			UsersList.loggedInUser = null;
			login.init();
			showServices();
			break;
		default:
			Utils.printMessage("Invalid option!!! Please choose valid option.");
			break;
		}

	}

	public void showServices() {
		Utils.printMessage("*************************");
		Utils.printMessage("|	PayTM		|");
		Utils.printMessage("*************************");
		Utils.printMessage("Please choose a Service");
		Utils.printMessage("1. Recharge");
		Utils.printMessage("2. Bank Balance");
		Utils.printMessage("3. Ticket Booking");
		Utils.printMessage("4. Payment History");
		Utils.printMessage("5. Logout");
		handleUserOption();
	}

	public int getUserOption() {
		int optionInInteger = 0;
		String option;
		boolean isValidOption = false;
		do {
			option = scanner.next();
			if (Character.isDigit(option.charAt(0))) {
				optionInInteger = Integer.parseInt(String.valueOf(option.charAt(0)));
				if (optionInInteger > 0 && optionInInteger <= 5) {
					isValidOption = true;
				} else {
					Utils.printMessage("Invalid option!!! Please choose valid option.");
				}
			} else {
				Utils.printMessage("Invalid option!!! Please choose valid option.");
			}
		} while (!isValidOption);
		return optionInInteger;
	}
}

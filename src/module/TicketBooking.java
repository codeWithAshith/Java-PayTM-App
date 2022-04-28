package module;

import java.util.InputMismatchException;
import java.util.Scanner;

import utils.Utils;

public class TicketBooking {

	public void handleTicketBooking(Scanner scanner) {
		String destination, boardingPoint;
		int numberOfpassengers, userChoice;
		int defaultPrice = 500;
		Utils.printMessage("Enter your destination");
		destination = scanner.next();
		Utils.printStars();
		Utils.printMessage("Enter your boarding point");
		boardingPoint = scanner.next();
		Utils.printStars();
		if (Utils.isAlphabets(destination) && Utils.isAlphabets(boardingPoint)) {
			Utils.printMessage("Please enter the number of passengers");
			try {
				numberOfpassengers = scanner.nextInt();
				double amount = numberOfpassengers * defaultPrice;
				Utils.printStars();
				Utils.printMessage("Your total payable amount is Rs." + amount);
				Utils.printMessage("Would you like to proceed?");
				Utils.printMessage("1. Yes");
				Utils.printMessage("2. No");
				Utils.printStars();
				try {
					userChoice = scanner.nextInt();
					if (userChoice == 1) {
						Utils.printMessage("Your ticket is booked! From " + boardingPoint + " to " + destination);
					} else if (userChoice == 2) {
						Utils.printMessage("Your booking is cancelled");
					} else {
						Utils.printMessage("Invalid input! please try again later");
					}
				} catch (InputMismatchException e) {
					Utils.printMessage("Invalid input! please try again later");
				}
			} catch (InputMismatchException e) {
				Utils.printMessage("Invalid input! please try again later");
			}
		} else {
			Utils.printMessage("Please enter a valid destination and boarding point!");
		}

	}

}

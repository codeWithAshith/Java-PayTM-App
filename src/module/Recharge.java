package module;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.PaymentHistory;
import model.Plan;
import model.Provider;
import utils.UsersList;
import utils.Utils;

public class Recharge {

	private Scanner scanner;
	private ArrayList<Provider> providersArrayList;

	public Recharge(Scanner scanner) {
		this.scanner = scanner;
	}

	public void handleRecharge() {
		providersArrayList = new ArrayList<Provider>();
		Utils.printMessage("Please enter the phone number");
		boolean isValidPhoneNumber = false;
		do {
			String contactNumber = scanner.next();
			if (Utils.isNumber(contactNumber)) {
				if (Utils.isValidPhoneNumber(contactNumber)) {
					// reads all the providers name from file and stores it in providersArrayList
					getProvidersFromFile();
					// reads all the plans from file and set it to respective providers
					getPlansForProvidersFromFile();
					// displays all the provider name from providersArrayList
					showProviders();
					// accepts user choice of provider
					int providerChoice = scanner.nextInt();
					// getting the user chosen provider
					Provider provider = getProvider(providerChoice);
					// displays all the plans for the selected provider
					showPlans(provider);
					int plan = scanner.nextInt();
					String amount = getAmount(provider, plan);
					int userBalance = UsersList.loggedInUser.getBankAccount().getBalance();
					if (Integer.parseInt(amount) <= userBalance) {
						Utils.printMessage("Recharge Sucessfully!! Recharge Amount is Rs." + amount);
						UsersList.debitBalance(Integer.parseInt(amount));
						
						PaymentHistory history = new PaymentHistory();
						history.setTime(String.valueOf(LocalDateTime.now()));
						history.setAmount(Integer.parseInt(amount));
						UsersList.loggedInUser.getPaymentHistoriesArrayList().add(history);
						
						Utils.printMessage("User Balance Rs." + UsersList.loggedInUser.getBankAccount().getBalance());
					} else {
						Utils.printMessage("Insufficient Balance");
					}
					isValidPhoneNumber = true;
				} else {
					Utils.printMessage("Invalid phone number! Please enter a valid phone number.");
				}
			} else {
				Utils.printMessage("Invalid phone number! Please enter a valid phone number.");
			}
		} while (!isValidPhoneNumber);
	}

	private void getPlansForProvidersFromFile() {
		File file = new File(Utils.getProviderLocation());
		Scanner sc;
		try {
			sc = new Scanner(file);
			if (sc.hasNextLine()) {
				String line = sc.nextLine();
				while (sc.hasNextLine()) {
					if (Utils.isAlphabets(line)) {
						Provider provider = getProvider(line);
						if (provider != null) {
							String planLine = sc.nextLine();
							do {
								String[] parts = planLine.split(" - ");
								String numberOfDays = parts[0];
								String price = parts[1];
								Plan plan = new Plan();
								plan.setNumberOfDays(numberOfDays);
								plan.setPrice(price);
								ArrayList<Plan> plansArrayList = provider.getPlansArrayList();
								plansArrayList.add(plan);
								if (sc.hasNextLine()) {
									planLine = sc.nextLine();
								} else {
									planLine = "Exit";
								}
							} while (!Utils.isAlphabets(planLine));
							line = planLine;
						} else {
							Utils.printMessage("Invalid Provider");
						}

					}
				}
			}
		} catch (FileNotFoundException e) {
			Utils.printMessage("Invalid File loaded in");
		}

	}

	private void getProvidersFromFile() {
		File file = new File(Utils.getProviderLocation());
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (Utils.isAlphabets(line)) {
					Provider provider = new Provider();
					provider.setProviderName(line);
					providersArrayList.add(provider);
				}
			}
		} catch (FileNotFoundException e) {
			Utils.printMessage("Invalid File loaded in");
		}
	}

	private Provider getProvider(String providerName) {
		for (Provider provider : providersArrayList) {
			if (provider.getProviderName().equals(providerName)) {
				return provider;
			}
		}
		return null;
	}

	private void showProviders() {
		Utils.printMessage("Please choose your provider");
		for (int i = 1; i <= providersArrayList.size(); i++) {
			Utils.printMessage(i + ". " + providersArrayList.get(i - 1).getProviderName());
		}
	}

	private void showPlans(Provider provider) {
		Utils.printMessage("-----Plans-----");
		for (int i = 1; i <= provider.getPlansArrayList().size(); i++) {
			Plan plan = provider.getPlansArrayList().get(i - 1);
			Utils.printMessage(i + ". " + plan.getNumberOfDays() + " - Rs. " + plan.getPrice());
		}
	}

	private String getAmount(Provider provider, int plan) {
		return provider.getPlansArrayList().get(plan - 1).getPrice();
	}

	private Provider getProvider(int providerChoice) {
		return providersArrayList.get(providerChoice - 1);
	}

}

package App;

import module.PayTM;

public class App {

	public static void main(String[] args) {
		PayTM payTm = initPayTM();
		payTm.initApp();
	}

	private static PayTM initPayTM() {
		return new PayTM();
	}

}




//Login
// mask password with *

//TicketBooking
// multiple destination and boarding
// we charge based on distance
// add gst

//insurance

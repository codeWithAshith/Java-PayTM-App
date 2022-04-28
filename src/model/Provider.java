package model;

import java.util.ArrayList;

public class Provider {
	private String providerName;
	private ArrayList<Plan> plansArrayList = new ArrayList<Plan>();

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public ArrayList<Plan> getPlansArrayList() {
		return plansArrayList;
	}

	public void setPlansArrayList(ArrayList<Plan> plansArrayList) {
		this.plansArrayList = plansArrayList;
	}

}

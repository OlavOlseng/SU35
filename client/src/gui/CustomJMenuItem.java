package gui;

import javax.swing.JMenuItem;

public class CustomJMenuItem extends JMenuItem{

	private int appID;
	private String empID;

	public CustomJMenuItem(String s) {
		super(s);
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
}

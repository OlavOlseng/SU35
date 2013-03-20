package gui;

import javax.swing.JMenuItem;

public class CustomJMenuItem extends JMenuItem{

	private int appID;

	public CustomJMenuItem(String s) {
		super(s);
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}
}

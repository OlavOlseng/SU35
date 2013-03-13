package models;

import java.util.ArrayList;

public class Group {
	private final int groupID;
	private String name;
	private ArrayList<Employee> members;
	
	public Group(int groupID) {
		this.groupID = groupID;
		members = new ArrayList<Employee>();
	}
	
	public ArrayList<Employee> getMembers() {
		String query = "SELECT...";
		/*
		 * send query to server
		 */
		return members;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String ret = "Group ID:\t" + groupID + "\nGroup name:\t" + name + "\nGroup members:\t";
		for(Employee e : members) {
			ret += e.toString() + ", ";
		}
		return ret;
	}
}

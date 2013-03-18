package models;

import java.util.ArrayList;

public class Group {
	private final String groupID;
	private ArrayList<String> members;
	
	public Group(String groupID) {
		this.groupID = groupID;
		members = new ArrayList<String>();
	}
	
	public void addMember(String id){
		// TODO add code for sending to database
		members.add(id);
	}
	
	public ArrayList<String> getMembers() {
		return members;
	}
	
	public String toString() {
		String ret = "Group ID:\t" + groupID + "\nGroup members:\t";
		for(String e : members) {
			ret += e.toString() + ", ";
		}
		return ret;
	}
}

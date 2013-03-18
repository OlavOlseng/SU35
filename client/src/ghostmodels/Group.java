package ghostmodels;

import java.util.ArrayList;

public class Group {
	private final int groupID;
	private String name;
	private ArrayList<String> members;
	
	public Group(int groupID) {
		this.groupID = groupID;
		members = new ArrayList<String>();
		name = "";
	}
	
	public void addMember(String id){
		// TODO add code for sending to database
		members.add(id);
	}
	
	public ArrayList<String> getMembers() {
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
		for(String e : members) {
			ret += e.toString() + ", ";
		}
		return ret;
	}
}

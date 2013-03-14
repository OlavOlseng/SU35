package models;

public class Employee {
	private String email;
	public void setEmail(String email) {
		this.email = email;
	}

	private String  firstName, lastName, homePhone, mobilePhone;
	
	public Employee(String email, String firstName, String lastName, String homePhone, String mobilePhone) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
	}
	
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}



	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}



	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public String toString() {
		String s = new String();
		s += "Employee Email:\t\t\t" + email;
		s += "\nEmployee Name:\t\t\t" + lastName +", " + firstName;
		
		if (homePhone != null) {
			s += "\nEmployee Home phn.:\t\t" + homePhone; 
		}
		
		if (mobilePhone != null) {
			s += "\nEmployee Mobile phn.:\t\t" + mobilePhone;
		}
		return s;
	}
	
}

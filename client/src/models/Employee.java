package models;

public class Employee {
	
	private final String email;
	private String  firstName, lastName,
		homePhone, mobilePhone;
	
	public Employee(String email, String firstName, String lastName, String homePhone, String mobilePhone) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhone = homePhone;
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
		s += "Email:\t" + email;
		s += "\nName:\t" + lastName +", " + firstName;
		
		if (homePhone != null) {
			s += "\nHome phn.:\t" + homePhone; 
		}
		
		if (mobilePhone != null) {
			s += "\nMobile phn.:\t" + mobilePhone;
		}
		return s;
	}
	
}

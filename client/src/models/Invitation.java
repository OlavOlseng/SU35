package models;

public class Invitation {
	public enum Answer { ACCEPTED, DECLINED, PENDING };
	private Answer answer;
	
	public Invitation() {
		answer = Answer.PENDING;
	}
	
	public void acceptInvite() {
		answer = Answer.ACCEPTED;
	}
	
	public void declineInvite() {
		answer = Answer.DECLINED;
	}
	
	public Answer getStatus() {
		return answer;
	}
	
	public String toString() {
		if(answer == Answer.ACCEPTED) {
			return "Invitation accepted";
		}
		else if(answer == Answer.DECLINED){
			return "Invitation declined";
		}
		else {
			return "Invitation pending";
		}
	}
}

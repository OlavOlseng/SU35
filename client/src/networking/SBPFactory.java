package networking;

public class SBPFactory {
	
	public enum MessageType {
		LOGIN('l'),
		GET('g'), 
		UPDATE('u'),
		CREATE('c'),
		BATCH_UPDATE('b'),
		ERROR('e'),
		DELETE('d');
		
		char c;
		MessageType(char c) {
			this.c = c;
		}
		
		public char getType() {
			return c;
		}
	}
	
	public final static String OPTION_NONE = "none";
	public final static String OPTION_EMPLOYEE = "employee";
	public final static String OPTION_APPOINTMENT = "appointment";
	public final static String OPTION_INVITATION = "invitation";
	public final static String OPTION_INVITATIONS_BY_EMPLOYEE = "invitationE";
	public final static String OPTION_INVITATIONS_BY_APPOINTMENT = "invitationA";
	public final static String OPTION_ROOM = "room";
	public final static String OPTION_ALARM = "alarm";
	public final static String OPTION_GROUP = "group";
	
	public String makeLoginMessage(String user, String password) {
		String payload = String.format("%s¤%s", user, password);
		return createMessage(MessageType.LOGIN, false, null, OPTION_NONE, payload);
	}
	
	public String makeGetMessage(String option, String id) {
		String payload = String.format("%s", id);
		return createMessage(MessageType.GET, false, null, option, payload);
	}
	
	public String makeDeleteMessage(String option, String condition) {
		String payload = String.format("%s", condition);
		return createMessage(MessageType.DELETE, false, null, option, payload);
	}
	
	//Not finalized
	public String makeCreateMessage(String option, String what, String values){
		String payload = String.format("%s¤%s", what, values);
		return createMessage(MessageType.CREATE, false, null, option, payload);
	}
	
	public String makeUpdateMessage(String option, String what, String condition) {
		String payload = String.format("%s¤%s", what, condition);
		return createMessage(MessageType.UPDATE, false, null, option, payload);
	}
	
	public String createMessage(MessageType t, boolean error, String errorMsg, String option, String payload) {
		String msg = new String();
		
		msg += t.getType() + "~";
		
		if (error) {
			msg += 1;
		} else msg += 0;
		msg += "~";
		
		msg += errorMsg + "~";
		msg += option + "~";
		msg += payload;
		
		return msg;
	}
}

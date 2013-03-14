package networking;

public class SBPFactory {
	
	public enum MessageType {
		LOGIN('l'),
		GET('g'), 
		UPDATE('u'),
		CREATE('c'),
		BATCH_UPDATE('b'),
		DELETE('d');
		
		char c;
		MessageType(char c) {
			this.c = c;
		}
		
		public char getType() {
			return c;
		}
	}
	
	public String makeLoginMessage(String user, String password) {
		String payload = String.format("%s^%s", user, password);
		return createMessage(MessageType.LOGIN, false, "", payload);
	}
	
	public String makeGetMessage(String what, String id) {
		String payload = String.format("%s^%s", what, id);
		return createMessage(MessageType.GET, false, "", payload);
	}
	
	public String makeDeleteMessage(String table, String condition) {
		String payload = String.format("%s^%s", table, condition);
		return createMessage(MessageType.DELETE, false, "", payload);
	}
	
	public String makeCreateMessage(String table, String what, String values){
		String payload = String.format("%s^%s^%s", table, what, values);
		return createMessage(MessageType.CREATE, false, "", payload);
	}
	
	public String makeUpdateMessage(String table, String what, String condition) {
		String payload = String.format("%s^%s^%s",table, what, condition);
		return createMessage(MessageType.UPDATE, false, "", payload);
	}
	
	public String createMessage(MessageType t, boolean error, String errorMsg, String payload) {
		String msg = new String();
		
		msg += t.getType() + "~";
		
		if (error) {
			msg += 1;
		} else msg += 0;
		msg += "~";
		
		msg += errorMsg + "~";
		msg += payload;
		
		return msg;
	}
}

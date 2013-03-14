package networking;

public class SBPFactory {
	
	public enum MessageType {
		QUERY('q'), 
		UPDATE('u'), 
		BATCH_UPDATE('b'),
		OBJECT('o');
		
		char c;
		MessageType(char c) {
			this.c = c;
		}
		
		public char getType() {
			return c;
		}
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

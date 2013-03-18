package networking;

import networking.SBPFactory.MessageType;

public abstract class MessageHandler {

	public void handleMessage(String msg) {
		String[] msga = msg.split("~");
		if (msga.length != 5) msga[0] = String.valueOf(MessageType.ERROR.c);
		switch(msga[0]) {
			case("g"):
				getEntry(msga);
				break;
			case("u"):
				updateEntry(msga);
				break;
			case("b") :
				makeBatchUpdate(msga);
				break;
			case("c"):
				createEntry(msga);
				break;
			case("d"):
				deleteEntry(msga);
				break;
			case("l"):
				checkLogin(msga);
			default:
				errorResponse(msga);
		}
		return;
	}
	
	public abstract void getEntry(String[] data);
	public abstract void updateEntry(String[] data);
	public abstract void makeBatchUpdate(String[] data);
	public abstract void createEntry(String[] data);
	public abstract void deleteEntry(String[] data);
	public abstract void checkLogin(String[] data);
	
	public void errorResponse(String[] data) {
		System.err.println("Invalid message format");
		System.err.println(data);
	}
}


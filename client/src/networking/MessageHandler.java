package networking;

public abstract class MessageHandler {

	public void handleMessage(String msg) {
		String[] msga = msg.split("~");
		
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
		}
		return;
	}
	
	public abstract void getEntry(String[] data);
	public abstract void updateEntry(String[] data);
	public abstract void makeBatchUpdate(String[] data);
	public abstract void createEntry(String[] data);
	public abstract void deleteEntry(String[] data);
	public abstract void checkLogin(String[] data);
}


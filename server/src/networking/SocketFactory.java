package networking;

import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;


public class SocketFactory implements SocketImplFactory {

	@Override
	public SocketImpl createSocketImpl() {
		// TODO Auto-generated method stub
		System.out.println("GAY");
		return new Bizz();
	}

}

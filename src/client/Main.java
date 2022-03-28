package client;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Client client1 = new Client("localhost", 92);
	    //Client client2 = new Client("192.168.1.101", 8888);
	
	    client1.start();
	    //client2.start();
	
	    
	}
}

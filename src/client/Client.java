package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class Client  extends Thread {
	
	private String ip;
    private int port;


    public Client(String serverIP, int serverPort) {
        this.ip = new String(serverIP);
        this.port = serverPort;
    }

    private void startClient() throws ClassNotFoundException {
        try {
            Socket socket = new Socket(ip, port); 
            //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //InputStreamReader in = new InputStreamReader(s.getInputStream());
            //BufferedReader bf = new BufferedReader(in);

            InputStream yourInputStream = socket.getInputStream(); 
            ObjectInputStream mapInputStream = new ObjectInputStream(yourInputStream);
            Map < String, Integer > yourMap = (Map) mapInputStream.readObject();

            //Reading the server response
            

            //Printing the server response
            for (Map.Entry<String, Integer> entry : yourMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
            //System.out.println(tiempo);
            //socket.close();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void run() {

        try {
			startClient();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        super.run();
    }
    public static void main(String[] args) throws IOException {
    	Client client1 = new Client("localhost", 8888);
	    //Client client2 = new Client("192.168.1.101", 8888);
	
	    client1.start();
    }
}

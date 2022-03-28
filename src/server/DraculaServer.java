package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DraculaServer extends Thread {
	
	ExecutorService threadPool = Executors.newCachedThreadPool();
	
	class ClientSocket implements Runnable {

	    private Socket m_socket;

	    ClientSocket(Socket sock) {
	        m_socket = sock;
	    }

	    @Override
	    public void run() {
	        try {
	           
	        	 //Open a socket connection
	            //ServerSocket socket = new ServerSocket(93);

	            //accept and establish the connection
	            //Socket socket1 = socket.accept();

	            File f1 = new File("D://JavaTask/test2.txt");

	            //Initialize the word Array
	            String[] words=null; 

	            //Creation of File Reader object
	            FileReader fr = new FileReader(f1);

	            //Creation of BufferedReader object
	            BufferedReader br = new BufferedReader(fr);

	            //String str = br.readLine();
	            System.out.println("Descriptor File path: " + " JavaTask/dracula.txt" );

	            String s;

	            //Scanner inp = new Scanner(System.in);
	            //System.out.println("Enter the string to be searched");

	            //Input String to be searched
	            //String input= inp.nextLine();
	            //---------------------------------------------------------
	            //Initialize the word to zero
	            int count=0;
	            Map < String, Integer > wordMap = new HashMap < > ();
	            //Reading Content from the file
	            while((s=br.readLine())!=null)
	            {
	                //Split the word using space
	                words=s.split(" ");
	                for (String word : words)
	                {
	                    //Search for the given word
	                	if (wordMap.containsKey(word)) {
	      		          wordMap.put(word, (wordMap.get(word) + 1));
	      		        } else {
	      		          wordMap.put(word, 1);
	      		        }
	                }
	            }
	        	
	            //PrintWriter out = new PrintWriter(m_socket.getOutputStream(),true);
	            //out.flush();
	            //System.out.println(time);
	            //System.out.flush();
	            OutputStream yourOutputStream = m_socket.getOutputStream(); // OutputStream where to send the map in case of network you get it from the Socket instance.
	            ObjectOutputStream mapOutputStream = new ObjectOutputStream(yourOutputStream);
	            mapOutputStream.writeObject(wordMap);
	        } catch (IOException ex) {
	            Logger.getLogger(DraculaServer.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	}


	@Override
	public void run() {
	    startServer();
	    super.run();
	}

	private void startServer() {

	    try {
	        ServerSocket ss = new ServerSocket(8888);
	        System.out.println("Waiting for connection Server One");

	        do {
	            Socket socket = ss.accept();

	            threadPool.execute(new ClientSocket(socket));

	        } while(true);

	    } catch (IOException ex) {
	        Logger.getLogger(DraculaServer.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
}

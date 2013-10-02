import java.net.*;
import java.io.*;


public class ClientThread extends Thread{

	RPiServer server;
    private Socket clientSocket = null;
	String configuration;
	
    ClientThread(Socket socket, RPiServer server){
		super("ClientThread");
		this.clientSocket =  socket;
		this.server = server;
	}

    
    public void run(){
        System.out.println("Connected to "+ clientSocket.toString());
		try{
			  PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			  while ((configuration = in.readLine()) != null) {
				server.assignConfig(configuration);
				System.out.println(configuration);
				
			 }
			  out.close();
			  in.close();
			  clientSocket.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		 
		}
    }

	String getIP(){
		return clientSocket.getInetAddress().toString();
	}
	
	
	String getConfig(){
		return configuration;
	}
	
}

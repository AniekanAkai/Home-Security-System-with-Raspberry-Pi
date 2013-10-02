import java.net.*;
import java.io.*;

 
public class RPiServer {

	ClientThread clientThread;
	ArrayList<Socket> clientSocketList;
	Socket clientSocket = null;
    ServerSocket serverSocket = null;
	String [][] user = new String[2][2];
    /**    		    Gamer 1       Gamer 2
	* IP Addr     gamer(0,0)	 gamer(0,1)
	* Config	  gamer(1,0)	 gamer(1,1)
	*
	*/
	int numUsers = 0;
	boolean check = false;
	
	synchronized void assignConfig(String IP, String config){
			if (IP == user[0],[0]){
				user[1][0] = config;
			}else if(IP == gamer[0][1]){
				user[1][1] = config;
			}else{
				System.out.println("This user is not registered!");
			}
	}
	
	boolean checkMatch(){
			if(user[1][0]== user[1][1]){
				return true;
			}else{
				return false;
			}
			
	}
	public static void main(String[] args) throws IOException {
	
		clientSocketList = new ArrayList();
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

		while(true){
			try {
				System.out.println("Waiting for client...");
				clientSocket = serverSocket.accept();
				clientThread = new ClientThread(clientSocket, this);

				System.out.println("New Gamer connected.\n ID: "+ clientThread.getId+" ; IP: "+ clientSocket.getInetAddress()+"\n");
				if(numUsers>=2){
					System.out.println("Max Users reached.");
					
				}else if(numUsers==1){
					clientSocketList.add(clientSocket);
					user[0][0] = clientSocket.getInetAddress().toString();// Gamer 1 IP address
					user[1][0] = "2222";//Default config for gamer 1
					numUsers++;
				}else if(numUsers == 0){
					clientSocketList.add(clientSocket);
					user[0][1] = clientSocket.getInetAddress().toString(); // Gamer 2 IP address
					user[1][1] = "3333";//Default config for gamer 2
				}
				
				
				clientThread.start();			
				
				if(numUsers == 1){
					while(user[1][0]=="2222" || user[1][1]== "3333"){}
					System.out.println(checkMatch);
					for (Socket s : clientSocketList){
						PrintWriter outToClient = s.getOutputStream();
						if(checkMatch){
							outToClient.write("win")
						} else{
							outToClient.write("fail");
						}
						
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
    }
	

	}

}


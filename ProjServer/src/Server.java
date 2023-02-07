	

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Sound;

public class Server {
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    Comandi Movimento;
    
   

    public void startServer(int port) throws IOException {
    	
        //creo un socket "serverSocket" in ascolto nella porta 30015
        serverSocket = new ServerSocket(port);

        System.out.println("Il server e' partito!");
        System.out.println("IP: 10.0.1.1");
        System.out.println("Porta: 9876");
        

        //accetto la comunicazione del client creando un altro oggetto socket
        clientSocket = serverSocket.accept();

        System.out.println("Client connesso correttamente!");
    }
    	

    public void riceviComandi() throws IOException {

    	Movimento = new Comandi();
    	
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            char c = (char)input.read();
            System.out.println("Client: " + c);
             
           switch (c)
           {
            
            case '0':
            	
            	//System.out.println("rallenta");	
            	//Movimento.rallenta();
            	break;
            	
               
            	
            case 'w':
            	System.out.println("avanti");
            	Movimento.vaiAvanti();
            	break;
            	
            case 's':
            	System.out.println("indietro");
            	Movimento.vaiIndietro();
            	break;
            	
            case 'a':
            	System.out.println("sinistra");
            	Movimento.curvaSx();
            	break;
            	
            case 'd':
            	System.out.println("destra");
            	Movimento.curvaDx();
            	break;
            	
            case 'q':
            	System.out.println("accelera");
            	Movimento.setMaxVel();
            	break;
            	
            case 27:
            	System.out.println("Spegnimento server!");
            	Movimento.shutDown();
            	stopServer();
            	break;
           }
            
            getMidVel();
        }
    }
    
    public void getMidVel() {
    	
    	//invio velocità al client
        output.println(Movimento.getMidVel());
    	
    	//int veloc = (int) Math.floor(Math.random()*(740-100+1)+100);
        //output.println(veloc);
        
    }
    
    public void stopServer() throws IOException {
    	
    	clientSocket.close();
    	serverSocket.close();
    	output.close();
    	input.close();
    }
   

 
    
    
    
    
    

        public static void main(String[] args) throws IOException {

            Server server = new Server();

            server.startServer(9876);
            //while(true)
            server.riceviComandi();
            
            server.getMidVel();
            
        }
    }

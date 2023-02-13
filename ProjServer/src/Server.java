import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import lejos.hardware.Sound;
public class Server {
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    Comandi Movement;
    
   

    public void startServer(int port) throws IOException {
    	
        serverSocket = new ServerSocket(port);
        System.out.println("Server: ON");
        System.out.println("IP: 10.0.1.1");
        System.out.println("Porta: 8888");
        clientSocket = serverSocket.accept();
        System.out.println("Client connesso correttamente!");
    }

    public void riceviComandi() throws IOException {

    	Movement = new Comandi();
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            char c = (char)input.read();
            System.out.println("Client: " + c);
           switch (c)
           {
            case '0':	
            	System.out.println("slow");	
            	Movement.slow();;
            	break;   	
            case 'w':
            	System.out.println("forward");
            	Movement.goForward();
            	break;
            case 's':
            	System.out.println("backward");
            	Movement.goBackward();
            	break;
            case 'a':
            	System.out.println("left");
            	Movement.rotateLeft();
            	break;
            case 'd':
            	System.out.println("right");
            	Movement.rotateRight();
            	break;
            case 'q':
            	System.out.println("speed up");
            	Movement.setMaxSpeed();
            	break;
            case 27:
            	System.out.println("Spegnimento server!");
            	Movement.shutDown();
            	stopServer();
            	break;
           }
            getMidVel();
        }
    }
    
    public void getMidVel() {
    	
        output.println(Movement.getMidSpeed());
    }
    
    public void stopServer() throws IOException {
    	clientSocket.close();
    	serverSocket.close();
    	output.close();
    	input.close();
    }


        public static void main(String[] args) throws IOException {
            Server server = new Server();
            server.startServer(8888);
            server.riceviComandi();
            server.getMidVel();
            
        }
    }

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {




    private Socket clientSocket;
    private DataOutputStream output;
    private BufferedReader input;
    private String speed = "0";

    public void connessione() throws UnknownHostException, IOException {

        String ipServer = JOptionPane.showInputDialog("Inserisci l'indirizzo del server");
        String portaServer = JOptionPane.showInputDialog("Inserisci la porta del server");

        int porta = Integer.parseInt(portaServer);

        clientSocket = new Socket(ipServer, porta);
    }

    public void InvioDati(char msg) throws IOException {

        output = new DataOutputStream(clientSocket.getOutputStream());

        output.writeChar(msg);
    }

    public void ricezioneDati() throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        System.out.println("speedocita: " + speed);
        
        speed = input.readLine();
    }
    
    public String getspeed() {
    	
    	return this.speed;
    }

    public void chiudiConnessione() throws IOException {

        clientSocket.close();
        output.close();
        input.close();
    }
    
    
    
    
    
    public static void main(String[] args) throws UnknownHostException, IOException {

        Client client = new Client();
        remote telecomando = new remote();
        
        telecomando.gestisciremote();
        
        client.connessione();

        while (true) {

            client.InvioDati(telecomando.getvalore());

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                client.chiudiConnessione();
            }
            client.ricezioneDati();
            
            telecomando.setSpeed(client.getspeed());


            }
    }
}

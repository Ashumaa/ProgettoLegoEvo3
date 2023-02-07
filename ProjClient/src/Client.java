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
    private String vel = "0";

    public void connessione() throws UnknownHostException, IOException {

        //richiedo il nome del server per connessione
        String nomeServer = JOptionPane.showInputDialog("Inserisci l'indirizzo del server");
        //richiedo la porta del server per connessione
        String portaServer = JOptionPane.showInputDialog("Inserisci la porta del server");

        //trasformo la porta da String a Integer
        int porta = Integer.parseInt(portaServer);

        //istanzio un socket inserendo il nome del server e la porta
        clientSocket = new Socket(nomeServer, porta);
    }

    public void mandaMessaggio(char msg) throws IOException {

        //invio dati in output al server
        output = new DataOutputStream(clientSocket.getOutputStream());

        //invio char lettera
        output.writeChar(msg);
    }

    public void riceviMessaggio() throws IOException {

        //ricezione dati da server
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        System.out.println("Velocita: " + vel);
        
        vel = input.readLine();
    }
    
    public String getVel() {
    	
    	return this.vel;
    }

    public void chiudiConnessione() throws IOException {

        //chiudo tutto
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

            client.mandaMessaggio(telecomando.getValue());

            try {
                Thread.sleep(180);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                client.chiudiConnessione();
            }
            client.riceviMessaggio();
            
            telecomando.setVeloc(client.getVel());

        }
    }
}
package prankmailsender.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;
import prankmailsender.model.mail.IMail;

/**
 * Concrete SMTP client
 * @author Amel Dussier & Sarra Berich
 */
public class SmtpClient implements ISmtpClient {

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    
    /**
     * Encoding used to communicate with SMTP server
     */
    private static final String ENCODING = "UTF-8";
    
    /**
     * The server address
     */
    private final String serverAddress;
    
    /**
     * The server port
     */
    private final int serverPort;
    
    /**
     * Constructor
     * @param address The SMTP server address
     * @param port The SMTP server port
     */
    public SmtpClient(String address, int port) {
        serverAddress = address;
        serverPort = port;
    }
    
    @Override
    public void sendMail(IMail mail) throws IOException {
        
        // open socket
        Socket socket = new Socket(serverAddress, serverPort);
        
        // get input & ouput streams
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENCODING));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), ENCODING));
        
        // read server welcome message
        String line = reader.readLine();
        LOG.info(line);
        
        // announce to server
        writer.println("EHLO dummy");
        writer.flush();
        
        // get server features
        do {
            line = reader.readLine();
            LOG.info(line);
        } while (line.startsWith("250-"));
        
        // detect server error
        if (!line.startsWith("250 "))
            throw new IOException("SMTP server returned : " + line);
        
        // send mail from
        writer.println("MAIL FROM:" + mail.getSender());
        writer.flush();
        
        // read server response
        line = reader.readLine();
        LOG.info(line);
        
        for(String to : mail.getRecipients()) {
            // send rcpt to
            writer.println("RCPT TO: " + to);
            writer.flush();
            
            // read server response
            line = reader.readLine();
            LOG.info(line);
        }
        
        // send data
        writer.println("DATA");
        writer.flush();
        
        // read server response
        line = reader.readLine();
        LOG.info(line);
        
        // send header
        writer.println("From: " + mail.getSender());
        writer.print("To: " + mail.getRecipients().get(0));
        for (int i = 1; i < mail.getRecipients().size(); i++)
            writer.print(", " + mail.getRecipients().get(i));
        writer.println();
        writer.println("Subject: " + mail.getSubject());
        writer.flush();
        writer.println();
        
        // send body
        writer.println(mail.getBody());
        
        // end mail
        writer.println(".");
        writer.flush();
        
        // read server response
        line = reader.readLine();
        LOG.info(line);
        
        // quit
        writer.println("QUIT");
        writer.flush();
        
        // close socket and streams
        writer.close();
        reader.close();
        socket.close();
    }
}

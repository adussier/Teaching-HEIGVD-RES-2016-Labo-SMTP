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
 *
 * @author Amel Dussier
 */
public class SmtpClient implements ISmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());
    
    private final String serverAddress;
    
    private final int serverPort;
    
    public SmtpClient(String address, int port) {
        
        serverAddress = address;
        serverPort = port;
    }
    
    @Override
    public void sendMail(IMail mail) throws IOException {
        
        // open socket
        Socket socket = new Socket(serverAddress, serverPort);
        
        // get input & ouput streams
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        
        // read server welcome message
        String line = reader.readLine();
        LOG.info(line);
        
        // announce to server
        writer.println("EHLO dummy");
        
        // get server features
        do {
            line = reader.readLine();
            LOG.info(line);
        } while (line.startsWith("250-"));
        
        // detect server error
        if (!line.startsWith("250 "))
            throw new IOException("SMTP server returned : " + line);
        
        // send mail from
        writer.println("MAIL FROM:" + mail.getFrom());
        
        // read server response
        line = reader.readLine();
        LOG.info(line);
        
        for(String to : mail.getTo()) {
            // send rcpt to
            writer.println("RCPT TO: " + to);
            
            // read server response
            line = reader.readLine();
            LOG.info(line);
        }
        
        // send data
        writer.println("DATA");
        
        // read server response
        line = reader.readLine();
        LOG.info(line);
        
        // send header
        writer.println("FROM: " + mail.getFrom());
    }
}

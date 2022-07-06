import java.io.*;
import java.security.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private static final String  regex   ="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";// "^[a-zA-Z0-9_.,;]";//"[a-zA-Z0-9]";
    private static final Pattern pattern1 = Pattern.compile(regex);

     private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
private static final String TRANS_REMARK_VNPAY   = "^[0-9A-Za-z.()_ \\-\\s]{0,200}[0-9A-Za-z.()_ \\-\\s]$";
    private static final Pattern pattern = Pattern.compile(TRANS_REMARK_VNPAY);

    public static boolean isValid(final String username) {
        if(username==null||username.isEmpty()) return true;
        String REGEX_STRING   = "^[0-9A-Za-z.()_ \\-\\s]{0,200}[0-9A-Za-z.()_ \\-\\s]$";
        Pattern pattern = Pattern.compile(REGEX_STRING);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    private static boolean matchesPattern(String query) {
        return pattern.matcher(query).matches();
    }
	public static void main(String[] args) throws IOException {

            String path="D:\\apache-tomcat-9.0.20\\webapps\\IMS_REPORTS\\/EXPORT_REPORT/PDF/2501_BCTD_0310_30062020_9555.PDF";
            if(!path.contains("IMS_REPORTS"))
            {
                System.out.println("------------------- Server.main()");
                return;
            }
            
             



        String[] queries = {
            "hfjdhfjd767sds8ds8d9sd8s", 
                null,
                " ",
                "",
                "05-Jul-2022",
                "002721"
        };

        for (String query: queries){
            System.out.println(isValid(query));
        }
    
            /*
		int portNumber = 4000;
		int maxConnections = 5;
		int i = 0;
		
		// Server initializes the socket it must listen on
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			// Generate RSA keypair
			KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
			
			// THREADING FOR MULTIPLE CONNECTIONS
			while ((i++ < maxConnections) || (maxConnections == 0)) {
				Socket clientSocket = serverSocket.accept();// Wait for connection...
				
				// Initialize the communication protocol for the server,
				// and pass it the RSA keypair.
				DoComms conn_c = new DoComms(clientSocket, keyPair);
				
				// Run the communication protocol
				Thread t = new Thread(conn_c);
				t.start();
			}
		// Catch exceptions
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Exception caught when trying to generate RSA keypair");
		}
*/
	}
}

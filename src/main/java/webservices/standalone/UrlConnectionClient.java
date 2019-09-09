package webservices.standalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionClient {
    public static void main(String[ ] args) {
       
        if (args.length < 1) {
            System.err.println("Usage: UrlConnectionClient <url>");
            return;
        }
        try {
             
            URL url = new URL(args[0].trim());
            URLConnection sock = url.openConnection();
            
            BufferedReader reader =
            new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String next_record = null;
            
            while ((next_record = reader.readLine()) != null)
            System.out.println(next_record);
            
            reader.close();
            
	      }catch(MalformedURLException e) { 
	               throw new RuntimeException(e); 
	      }catch(IOException e) { 
	               throw new RuntimeException(e); 
	      }
    }
}
//Using recommended code, with changes to fit our own program
package model;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

public class WebDownloader {

	public static String testIt(String text){

      String https_url = text;
		
      URL url;
      try {

	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

	     /*Not needed:
	        dumpl all cert info 
	     	print_https_cert(con);

	     	dump all the content
		 	print_content(con);*/

	      return print_content(con);
	      
      } catch (MalformedURLException e) {
    	  e.printStackTrace();
    	  } catch (IOException e) {
    		  e.printStackTrace();
    		  }
      return "Fail";
    }
	
	/*Not needed:
	 private static void print_https_cert(HttpsURLConnection con){
		if(con!=null){
			try {
				System.out.println("Response Code : " + con.getResponseCode());
				System.out.println("Cipher Suite : " + con.getCipherSuite());
				System.out.println("\n");
				
				Certificate[] certs = con.getServerCertificates();
				for(Certificate cert : certs){
					System.out.println("Cert Type : " + cert.getType());
					System.out.println("Cert Hash Code : " + cert.hashCode());
					System.out.println("Cert Public Key Algorithm : "
	                                    + cert.getPublicKey().getAlgorithm());
					System.out.println("Cert Public Key Format : "
	                                    + cert.getPublicKey().getFormat());
					System.out.println("\n");
					}
				} catch (SSLPeerUnverifiedException e) {
					e.printStackTrace();
					} catch (IOException e){
						e.printStackTrace();
						}
			}
		}*/
	
	private static String print_content(HttpsURLConnection con){
		String string = "";
		
		if(con!=null){
			try {
				main.Main.window.textArea.append("**** Content of the URL ****\n");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String input;
				int i = 0;
				
				while ((input = br.readLine()) != null){
					string += input;
					i++;
					
					if (i % 100 == 0) {
						main.Main.window.textArea.append(i + " lines...\n");
						main.Main.window.packMe();
						}
					}
				br.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
		
		main.Main.window.resetDataSeries();
		return string;
	}
}
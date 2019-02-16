
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;

public class WebDownloader {
	public static void main(String[] args) {
		testIt();
	   }

	static String testIt(){

      String https_url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=15min&outputsize=full&apikey=X0E92VRLD6Z3KLH0";
      URL url;
      try {

	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

	     //dumpl all cert info <----not needed at all accordign to teacher.. altså för vår projekt
	     //print_https_cert(con);

	     //dump all the content <-- no print needed, but need json parse
		 //print_content(con);

	      return print_content(con);
      } catch (MalformedURLException e) {
    	  e.printStackTrace();
    	  } catch (IOException e) {
    		  e.printStackTrace();
    		  }
	return "Fail";
      }
	/*private static void print_https_cert(HttpsURLConnection con){
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
				System.out.println("** Content of the URL ****");
				BufferedReader br =
						new BufferedReader(
								new InputStreamReader(con.getInputStream()));
				String input;
				while ((input = br.readLine()) != null){
					string += input;
					}
				br.close();
				} catch (IOException e) {
					e.printStackTrace();
					}
			}
		return string;
		}
}

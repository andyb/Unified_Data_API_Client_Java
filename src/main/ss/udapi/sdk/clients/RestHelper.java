package ss.udapi.sdk.clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class RestHelper {

	private static Logger logger = Logger.getAnonymousLogger();
	
	public static HttpURLConnection createConnection(URL url, String data, String httpMethod, String contentType, Integer timeout, Map<String,String> headers, Boolean gzip){
		HttpURLConnection connection = null;
		try{
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod(httpMethod.toUpperCase());
			connection.setRequestProperty("Content-Type", contentType);
			connection.setReadTimeout(timeout);
			connection.setUseCaches(false);
			connection.setDoInput(true);
	  		connection.setDoOutput(true);
	  		
			if(headers != null){
				for(String key:headers.keySet()){
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			
			if(gzip){
				connection.setRequestProperty("Accept-Encoding", "gzip");
			}
			
			if(data == null){
				data = "";
			}
			
			connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
			
			if(!data.equals("")){
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			    wr.writeBytes(data); 
			    wr.close();
			}
			
		}catch(Exception ex){
			logger.log(Level.WARNING, "Connection Creation Failed", ex );
		}
		return connection;
	}
	
	public static String getResponse(URL url, String data, String httpMethod, String contentType, Integer timeout, Map<String,String> headers, Boolean gzip){
		HttpURLConnection theConnection = RestHelper.createConnection(url, data, httpMethod, contentType, timeout, headers, gzip);
		InputStream inputStream = null;
		try {
			inputStream = theConnection.getInputStream();
		} catch (IOException ex) {
			logger.log(Level.WARNING, "Unable to read response", ex);
		}
		return getResponse(inputStream, gzip);
	}
	
	public static String getResponse(InputStream inputStream, Boolean gzip){
		
		StringBuffer responseBuffer = new StringBuffer(); 
		
		try{
			if(gzip){
				inputStream = new GZIPInputStream(inputStream);  
			}
		
			BufferedReader rd = new BufferedReader( new InputStreamReader(inputStream) );
			String line;

	    	while((line = rd.readLine()) != null) {
		    	responseBuffer.append(line);
		        responseBuffer.append('\n');
		    }
		    rd.close();
	    }catch(Exception ex){
	    	logger.log(Level.WARNING, "Get Response Failed", ex );	
	    } 
	    
	    return responseBuffer.toString();
	}

}

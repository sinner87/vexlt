package ru.expandable.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import ru.expandable.util.RequestResult.ReqType;


import loc.org.json.JSONException;
import loc.org.json.JSONObject;
import loc.org.json.JSONTokener;

import android.accounts.NetworkErrorException;
import android.content.Context;

public class GatewayUtil {

	private static final int POST_TIMEOUT = 10 * 1000;

	private static final String token = "355C84E8-5CEB-EC7D-F218-EDB07FB64ADD";
	public final static String GatewayHost = "http://api.dev.masterbiletov.ru/"+token+"/1.0/"; 
	private Context context;

	public GatewayUtil ( Context c ) {
		this.context = c;
	}
	

	public RequestResult requestRubricsTest ( ) throws JSONException, Exception {
		
			JSONObject req_json = new JSONObject();
			req_json.put("jsonrpc", "2.0");
			req_json.put("method", "getTest");
			req_json.put("params", new JSONObject());
			req_json.put("id", 1);
			
			NetLog.v("json request: %s", req_json.toString());
			
			String response = sendSimplePost(req_json.toString());
			if ( response.length() <= 0 )
				throw new NetworkErrorException("Send POST failed");
			
			NetLog.v("%s", response );
			
			JSONTokener tokener = new JSONTokener(response);
			
			JSONObject root = (JSONObject) tokener.nextValue();
			
			return new RequestResult(root,ReqType.REQ_RUBRICS);
		
	}
	
	private String sendSimplePost(String msg) {

		String lineEnd = "\r\n" ;
		String result = new String("");
	    HttpURLConnection connection = null ;
	    DataOutputStream outputStream = null ;
	     
	    
		String sURL = ( GatewayHost );

		try {
			URL url = new URL(sURL);
			connection = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(false);
			connection.setConnectTimeout(POST_TIMEOUT);

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Enable POST method
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");

			outputStream = new DataOutputStream(connection.getOutputStream());

			outputStream
					.writeBytes(lineEnd + lineEnd + msg + lineEnd + lineEnd);
			outputStream.flush();

			int respCode = connection.getResponseCode();
			NetLog.i("server response code: %d", respCode);


			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringBuilder json_response = new StringBuilder();
			while ((line = br.readLine()) != null) {
				json_response.append(line);
			}
			br.close();
			
			if ( json_response.length() > 0 )
				result = json_response.toString(); 
			else
				NetLog.e("json_response.length() <= 0");
			
			
		} catch (MalformedURLException e) {
			NetLog.e("%s",e.toString());
			e.printStackTrace();
		} catch (ProtocolException e) {
			NetLog.e("%s",e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			NetLog.e("%s",e.toString());
			e.printStackTrace();
		} catch ( Exception e ) {
			NetLog.e("%s",e.toString());
		} finally {
			if( connection != null)
				connection.disconnect();
		}

		return result;
	}
	
}



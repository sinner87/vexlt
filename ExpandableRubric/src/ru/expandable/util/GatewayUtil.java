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
		
			/*
			JSONObject req_json = new JSONObject();
			req_json.put("jsonrpc", "2.0");
			req_json.put("method", "getTest");
			req_json.put("params", new JSONObject());
			req_json.put("id", 1);
			
			NetLog.v("json request: %s", req_json.toString());
			
			String response = sendSimplePost(req_json.toString());
			if ( response.length() <= 0 )
				throw new NetworkErrorException("Send POST failed");
			*/
			
			String response = "{\"jsonrpc\":\"2.0\",\"result\":[{\"id\":103,\"name\":{\"full\":\"Музыка / концерты\",\"short\":\"Музыка\"},\"sort\":1,\"count\":15,\"categories\":[{\"id\":111,\"name\":{\"full\":\"Классика\",\"short\":null},\"sort\":1,\"count\":2},{\"id\":128,\"name\":{\"full\":\"Опера, оперетта, мюзикл \",\"short\":null},\"sort\":3,\"count\":3},{\"id\":109,\"name\":{\"full\":\"Рок / альтернатива\",\"short\":null},\"sort\":4,\"count\":6},{\"id\":130,\"name\":{\"full\":\"Популярная музыка / шансон\",\"short\":null},\"sort\":5,\"count\":3},{\"id\":132,\"name\":{\"full\":\"Народная / музыка мира\",\"short\":null},\"sort\":7,\"count\":1}]},{\"id\":104,\"name\":{\"full\":\"Театры / шоу\",\"short\":\"Театры\"},\"sort\":2,\"count\":42,\"categories\":[{\"id\":134,\"name\":{\"full\":\"Драма / трагедия\",\"short\":null},\"sort\":1,\"count\":7},{\"id\":123,\"name\":{\"full\":\"Комедия / трагикомедия\",\"short\":\"Комедия\"},\"sort\":2,\"count\":11},{\"id\":124,\"name\":{\"full\":\"Опера / оперетта\",\"short\":null},\"sort\":3,\"count\":3},{\"id\":125,\"name\":{\"full\":\"Балет\",\"short\":null},\"sort\":4,\"count\":1},{\"id\":122,\"name\":{\"full\":\"Мюзикл / музыкальные шоу\",\"short\":\"Мюзикл\"},\"sort\":5,\"count\":2},{\"id\":135,\"name\":{\"full\":\"Пьеса\",\"short\":null},\"sort\":6,\"count\":18}]},{\"id\":105,\"name\":{\"full\":\"Кино\",\"short\":null},\"sort\":3,\"count\":64,\"categories\":[{\"id\":149,\"name\":{\"full\":\"Драма\",\"short\":null},\"sort\":1,\"count\":10},{\"id\":150,\"name\":{\"full\":\"Комедия\",\"short\":null},\"sort\":2,\"count\":9},{\"id\":151,\"name\":{\"full\":\"Триллер\",\"short\":null},\"sort\":3,\"count\":7},{\"id\":152,\"name\":{\"full\":\"Боевик\",\"short\":null},\"sort\":4,\"count\":8},{\"id\":153,\"name\":{\"full\":\"Приключения\",\"short\":null},\"sort\":5,\"count\":6},{\"id\":154,\"name\":{\"full\":\"Мелодрама\",\"short\":null},\"sort\":6,\"count\":4},{\"id\":155,\"name\":{\"full\":\"Ужасы\",\"short\":null},\"sort\":7,\"count\":3},{\"id\":156,\"name\":{\"full\":\"Анимация\",\"short\":null},\"sort\":8,\"count\":3},{\"id\":157,\"name\":{\"full\":\"Фантастика\",\"short\":null},\"sort\":9,\"count\":7},{\"id\":158,\"name\":{\"full\":\"Детектив\",\"short\":null},\"sort\":10,\"count\":2},{\"id\":159,\"name\":{\"full\":\"Другое\",\"short\":null},\"sort\":11,\"count\":5}]},{\"id\":106,\"name\":{\"full\":\"Спорт\",\"short\":null},\"sort\":4,\"count\":4,\"categories\":[{\"id\":117,\"name\":{\"full\":\"Бокс\",\"short\":null},\"sort\":0,\"count\":1},{\"id\":119,\"name\":{\"full\":\"Теннис\",\"short\":null},\"sort\":0,\"count\":1},{\"id\":148,\"name\":{\"full\":\"Другое\",\"short\":null},\"sort\":10,\"count\":2}]},{\"id\":107,\"name\":{\"full\":\"Выставки / музеи\",\"short\":\"Выставки\"},\"sort\":5,\"count\":1,\"categories\":[{\"id\":137,\"name\":{\"full\":\"Музеи\",\"short\":null},\"sort\":1,\"count\":1}]},{\"id\":108,\"name\":{\"full\":\"Для всей семьи\",\"short\":null},\"sort\":6,\"count\":27,\"categories\":[{\"id\":126,\"name\":{\"full\":\"Детские спектакли\",\"short\":null},\"sort\":0,\"count\":11},{\"id\":127,\"name\":{\"full\":\"Семейные мероприятия\",\"short\":null},\"sort\":0,\"count\":13},{\"id\":142,\"name\":{\"full\":\"Цирки / шоу\",\"short\":null},\"sort\":2,\"count\":2},{\"id\":144,\"name\":{\"full\":\"Представления для детей\",\"short\":null},\"sort\":4,\"count\":1}]}],\"id\":1}";
			
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



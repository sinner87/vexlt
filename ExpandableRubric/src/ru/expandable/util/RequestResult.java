package ru.expandable.util;

import ru.expandable.rubric.R;
import android.content.Context;
import loc.org.json.JSONArray;
import loc.org.json.JSONException;
import loc.org.json.JSONObject;

public class RequestResult {

	public enum Predefined {
		INVALID_CONNECTION,
		INVALID_SERVER_ANSEWER,
		UNDEFINED_ERROR
	}
	
	public enum ReqType {
		UNDEFINED,
		REQ_RUBRICS
	};
	
	ReqType type ;
	private String jsonrpc;
	private JSONArray result;
	private String errortext;
	private int id;

	public RequestResult ( JSONObject root, ReqType r ) throws JSONException {
		
		this.type = r;
		this.jsonrpc = root.getString("jsonrpc");
		this.result = root.getJSONArray("result");
		this.id = root.getInt("id");
	}

	protected RequestResult( ) {
		
		type = ReqType.UNDEFINED;
		result = null;
		jsonrpc = null;
		errortext = "";
		id = 0;
		
	}

	public ReqType getType() {
		return type;
	}
	
	public static RequestResult createPredefinedResult ( Predefined key, Context context  ) {
		
		RequestResult r = new RequestResult();
		
		if( key == Predefined.INVALID_CONNECTION ) {
			r.setErrortext(context.getString(R.string.invalid_connection));
			
		} else if ( key == Predefined.INVALID_SERVER_ANSEWER ) {
			r.setErrortext(context.getString(R.string.invalid_server_answer));
			
		} else if ( key == Predefined.UNDEFINED_ERROR ) {
			r.setErrortext(context.getString(R.string.undefined_error));
		}
		
		return r;
	}

	protected void setErrortext(String errortext) {
		this.errortext = errortext;
	}

	public String getJsonrpc() {
		return jsonrpc;
	}

	public JSONArray getResult() {
		return result;
	}

	public String getErrortext() {
		return errortext;
	}

	public int getId() {
		return id;
	}
}

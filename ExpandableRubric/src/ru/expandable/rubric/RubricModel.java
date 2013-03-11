package ru.expandable.rubric;

import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;


import loc.org.json.JSONArray;
import loc.org.json.JSONException;
import loc.org.json.JSONObject;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.AsyncTask;
import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IRequestCompleteObserver;
import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.rubric.units.RubricGroup;
import ru.expandable.util.GatewayUtil;
import ru.expandable.util.NetLog;
import ru.expandable.util.Parser;
import ru.expandable.util.RequestResult;
import ru.expandable.util.RequestResult.Predefined;

public class RubricModel implements IModel {

	private volatile static RubricModel uniqueInstance;
	private List<IRequestCompleteObserver> observers = new LinkedList<IRequestCompleteObserver>();
	private Context context;
	private AsyncTask<Void, Void, Void> uiTask;
	protected RequestResult lastRequestResult;
	protected RubricBlock lastRubricBlock;
	
	protected RubricModel(Context applicationContext) {
		this.context = applicationContext;
	}

	public static IModel instance(Context applicationContext) {
		
		if( uniqueInstance == null ) {
			synchronized ( RubricModel.class ) {
				if( uniqueInstance == null ) {
					uniqueInstance = new RubricModel(applicationContext);
				}
			}
		}
		
		return uniqueInstance;
	}

	public void registerReqCompleteObserver(IRequestCompleteObserver obs) {
		this.observers.add(obs);
		NetLog.i("register observer. Count:%d",observers.size());
		
	}

	public void removeReqCompleteObserver(IRequestCompleteObserver obs) {
		int i = observers.indexOf(obs);
		if( i >= 0 )
			observers.remove(i);
		else 
			NetLog.e("Try remove undefined observer");
		
		NetLog.i("remove observer. Count:%d",observers.size());
	}

	public void notifyReqCompleteObservers() {
		for (IRequestCompleteObserver cur : observers) {
			cur.requestComplete();
		}
		
	}

	@Override
	public RequestResult getLastRequestResult () {
		return lastRequestResult;
	}
	
	public void requestRubrics () {
		
		final GatewayUtil gateway = new GatewayUtil(context);

		uiTask = new AsyncTask<Void, Void, Void> () {
			
			private RequestResult lastRequestResult;
			private RubricBlock block = new RubricBlock();
			
			@Override
			protected Void doInBackground(Void... params) {
				
				try {
					
						lastRequestResult = gateway.requestRubricsTest();
						JSONArray groups = lastRequestResult.getResult();
						
						
						for ( int i = 0; i < groups.length(); ++i ) {
							
							JSONObject obj = groups.getJSONObject(i);
							RubricGroup g = new RubricGroup ( Parser.parseIRubric ( obj ) );
							
							
							JSONArray categories = obj.getJSONArray("categories");
							
							for ( int j = 0; j < categories.length(); ++j ) {
								
								JSONObject item = categories.getJSONObject(j);
								g.add ( Parser.parseIRubric ( item ) );
							}
							
							block.add(g);
						}
					
				} catch (JSONException e) {
					NetLog.e("%s", e.toString());
					e.printStackTrace();
					this.lastRequestResult = RequestResult.createPredefinedResult(Predefined.INVALID_SERVER_ANSEWER, RubricModel.this.context);
					
				} catch ( NetworkErrorException e ) {
					NetLog.e("%s", e.toString());
					e.printStackTrace();
					this.lastRequestResult = RequestResult.createPredefinedResult(Predefined.INVALID_CONNECTION, RubricModel.this.context);
					
				} catch ( Exception e ) {
					NetLog.e("%s", e.toString());
					e.printStackTrace();
					this.lastRequestResult = RequestResult.createPredefinedResult(Predefined.UNDEFINED_ERROR, RubricModel.this.context);
				}
				
				
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				RubricModel.this.lastRequestResult = this.lastRequestResult;
				RubricModel.this.lastRubricBlock = this.block;
				notifyReqCompleteObservers();
			}
		};
		
		uiTask.execute();
		
	}

	@Override
	public RubricBlock getRubrics() {
		return lastRubricBlock;
	}
}

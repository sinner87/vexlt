package ru.expandable.rubric;

import java.lang.Character.UnicodeBlock;
import java.util.LinkedList;
import java.util.List;


import android.content.Context;
import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IRequestCompleteObserver;
import ru.expandable.util.NetLog;

public class RubricModel implements IModel {

	private volatile static RubricModel uniqueInstance;
	private List<IRequestCompleteObserver> observers = new LinkedList<IRequestCompleteObserver>();
	private Context context;
	
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

}

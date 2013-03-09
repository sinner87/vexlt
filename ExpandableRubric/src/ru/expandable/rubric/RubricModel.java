package ru.expandable.rubric;

import java.lang.Character.UnicodeBlock;

import android.content.Context;
import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IRequestCompleteObserver;

public class RubricModel implements IModel {

	private volatile static RubricModel uniqueInstance;
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

	@Override
	public void registerReqCompleteObserver(IRequestCompleteObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeReqCompleteObserver(IRequestCompleteObserver obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyReqCompleteObservers() {
		// TODO Auto-generated method stub
		
	}

}

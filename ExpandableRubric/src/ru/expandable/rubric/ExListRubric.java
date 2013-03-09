package ru.expandable.rubric;

import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;
import android.app.Activity;
import android.os.Bundle;

public class ExListRubric extends Activity implements IPresenter {

	IModel model;
	IView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		model = RubricModel.instance(getApplicationContext());
		model.registerReqCompleteObserver(this);
		view = new RubricExListView ( this );
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		model.removeReqCompleteObserver(this);
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		view.onStart();
	}

	@Override
	public void requestComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activity getActivity() {
		return this;
	}

	@Override
	public void updateExpandableList() {
		// TODO Auto-generated method stub
		
	}

}

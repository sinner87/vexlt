package ru.expandable.rubric;

import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;
import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.rubric.units.RubricGroup;
import ru.expandable.rubric.units.RubricItem;
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


	@Override
	public RubricBlock getRubricBloc() {
		
		RubricBlock block = new RubricBlock();
		
		RubricGroup g = new RubricGroup(103,"Music","none",25,1);
		g.add( new RubricItem(39, "classic", "none", 3, 1));
		g.add( new RubricItem(38, "rock", "none", 20, 2));
		g.add( new RubricItem(37, "pop", "none", 2, 3));
		
		block.add ( g );
		
		RubricGroup g2 = new RubricGroup(104,"Theater","none",22,1);
		g2.add( new RubricItem(49, "comedy", "none", 15, 1));
		g2.add( new RubricItem(48, "tragedy", "none", 4, 2));
		g2.add( new RubricItem(47, "classic", "none", 3, 3));
		
		block.add ( g2 );
		
		
		return block;
	}

}

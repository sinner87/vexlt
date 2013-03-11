package ru.expandable.rubric;

import ru.expandable.interfaces.IModel;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;
import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.rubric.units.RubricGroup;
import ru.expandable.rubric.units.RubricItem;
import ru.expandable.util.RequestResult;
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
		
		view.stopWaitDialog();
		
		RequestResult r = model.getLastRequestResult();
		
		if( r.getResult() == null ) {
			
			view.printError(r.getErrortext());

		} else {
			view.updateExpandableList( model.getRubrics() );
		}

		
	}

	@Override
	public Activity getActivity() {
		return this;
	}


	@Override
	public void requestRubrics() {
		view.beginWaitDialog(getString(R.string.load_rubrics));
		model.requestRubrics();
	}

	private RubricBlock testRubricBlock() {
		RubricBlock block = new RubricBlock();
		
		RubricGroup g = new RubricGroup(103,"Music","none",27,99);
		g.add( new RubricItem(39, "classic", "none", 3, 1));
		g.add( new RubricItem(38, "rock", "none", 20, 2));
		g.add( new RubricItem(37, "pop", "none", 2, 3));
		g.add( new RubricItem(37, "metall", "none", 2, 4));
		
		block.add ( g );
		
		RubricGroup g2 = new RubricGroup(104,"Theater","none",22,98);
		g2.add( new RubricItem(49, "comedy", "none", 15, 3));
		g2.add( new RubricItem(48, "tragedy", "none", 4, 2));
		g2.add( new RubricItem(47, "classic", "none", 3, 1));
		
		block.add ( g2 );
		
		RubricGroup g3 = new RubricGroup(105,"Films","none",27,97);
		g3.add( new RubricItem(59, "horror", "none", 15, 5));
		g3.add( new RubricItem(58, "comedy", "none", 4, 1));
		g3.add( new RubricItem(57, "triller", "none", 2, 4));
		g3.add( new RubricItem(56, "documental", "none", 6, 3));
		
		block.add ( g3 );
		
		RubricGroup g4 = new RubricGroup(106,"Sport","none",45,96);
		g4.add( new RubricItem(69, "football", "none", 15, 1));
		g4.add( new RubricItem(68, "hockey", "none", 10, 2));
		g4.add( new RubricItem(67, "basketball", "none", 5, 3));
		g4.add( new RubricItem(66, "volleyball", "none", 5, 4));
		g4.add( new RubricItem(66, "biathlon", "none", 10, 5));
		
		block.add ( g4 );
		
		block.sort();
		return block;
	}


	

}

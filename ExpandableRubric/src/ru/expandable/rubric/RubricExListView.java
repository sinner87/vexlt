package ru.expandable.rubric;

import android.app.Activity;
import android.content.Context;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;

public class RubricExListView implements IView {

	private IPresenter presenter;
	private Activity activity;

	public RubricExListView(IPresenter presenter) {
		this.presenter = presenter;
		this.activity = ((Activity)presenter);
	}

	@Override
	public void printError(String errortext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beginWaitDialog(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopWaitDialog() {
		// TODO Auto-generated method stub

	}
/**
 *  <p> Стартует вьюшку <p>
 */
	@Override
	public void onStart() {
		
		activity.setContentView(R.layout.)
	}

}

package ru.expandable.rubric;

import android.app.Activity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;
import ru.expandable.util.NetLog;

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

	
	@Override
	public void onStart() {
		activity.setContentView(R.layout.exlist);
		NetLog.i("IView onStart");
		
		
		ImageButton back = (ImageButton) activity.findViewById(R.id.arrow);
		back.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});
		
		ExpandableListView ex_list = (ExpandableListView) activity.findViewById(R.id.ex_list);
		ex_list.setAdapter( new RubricAdapter (presenter.getRubricBloc(),activity) );
	}

}

package ru.expandable.rubric;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
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
		
		
		ImageButton back = (ImageButton) activity.findViewById(R.id.back_button);
		back.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.finish();
			}
		});
		
		ExpandableListView ex_list = (ExpandableListView) activity.findViewById(R.id.ex_list);
		// TODO: ex_list.setAdapter( );
	}

}

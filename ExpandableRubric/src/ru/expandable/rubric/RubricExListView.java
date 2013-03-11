package ru.expandable.rubric;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import ru.expandable.interfaces.IPresenter;
import ru.expandable.interfaces.IView;
import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.util.NetLog;

public class RubricExListView implements IView {

	private IPresenter presenter;
	private Activity activity;
	private ProgressDialog progressDialog = null;

	public RubricExListView(IPresenter presenter) {
		this.presenter = presenter;
		this.activity = ((Activity)presenter);
	}

	public void printError(String errortext) {
		NetLog.MsgBox(activity, activity.getString(R.string.attention),"%s",errortext);
	}

	public void beginWaitDialog(String message) {
		progressDialog  = ProgressDialog.show(activity, "", message);
		
	}

	public void stopWaitDialog() {
		if( progressDialog != null )
			progressDialog.dismiss();
		
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
		
		TextView title = (TextView) activity.findViewById(R.id.head_title);
		TextView sub_title = (TextView) activity.findViewById(R.id.head_sybtitle);
		
		Typeface tf_normal = null;
		Typeface tf_bold = null;
		
		try {	
			tf_normal = Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_condenced.ttf");
			tf_bold = Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_bold_condenced.ttf");
			title.setTypeface(tf_bold);
			sub_title.setTypeface(tf_normal);
			
		} catch ( Exception e ) {
			NetLog.e("%s", e);
			e.printStackTrace();
		}
		
		presenter.requestRubrics();
	}

	@Override
	public void updateExpandableList(RubricBlock rubrics) {
		
		ExpandableListView ex_list = (ExpandableListView) activity.findViewById(R.id.ex_list);
		ex_list.setAdapter( new RubricAdapter (rubrics,activity) );
		
	}

}

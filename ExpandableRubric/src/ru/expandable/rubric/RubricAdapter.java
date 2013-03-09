package ru.expandable.rubric;

import ru.expandable.interfaces.IRubric;
import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.util.NetLog;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class RubricAdapter extends BaseExpandableListAdapter {

	private class ViewHolder {
		
		public TextView title;
		public ImageView icon;
		public ImageView arrow;
		public TextView count;
	}
	
	
	private RubricBlock block;
	private Activity activity;
	
	public RubricAdapter ( RubricBlock block, Activity activity ) {
		this.block = block;
		this.activity = activity;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return block.getItem(groupPosition,childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return block.getItem(groupPosition,childPosition).getId();
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		View view = convertView;
		
		if( view == null ) {
			LayoutInflater inflater = activity.getLayoutInflater();
			view = inflater.inflate(R.layout.row_item, null);
			ViewHolder holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.item_text);
			holder.count = (TextView) view.findViewById(R.id.item_count);
			holder.arrow = (ImageView) view.findViewById(R.id.arrow);
			
			view.setTag(holder);
		}
		
		
		ViewHolder holder = (ViewHolder) view.getTag();
		final IRubric rubric = block.getGroup(groupPosition).get(childPosition);
		
		holder.title.setText(rubric.getFull_name());
		holder.count.setText(Integer.toString(rubric.getCount()));
		holder.arrow.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NetLog.Toast(activity.getApplicationContext(), "item(%d,%d) - %s [%d]",
						groupPosition,childPosition, rubric.getFull_name(), rubric.getId());
			}
		});
		
		
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return block.getGroup(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return block.getGroup(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return block.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return block.getGroup(groupPosition).getId();
	}

	@Override
	public View getGroupView(final int groupPosition, final boolean isExpanded,
			View convertView, final ViewGroup parent) {
		
		View view = convertView;
		
		if( view == null ) {
			LayoutInflater inflater = activity.getLayoutInflater();
			view = inflater.inflate(R.layout.row_group, null);
			ViewHolder holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.item_text);
			holder.count = (TextView) view.findViewById(R.id.item_count);
			holder.icon =  (ImageView) view.findViewById(R.id.group_icon);
			holder.arrow = (ImageView) view.findViewById(R.id.expand_arrow);
			
			view.setTag(holder);
		}
		
		
		ViewHolder holder = (ViewHolder) view.getTag();
		final IRubric rubric = block.getGroup(groupPosition);
		
		holder.title.setText(rubric.getFull_name());
		holder.count.setText(Integer.toString(rubric.getCount()));
		holder.arrow.setImageResource(isExpanded?R.drawable.navigation_expand:R.drawable.navigation_collapse);
		
		holder.arrow.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ExpandableListView list =  (ExpandableListView) parent;
				
				if( isExpanded ) 
					list.collapseGroup(groupPosition);
				else 
					list.expandGroup(groupPosition);
			}
		});
		
		
		switch (rubric.getId()) {
		case 103: holder.icon.setImageResource(R.drawable.r103); break;
		case 104: holder.icon.setImageResource(R.drawable.r104); break;
		case 105: holder.icon.setImageResource(R.drawable.r105); break;
		case 106: holder.icon.setImageResource(R.drawable.r106); break;
		case 107: holder.icon.setImageResource(R.drawable.r107); break;
		case 108: holder.icon.setImageResource(R.drawable.r108); break;
			
		default: holder.icon.setImageResource(R.drawable.collections_view_as_list); break;

		}
		
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

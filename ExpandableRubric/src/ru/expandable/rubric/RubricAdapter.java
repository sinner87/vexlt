package ru.expandable.rubric;

import ru.expandable.rubric.units.RubricBlock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class RubricAdapter extends BaseExpandableListAdapter {

	RubricBlock block;
	
	public RubricAdapter ( RubricBlock block ) {
		this.block = block;
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
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
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
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}

package ru.expandable.rubric.units;

import java.util.ArrayList;

import ru.expandable.interfaces.IRubric;

public class RubricBlock {

	ArrayList<RubricGroup> groups = new ArrayList<RubricGroup>();
	
	public IRubric getItem(int groupPosition, int childPosition) {
		return groups.get(groupPosition).get(childPosition);
	}


	public int size() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	public RubricGroup getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}


	public void add(RubricGroup g) {
		groups.add(g);
	}

}

package ru.expandable.rubric.units;

import java.util.ArrayList;
import java.util.Collections;

import ru.expandable.interfaces.IRubric;

public class RubricBlock {

	ArrayList<RubricGroup> groups = new ArrayList<RubricGroup>();
	
	public IRubric getItem(int groupPosition, int childPosition) {
		return groups.get(groupPosition).get(childPosition);
	}


	public int size() {
		return groups.size();
	}

	public RubricGroup get(int groupPosition) {
		return groups.get(groupPosition);
	}


	public void add(RubricGroup g) {
		groups.add(g);
	}
	
	public void sort () {
		
		Collections.sort(groups);
		
		for (RubricGroup r : groups) {
			r.sort();
		}
	}
}

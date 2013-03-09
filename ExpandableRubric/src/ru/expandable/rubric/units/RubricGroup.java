package ru.expandable.rubric.units;

import java.util.ArrayList;

import ru.expandable.interfaces.IRubric;

public class RubricGroup extends RubricItem {

	ArrayList<IRubric> items = new ArrayList<IRubric>();
	
	public RubricGroup(int id, String full_name, String short_name, int count,
			int sort) {
		super(id, full_name, short_name, count, sort);
		items = new ArrayList<IRubric>();
	}
	
	public RubricGroup(int id, String full_name, String short_name, int count,
			int sort, int capacity) {
		super(id, full_name, short_name, count, sort);
		new ArrayList<IRubric>(capacity);
	}
	
	public void add (IRubric r ) {
		items.add(r);
	}
	
	public IRubric get ( int item_pos ) {
		return items.get(item_pos);
	}

	public int size() {
		return items.size();
	}
	
}
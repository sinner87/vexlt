package ru.expandable.rubric.units;

import ru.expandable.interfaces.IRubric;



public class RubricItem implements Comparable<RubricItem>, IRubric {

	
	public RubricItem(int id, String full_name, String short_name, int count,
			int sort) {
		super();
		this.id = id;
		this.full_name = full_name;
		this.short_name = short_name;
		this.count = count;
		this.sort = sort;
	}
	
	private int id;
	private String full_name;
	private String short_name;
	private int count;
	private int sort;
	
	
	
	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public String getFull_name() {
		return full_name;
	}

	public String getShort_name() {
		return short_name;
	}
	@Override
	public int compareTo(RubricItem r) {
		return this.sort-r.sort;
	}
	
}

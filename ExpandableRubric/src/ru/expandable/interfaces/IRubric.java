package ru.expandable.interfaces;

import ru.expandable.rubric.units.RubricItem;

public interface IRubric extends Comparable<IRubric> {

	public abstract int getId();

	public abstract int getCount();

	public abstract String getFull_name();

	public abstract String getShort_name();

	public abstract int getSort();

}
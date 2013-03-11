package ru.expandable.interfaces;

import ru.expandable.rubric.units.RubricBlock;


public interface IView {

	void printError(final String errortext);

	void beginWaitDialog(final String string);

	void stopWaitDialog();

	void onStart();

	void updateExpandableList(RubricBlock rubrics);

}

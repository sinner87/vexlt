package ru.expandable.interfaces;

import ru.expandable.rubric.units.RubricBlock;
import android.app.Activity;

public interface IPresenter extends IRequestCompleteObserver {

	Activity getActivity();

	void updateExpandableList ();

	RubricBlock getRubricBloc();

}

package ru.expandable.interfaces;

import android.app.Activity;

public interface IPresenter extends IRequestCompleteObserver {

	Activity getActivity();

	void updateExpandableList ();

}

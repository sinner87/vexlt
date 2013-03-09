package ru.expandable.interfaces;


public interface IView {

	void printError(final String errortext);

	void beginWaitDialog(final String string);

	void stopWaitDialog();

	void onStart();

}

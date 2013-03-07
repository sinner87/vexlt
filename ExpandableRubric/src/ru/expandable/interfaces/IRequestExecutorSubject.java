package ru.expandable.interfaces;

public interface IRequestExecutorSubject {

	void registerReqCompleteObserver ( IRequestCompleteObserver obs );
	void removeReqCompleteObserver ( IRequestCompleteObserver obs );
	void notifyReqCompleteObservers ();
}

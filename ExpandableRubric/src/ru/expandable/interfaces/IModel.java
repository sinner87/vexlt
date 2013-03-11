package ru.expandable.interfaces;

import ru.expandable.rubric.units.RubricBlock;
import ru.expandable.util.RequestResult;


public interface IModel extends IRequestExecutorSubject {

	void requestRubrics();

	public abstract RequestResult getLastRequestResult();

	RubricBlock getRubrics();

}

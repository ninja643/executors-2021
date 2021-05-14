package rs.ac.ni.pmf.oop3.executors;

import lombok.Getter;

@Getter
public abstract class RunnableWithResult<T> implements Runnable
{
	private boolean finished;
	private T result;
	private Exception exception;

	@Override
	public void run()
	{
		finished = false;

		try
		{
			exception = null;
			result = doWork();
		}
		catch (final Exception e)
		{
			exception = e;
		}
		finally
		{
			finished = true;
		}
	}

	protected abstract T doWork() throws Exception;
}

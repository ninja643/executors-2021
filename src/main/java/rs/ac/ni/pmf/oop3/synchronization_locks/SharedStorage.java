package rs.ac.ni.pmf.oop3.synchronization_locks;

import java.util.concurrent.locks.*;

public class SharedStorage
{
	private String _message;
	private volatile boolean _empty = true;

	private final Lock _lock = new ReentrantLock();
	private final Condition bufferNotFull = _lock.newCondition();
	private final Condition bufferNotEmpty = _lock.newCondition();

	public void setMessage(final String message) throws InterruptedException
	{
		_lock.lock();
		try
		{
			while (!_empty)
			{
				System.out.println(Thread.currentThread().getName() + ": Wait to produce message...");
				bufferNotEmpty.await();
			}

			_message = message;
			_empty = false;
			bufferNotFull.signalAll();
		}
		finally
		{
			_lock.unlock();
		}
	}

	public String getMessage() throws InterruptedException
	{
		_lock.lock();
		try
		{
			while (_empty)
			{
				bufferNotFull.await();
			}

			bufferNotEmpty.signalAll();
			_empty = true;
			return _message;
		}
		finally
		{
			_lock.unlock();
		}
	}
}

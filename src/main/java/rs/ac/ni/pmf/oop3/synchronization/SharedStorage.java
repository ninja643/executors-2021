package rs.ac.ni.pmf.oop3.synchronization;

public class SharedStorage
{
	private String _message;
	private volatile boolean _empty = true;

	public synchronized String getMessage()
	{
		while (_empty)
		{
			System.out.println(Thread.currentThread().getName() + ": Wait for message to be generated...");
			// Sacekaj da poruka stigne
			try
			{
				wait();
				System.out.println(Thread.currentThread().getName() + ": wait finished, trying to consume the message...");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName() + ": Getting the message");
		_empty = true;
		notifyAll();
		return _message;
	}

	public synchronized void setMessage(final String message)
	{
		while (!_empty)
		{
			System.out.println(Thread.currentThread().getName() + ": Wait to produce message...");
			// Sacekaj da poruka ode na obradu
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName() + ": Setting the new message");
		_empty = false;
		notifyAll();
		_message = message;
	}
}

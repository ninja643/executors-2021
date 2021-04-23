package rs.ac.ni.pmf.oop3.synchronization;

import lombok.RequiredArgsConstructor;

public class Consumer extends Thread
{
	private final SharedStorage _sharedStorage;

	public Consumer(String name, SharedStorage sharedStorage)
	{
		super(name);
		_sharedStorage = sharedStorage;
	}

	@Override
	public void run()
	{
		final String message = _sharedStorage.getMessage();

		System.out.println(Thread.currentThread().getName() + ": " + message);
	}
}

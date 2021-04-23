package rs.ac.ni.pmf.oop3.synchronization;

import lombok.RequiredArgsConstructor;

public class Producer extends Thread
{
	private final SharedStorage _sharedStorage;

	public Producer(final String name, final SharedStorage sharedStorage)
	{
		super(name);
		_sharedStorage = sharedStorage;
	}

	@Override
	public void run()
	{
		_sharedStorage.setMessage("Hello");
	}
}

package rs.ac.ni.pmf.oop3.synchronization;

import java.util.concurrent.CountDownLatch;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Service implements Runnable
{
	private final String _serviceName;
	private final long _timeout;
	private final CountDownLatch _latch;

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(_timeout);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.printf("Service '%s' is up and running\n", _serviceName);
		_latch.countDown();
	}
}

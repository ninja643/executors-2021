package rs.ac.ni.pmf.oop3.synchronization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo
{

	public static final int MAX_SERVICES = 3;

	public static void main(String[] args) throws InterruptedException
	{
		final CountDownLatch latch = new CountDownLatch(MAX_SERVICES);

		final Thread cacheService = new Thread(new Service("Cache Service", 2000, latch));
		final Thread translateService = new Thread(new Service("Translate Service", 1500, latch));
		final Thread systemService = new Thread(new Service("System Service", 3000, latch));

		cacheService.start();
		translateService.start();
		systemService.start();

		//		latch.await();
		//		System.out.println("All services are up and running");

		while (latch.getCount() > 0)
		{
			latch.await(1, TimeUnit.SECONDS);
			System.out.printf("Running services: [%d / %d]\n", MAX_SERVICES - latch.getCount(), MAX_SERVICES);
		}

		System.out.println("All services are up and running");
	}
}

package rs.ac.ni.pmf.oop3.executors;

import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorsDemo
{
	public static void main(String[] args)
	{
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		log.info("Scheduling the job");
		//		executorService.schedule(new ScheduledWorker(), 2, TimeUnit.SECONDS);
//		executorService.scheduleAtFixedRate(new ScheduledWorker(), 2, 3, TimeUnit.SECONDS);
		executorService.scheduleWithFixedDelay(new ScheduledWorker(), 0, 3, TimeUnit.SECONDS);

//		executorService.shutdown();
	}

	private static class ScheduledWorker implements Runnable
	{

		@Override
		public void run()
		{
			log.info("Starting job");
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			log.info("Job finished");
		}
	}

	public static void main1(String[] args) throws InterruptedException
	{
		//		final ExecutorService executorService = Executors.newSingleThreadExecutor();
		int n = Runtime.getRuntime().availableProcessors();
		log.info("Available processors: {}", n);

		//		final ExecutorService executorService = Executors.newFixedThreadPool(20000);
		final ExecutorService executorService = Executors.newCachedThreadPool();

		log.info("Starting jobs");

		for (int i = 0; i < 1000; ++i)
		{
			executorService.submit(new Worker("Worker-" + i, i * 500));

			if (i % 100 == 0)
			{
				Thread.sleep(10);
			}
		}

		//		System.out.printf("[%s] Hello main thread!!!\n", Thread.currentThread().getName());

		executorService.shutdown();

		//		while (executorService.awaitTermination(1, TimeUnit.SECONDS))
		//		{
		//		}

		log.info("Task finished");
	}

	private static class Worker implements Runnable
	{
		private String _taskName;
		private final long _timeout;

		public Worker(String taskName, long timeout)
		{
			_taskName = taskName;
			_timeout = timeout;
		}

		@Override
		public void run()
		{
			double sum = 0.0f;

			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					sum += Math.sqrt(i * j);
				}
			}

			log.info("[{}, {}] - Result: {}", Thread.currentThread().getName(), _taskName, sum);
		}
	}
}

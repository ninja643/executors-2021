package rs.ac.ni.pmf.oop3.executors;

import java.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorsDemo2
{
	public static void main(String[] args)
	{
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

		final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		final ExecutorService callbackHandlerService = MoreExecutors.getExitingExecutorService(threadPoolExecutor);

		final ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new IntegerCallable());

		Futures.addCallback(listenableFuture, new FutureCallback<Integer>()
		{
			@Override
			public void onSuccess(@Nullable Integer result)
			{
				log.info("Result: {}", result);
			}

			@Override
			public void onFailure(Throwable t)
			{
				log.error("Error: {}", t.getMessage());
			}
		}, callbackHandlerService);

		listeningExecutorService.shutdown();
		//		callbackHandlerService.shutdown(); -- Ne valja!
	}

	//	private static final int MAX_RETRIES = 3;
	public static void mainCallableWithExecutorService(String[] args)
	{
		final ExecutorService executorService = Executors.newCachedThreadPool();
		final Future<Integer> future = executorService.submit(new IntegerCallable());

		//		int retries = 0;

		while (!future.isDone())
		{
			//			retries++;

			try
			{
				//				if (retries > MAX_RETRIES)
				//				{
				//					future.cancel(true);
				//				}

				final Integer result = future.get(200, TimeUnit.MILLISECONDS);
				System.out.println("Result: " + result);
			}
			catch (CancellationException | InterruptedException e)
			{
				System.out.println("Will not wait anymore...");
			}
			catch (ExecutionException e)
			{
				System.out.println("Error: " + e.getCause().getMessage());
			}
			catch (TimeoutException e)
			{
				log.debug("Still waiting for task to finish...");
			}
		}

		executorService.shutdown();
	}

	public static void mainRunnableWithResult(String[] args) throws InterruptedException
	{
		final RunnableWithIntegerResult task = new RunnableWithIntegerResult();

		final Thread t = new Thread(task);
		t.start();

		while (!task.isFinished())
		{
			System.out.println("Checking if task is finished...");
			t.join(200);
		}

		System.out.println("Task finished");
		if (task.getException() != null)
		{
			System.out.println("There was an error: " + task.getException().getMessage());
		}
		else
		{
			System.out.println("Result: " + task.getResult());
		}
	}
}

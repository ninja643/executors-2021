package rs.ac.ni.pmf.oop3.completable_futures;

import java.util.concurrent.*;

public class CompletableFutureDemo
{
	private static void sleep(long timeout)
	{
		try
		{
			Thread.sleep(timeout);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static int getValue()
	{
		System.out.println(Thread.currentThread() + " Getting the value...");
		sleep(1000);
		return 2;
	}

	public static String process(Integer value)
	{
		System.out.println("Processing the value " + value);
		sleep(1000);
		return "V: " + value;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException
	{
//		ExecutorService service = Executors.newFixedThreadPool(3);
//		Future<Integer> future = service.submit(CompletableFutureDemo::getValue);
//
//		int v = future.get();
//
//		System.out.println("Value: " + v);
//		service.shutdown();

		CompletableFuture
			.supplyAsync(CompletableFutureDemo::getValue)
			.thenApply(CompletableFutureDemo::process)
			.thenAccept(System.out::println);

//		System.out.println("Done");
		sleep(2500);
	}
}

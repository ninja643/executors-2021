package rs.ac.ni.pmf.oop3.streams;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.*;

public class ParallelStreamsDemo
{
	private static boolean test(int x)
	{
		System.out.println("test - " + x + " - " + Thread.currentThread());
		return x % 2 == 0;
	}

	private static int transform(int x)
	{
		System.out.println("transform - " + x + " - " + Thread.currentThread());
		sleep(300);
		return x * 2;
	}

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

	private static int compute(Stream<Integer> stream)
	{
		return stream
			.parallel()
			.filter(ParallelStreamsDemo::test)
			.mapToInt(ParallelStreamsDemo::transform)
			.sum();
	}

	private static boolean test4(int x)
	{
		System.out.println("test - " + x + " - " + Thread.currentThread());
		sleep(1000);
		return x % 4 == 0;
	}

	private static int accumulate(int total, int v)
	{
		System.out.println("Accumulating " + v + " on thread " + Thread.currentThread() + ", current total: " + total);
		return total + v;
	}

	public static void main(String[] args) throws InterruptedException
	{
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

//		final Set<Integer> set = new HashSet<>(numbers);

//		int sum = compute(numbers.stream());
//		System.out.println(sum);

//		numbers.parallelStream()
//			.map(ParallelStreamsDemo::transform)
//			.forEach(System.out::println)
//			.forEachOrdered(System.out::println);
//		;

//		set.stream()
//			.map(ParallelStreamsDemo::transform)
//			.forEach(System.out::println);

//		int value = numbers.parallelStream()
//			.filter(ParallelStreamsDemo::test4)
//			.findAny()
//			.get();
//
//		System.out.println(value);

		int neutral = 0;

//		int sum = numbers.stream()
//			.reduce(neutral, ParallelStreamsDemo::accumulate);

		/*
			stream --->  1  --->   2  --->    3  --->  ...  --->  10
					     |         |
		  acc neutral:0  op(0, 1)  op(acc, 2) ...
		 */
//		System.out.println(sum);

//		int parallelSum = numbers.parallelStream()
//			.reduce(neutral, ParallelStreamsDemo::accumulate);
//
//		System.out.println(parallelSum);

//		System.out.println(numbers.parallelStream()
//			.map(String::valueOf)
//			.reduce("_", (total, v) -> total + v)
//		);

		process(numbers.stream().map(ParallelStreamsDemo::transform));

	}

	private static void process(Stream<Integer> stream) throws InterruptedException
	{
		final ForkJoinPool pool = new ForkJoinPool();

		pool.submit(() -> stream.forEach(e -> {}));
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.SECONDS);
	}
}

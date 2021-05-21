package rs.ac.ni.pmf.oop3.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo
{
	public static void main(String[] args)
	{
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

/*		System.out.println("Iterating using for loop");
		for (int v : numbers)
		{
			System.out.println("Checking element: " + v);
			if (v % 2 == 0)
			{
				String r = "Number: " + v;
//				System.out.println(r);
			}
		}*/

/*		System.out.println("\n\n\nUsing stream");
		final Stream<String> numbersPipeline = numbers.stream()
			.peek(v -> System.out.println("Checking element: " + v))
			.filter(v -> v % 2 == 0)
			.map(v -> "Number: " + v);

		print(numbersPipeline);*/
/*

		numbers.stream()
			.peek(v -> {
				v = v + 1000;
				System.out.println("Value in peek after increase: " + v);
			})
			.forEach(System.out::println);
*/
/*

		final Integer min = numbers.stream()
			.min(Integer::compareTo)
			.orElse(-1);
		System.out.println("Min: " + min);

		final Integer max = numbers.stream()
			.max(Integer::compareTo)
			.orElse(-1);
		System.out.println("Max: " + max);
*/
/*
		boolean b = numbers.stream()
			.peek(System.out::println)
			.anyMatch(v -> v > 5);

		System.out.println(b);

		b = numbers.stream()
			.peek(System.out::println)
			.allMatch(v -> v < 4);

		System.out.println(b);
		*/
/*

		long cnt = IntStream.range(1, 10000)
			.filter(v -> v % 3 == 0)
			.filter(v -> v % 7 != 0)
			.count();

		long sum = IntStream.range(1, 10000)
			.filter(v -> v % 3 == 0)
			.filter(v -> v % 7 != 0)
			.sum();

		System.out.println(cnt);
		System.out.println(sum);
*/
/*

		numbers.stream()
			.limit(3)
			.forEach(System.out::println);
*/

		List<Integer> doubleList = new ArrayList<>();

		for (int v : numbers)
		{
			final int value = v * 2;
			doubleList.add(value);
			doubleList.add(value + 1);
		}
		print(doubleList.stream());

		System.out.println("\n\n==============================================\n\n");

/*
	numbers ---> 1 ---> 2 ---> ... ---> 10
	.map 		 |      |               |
	             2		4				20
	.collect     |      |               |
				{2,4,...,20}
 */

/*
	numbers ---> 1 ---> 2 ---> ... ---> 10
	.map 		 |      |               |
	             2,3	4,5				20,21
	.collect     |      |               |
				{2,3,4,...,20,21}
 */

/*
	numbers ---> 1 ---> 	2 ---> ... ---> 	10
	.flatmap 		 |      |               |
				{2,3}		{4,5}			{20,21}
	             2--->3--->	4--->5--->	--->20--->21
	.collect     |      |               |
				{2,3,4,...,20,21}
 */

		List<Integer> doubleList2 = numbers.stream()
//			.map(v -> Arrays.asList(2 * v, 2 * v + 1))
//			.flatMap(v -> Arrays.asList(2 * v, 2 * v + 1).stream())
			.flatMap(v -> Stream.of(2 * v, 2 * v + 1))
			.collect(Collectors.toList());
		print(doubleList2.stream());
	}

	private static <T> void print(final Stream<T> stream)
	{
		stream
			.forEach(System.out::println);
	}
}

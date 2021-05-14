package rs.ac.ni.pmf.oop3.lambda;

public class LambdaDemo2
{
	@FunctionalInterface
	public interface SingleParameterOperation
	{
		int apply(int a);
	}

	public static void main(String[] args)
	{
		final IntOperation plus = (int a, int b) -> a + b;
		final IntOperation minus = (a, b) -> a - b;
		final SingleParameterOperation negative = a -> -a;
		final SingleParameterOperation square = a -> a * a;

		System.out.println(plus.calculate(5, 3));
		System.out.println(minus.calculate(5, 3));
		System.out.println(negative.apply(10));
		System.out.println(square.apply(10));
	}
}

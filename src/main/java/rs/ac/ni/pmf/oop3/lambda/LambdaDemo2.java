package rs.ac.ni.pmf.oop3.lambda;

import java.util.function.*;

public class LambdaDemo2
{
	@FunctionalInterface
	public interface SingleParameterOperation
	{
		int apply(int a);
	}

	private static void useInt(Supplier<Integer> supplier)
	{
		System.out.println(supplier.get());
	}

	private static void printFiltered(Predicate<Integer> filter, Integer value)
	{
		if (filter.test(value))
		{
			System.out.println(value);
		}
	}

	private static void printTransformation(Student student, Function<Student, String> mapper)
	{
		System.out.println(mapper.apply(student));
	}

	private static void consume(Student student, Consumer<Student> consumer)
	{
		System.out.println("Consuming student: " + student.getFirstName());
		consumer.accept(student);
	}

	public static void main(String[] args)
	{
		// Supplier<T>
		// T get() 			- factory metod
		useInt((() -> 1));

		// Predicate<T>
		// boolean test(T value) - testiranje/filtriranje vrednosti

//		boolean useEven = false;
//
//		final Predicate<Integer> evenFilter = v -> v % 2 == 0;
//		final Predicate<Integer> oddFilter = v -> v % 2 == 1;
//
//		final Predicate<Integer> filter = useEven ? evenFilter : oddFilter;
//
//		printFiltered(filter, 2);
//		printFiltered(filter, 3);
//		printFiltered(filter, 4);
//		printFiltered(filter, 5);

		// Function<T, R>			- transformacije/map
		// R apply(T t)
		final Student student = Student.builder().firstName("Marko").lastName("Milosevic").age(20).build();
//
		printTransformation(student, s -> String.valueOf(s.getAge()));
		printTransformation(student, Student::toString);
		printTransformation(student, Student::getFirstName);
		printTransformation(student, Student::getLastName);
		printTransformation(student, s -> s.getFirstName() + " " + s.getLastName());
		printTransformation(student, s ->  s.getLastName()+ " " + s.getFirstName());

		// Consumer<T>
		// void accept(T t)
		consume(student, s -> System.out.println(s.getFirstName()));
		consume(student, LambdaDemo2::increaseAgeAndPrint);

		final LambdaDemo2 instance1 = new LambdaDemo2("Pera");
		final LambdaDemo2 instance2 = new LambdaDemo2("Mika");

		consume(instance1.getStudent(), instance1::printStudent);
		consume(instance2.getStudent(), instance2::printStudent);
	}

	private final Student _student;

	public Student getStudent()
	{
		return _student;
	}

	public LambdaDemo2(final String name)
	{
		_student = Student.builder().firstName(name).lastName(name).age(20).build();
	}

	public void printStudent(Student s)
	{
		System.out.println(s.getLastName());
	}

	public static void increaseAgeAndPrint(Student s)
	{
		int age = s.getAge();
		System.out.println(age + 1);
	}

	public static void main1(String[] args)
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

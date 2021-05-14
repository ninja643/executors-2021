package rs.ac.ni.pmf.oop3.lambda;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaDemo
{
	public static void main(String[] args)
	{
		/*	Delovi funkcije
				1. Ime funkcije - nebitno
				2. Lista parametara - bitno
				3. Telo funkcije - bitno
				4. Tip rezultata - može biti implictno određen
			 */

		Thread t;
		t = new Thread(LambdaDemo::doWork);

//		t = new Thread(() -> System.out.println("Hello from runnable!"));

		t.start();
	}

	private static void doWork()
	{
		final Random r = new Random();
		int sum = 0;

		for (int i = 0; i < 10; ++i)
		{
			try
			{
				TimeUnit.MILLISECONDS.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			int value = r.nextInt(10);
			log.info("Generated value: {}", value);

			if (value % 5 == 0)
			{
				throw new ArithmeticException("Got a wrong number!");
			}

			sum += value;
		}

		System.out.println("Sum: " + sum);
	}
}

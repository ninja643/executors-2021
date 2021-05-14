package rs.ac.ni.pmf.oop3.executors;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableWithIntegerResult extends RunnableWithResult<Integer>
{
	@Override
	protected Integer doWork() throws Exception
	{
		final Random r = new Random();
		int sum = 0;

		for (int i = 0; i < 10; ++i)
		{
			TimeUnit.MILLISECONDS.sleep(100);

			int value = r.nextInt(10);
			log.info("Generated value: {}", value);

			if (value % 5 == 0)
			{
				throw new ArithmeticException("Got a wrong number!");
			}

			sum += value;
		}

		return sum;
	}
}

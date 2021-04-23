package rs.ac.ni.pmf.oop3.immutable;

public class MutableColorProblem
{
	public static void main(String[] args) throws InterruptedException
	{
		final SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");

		final Thread t1 = new Thread(new ColorPrinter(color));
		final Thread t2 = new Thread(new ColorInverter(color));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private static class ColorPrinter implements Runnable
	{
		private final SynchronizedRGB _color;

		public ColorPrinter(SynchronizedRGB color)
		{
			_color = color;
		}

		@Override
		public void run()
		{
			final String colorName;
			final int rgb;

			synchronized (_color)
			{
				colorName = _color.getName();

				sleep(1000);

				rgb = _color.getRGB();
			}

			System.out.printf("%s: %d\n", colorName, rgb);
		}
	}

	private static class ColorInverter implements Runnable
	{
		private final SynchronizedRGB _color;

		public ColorInverter(SynchronizedRGB color)
		{
			_color = color;
		}

		@Override
		public void run()
		{
			sleep(200);

			final String colorName;
			final int rgb;

			synchronized (_color)
			{
				_color.invert();

				colorName = _color.getName();
				rgb = _color.getRGB();
			}

			System.out.printf("%s: %d\n", colorName, rgb);
		}
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
}

package rs.ac.ni.pmf.oop3.immutable;

public class ImmutableColorDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		final ImmutableRGB color = ImmutableRGB.builder()
			.red(0)
			.green(0)
			.blue(0)
			.name("Pitch Black")
			.build();

			//new ImmutableRGB(0, 0, 0, "Pitch Black");

		final Thread t1 = new Thread(new ColorPrinter(color));
		final Thread t2 = new Thread(new ColorInverter(color));

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	private static class ColorPrinter implements Runnable
	{
		private final ImmutableRGB _color;

		public ColorPrinter(ImmutableRGB color)
		{
			_color = color;
		}

		@Override
		public void run()
		{
			final String colorName = _color.getName();

			sleep(1000);

			final int rgb = _color.getRGB();

			System.out.printf("%s: %d\n", colorName, rgb);
		}
	}

	private static class ColorInverter implements Runnable
	{
		private final ImmutableRGB _color;

		public ColorInverter(ImmutableRGB color)
		{
			_color = color;
		}

		@Override
		public void run()
		{
			sleep(200);

			final ImmutableRGB invertedColor = _color.invert();

			final String colorName = invertedColor.getName();
			final int rgb = invertedColor.getRGB();

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

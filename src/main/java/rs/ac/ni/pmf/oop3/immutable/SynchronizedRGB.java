package rs.ac.ni.pmf.oop3.immutable;

public class SynchronizedRGB
{
	private int _red;
	private int _green;
	private int _blue;
	private String _name;

	public SynchronizedRGB(int red, int green, int blue, String name)
	{
		check(red, green, blue);
		_red = red;
		_green = green;
		_blue = blue;
		_name = name;
	}

	public synchronized String getName()
	{
		return _name;
	}

	public synchronized int getRGB()
	{
		return (_red << 16) | (_green << 8) | _blue;
	}

	public synchronized void set(int red, int green, int blue, String name)
	{
		check(red, green, blue);
		_red = red;
		_green = green;
		_blue = blue;
		_name = name;
	}

	private void check(int red, int green, int blue)
	{
		if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255)
		{
			throw new IllegalArgumentException();
		}
	}

	public synchronized void invert()
	{
		_red = 255 - _red;
		_green = 255 - _green;
		_blue = 255 - _blue;
		_name = "Inverse of " + _name;
	}
}

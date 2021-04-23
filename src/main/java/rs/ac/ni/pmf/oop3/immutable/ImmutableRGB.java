package rs.ac.ni.pmf.oop3.immutable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ImmutableRGB
{
	int red;
	int green;
	int blue;
	String name;

	private ImmutableRGB(int red, int green, int blue, String name)
	{
		check(red, green, blue);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.name = name;
	}

	public int getRGB()
	{
		return (red << 16) | (green << 8) | blue;
	}

	private void check(int red, int green, int blue)
	{
		if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255)
		{
			throw new IllegalArgumentException();
		}
	}

	public ImmutableRGB invert()
	{
		return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name);
	}
}

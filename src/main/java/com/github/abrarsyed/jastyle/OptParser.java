package com.github.abrarsyed.jastyle;

import com.github.abrarsyed.jastyle.constants.FormatStyle;

public class OptParser
{
	public final ASFormatter	formatter;

	/**
	 * @param formatter The formatter to which the parsed options will apply.
	 */
	public OptParser(ASFormatter formatter)
	{
		this.formatter = formatter;
	}

	/**
	 * @param opt This string is assumed to be starting with one or two hyphens.
	 */
	public boolean parseOption(String opt)
	{
		// long option
		if (opt.startsWith("--"))
		{
			return parseLongOption(opt.replaceFirst("[-]{2}", ""));
		}
		// only 1? short option
		else if (opt.startsWith("-"))
		{
			return parseShortOption(opt.replaceFirst("[-]", ""));
		}
		// must be a long-option from the file.
		// if its a file path from the command line, itl be handled outside here, after this returns false.
		else
		{
			return parseLongOption(opt);
		}
	}

	private boolean parseLongOption(String opt)
	{
		if (opt.startsWith("--"))
			throw new IllegalArgumentException("Trying to parse long option " + opt + " while it still cotnains a -");

		// yay for delegating stuf to different methods.
		if (opt.startsWith("style="))
		{
			return parseStyleOption(opt.replaceFirst("style=", ""), true);
		}

		return false;
	}

	/**
	 * This method is used to parse short options. Short options should be no less than 1 and no more than 5 characters long.
	 * @param opt Should not start with a hyphen.
	 * @return
	 */
	private boolean parseShortOption(String opt)
	{
		if (opt.startsWith("-"))
			throw new IllegalArgumentException("Trying to parse short option " + opt + " while it still cotnains a -");

		if (opt.startsWith("A"))
		{
			return parseStyleOption(opt.replaceFirst("A", ""), false);
		}

		return false;
	}

	/**
	 * Parses the style options.
	 * @param opt
	 * @param isLong
	 * @return
	 */
	private boolean parseStyleOption(String opt, boolean isLong)
	{
		// long type of option
		if (isLong)
		{
			// should be removed by now.
			if (opt.startsWith("style="))
				throw new IllegalArgumentException();

			String val = opt.toUpperCase();
			val = val.replace("&", "and");
			val = val.replace("/", "and");

			try
			{
				FormatStyle style = FormatStyle.valueOf(val);
				formatter.setFormattingStyle(style);
			}
			catch (Exception e)
			{
				// no possible style. fail.
				return false;
			}
		}
		// short type of option.
		else
		{
			// should be removed by now.
			if (opt.startsWith("A"))
				throw new IllegalArgumentException();

			try
			{
				int num = Integer.parseInt(opt);
				FormatStyle style = FormatStyle.values()[num];
				formatter.setFormattingStyle(style);
			}
			catch (Exception e)
			{
				// no possible style. fail.
				return false;
			}
		}

		return true;
	}
}

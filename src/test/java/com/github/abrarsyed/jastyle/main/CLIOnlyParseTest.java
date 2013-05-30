package com.github.abrarsyed.jastyle.main;

import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;

import com.github.abrarsyed.jastyle.Main;
import com.github.abrarsyed.jastyle.NoExitTestCase;

/**
 * @author AbrarSyed
 *         This class is designed to test the parse options method in the Main class only.
 *         This is not designed for the full OptParser
 */
public class CLIOnlyParseTest extends NoExitTestCase
{
	private File	notOptions;
	private File	options;

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();

		notOptions = new File("nothing.options");
		if (notOptions.exists())
			notOptions.delete();

		options = new File("here.options");
		options.createNewFile();
	}

	public void testOptionsSearch()
	{
		ArrayList<String> opts = new ArrayList<String>();

		// test with nonexistant file.
		opts.add("--options=" + notOptions.getPath());
		Main.parseConsoleOptions(opts);
		Assert.assertTrue(Main.errors.contains("the file " + notOptions.getPath() + " could not be found."));

		// clear for next test
		Main.errors.clear();
		opts.clear();

		// do next test
		opts.add("--options=" + options.getPath());
		Main.parseConsoleOptions(opts);
		Assert.assertTrue(Main.errors.isEmpty());
	}

	@Override
	protected void tearDown() throws Exception
	{
		options.delete();
		super.tearDown();
	}

	public void testVersion()
	{
		ArrayList<String> opts = new ArrayList<String>();
		opts.add("-V");
		try
		{
			Main.parseConsoleOptions(opts);
			Assert.fail("If the test gets here. The version option did not kill the instance");
		}
		catch (ExitException e)
		{
			Assert.assertEquals(0, e.status);
		}
	}

	public void testHelp()
	{
		ArrayList<String> opts = new ArrayList<String>();
		opts.add("-h");
		try
		{
			Main.parseConsoleOptions(opts);
			Assert.fail("If the test gets here. The help option did not kill the instance");
		}
		catch (ExitException e)
		{
			Assert.assertEquals(0, e.status);
		}
	}
}

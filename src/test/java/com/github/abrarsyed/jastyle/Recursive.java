package com.github.abrarsyed.jastyle;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Recursive
{
	private final File root = new File("temp\\recurseTest");
	private int generated = 0, tmpFiles = 0, recurseFiles = 0;
	
	private final String EXT_TMP = ".tmp";
	private final String EXT_RECURSE = ".recursed";

	@Before
	public void setupFiles()
	{
		try
		{
			root.mkdirs();
			
			for (int i = 0; i < 3; i++)
			{
				File.createTempFile("test", EXT_TMP, root);
				generated++;
				tmpFiles++;
			}
			
			File dir = root;
			for (int i = 0; i < 5; i++)
			{
				dir = new File(dir, "folder");
				dir.mkdirs();
			
				for (int j = 0; j < 3; j++)
				{
					File.createTempFile("test", EXT_RECURSE, dir);
					generated++;
					recurseFiles++;
				}
			}
		}
		catch (IOException e)
		{
			Assert.fail("Error generating files");
		}
	}

	@Test
	public void testMultiply()
	{
		ArrayList<File> list = Main.collectFiles(root, null, true);
		Assert.assertEquals(generated, list.size());
		
		list = Main.collectFiles(root, new Filter(EXT_TMP), true);
		Assert.assertEquals(tmpFiles, list.size());
		
		list = Main.collectFiles(root, null, false);
		Assert.assertEquals(tmpFiles, list.size());
		
		list = Main.collectFiles(root, new Filter(EXT_RECURSE), true);
		Assert.assertEquals(recurseFiles, list.size());
	}

	@After
	public void removeFiles()
	{
		deleteRecursive(root);
	}
	
	private void deleteRecursive(File file)
	{
		if (file.isFile())
			file.delete();
		else
		{
			for (File temp : file.listFiles())
			{
				deleteRecursive(temp);
			}
			
			// delete now empty directory
			file.delete();
		}
	}
	
	private class Filter implements FilenameFilter
	{
		private final String extension;
		
		public Filter(String extension)
		{
			super();
			this.extension = extension;
		}
		
		@Override
		public boolean accept(File dir, String name)
		{
			return name.endsWith(extension);
		}
	}
}

package com.github.abrarsyed.jastyle;

import java.util.Random;

public final class TestUtil
{
	public static final Random	RANDOM	= new Random();
	
	private static String letters = "abcdefghijklmnopqrstuvwxyz"; 

	public static String getRandomWord(int length)
	{
		StringBuilder builder = new StringBuilder(length);
		int num;
		
		while (length > 0)
		{
			num = RANDOM.nextInt(letters.length() - 1);
			builder.append(letters.charAt(num));
		}
		
		return builder.toString();
	}
	
	public static String getRandomWordWithChars(int length, String extraChars)
	{
		StringBuilder builder = new StringBuilder(length);
		String possible = letters+extraChars;
		int num;
		
		while (length > 0)
		{
			num = RANDOM.nextInt(possible.length() - 1);
			builder.append(possible.charAt(num));
		}
		
		return builder.toString();
	}

}

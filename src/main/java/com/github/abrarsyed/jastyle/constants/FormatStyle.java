package com.github.abrarsyed.jastyle.constants;

import com.github.abrarsyed.jastyle.ASFormatter;
import com.github.abrarsyed.jastyle.ASFormatter.BracketMode;

public enum FormatStyle
{
	NONE,
	ALLMAN,
	JAVA,
	KandR,
	STROUSTRUP,
	WHITESMITH,
	BANNER,
	GNU,
	LINUX;
	
	@SuppressWarnings("incomplete-switch")
	public void apply(ASFormatter formatter)
	{
		switch (this)
		{
			case ALLMAN:
				formatter.setBracketFormatMode(BracketMode.BREAK_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(false);
				break;

			case JAVA:
				formatter.setBracketFormatMode(BracketMode.ATTACH_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(false);
				break;

			case KandR:
				formatter.setBracketFormatMode(BracketMode.LINUX_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(false);
				break;

			case STROUSTRUP:
				formatter.setBracketFormatMode(BracketMode.STROUSTRUP_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(false);
				break;

			case WHITESMITH:
				formatter.setBracketFormatMode(BracketMode.BREAK_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(true);
				formatter.setClassIndent(true);
				formatter.setSwitchIndent(true);
				break;

			case BANNER:
				formatter.setBracketFormatMode(BracketMode.ATTACH_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(true);
				formatter.setClassIndent(true);
				formatter.setSwitchIndent(true);
				break;

			case GNU:
				formatter.setBracketFormatMode(BracketMode.BREAK_MODE);
				formatter.setBlockIndent(true);
				formatter.setBracketIndent(false);
				formatter.setSpaceIndentation(2);
				break;

			case LINUX:
				formatter.setBracketFormatMode(BracketMode.LINUX_MODE);
				formatter.setBlockIndent(false);
				formatter.setBracketIndent(false);
				formatter.setSpaceIndentation(8);
				break;
		}
	}
}

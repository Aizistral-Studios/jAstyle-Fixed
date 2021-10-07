package com.integral.jastyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.abrarsyed.jastyle.ASFormatter;
import com.github.abrarsyed.jastyle.constants.EnumFormatStyle;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class MainTest {

    private static int collapseColor(int color) {
        return ~color & 15;
    }
    
	public static void main(String[] args) throws Exception {
	    int color = -15;
	    
	    System.out.println("Color " + color + " collapsed to " + collapseColor(color));
	    
	    if (true)
	        return;
	    
		File testClass = new File("C:\\Users\\Vessel of Darkness\\Desktop\\RandomClass.java");
		FileInputStream testInput = new FileInputStream(testClass);
		String testClassContent = new String(ByteStreams.toByteArray(testInput), Charset.defaultCharset());
		
		System.out.println("Input class: ");
		System.out.println(testClassContent);
		
		ASFormatter formatter = new ASFormatter();
		
		formatter.setFormattingStyle(EnumFormatStyle.JAVA);
        formatter.setBreakElseIfsMode(false);
        formatter.setSpaceIndentation(4);
        formatter.setClassIndent(false);
        formatter.setNamespaceIndent(false);
        formatter.setCaseIndent(true);
        formatter.setBreakClosingHeaderBracketsMode(false);
        formatter.setDeleteEmptyLinesMode(false);
        formatter.setMaxInStatementIndentLength(40);
        //formatter.setUseProperInnerClassIndenting(true);
        
        Reader reader;
        Writer writer;
        
        reader = new StringReader(testClassContent);
        writer = new StringWriter();
        formatter.format(reader, writer);
        reader.close();
        writer.flush();
        writer.close();
        String formattedClass = writer.toString();
        
        File testFormattedClass = new File("C:\\Users\\Vessel of Darkness\\Desktop\\RandomFormattedClass.java");
        Files.write(formattedClass.getBytes(Charset.defaultCharset()), testFormattedClass);
        
        System.out.println("Output saved as: " + testFormattedClass);
	}
	
}

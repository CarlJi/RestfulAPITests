package com.jcj.rest.general.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.commons.io.IOUtils;

/**
 * 
 * Super class which provide some helper methods to help write API tests
 * 
 * 
 * @author Carl Ji
 *
 */
public abstract class APITest {


	/**
	 * Load file in the classpath as String
	 * 
	 * @param file
	 *        The file in the Classpath
	 * @return
	 *        file contens as String
	 */
	public static String loadFile(String file) 
	{
		try{
			InputStream stream = APITest.class.getClassLoader().getResourceAsStream(file);
			return IOUtils.toString(stream);
		}catch(IOException ex){
			ex.printStackTrace();
			throw new RuntimeException("Unable load file " + file);
		}
	}
	
	/**
	 * Generate a random string with expected length
	 * 
	 * @param length
	 *        The length of the expected String
	 * @return
	 *        A randome string with expected length
	 */
	public static String getRandomString(String length)
	{
		String sources = "abcdefghijklmnopqistuvwxyz0123456789ABCDEFGHIJKLMNOPQISTUVWXYZ";
		char[] chars = sources.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for(int i = 0; i < Integer.parseInt(length);i++)
		{
			sb.append(chars[rand.nextInt(chars.length)]);
		}
		
		return sb.toString();
	}

}

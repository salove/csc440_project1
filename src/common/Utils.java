package common;

import java.util.Random;

public class Utils {
	/**
	 * Determine either "a" or "an" pronoun
	 * @param subject Subject of the pronoun
	 * @return Either "a" or "an"
	 */
	public static String getPronoun(String subject) {
		char firstLetter=subject.toLowerCase().charAt(0);
		String retVal="a";
		switch(firstLetter) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			retVal="an";
		}
		return retVal;
	}
	
	/**
	 * An example (and very insecure) password hash 
	 * @param pw The password to hash
	 * @return A string hash of the pw.
	 */
	public static String pwHash(String pw) {
		return Integer.toHexString(pw.hashCode());
	}
	
	public static int random(int min, int max) {
	    if (min==max) {
	        return min;
	    } else if (min>max) {
	        throw new RuntimeException("Utils.random called with min>max");
	    } else {
	        Random r=new Random(System.currentTimeMillis());
	        return (min+r.nextInt(max-min+1));
	    }    
	}
	
	public static int str2int(String s) {
	    try {
	        int i=Integer.parseInt(s);
	        return i;
	    } catch (NumberFormatException e) {
	        return -1;
	    }
	}
}
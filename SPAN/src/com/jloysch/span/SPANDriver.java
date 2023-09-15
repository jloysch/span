package com.jloysch.span;

public class SPANDriver {

	public static void main(String args[]) {
		
		//ENCRYPT SPAN.encrypt(String phrase, float ratio, int blocksize, writetofile);
		String[] pair = SPAN.encrypt("EXAMPLE", (float) 0.888, 8, false);
		
		String cipher = pair[0];
		String key = pair[1];
		
		System.out.println(cipher + "\n\n" + key);
	}
}

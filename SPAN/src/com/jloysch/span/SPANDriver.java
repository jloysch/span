package com.jloysch.span;

public class SPANDriver {

	public static void main(String args[]) {
		
		String to_encrypt = "Let's Test This Now!";
		
		String[] pair = SPAN.encrypt(to_encrypt, (float) 0.888, 8, false);
		
		System.out.println("PLAINTEXT > '" + to_encrypt + "'\n");
		
		String cipher = pair[0];
		String key = pair[1];
		
		System.out.println(cipher + "\n" + key);
		
		String decrypt = SPAN.decrypt(cipher, key);
		
		System.out.println("\nDECRYPT > '" + decrypt + "'");
	}
}

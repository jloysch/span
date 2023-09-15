package com.jloysch.span;

public class Adversary {
	
	private static final String BRUTEFORCE_SIM_STR = "HELLO!";
	
	public static boolean aPhraseInMyDict(String phrase) { //Simulate attacker checking their result against a dict
		
		
		return (BRUTEFORCE_SIM_STR.equals(phrase));
	}
	
	public static void bruteforceSim(String intended_phrase) {
		boolean cracked = false;
		
		String phrase = intended_phrase;
		
		String[] pair = SPAN.encrypt(phrase, (float) 0.888, 8, false); //TODO Allow doubles in and cast in-method
		
		//String key = "//00.000000D0.000R000S000F\\\\";
		
		
		
		
		final String prefix = "//", postfix = "\\\\";
		
		String dc, fakekey;
		
		while (!cracked) { //An adversary may cryptanalyze 'likelihoods' to limit their search
			
			
			//Let's assume, for now, all ratios are bound as initially intended between (0,1)
			
			for (int blocksize = 8; blocksize < 12; blocksize++) {
				
				for (float degree = (float) 10.0; degree < 99.999999; degree+=0.00001) {
				
					for (float ratio = (float) .01; ratio < 1.0; ratio+=0.00001) {
				
						for (int start = 0; start < 256; start++) {
		
							for (int finish = start+1; finish < 256; finish++) {
								
								fakekey = prefix + degree + "D" + ratio + "R" + start + "S" + finish + "F" + postfix;
								
								//System.out.println("KEY > " + fakekey);
								
								
								try {
									dc = SPAN.decrypt(pair[0], fakekey);
									
									if (dc != null) {
										if (aPhraseInMyDict(SPAN.decrypt(pair[0], fakekey))) {
											System.out.println("FOUND!");
											System.exit(100);
										} else {
											System.out.println("KEY " + fakekey + " FAILED >> INCORRECT '" + dc + "'");
										}
									} else {
										System.out.println("KEY " + fakekey + " FAILED >> INCORRECT '" + dc + "'");
									}
								
								} catch (Exception e) {
									System.out.println("KEY " + fakekey + " FAILED >> BROKEN BLOCK");
								}
								
							}
							
						}
					}
				}
			}

		}
	}
	
	public static void useWrongKeyForPlaintext(String to_encrypt) {
		//String to_encrypt = "Let's Test This Now!";
		
		String[] pair = SPAN.encrypt(to_encrypt, (float) 0.888, 8, false);
		
		System.out.println("PLAINTEXT > '" + to_encrypt + "'\n");
		
		String cipher = pair[0];
		//String key = pair[1];
		String key = "//20.138517D0.888R111S131F\\\\";
		
		System.out.println(cipher + "\n" + key);
		
		String decrypt = SPAN.decrypt(cipher, key);
		
		System.out.println("\nDECRYPT > '" + decrypt + "'");
	}
	
	public static void main(String args[]) throws NumberFormatException{
		
		
		//useWrongKeyForPlaintext("Let's Test This!");
		
		bruteforceSim(BRUTEFORCE_SIM_STR);
	}
	

}

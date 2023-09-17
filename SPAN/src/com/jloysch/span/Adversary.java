package com.jloysch.span;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Adversary {
	
	private static final String BRUTEFORCE_SIM_STR = "Example_Password123!";
	
	public static boolean aPhraseInMyDict(String phrase) { //Simulate attacker checking their result against a dict
		
		
		return (BRUTEFORCE_SIM_STR.equals(phrase));
	}
	
	public static void bruteforceSim(String intended_phrase) {
		boolean cracked = false;
		
		String phrase = intended_phrase;
		
		String[][] pair = SPAN.encrypt(phrase, (float) 0.888, false); //TODO Allow doubles in and cast in-method
		
		
		String originalcipherkey = pair[1][0];
		
		//String key = "//00.000000D0.000R000S000F\\\\";
		
		
		
		
		final String prefix = "//", postfix = "\\\\";
		
		String dc, fakekey;
		
		while (!cracked) { //An adversary may cryptanalyze 'likelihoods' to limit their search
			
			
			//Let's assume, for now, all ratios are bound as initially intended between (0,1)
			FileWriter fout = null;
			FileWriter foutstrings = null;
			
			try {
				 fout = new FileWriter("brutestringslog.txt", false); //apend
				 fout.write("BEGIN>\n");
				 fout = new FileWriter("brutestringslog.txt", true); //apend
				 
				 foutstrings = new FileWriter("brutestringsonlylog.txt", false); //apend
				 foutstrings.write("BEGIN>\n");
				 foutstrings = new FileWriter("brutestringsonlylog.txt", true); //apend
				 
			} catch (Exception e) {
				System.out.println("Couldn't create dump files.");
				System.exit(-1);
			}
			
			Float[] crypttokens = new Float[4];
			int cryptct = 0;
			int last = 0;
			
			String pkey = pair[1][0].substring(2, pair[1][0].length()-2);
			
			for (int i = 0; i < pkey.length(); i++) {
				
				if (Character.isLetter(pkey.charAt(i))) {
					
					crypttokens[cryptct++] = Float.parseFloat(pkey.substring(last, i));
					last = i + 1;
				}
			
			}
			
			for (Float f : crypttokens) {
				System.out.println("TOKEN > " + f);
			}
			//System.exit(0);
			String recomp = "";
			for (String s : pair[0]) recomp += s; //TODO CHECK BLOCK LABELS
			
			
			
			final boolean helpkey = true; //IF HELP KEY IS TURNED OFF THE PROGRAM FREEZES, SOME BAD KEYS WILL CAUSE THE PROGRAM TO FREEZE!
			
			float helpdoffset = 0f;
			float helproffset = 0.001f;
			
			
			LinkedList<String> keys = new LinkedList<String>();
			//originalcipherkey += "\\";
			for (int blocksize = 8; blocksize < 12; blocksize++) {
				
				for (float degree = helpkey ? 0f : 1f ; degree < 99.999999f; degree+=0.000001f) {
				
					for (float ratio = helpkey ? 0f : .1f ; ratio < 1.0f; ratio+=0.000001f) {
				
						for (int start = 0; start < 256; start++) {
		
							for (int finish = start+1; finish < 256; finish++) {
								
								/*
								 * HELP GET CLOSER
								 */
								//degree = crypttokens[0];
								//ratio = crypttokens[1];
								
								fakekey = prefix + (crypttokens[0]-helpdoffset + degree) + "D" + (crypttokens[1]-helproffset + ratio) + "R" + start + "S" + finish + "F" + postfix;
								
								if (!helpkey) fakekey = prefix + degree + "D" + ratio + "R" + start + "S" + finish + "F" + postfix;
								
								System.out.println("\t" + fakekey);
								
								//System.out.println("KEY > " + fakekey);
								
								//System.out.println("CIPHER | " + recomp);
								
								try {
									
									
									//dc = SPAN.decrypt(recomp, fakekey);
									
									
									
									dc = SPAN.decrypt_shuffled(recomp, fakekey);
									//System.out.println("ORIGINAL CIPHER KEY > " + originalcipherkey);
									//System.out.println("START > " + crypttokens[2]);
									//System.out.println("START > " + crypttokens[2]);
									
									if (dc != null) {
										
										if ((start == crypttokens[2]) && (finish == crypttokens[3])) {
											System.out.println("FOUND!");
											//System.exit(100);
											
										}
										
										if (fakekey.equals(originalcipherkey)) {
											System.out.println("Found...");
											keys.add(fakekey);
											System.exit(1);
										}
										
										if (aPhraseInMyDict(dc)) {
											System.out.println("FOUND IN DICT!");
											//System.exit(100);
											keys.add(fakekey);
											//System.exit(100);
											
											
										} else {
											System.out.println("FAKEKEY\t" + fakekey + " FAILED >> INCORRECT\n\t" + dc + "'");
											System.out.println("REAL\t" + pair[1][0]);
										}
									} else {
										System.out.println("FAKEKEY\t" + fakekey + " FAILED >> INCORRECT\n\t" + dc + "'");
										System.out.println("REAL\t" + pair[1][0]);
									}
									
									foutstrings.append("CIPHER: " + recomp + "\nKEY: " + fakekey + "\nDECRYPT:" + dc + "\n");
									fout.append(dc + "\n");
								
								} catch (Exception e) {
									System.out.println("FAKEKEY " + fakekey + " FAILED >> BROKEN BLOCK");
								}
								
								//System.exit(0);
								
								for (String possiblekey : keys) {
									
									System.out.println("\tPOSSIBLE DECRYPT > " + SPAN.decrypt_shuffled(recomp, possiblekey));
									
									System.out.println("\tOG > " + SPAN.decrypt_shuffled(recomp, originalcipherkey));
									
									
									if (possiblekey.equals(keys.getLast())) {
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										//System.exit(1);
										return;
									}
									
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
		
		String[][] pair = SPAN.encrypt(to_encrypt, (float) 0.569, false);
		
		System.out.println("PLAINTEXT > '" + to_encrypt + "'\n");
		
		//String cipher = pair[0];
		
		String cipher = "";
		for (String s : pair[0]) cipher += s; //TODO CHECK BLOCK LABELS
		//String key = pair[1];
		String key = "//20.138517D0.888R111S131F\\\\";
		
		System.out.println(cipher);
		
		String decrypt = SPAN.decrypt_shuffled(cipher, key);
		
		System.out.println("\nDECRYPT bad > '" + decrypt + "'\nkey > " + key);
		
		System.out.println(pair[1][0]);
		
		System.out.println("Should've received > '" + SPAN.decrypt_shuffled(cipher, pair[1][0]) + "'");
	}
	
	
	public static void useWrongKeyForPlaintext() {
		
		String to_encrypt = BRUTEFORCE_SIM_STR;
		
		String[][] cipherpair = SPAN.encrypt(to_encrypt, (float) 0.569, false);
		
		//String[] min = SPAN.encrypt(to_encrypt, 0.569f, 8, false);
		
		//System.out.println("MIN > " + min[0] + "\n" + min[1]);
	
		
		String recomp = "";
		
		for (String s : cipherpair[0]) recomp += s;
		
		try {
	      FileWriter writer = new FileWriter("shufflecipher.txt");
	      writer.write(recomp);
	      writer.close();
	      writer = new FileWriter("shufflekey.txt");
	      writer.write(cipherpair[1][0]);
	      writer.close();
	      System.out.println("Successfully wrote to the files.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
			
		//Manually testing the last generation 
		
		//String cipher = "175.90337A296.24985A191.65604A47.307793A138.83098A4.485219A9.855994A272.0071A128.78516A277.5285A58.21579A290.42645A177.3203A173.9456A78.856674A116.56042A40.120422A220.8139A131.19684A170.35728A2.4748738A211.44354A268.34882A124.05054A12.591539A105.561295A250.35162A138.1265A223.45534A64.68668A246.06114A37.805733A240.46072A76.94098A160.16783A53.17277A193.98218A319.37817A162.0664A10.887565A114.00706A325.03992A160.93025A242.7011A127.653984A280.46045A35.135006A236.16287A125.50075A186.16862A43.85033A276.37726A309.39243A181.45915A65.31283A99.86694A325.6724A209.80513A244.6709A106.08023A311.83408A39.475666A258.1482A119.67593A212.44572A46.676746A228.76286A172.83727A166.09822A50.12204A104.59685A294.16833A195.27509A50.163326A118.20146A304.44098A321.967A251.50996A120.68554A271.2942A90.20307A217.83981A216.65398A204.78142A54.30074A132.14182A26.253868A175.95659A62.683357A171.6332A280.75052A197.10547A273.36755A114.98644A276.5147A101.35427A288.88004A117.24352A240.89482A102.88415A280.9859A24.316597A208.73705A61.34629A169.07397A32.14372A191.27925A291.5231A180.79553A330.38956A87.118454A263.65143A194.19672A240.46405A105.87116A302.68265A18.817379A204.68213A100.30048A139.6981A29.991274A249.0018A279.4394A155.76608A36.43852A84.70436A264.40363A185.28139A269.46902A105.91285A271.68018A264.3867A202.70767A95.106026A212.28896A25.45385A216.94447A88.52805A180.09158A43.00436A108.703476A11.552192A185.90399A45.500515A150.63815A269.87473A316.49286A283.10315A99.62229A245.81967A76.17819A227.57481A111.08418A193.99306A39.517696A222.93832A7.143616A224.00758A42.19273A132.5402A18.908497A164.2469A276.1294A163.67006A304.6605A75.06945A294.5099A108.703186A190.01361A89.00079A240.44322A5.155398A213.91573A90.82254A149.45526A14.682845A247.10516A290.66315A154.26727A27.390265A79.14277A275.13477A172.82008A198.96483A81.11773A293.39993A3.0155845A198.20624A91.80584A196.5138A13.562932A238.63821A278.20566A135.67468A25.727842A24.809587A270.6363A148.77985A166.41801A82.29045A278.74875A184.44482A192.29541A82.7701A184.39331A58.21579A232.26915A82.45647A173.9456A20.4546A51.2015A311.14978A162.01483A28.989761A133.90135A301.66534A152.54204A269.75952A83.65606A264.21274A63.433773A250.35162A88.766815A201.38802A64.68668A214.77463A336.88394A181.9435A26.246428A139.33502A328.036A178.34369A256.16818A144.0186A247.08655A61.774857A283.50275A160.93025A185.23332A64.17706A280.46045A329.33664A183.46664A83.6541A129.89476A303.63132A206.94234A231.52089A123.29547A8.282134A57.045555A248.67967A146.57483A244.6709A68.233055A278.30652A";
		String altkey = "//46.317398D0.569R228S255F\\\\";
		
		String cipher = recomp;
		String key = cipherpair[1][0];
		
		System.out.println("REAL KEY DECRYPT > " + SPAN.decrypt_shuffled(cipher, key));
		System.out.println("FAKE KEY DECRYPT > " + SPAN.decrypt_shuffled(cipher, altkey));
	}
	
	public static void main(String args[]) throws NumberFormatException{
		
		
		//useWrongKeyForPlaintext("Let's Test This!");
		
		bruteforceSim(BRUTEFORCE_SIM_STR);
		
		//useWrongKeyForPlaintext(BRUTEFORCE_SIM_STR);
		//useWrongKeyForPlaintext();
	}
	

}

package com.jloysch.span;

public class Adversary {
	
	public static String[] blockstr_to_blocks(String str, int blocksize) {
		String[] ret = new String[str.length()/blocksize];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = str.substring(i*blocksize, i*blocksize + blocksize);
		}
		return ret;
	}
	
	public static void go() throws NumberFormatException {
		
		String tolist = "MY@NAME@IS@JOSH"; //TODO FIX @ for space
		char aslist[] = new char[tolist.length()];
		
		for (int i = 0; i < tolist.length(); i++) {
			aslist[i] = Character.valueOf(tolist.charAt(i));
		}
		
		//make expected message to crack properly
		
		boolean cracked = false;
		
		float changingRatio = (float) 0.0;
		float changingDegree = (float) 0.0;
		int blocksize = 8;
		
		int tries = 0;
		
		String[] bins = SPAN.to_padded_list(SPAN.to_binary_list("MY NAME IS JOSH"));
		
		while (!cracked) {
			
			changingRatio += 0.000001;
			changingDegree += 0.000001;
			
			System.out.println("65.2816A136.60081A139.01712A213.64763A38.62887A99.619354A134.64043A162.92131A24.695885A86.92618A110.23209A173.28201A6.391523A66.10949A107.01292A");
			System.out.println(changingRatio + "//" + changingDegree);
			
			String[] dc = SPAN.decrypt_string("65.2816A136.60081A139.01712A213.64763A38.62887A99.619354A134.64043A162.92131A24.695885A86.92618A110.23209A173.28201A6.391523A66.10949A107.01292A", blocksize, (float) changingRatio, (float) changingDegree);
			
			int t = 0;
			boolean ok = true;
			
			//cracked = true;
			
			for (int i = 0; i < dc.length; i++) {
				if (dc[i] != bins[i]) ok = false;
				try {
				System.out.println(">" + SPAN.binary_to_char(SPAN.sum_to_binary(Integer.parseInt(dc[i]))));
				} catch (Exception e) {
					
				}
			}
			
			/*
			for (String d : dc) {
				
					System.out.println("DECRYPT DC > " + d);
					
					try {
					System.out.println(SPAN.sum_to_binary(Integer.parseInt(d)));
					System.out.println( Integer.toString(Integer.parseInt(d)));
					System.out.println(SPAN.binary_to_char(SPAN.sum_to_binary(Integer.parseInt(d))));
					
					
					
					} catch (Exception e) {
						
					}
				
					//System.out.println("EXPECT")
				
			}
			*/
			
			if (ok) cracked = true;
			System.out.println("SURVIVED " + tries++ + " TIMES.");
		}
		
		
	}
	
	public static void main(String args[]) throws NumberFormatException{
		
		try {
		//go();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
	

}

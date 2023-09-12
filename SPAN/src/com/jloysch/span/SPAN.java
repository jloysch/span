package com.jloysch.span;

/*
 * @Author: Joshua Loysch
 * All Rights Reserved
 * 
 * Version 1.0 (Rough toy demo)
 * 
 * Introducing a toy demo of an encryption algorithm I'm developing, 'SPAN'. I have had the idea for this for quite a while and figured
 * I would could it up since it seemed like an interesting approach. This version currently has a theoretical complexity
 * of 2^129 + 3.194e38 using some mathematics. This algorithm also has the ability to generate both reversible and non-reversible tokens
 * so it can also be used for data destruction. If you would like to run the program simply run main(), it will take you to a REPL.
 * 
 * Some crypts generated may be quite large. In the future I will add updates to place this into a text file.
 * 
 * Simply have your crypt, your degree, your ratio, and your block size.*
 * 
 * *** BLOCK SIZE / PADDING TO BE ADRESSED IN LATER UPDATES ***
 * 
 * A simple plaintext phrase like 'hello' may become "2.2398A23.2308A..." so on.
 * 
 * The part that's important to the receiver is the end part, e.g. [38.453421degR\\0.8679\\8]
 * 
 * Use this and the crypt to get the plaintext phrase back.
 * 
 * This is version 1 implementaion 1 and working demo 1 so there may be some hiccups. This is a very exciting project and I can't wait to 
 * work on it over time.
 * 
 * Theoretical complexity of 2^129+3.194e^38 to start seems incredible. >.>
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SPAN {
	
	private static float TRI_RATIO_FINAL = (float) 0.86;
	
	private static int binary_to_sum(String binary_representation) {
		/*
		int sum = 0;
		char[] chars = binary_representation.toCharArray();
		
		for (int i = 0; i < binary_representation.toCharArray().length; i++) {
			
			if (chars[i] == '1') sum+= Math.pow(2, i);
			
		}
		
		chars = null;
		
		return sum;
		*/
		return Integer.parseInt(binary_representation,2);
	}
	
	private static double degree_to_radian(float degrees) {
		//0.0174533 | 1° × π/180 = 0.01745rad
		
		double rads = (double) degrees * (double) (Math.PI / 180);
		
		return rads;
	}
	
	
	private static float find_some_x(float mod, float degree_obsf) { //Correct and verified
	
		//C^2 = A^2 + B^2 - 2ab*cosC
		
		
		
		//* double vs float
		
		float c2, c;
		
		float degone = (float) (2 * (Math.pow(mod, 2)));
		
		float degtwo = 2 * (float) mod * (float) mod * (float) Math.cos(degree_to_radian(degree_obsf));
		
		c2 = (float) degone - degtwo;
		
		//c2 = ((float) Math.pow(mod, 2) + (float) Math.pow(mod, 2)) - ((2*mod*mod) * (float) (Math.cos(degree_obsf)));
		 
		c = (float) Math.sqrt(c2);
		
		return c; 
		
	}
	
	private static float generate_some_degree() {
		
		float num = (float) 100.0; //def
		
		
		while (num >= (float) 60.0) {
			
			num = (new Random()).nextFloat();
			
			while (num == (float) 0.0) { //Fix zero
				num = (new Random()).nextFloat();
			}
			
			String s = String.valueOf(num).substring(2, 4); //get first 2nums after decimal
			//int newNum = Integer.parseInt(s); //make it a number
			float final_number = Float.parseFloat(s); //make float
			
			num = (new Random()).nextFloat(); //Regenerate decimals
			
			num += final_number; //Make the final number, it'll check in while loop or fall through with angle.
		}
		
		return num;
	}
	
	private static void make_triangle() {
		
	}
	
	private static float next_degree(float start, float ratio, int iteration) {
		//start + (start*ratio - start%ratio) = diff
		//final = diff + start
		float next = ((float) iteration)*( (float) start*ratio - ((float) start%ratio)) + start;
		
		if (next >= (float) 90.0) {
			next = (float) next%(float)90.0;
		}
		
		//System.out.println("NEXT DEGREE = " + next + " | {ST_" + start + ".*IT_" + iteration + "}<" + ratio + ">");
		
		return next;
	}
	
	private static int[] decrypt(String[] crypt_tokens, float R, float degree_obsf) {
		
		int dec_tokens[] = new int[crypt_tokens.length];
		
		//TODO make array have tokens seperate
		
		for (String s : crypt_tokens) {
			//System.out.println("CRYPT > " + s);
		}
		int step = 0;
		//System.out.println("DECRYPT");
		
		for (String s : crypt_tokens) {
			//System.out.println("PREDECRYPTTOKEN > " + s);
			s = s.substring(0, s.length()-1); //Chop off block check
			//System.out.println("DECRYPT TOKEN > " + s);
			
			double res = 0;
			
			if (step == 0) {
				res = (double) (Float.parseFloat(s)/2/Math.sin((degree_obsf/2)/57.2985));
			} else {
				res = (double) (Float.parseFloat(s)/2/Math.sin((next_degree(degree_obsf, R, step)/2)/57.2985));
			}
			
			/*
			if  (res < 1.0) { //catch zero case; TODO: CHECK PRECISIONS
				res = (float) 1.0;
			}
			*/
			
			
			
			int fres = (int) Math.round(res); // demote after round :]
			
			//System.out.println(res + " >RND> " + fres);
			dec_tokens[step++] = fres;
		}
		
		//System.out.println("DECRYPT");
		/*
		for (int in : dec_tokens) {
			//System.out.println(in);
		}
		*/
		return dec_tokens;
	}
	
	public static String generate_test_string(int block_size, int blocks) {
		String gen = "";
		
		for (int i = 0; i < blocks; i++) {
			for (int j = 0; j < block_size; j++) {
				gen += (new Random().nextBoolean() ? "0" : "1");
			}
		}
		
		return gen;
	}
	
	public static LinkedList encrypt(String rawbinary, int blocksize, float ratio, float degree, int blocknumber){ 
		String data = rawbinary;
		
		int blocks = rawbinary.length()/blocksize;
		
		//int sum_of_data_at_chunk = binary_to_sum(data);
		
		int[] sums = new int[data.length()/blocksize];
		
		System.out.println(data);
		for (int i = 0; i < sums.length; i++) {
			//System.out.println(i);
			
			sums[i] = binary_to_sum(data.substring(0 + blocksize*i, blocksize + blocksize*i));
			
			System.out.println("Sum[" + i + "] = " + sums[i]);
		}
		
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		
		if (degree == 0) {
			degree = generate_some_degree();
		} else {
			degree = next_degree(degree, ratio, blocknumber); //Important to know blocknumber because each block is dependent on last
		}
		
		
		String crypt = "";
		String crypts[] = new String[sums.length];
		float degrees[] = new float[sums.length];
		
		//final float ratio = (float) 0.86;
		
		//final float ratio = TRI_RATIO_FINAL;
		
		for (int i = 0; i < sums.length; i++) { //populate sums
			
			if (degree == 0) { //TODO INCREMENT
				crypt += find_some_x(sums[i], degree);
				degrees[0] = degree;
			} else {
				crypt += find_some_x(sums[i], next_degree(degree, ratio, i));
				degrees[i] = next_degree(degree, ratio, i);
			}
			
			crypt+= (i > alphabet.length() ? alphabet.charAt(alphabet.length()-i) : alphabet.charAt(i));
			crypts[i] = crypt;
			crypt = "";
			System.out.println("SUMGENERATED > " + crypt);
		}
		
		System.out.println("DEGREES");
		
		for (float f : degrees) {
			System.out.println(f);
		}
		
		System.out.println("R = " + ratio);
		System.out.println("\n\n");
		
		//System.out.println("CRYPT > " + crypt);
		
		System.out.println("CRYPT > ") ;
		System.out.print("\n--BEGIN--\n\t[");
		for (String s : crypts) {
			System.out.print(s);
		}
		System.out.println("]" + "//" + degree + "degsR" +"\n--END--");
		
		LinkedList values = new LinkedList();
		
		String cryptStrFull = "";
		
		for (String s : crypts) { //should be delimited by blocks A-Z
			cryptStrFull += s;
		}
		values.add(cryptStrFull);
		values.add(degree);
		values.add(degrees);
		values.add(ratio);
		
		return values;	
	}
	
	public static void test_a() {
		//System.out.println(sum_of_data_at_chunk);
		
				//float degree_of_obsfucation = generate_some_degree();
				
				//System.out.println(degree_of_obsfucation);
				
				//float x = find_some_x(sum_of_data_at_chunk, degree_of_obsfucation);
				
				//float x = find_some_x(sum_of_data_at_chunk, (float) 30.301506);
				/*
				System.out.println("Some x > " + x);
				
				System.out.println("--");
				System.out.println("RAW > " + data);
				System.out.println("ENCRYPT > ");
				System.out.println(".. SUM = " + binary_to_sum(data));
				  float degree = generate_some_degree();
				System.out.println(".. DEGREE OF OBFS = " + degree);
				System.out.println(".. SOME X = " + find_some_x(sum_of_data_at_chunk, degree));
				System.out.println(".. OK.");
				*/
				
				//String data = "101110111000";
				String data = generate_test_string(4, 8);
				
				int blocks = 1;
				int block_size = 4;
				
				int sum_of_data_at_chunk = binary_to_sum(data);
				
				int[] sums = new int[data.length()/4];
				
				System.out.println(data);
				for (int i = 0; i < sums.length; i++) {
					//System.out.println(i);
					
					sums[i] = binary_to_sum(data.substring(0 + 4*i, 4 + 4*i));
					
					System.out.println("Sum[" + i + "] = " + sums[i]);
				}
				
				final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				float degree = generate_some_degree();
				
				String crypt = "";
				String crypts[] = new String[sums.length];
				float degrees[] = new float[sums.length];
				
				final float ratio = (float) 0.86;
				
				for (int i = 0; i < sums.length; i++) { //populate sums
					
					if (degree == 0) {
						crypt += find_some_x(sums[i], degree);
						degrees[0] = degree;
					} else {
						crypt += find_some_x(sums[i], next_degree(degree, ratio, i));
						degrees[i] = next_degree(degree, ratio, i);
					}
					
					crypt+= (i > alphabet.length() ? alphabet.charAt(alphabet.length()-i) : alphabet.charAt(i));
					crypts[i] = crypt;
					crypt = "";
				}
				
				System.out.println("DEGREES");
				
				for (float f : degrees) {
					System.out.println(f);
				}
				
				System.out.println("R = " + ratio);
				System.out.println("\n\n");
				
				//System.out.println("CRYPT > " + crypt);
				
				System.out.println("CRYPT > ") ;
				System.out.print("\n--BEGIN--\n\t[");
				for (String s : crypts) {
					System.out.print(s);
				}
				System.out.println("]" + "//" + degree + "degsR" +"\n--END--");
				
				decrypt(crypts, ratio, degree);
	}
	
	public static boolean verify(int times) {
		Boolean logs[] = new Boolean[times];
		
		for (int a = 0; a < times; a++) {
			//String data = "101110111000";
			
			//System.out.println("BEGIN PASS " + a);
			String data = generate_test_string(4, 16);
			
			int blocks = 1;
			int block_size = 4;
			
			int sum_of_data_at_chunk = binary_to_sum(data);
			
			int[] sums = new int[data.length()/4];
			
			//System.out.println("RAW DATA >> data");
			for (int i = 0; i < sums.length; i++) {
				//System.out.println(i);
				
				sums[i] = binary_to_sum(data.substring(0 + 4*i, 4 + 4*i));
				
				//System.out.println("Sum[" + i + "] = " + sums[i]);
			}
			
			final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			float degree = generate_some_degree();
			
			String crypt = "";
			String crypts[] = new String[sums.length];
			float degrees[] = new float[sums.length];
			
			final float ratio = (float) 0.86;
			
			for (int i = 0; i < sums.length; i++) { //populate sums
				
				if (degree == 0) {
					crypt += find_some_x(sums[i], degree);
					degrees[0] = degree;
				} else {
					crypt += find_some_x(sums[i], next_degree(degree, ratio, i));
					degrees[i] = next_degree(degree, ratio, i);
				}
				
				crypt+= (i > alphabet.length() ? alphabet.charAt(alphabet.length()-i) : alphabet.charAt(i));
				crypts[i] = crypt;
				crypt = "";
			}
			/*
			System.out.println("DEGREES");
			
			for (float f : degrees) {
				System.out.println(f);
			}
			
			System.out.println("R = " + ratio);
			System.out.println("\n\n");
			
			//System.out.println("CRYPT > " + crypt);
			
			System.out.println("CRYPT > ") ;
			System.out.print("\n--BEGIN--\n\t[");
			for (String s : crypts) {
				System.out.print(s);
			}
			System.out.println("]" + "//" + degree + "degsR" +"\n--END--");
			*/
			int[] dec_tokens = decrypt(crypts, ratio, degree);
			
			logs[a] = true;
			
//			/System.out.println(">> End Verification pass " + a);
			for (int t = 0; t < dec_tokens.length; t++) {
				
				if (sums[t] != dec_tokens[t]) {
					System.out.println(sums[t] + "<----------!--------->" + dec_tokens[t]);
					logs[a] = false;
				} else {
					//System.out.println(sums[t] + "<OK>" + dec_tokens[t]);
				}
			}
		}
		
		boolean ok = true;
		int step = 0;
		int[] loglevels = new int[logs.length];

		for (boolean b : logs) {
			if (!b) ok = false;
			loglevels[step++] = !b ? step-1 : 0;
		}
		
		//System.out.println(ok ? "All tests passed (" + times + "/" + times + ")" : "Verification failed for " + times + " passes.");
		
		System.out.print("\n[");
		for (int i : loglevels) {
			System.out.print(i + ",");
		}
		System.out.print("] <<ERROR LEVELS\n");
		
		return ok;
	}
	
	public static void loop_test() {
		int step = 0;
		while (verify(1)) {
			System.out.println(">>>>>>>>>>>>>>>>TRY " + step++ + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	public static String sum_to_binary(int sum) {
		/*
		int bits = 0;
		int tempsum = sum;
		for (int i = 0; i < tempsum; i++) { //silly way to calc bits but should work
			tempsum-= (int) Math.pow(2, i);
			bits++;
		}
		
		System.out.println("NEEDS " + bits + " bits");
		
		String reverse = "";
		
		tempsum = sum; //make into binary reversed
		for (int i = 0; i > sum; i--) { //Make it in reverse order
			
			if (tempsum - (int) Math.pow(2, i) > 0) {
				//System.out.println("TEMPSUM BEFORE " + tempsum);
				tempsum -= (int) Math.pow(2, i);
				//System.out.println("TEMPSUM AFTER " + tempsum);
				reverse += "1";
				//System.out.println("REV > ");
			} else {
				reverse += "0";
				//System.out.println("TEMPSUM BEFORE " + tempsum);
			}
			
			
			//reverse += (tempsum - (int) Math.pow(2, i) > 0 ? "1" : "0");
		}
		

		
		System.out.println("REVERSE > " + reverse + " <<");
		
		String reversed = "";
		for (int i = 0; i < reverse.length(); i++) {
			reversed += reverse.charAt(reverse.length() -i);
		}
		
		System.out.println("BITTRANSLATION > " + sum + " >>> " + reversed);
		
		return reversed;
		*/
		
		return Integer.toBinaryString(sum);
	}
	
	public static int[] decrypt_manual(String crypt, float R, double start) {
		String[] crypt_tokens = new String[1]; //TODO PLEASE CHANGE THIS IS JUST BCS IM ANNOYED, NEEDS TO BE EXACT LENGTH
		crypt_tokens[0] = crypt;
		
		int blocks = 0;
		for (int i = 0; i < crypt.length(); i++) { //1st pass check blocks
			if (Character.isLetter(crypt.charAt(i))) {
				blocks++;
			}
		}
		
		String[] resequenced_safe = new String[blocks];
		
		int blck_ct = 0, lastindex = 0;
		
		for (int i = 0; i < crypt.length(); i++) { //2nd pass format and transfer
			if (Character.isLetter(crypt.charAt(i))) {

				resequenced_safe[blck_ct++] = crypt.substring(lastindex, i);
				lastindex = i+1;
			}
		}
		
		int[] dec_tokens = decrypt(resequenced_safe, R, (float) start);
		return dec_tokens;
	}
	
	public static void test_single() {
		//test_a();	
		
				//PASS DATA INTO THIS BLOCK BY BLOCK
				
				//TODO MAKE BLOCK CHUNKING
				
				//So this is a single run, I can make a method to keep taking stuff as a string of binary and passing back a token
				//1 encrypt token : 1 decrypt token
				
				String data = "10111011"; 
				int block_size = data.length();
				LinkedList res = encrypt(data, block_size, TRI_RATIO_FINAL, 0, 0);
				
				/*
				values.add(crypt);
				values.add(degree);
				values.add(degrees);
				values.add(ratio);
				*/
				String crypt = (String) res.get(0);
				float degree = (float) res.get(1);
				float[] degrees = (float[]) res.get(2);
				float ratio = (float) res.get(3);
				
				System.out.println("CRYPT > " + crypt);
				System.out.println("DEGREE > " + degree);
				
				String[] toDecryptTokens = new String[1];
				toDecryptTokens[0] = crypt;
				
				int dec_sums[] = decrypt(toDecryptTokens, ratio, degree);
				
				for (int i : dec_sums) {
					System.out.println(i);
					sum_to_binary(i);
				}
				
	}
	
	public static void main_test() {
		encrypt("101110111011", 12, TRI_RATIO_FINAL, 0, 0);
		TRI_RATIO_FINAL = (float) 0.5;
		
		decrypt_manual("782.1675A", TRI_RATIO_FINAL, (double) 14.965881);
	}
	
	public static boolean test_manual(String bits, int blocksize, float triratio) {
		LinkedList vals = encrypt(bits, blocksize, triratio, 0, 0);
		//decrypt_manual((String) vals.get(0), triratio, 3.0);
		int dec_tok[] = decrypt_manual((String) vals.get(0), triratio, (float) vals.get(1));
		
		System.out.println("BINSUMCHECK> " + binary_to_sum(bits));
		System.out.println("BINSUMCHECKB> " + dec_tok[0]);
		//bits gets 1111111 we need to know blocksize
		
		System.out.println("THENBITS>" + bits);
		System.out.println("BLOCKS >> " + blocksize);
		
		String[] blocks = new String[bits.length()/blocksize];
		int last = 0, blkct = 0;
		String check_str = "";
		
		if (blocksize == bits.length()) { //Catch case where we are using the whole 12 bits for a segment
			blocks[0] = bits;
		} else {
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = bits.substring(i*blocksize, i*blocksize + blocksize);
				//System.out.println("BITS > " + bits);
				//System.out.println(i + " > " + i+blocksize + " >> " + bits.substring(i, i+blocksize));
				//last = i;
			}
		}
		
		for (String s : blocks) {
			System.out.println("BLOCKCHECK > " + s);
		}
		
		boolean blocks_ok = true;
		
		
		for (int t : dec_tok) {
			System.out.println("DEC >> " + t);
		}
		
		int block_counter = 0;
		boolean all_blocks_ok = true;
		for (String cryptblock : blocks) {
			if (binary_to_sum(cryptblock) == dec_tok[block_counter++]) {
				
			} else {
				all_blocks_ok = false;
			}
		}
		
		//return (binary_to_sum(bits) == (dec_tok[0]));
		
		return all_blocks_ok;
		
	}
	public static void loop_until_some_failure_test() {
		boolean error = false; //This one makes me laugh every time I write it
		int cnt = 0;
		while (!error) {
			String some_random_bits = generate_test_string(8, 1);
			LinkedList res = encrypt(some_random_bits, some_random_bits.length(), TRI_RATIO_FINAL, 0, 0);
			String crypt = (String) res.get(0);
			float degree = (float) res.get(1);
			float[] degrees = (float[]) res.get(2);
			float ratio = (float) res.get(3);
			
			int[] dec_sums = decrypt_manual(crypt, ratio, (double) degree);
			
			if (!sum_to_binary(dec_sums[0]).equals(some_random_bits)) {
				error = true;
				System.out.println("Failed on count " + cnt);
				System.out.println("BIN >> " + sum_to_binary(dec_sums[0]));
				return;
			}
			
			//System.out.println(some_random_bits);
			//error = true;
			cnt++;
		}
	}
	
	public static void loop_until_some_failure_12bitalign() { //Can proces a chunk of up to 12 bits
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("110111111101", 12, TRI_RATIO_FINAL)) {
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void loop_until_some_failure_4bitalign() { //Can proces a chunk of up to 12 bits
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("110011101101", 4, TRI_RATIO_FINAL)) { //test if we make sum into blocks, TODO: MAKE SUMBLOCK REVERSE TRANSLATOR!
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void loop_until_some_failure_4bitalignalt() { //Can proces a chunk of up to 12 bits
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("111111001111", 4, TRI_RATIO_FINAL)) { //test if we make sum into blocks, TODO: MAKE SUMBLOCK REVERSE TRANSLATOR!
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void loop_until_some_failure_6bitalign() { //Can proces a chunk of up to 12 bits
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("111001111111", 6, TRI_RATIO_FINAL)) { //test if we make sum into blocks, TODO: MAKE SUMBLOCK REVERSE TRANSLATOR!
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void loop_until_some_failure_3bitalign() { //RAISES ERRORS on 3 BIT DO NOT USE
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("111001111111", 3, TRI_RATIO_FINAL)) { //test if we make sum into blocks, TODO: MAKE SUMBLOCK REVERSE TRANSLATOR!
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void loop_until_some_failure_8bitalign() { //RAISES ERRORS on 3 BIT DO NOT USE
		boolean error = false;
		int CNT = 0;
		
		while (!error) {
			if(test_manual("11011101", 8, TRI_RATIO_FINAL)) { //test if we make sum into blocks, TODO: MAKE SUMBLOCK REVERSE TRANSLATOR!
				System.out.println("OK -> " + CNT);
			} else {
				System.out.println("ERROR! -> Failed on " + CNT);
				error = true;
			}
			
			CNT++;
		}
		
	}
	
	public static void hello() {
		//01001000 01000101 01001100 01001100 01001111
		
		
	}
	
	public static String to_binary_string(String phrase) {
		String ret = "";
		
		for (int i = 0; i < phrase.length(); i++) {
			if (i == phrase.length() - 1) {
				ret += Integer.toBinaryString(phrase.charAt(i));
			} else {
				ret += Integer.toBinaryString(phrase.charAt(i)) + " ";
			}
		}
		
		return ret;
	}
	
	public static String[] to_binary_list(String phrase) {
		String[] ret = new String[phrase.length()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.toBinaryString(phrase.charAt(i));
		}
		
		return ret;
	}
	
	public static String[] to_padded_list(String[] binary_list) {
		int longest = 0;
		for (int i = 0; i < binary_list.length; i++) {
			if (binary_list[i].length() > longest) longest = binary_list[i].length();
		}
		
		if ((longest < 4 )) {
			longest = 4;
		} else if (longest < 6) {
			longest = 6;
		} else if (longest < 8) {
			longest = 8;
		} else if (longest < 12) {
			longest = 12;
		} else {
			System.out.println("FAILURE > PHRASE OUT OF PADDING ZONE");
			System.exit(0);
			//throw new Exception("EPIC FAILURE!");
			//return;
		}
		
		
		//repad
		
		for (int i = 0; i < binary_list.length; i++) {
			//System.out.println("BIN[" + i + "]=" + binary_list[i]);
			for (int j = binary_list[i].length(); j < longest; j++) {
				binary_list[i] += "0"; //Pad 0 to rightmost, align all bits
			}
			//System.out.println("BIN[" + i + "]=" + binary_list[i]);
		}
		
		//return
		
		//4,6,8,12 n-bit support later
		
		return binary_list;
	}
	
	public static void pretend_adversary() {
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
		
		while (!cracked) {
			
			changingRatio += 0.000001;
			changingDegree += 0.000001;
			
			String[] dc = SPAN.decrypt_string("65.2816A136.60081A139.01712A213.64763A38.62887A99.619354A134.64043A162.92131A24.695885A86.92618A110.23209A173.28201A6.391523A66.10949A107.01292A", blocksize, (float) changingRatio, (float) changingDegree);
			
			int t = 0;
			boolean ok = false;
			
			cracked = true;
			
			for (String d : dc) {
				System.out.println("DECRYPT DC > " + d);
				
				if (!(SPAN.binary_to_char(SPAN.sum_to_binary(Integer.parseInt(d))) == aslist[t])) { //As soon as we find an error
					cracked = false;
				}
			}
			
			System.out.println("SURVIVED " + tries++ + " TIMES.");
		}
	}
	
	/*
	 * MAIN ENTRY POINT FOR PROGRAM
	 */
	
	public static String[] encrypt_bins(String[] bins, int blocksize, float TRI_RATIO) { //TODO FIXUP CLEANUP
		
		boolean verified = false;
		String bigcrypt = "";
		int stepct = 0;
		float startangle = (float) 0.0;
		LinkedList vals = null;
		
		while (!verified) {
			/*
			values.add(cryptStrFull);
			values.add(degree);
			values.add(degrees);
			values.add(ratio);
			*/

			
			for (String token : bins) {
				if (stepct == 0) {
					 vals = encrypt(token, blocksize, TRI_RATIO, 0, stepct++);
					bigcrypt += (String) vals.get(0);
					startangle = (float) vals.get(1);
				} else {
					 vals = encrypt(token, blocksize, TRI_RATIO, startangle, stepct++);
					bigcrypt += (String) vals.get(0);
				}
				
				
			}
			
			String[] dec = decrypt_string((String) vals.get(0), blocksize, TRI_RATIO, startangle);
			verified = true;
			
			for (int i = 0; i < dec.length; i++) {
				if (binary_to_char(sum_to_binary(Integer.parseInt(dec[i]))) != binary_to_char(bins[i])) {
					System.out.println("DECRYPT MISMATCH (" + sum_to_binary(Integer.parseInt(dec[i])) + " <> " + binary_to_char(bins[i]) + ")");
					verified = false;
				}
			}
			//VERIFY
		}
		
		return new String[] {bigcrypt, String.valueOf(startangle)};
	}
	
	public static String[] decrypt_string(String crypt, int blocksize, float TRI_RATIO, float start) {
		String ret = "";
		
		//34.7595A79.20002A14.227916A136.948A106.44044A
		
		int tokenct = 0;
		
		for (int i = 0; i < crypt.length(); i++) {
			if (Character.isLetter(crypt.charAt(i))) { 
				tokenct++ ;
			}
		}
		
	

		String tokens[] = new String[tokenct];
		tokenct = 0;
		int last = 0;
		
		for (int i = 0; i < crypt.length(); i++) {
			if (Character.isLetter(crypt.charAt(i))) {
				tokens[tokenct++] = crypt.substring(last, i + 1);
				last = i + 1;
			}
		}
		
		//return tokens;
		
		String rettok[] = new String[tokens.length];
		int rettokct = 0;
		
		for (String token : tokens) { //TODO FLOAT-DOUBLE PRECISION STARTING
			int[] dcm = decrypt_manual(token, TRI_RATIO, next_degree(start, TRI_RATIO, rettokct));
			for (int t : dcm) {
				//System.out.println("DC T>" + token + " >> " + t);
			}
			
			rettok[rettokct++] = String.valueOf(dcm[0]);//TODO FIX
		}
		
		return rettok;
	}
	
	public static char binary_to_char(String bin) {
	
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,';] [\\/!@#$%^&*()-+=1234567890~`";
		
		String[][] bin_array = new String[alphabet.length()][];
		
		for (int i = 0; i < alphabet.length(); i++) { //make array of every one :]
			bin_array[i] = to_padded_list(to_binary_list(String.valueOf(alphabet.charAt(i))));
		}
		
		int step = 0;
		for (String[] s2 : bin_array) {
			for (String s : s2) {
				
				//pad up before compare
				
				//while (bin.length() < s.length()) bin+= "0"; //pad right-most 0
				
				//System.out.println("COMPARE <" + s + " <> " + bin + ">");
				if (binary_to_sum(s) == binary_to_sum(bin)) {
					System.out.println("MATCH!");
					return alphabet.charAt(step);
				}
				step++;
			}
		}
		return '\0'; //next-best next to null
		
	}
	
	public static void justsomedebugstuff() {
		//loop_until_some_failure_test();
				//loop_until_some_failure_12bitalign();
				//loop_until_some_failure_6bitalign();
				//loop_until_some_failure_4bitalignalt();
				//loop_until_some_failure_4bitalign();
				//loop_until_some_failure_3bitalign(); // DO NOT USE
				//loop_until_some_failure_8bitalign();
				//12 BIT ENCRYPTION SYSTEM WITH 4, 6, or 12 bit Alignments
				
				String[] bins = to_padded_list(to_binary_list("MY NAME IS JOSH"));
				
				for (String s : bins) {
					System.out.println("BINARYBIN > " + s);
					//System.out.println("LENGTH ^ = " + s.length());
				}
				
				//String t = encrypt_bins(bins, 8, (float) 0.86);
				
				//System.out.println(t);
				
				System.out.println(encrypt_bins(bins, 8, (float) 0.86));
				
				//System.out.println(to_binary_string("Hello"));
				
				String[] dc = decrypt_string("65.2816A136.60081A139.01712A213.64763A38.62887A99.619354A134.64043A162.92131A24.695885A86.92618A110.23209A173.28201A6.391523A66.10949A107.01292A", 8, (float) 0.86, (float) 24.47369);
				
				for (String d : dc) {
					System.out.println("DECRYPT DC > " + d);
					
					System.out.println(sum_to_binary(Integer.parseInt(d)));
					System.out.println(Integer.toString(Integer.parseInt(d)));
					System.out.println(binary_to_char(sum_to_binary(Integer.parseInt(d))));
				}
				
				
				
				//pretend_adversary();
	}
	
	
	public static void repl() {
		boolean run = true;
		
		String[] menu = new String[] {"Encrypt","Decrypt","Evaluate"};
		Scanner input = new Scanner(System.in);
		while (run) {
			
			
			System.out.println("Welcome to SPAN (Toy Cipher, Alpha) - Joshua Loysch");
			System.out.println("Please choose a menu option: ");
			System.out.print("\n[0] " + menu[0] + "\n[1] " + menu[1] + "\n[2] " + menu[2] + "\n\n>> ");
			
			
			
			String choice = input.next();
			
			switch (choice) {
				case "0":
					
					input.nextLine(); //Consume newline character 
					
					System.out.println("Enter a phrase to encrypt:");
					
					String phrase = input.nextLine();
					
					System.out.println("Enter your skew ratio:\n>> ");
					//String wtf = input.next();
					float ratio = input.nextFloat();
					
					input.nextLine();
					
					System.out.print("Finally, enter the desired block size:\n>> ");
					int blocksize = input.nextInt();
					System.out.println("Encrypting '" + phrase + "'...");
					
					input.nextLine();
					
					
					String[] bins = to_padded_list(to_binary_list(phrase));
					
					//System.out.println(encrypt_bins(bins, blocksize, (float) ratio));
					
					String[] encrypted = encrypt_bins(bins, blocksize, (float) ratio);
					
					System.out.println("\nENCRYPTION COMPLETED AND REVERSIBILITY VERIFIED! DETAILS BELOW:\n\n>--- BEGIN ---<");
					System.out.println(encrypted[0]);
					System.out.println(encrypted[1]+"degsR\\\\"+ratio+"\\\\"+8);
					System.out.println(">--- END, PLEASE KEEP FOR YOUR RECORDS ---<");
					
					System.out.print("\n\n\n");
					break;
				case "1":
					
					input.nextLine(); //Consume newline character 
					
					System.out.println("Enter a phrase to decrypt:");
					
					phrase = input.nextLine();
					
					System.out.println("Enter the degree:");
					
					
					float degree = input.nextFloat();
					

					input.nextLine();
					
					System.out.println("Enter the skew ratio: ");
					
					ratio = input.nextFloat();
					
					
					
					input.nextLine();
					
					System.out.println("Enter the cipher block size: ");
					
					blocksize = input.nextInt();
					
					
					
					System.out.println("\nUnencrypting '" + phrase + "'...");
					
					input.nextLine();
					
					String[] decrypted = decrypt_string(phrase, blocksize, ratio, degree);
					
					System.out.println("\nDECRYPTION OUTPUT >\n");
					for (String s : decrypted) {
						System.out.println("\t" + s + " >> " + binary_to_char(sum_to_binary(Integer.parseInt(s))));
					}
					System.out.println("\n>--- END DECRYPTION OUTPUT ---<\n");
					
					break;
				case "2":
					break;
				default:
					break;
			}
			
			
		}
		input.close();
	}
	
	
	public static void main(String args[]) {
		repl();
	}

}
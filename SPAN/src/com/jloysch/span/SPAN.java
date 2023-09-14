package com.jloysch.span;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
import java.util.concurrent.ThreadLocalRandom;

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
		/*
		if (binary_representation.equals("000000000000")) return 0;
		if (binary_representation.equals("100000000000")) return 1;
		if (binary_representation.equals("010000000000")) return 2;
		if (binary_representation.equals("110000000000")) return 3;
		if (binary_representation.equals("001000000000")) return 4;
		if (binary_representation.equals("101000000000")) return 5;
		if (binary_representation.equals("011000000000")) return 6;
		if (binary_representation.equals("111000000000")) return 7;
		if (binary_representation.equals("000100000000")) return 8;
		if (binary_representation.equals("100100000000")) return 9;
		
		*/
		//System.out.println(binary_representation + " >>> " + binary_representation);
		
		//if (binary_representation.equals("10100000")) return 385; //P
		///if (binary_representation.equals("11100000")) return 386; //p
		
		
		
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
	
	private static void make_triangle() { //TODO Complete later
		
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
		
		//System.out.println("LINKEDLISTENCRYPT");
		int blocks = rawbinary.length()/blocksize;
		
		//int sum_of_data_at_chunk = binary_to_sum(data);
		
		int[] sums = new int[data.length()/blocksize];
		
		System.out.println(data);
		String subblock = "";
		for (int i = 0; i < sums.length; i++) {
			//System.out.println(i);
			subblock = data.substring(0 + blocksize*i, blocksize + blocksize*i);
			sums[i] = binary_to_sum(data.substring(0 + blocksize*i, blocksize + blocksize*i));
			System.out.println("SUBBLOCK > '" + subblock + "'");
			System.out.println("Sum[" + i + "] = " + sums[i]);
		}
		
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		
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
				
				final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
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
	
	//returns sum of the tokens it got back
	
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
		
		for (String s : resequenced_safe) {
			System.out.println("RESEQUENCESAFE > " + s);
		}
		
		int[] dec_tokens = decrypt(resequenced_safe, R, (float) start);
		
		for (int i : dec_tokens) {
			System.out.println("DECTOKEN > " + i);
		}
		
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
		/*
		if (phrase.equals("p")) return "11100001"; 
		if (phrase.equals("P")) return "11100011";
		if (phrase.equals("H")) return "11100111";
		*/
		System.out.println("PHRASE '" + phrase + "' NOT SPECIAL CASE");
		
		for (int i = 0; i < phrase.length(); i++) {
			if (i == phrase.length() - 1) {
				ret += Integer.toBinaryString(phrase.charAt(i));
			} else {
				ret += Integer.toBinaryString(phrase.charAt(i)) + " ";
			}
		}
		
		return ret;
	}
	
	public static String[] to_binary_list(String phrase) { //TODO LOOK
		//String numbers = "0123456789";
		String[] ret = new String[phrase.length()];
		boolean wasnumber = false;
		
		//System.out.println("PHRASE > " + phrase);
		//System.out.println("Phrase length is " + phrase.length());
		/*
		if (binary_list[i].equals("10100000")) binary_list[i] = "11100011"; //p -- manually correct missing alphabet
		if (binary_list[i].equals("11100111")) binary_list[i] = "11100111"; //P
		if (binary_list[i].equals("11100111")) binary_list[i] = "11100111"; //H
		*/
		
		//System.out.println("PHRASE = '" + phrase + "'");
		
		for (int i = 0; i < phrase.length(); i++) {
			
			/*
			switch (phrase.charAt(i)) {
				case '0':
					ret[i] = "00000000";
					wasnumber = true;
					break;
				case '1':
					ret[i] = "10000000";
					wasnumber = true;
					break;
				case '2':
					ret[i] = "01000000";
					wasnumber = true;
					break;
				case '3':
					ret[i] = "11000000";
					wasnumber = true;
					break;
				case '4':
					ret[i] = "00100000";
					wasnumber = true;
					break;
				case '5':
					ret[i] = "10100000";
					wasnumber = true;
					break;
				case '6':
					ret[i] = "01100000";
					wasnumber = true;
					break;
				case '7':
					ret[i] = "11100000";
					wasnumber = true;
					break;
				case '8':
					ret[i] = "00010000";
					wasnumber = true;
					break;
				case '9':
					ret[i] = "10010000";
					wasnumber = true;
					break;
				default:
					break;
					
			}
			
			if (!wasnumber) {
				ret[i] = Integer.toBinaryString(phrase.charAt(i));
				
				//System.out.println("NO NUMBER > '" + phrase + "'");
			}
			*/
			
			
			ret[i] = Integer.toBinaryString(phrase.charAt(i));
			//System.out.println("CONVERT > " + ret[i]);
			
		}
		for (String s : ret) {
			//System.out.println("CONVERET > " + s);
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
			
			/*
			 * FIX PAD ERRORS FOR ALPHABET!!!!!!!!!! TODO BIG ADDRESS
			 */
			
			/*
			 * 		else if (bin.equals("11011000")) return 'l';
				else if (bin.equals("11100010")) return 'q';
				else if (bin.equals("11100001")) return 'p';
				else if (bin.equals("11100011")) return 'P';
				else if (bin.equals("11100111")) return 'H';
			 */
			
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
	 * MAIN ENTRY POINT FOR PROGRAM, returns the crypt as a string | return cipherstr , degree as str
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

				/*
				 * FIX SUM COLLISIONS BEFORE ENCRYPT AND ENCRYPT_BINS!!!!!!!!
				 */
			
			for (String token : bins) {
				//System.out.println("PREENCRYPTTOKEN> '" + token + "'");
				if (stepct == 0) {
					vals = encrypt(token, blocksize, TRI_RATIO, 0, stepct++);
					bigcrypt += (String) vals.get(0);
					//bigcryptasarray[stepct] = (String) vals.get(0);
					startangle = (float) vals.get(1);
				} else {
					 vals = encrypt(token, blocksize, TRI_RATIO, startangle, stepct++);
					bigcrypt += (String) vals.get(0);
					//bigcryptasarray[stepct] = (String) vals.get(0);
				}
				
				
			}
			
			String[] dec = decrypt_string((String) vals.get(0), blocksize, TRI_RATIO, startangle);
			
			//verified = true;
			
			//for (int i = 0; i < dec.length; i++) {
				
				//System.out.println("BINARYCHAR>" + binary_to_char(sum_to_binary(Integer.parseInt(dec[i]))));
				
				//System.out.println("BINCHAR> " + binary_to_char(sum_to_binary(Integer.parseInt(dec[i]))));
				//System.out.println(dec[i]);
		//	}
			
			
			
			verified = true; //TODO EXTERNALIZE
			//VERIFY
		}
		
		return new String[] {bigcrypt, String.valueOf(startangle)};
	}
	
	/*
	 * main entry point for decrypting something encrypted in SPAN, 
return array of sums
	 */
	
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
		
		/*
		for (String s : rettok) {
			System.out.println("RETTOK > " + s);
		}
		*/
		
		return rettok;
	}
	
	public static void donothinglol() {}
	
	public static boolean forcharequals(String s1, String s2) {
		int len = 0;
		
		if (s1.length() != s2.length()) {
			//return false;
			
			len = (s1.length() < s2.length() ? s1.length() : s2.length());
		} else {
			len = s1.length();
		}
		
		boolean ok = true;
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) ok = false;
		}
		
		return ok;
	}
	
	public static char binary_to_char(String bin) { //THE DIRTY DEUCE! MEAT AND POTATOES!
	
		final String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,';] [\\/!@#$%^&*()-+=~`";
		
		String[][] bin_array = new String[alphabet.length()][];
		
		for (int i = 0; i < alphabet.length(); i++) { //make array of every one :]
			bin_array[i] = to_padded_list(to_binary_list(String.valueOf(alphabet.charAt(i))));
		}
		
		System.out.println("BINARY IN > " + bin);
		/*
		if (bin.equals("10000000")) return '1';
		
		else if (bin.equals("11011000")) return 'l';
		else if (bin.equals("11100010")) return 'q';
		else if (bin.equals("11100001")) return 'p';
		else if (bin.equals("11100011")) return 'P';
		else if (bin.equals("11100111")) return 'H';
		
		else if (bin.equals("1000000")) return '2';
		else if (bin.equals("11000000")) return '3';
		else if (bin.equals("100000")) return '4';
		else if (bin.equals("10100000")) return '5';
		else if (bin.equals("1100000")) return '6';
		else if (bin.equals("11100000")) return '7';
		else if (bin.equals("10000")) return '8';
		else if (bin.equals("10010000")) return '9';
		
		else if (forcharequals(bin,"100100000000")) return '9'; //TODO edgecase check 100100000000
		
		else if (forcharequals(bin, "10010000")) return 'H'; //10010000
		
		
		
	
		else
			//System.out.println("BINSUM > NaN '" + bin + "'");
			donothinglol(); //lol
		*/
		
		int step = 0;
		for (String[] s2 : bin_array) {
			for (String s : s2) {
				
				//pad up before compare
				
				//while (bin.length() < s.length()) bin+= "0"; //pad right-most 0
				
				//System.out.println("COMPARE <" + s + " <> " + bin + ">");
				if (binary_to_sum(s) == binary_to_sum(bin)) {
					
					//special case check for numbers
					
					//System.out.println("BINCHAR > " + bin);
					
					
					System.out.println("BINNUM > " + (Integer.parseInt(bin, 2)));
						
					//binnum 128 = 1
					//binnum 64 = 2
					//binnum 192 = 3
					//binnum 32 = 4
					//binnum 160 = 5
					//binnum 96 = 6
					//binnum 224 = 7
					//binnum 16 = 8
					//binnum 144 = 9
					
					
					
					System.out.println("MATCH!\n\n");
					return alphabet.charAt(step);
				}
				step++;
			}
		}
		
		
		System.out.println("BINNUM > RETURNING NULL FOR '" + bin + "'");
		
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
	
	/*
	 * get next biggest size with respect to the tokencount passed from sender
	 */
	public static int getPreambleSizeFor(int tokenct) { //TODO Implementation decision, stay 256 or do dynamic? Static should be better..
		return 256;
	}
	
	private static final int preambleAlphabetOffset = 26;
	private static final char preambleAlphabetOffsetChar = 'a';
	
	public static char generateRandomChar() { 
		return (char)((new Random()).nextInt(preambleAlphabetOffset) + preambleAlphabetOffsetChar);
	}
	
	/*
	 * generate random data that we will encrypt and pass back to sender
	 */
	
	public static String[] generatePreambleFor(int size) {
		String preambleDataPre = "";
		char c;
		for (int i = 0; i < size; i++) {
			 c = generateRandomChar();
			preambleDataPre += c;
			
			System.out.println("CHOOSE > " + c);
		}
		return cryptstring_to_array(encrypt_bins(to_padded_list(to_binary_list(preambleDataPre)), 8, (float) 0.3)[0]);
	}
	
	/*
	 * Takes the crypt tokens and the preamble tokens and carves out a space for it to sit, returns the indices where the actual data sits in the cipher block
	 returns:
	 0 - the preamble, data, postamble as str[]
	 1 - the start int as string
	 2 - the end int as string
	 */
	
	public static String[][] fitToPreamble(String cryptString, String[] preambleBinaryTokens) {
		
		//String[] encryptedPreamble = encrypt_bins(preambleBinaryTokens, 8, (float) 0.3); //TODO FIXUP
		
		//preambleBinaryTokens = cryptstring_to_array(encryptedPreamble[0]);
		
		String[] cryptTokens;
		
		
		int blockpadct = 0;
		char blockdenoter = 'A'; //Base
		
		for (int i = 0; i < cryptString.length(); i++) {
			if (Character.isLetter(cryptString.charAt(i))) {
				blockpadct++;
				
				blockdenoter = cryptString.charAt(i);
			}
		}
		
		
		for (int i = 0; i < preambleBinaryTokens.length; i++) {
			
			if (!((preambleBinaryTokens[i].charAt( preambleBinaryTokens[i].length()-1) == blockdenoter))) {
				preambleBinaryTokens[i] = preambleBinaryTokens[i] + blockdenoter;
			}
		}
		
		cryptTokens = new String[blockpadct];
		int last = 0;
		int repopct = 0;
		
		for (int i = 0; i < cryptString.length(); i++) {
			if (Character.isLetter(cryptString.charAt(i))) {
				cryptTokens[repopct++] = cryptString.substring(last, i + 1);
				last = i + 1;
			}
		}
		/*
		for (String s : cryptTokens) {
			System.out.println("REPOP > " + s);
		}
		*/
		/*
		for (String s : cryptTokens) {
			System.out.println("RECOVEREDCRYPTTOKEN > " + s);
		}
		*/
		int diff = preambleBinaryTokens.length - cryptTokens.length;
		
		if (diff < 0) {
			System.out.println("FATAL, SIZE MISMATCH FOR PREAMBLE FIT!");
			System.out.println("PREAMBLE TOKENS LENGTH = " + preambleBinaryTokens.length);
			System.out.println("CRYPT TOKENS LENGTH = " + cryptTokens.length);
			System.exit(-1);
			return null;
		}
		/*
		for (String s : preambleBinaryTokens) {
			System.out.println("PRE > " + s);
		}
		*/
		//int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		//int randomNum = ThreadLocalRandom.current().nextInt(cryptTokens.length, preambleTokens.length);
		
		boolean goodPair = false;
		
		int start = 0, end = 0;
		
		while (!goodPair) { //find good place to put it
			 start = ThreadLocalRandom.current().nextInt(cryptTokens.length, preambleBinaryTokens.length);
			 end =   start + cryptTokens.length; //TODO + 1?
			 
			 if (!(end > preambleBinaryTokens.length)) goodPair = true; 
		}
		
		//System.out.println("PROPER START > " + start);
		//System.out.println("PROPER END > " + end);
		
		while (end < start) end++;
		
		while ( (end-start) < (cryptTokens.length)) end++;
		
		String new_combined[] = new String[preambleBinaryTokens.length];
		
		int crypt_step = 0;
		
		
		
		for (int i = 0; i < new_combined.length; i++) {
			
			if (i < start) {
				new_combined[i] = preambleBinaryTokens[i]; //fill big array with random data, preamble
			} else if (i >= start && i < end) {
				//System.out.println(end - start);
				new_combined[i] = cryptTokens[crypt_step++]; //insert the crypt
			} else if (i >= end) {
				new_combined[i] = preambleBinaryTokens[i]; //postamble
			}
		}
		
		/*
		 * JUST TOKENS NO SHUFFLE
		 */
		
		return new String[][] {new_combined, new String[] {String.valueOf(start)}, new String[] {String.valueOf(end)}}; //~pay the cost to be the boss~ - James Brown
	}
	
	public static String[] getcryptfromblock(String[] cipherblock, int start, int end) { //TODO FINISH
		//System.out.println("CRYPTRECOVER START > " + start);
		//System.out.println("CRYPTRECOVER END > " + end);
		String[] ret = new String[end-start];
		int retct = 0;
		
		for (int i = start; i < end; i++) { //check off by one
			ret[retct++] = cipherblock[i];
		}
		
		if (!(ret.length == (end-start))) {
			System.out.println("OFFBYONE for getting crypt from block..");
			System.exit(-100);
		}
		
		for (String s : ret) {
			//System.out.println("RECOVERED " + s);
		}
		
		char blockdenoter = 'A'; //TODO FIXUP - CHANGE FOR MORE BLOCKS
		
		for (int i = 0; i < ret.length; i++) {
			
			if (!((ret[i].charAt( ret[i].length()-1) == blockdenoter))) {
				ret[i] = ret[i] + blockdenoter;
			}
		}
		
		return ret;
	}
	
	public static String[] unshufflecipherblock(String cipherblock, float ratio, int start, int end, int blocklength) {
		/*
		int[] replaces = generate_replacement_indices_for(ratio, start, end, blocklength); //TODO FIXUP
		
		String[] unshuffled = new String[replaces.length]; 
		
		//[0] = 75 so do backwards, e.g.
		
		HashMap<Integer, String> placement = new HashMap <Integer, String>();
		
		String[] cipherblockstringasarray = cryptstring_to_array(cipherblock);
		
		for (int i = 0; i < replaces.length; i++) {
			placement.put(replaces[i], cipherblockstringasarray[replaces[i]]);
		}
		
		for (int i = 0; i < blocklength; i++) {
			if (placement.get(i) != null) unshuffled[i] = placement.get(i);
		}
		
		for (int i : replaces) System.out.println("REPLACE > " + i);
		*/
		return cryptstring_to_array(cipherblock); //TODO FIX CIPHER SHUFFLE
	}
	
	public static String[] cryptstring_to_array(String crypt) {
		String[] bigcrypt;
		int ct = 0, last = 0;
		for (int i = 0; i < crypt.length(); i++) if (Character.isLetter(crypt.charAt(i))) ct++;
		
		bigcrypt = new String[ct];
		
		ct = 0;
		
		for (int i = 0; i < crypt.length(); i++) { //2nd pass format and transfer
			if (Character.isLetter(crypt.charAt(i))) {
				bigcrypt[ct++] = crypt.substring(last, i);
				last = i+1;
			}
		}
		
		return bigcrypt;
		
	}
	
	public static int[] generate_replacement_indices_for(float ratio, int start, int end, int cipherblocklength) {
		int[] finalindices = new int[cipherblocklength];
		int[] toswap = new int[cipherblocklength];
		int[] freenums = new int[cipherblocklength];
		float[] progressiveDegrees = new float[cipherblocklength];
		
		for (int i = 0; i < cipherblocklength; i++) {
			freenums[i] = i;
			toswap[i] = -1;
		}
		
		float newdegree = ((float) (start + end)) / ratio;
		//make new start degree independent of other degree based on the ratio and start/end
		
		HashMap<Integer, Boolean> placements = new HashMap<Integer, Boolean>();
		
		for (int i = 0; i < cipherblocklength; i++) {
			progressiveDegrees[i] = next_degree(ratio, newdegree, i);
			toswap[i] = (int) Math.round(next_degree(ratio, newdegree, i));
			placements.put(toswap[i], true);
		}
		

		boolean ok = true;
		
		while (!ok) {
			for (int i = 0; i < toswap.length; i++) {
				if (toswap[i] == -1) { //if we need placement
					for (int p = 0; p < toswap.length; p++) { //get a num that we havent placed for placement
						if (placements.get(p) == null) { //arrived at a number we haven't placed yet so put it in this spot
							toswap[i] = p;
							placements.put(p, true);
						}
					}
				}
			}
		
			//check again
			
			for (int i = 0; i < toswap.length; i++) {
				if (placements.get(i) == null) {
					System.out.println("FATAL! MISSING NO. '" + i + "'");
					ok = false;
				}
			}
		}
		
		if (!ok) System.exit(-1);
		
		
		String checkstrtemp = "";
		
		for (int i : toswap) {
			//System.out.println(i);
			checkstrtemp += i;
		}
		
		System.out.println("CHECKSTR FOR replace >> " + checkstrtemp);
		
		
		
		return toswap;
	}
	
	
	public static void repl() {
		boolean run = true;
		
		String[] menu = new String[] {"Encryptv1","Decrypt","Evaluate", "Encryptv2", "Decryptv2"};
		Scanner input = new Scanner(System.in);
		while (run) {
			
			
			
			System.out.println("Welcome to SPAN (Toy Cipher, Alpha) - Joshua Loysch");
			System.out.println("Please choose a menu option: ");
			System.out.print("\n[0] " + menu[0] + "\n[1] " + menu[1] + "\n[2] " + menu[2] + "\n[3] " + menu[3] + "\n[4] " + menu[4] + "\n\n>> ");
			
			
			
			String choice = input.next();
			float ratio = (float) 0.0;
			int blocksize;
			String phrase;

			switch (choice) {
				case "0":
					
					boolean verified = false;				
					
					while (!verified) {
						input.nextLine(); //Consume newline character 
						
						System.out.println("Enter a phrase to encrypt:");
						
						phrase = input.nextLine();
						
						System.out.println("Enter your skew ratio:\n>> ");
						
						ratio = input.nextFloat();
						
						input.nextLine();
						
						System.out.print("Finally, enter the desired block size:\n>> ");
						blocksize = input.nextInt();
						System.out.println("Encrypting '" + phrase + "'...");
						
						input.nextLine();
						
						
						String[] bins = to_padded_list(to_binary_list(phrase));
						
						
						for (String b : bins) {
							System.out.println("BINS > " + b);
						}
						
						//System.out.println(encrypt_bins(bins, blocksize, (float) ratio));
						
						String[] encrypted = encrypt_bins(bins, blocksize, (float) ratio);
						
						System.out.print("\n\n\n");
						
						String[] verify = decrypt_string(encrypted[0], blocksize, ratio, Float.parseFloat(encrypted[1]));
						
						verified = true;
						String verif_str = "";
						
						for (String s : verify) {
							System.out.println("Verifying..");
							System.out.println("\t" + binary_to_char(sum_to_binary(Integer.parseInt(s))));
							verif_str+=binary_to_char(sum_to_binary(Integer.parseInt(s)));
						}
						System.out.println("Verified.");
						
						
						
						if (verif_str.equals(phrase)) {
							//System.out.println("\n\nOK!");
							
							System.out.println("\nENCRYPTION COMPLETED AND REVERSIBILITY VERIFIED! DETAILS BELOW:\n\n>--- BEGIN ---<");
							System.out.println(encrypted[0]);
							System.out.println(encrypted[1]+"degsR\\\\"+ratio+"\\\\"+8);
							System.out.println(">--- END, PLEASE KEEP FOR YOUR RECORDS ---<\n\n");
						} else {
							verified = false;
							System.out.println("VERIFICATIONMISMATCH!");
							System.out.println("GOT >\n" + verif_str);
							System.out.println("EXPECTED >\n" + phrase);
							System.exit(-1);
						}
					}
					
					
					
//TODO HERE
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
					
					String dcstr = "";
					System.out.println("\nDECRYPTION OUTPUT >\n");
					for (String s : decrypted) {
						System.out.println("\t" + s + " >> " + binary_to_char(sum_to_binary(Integer.parseInt(s))));
						dcstr+= binary_to_char(sum_to_binary(Integer.parseInt(s)));
					}
					System.out.println("ASSTR >\n" + dcstr);
					
					System.out.println("\n>--- END DECRYPTION OUTPUT ---<\n");
					
					//System.out.println("SIZE DECRYPTED " + decrypted.length);
					break;
				case "2":
					break;
				case "3":
					verified = false;				
					
					while (!verified) {
						input.nextLine(); //Consume newline character 
						
						System.out.println("Enter a phrase to encrypt:");
						
						phrase = input.nextLine();
						
						System.out.println("Enter your skew ratio:\n>> ");
						
						ratio = input.nextFloat();
						
						input.nextLine();
						
						System.out.print("Finally, enter the desired block size:\n>> ");
						blocksize = input.nextInt();
						System.out.println("Encrypting '" + phrase + "'...");
						
						input.nextLine();
						
						
						String[] bins = to_padded_list(to_binary_list(phrase));
						
						for (String b : bins) {
							System.out.println("BINS > " + b);
						}
						//System.out.println(encrypt_bins(bins, blocksize, (float) ratio));
						
						String[] encrypted = encrypt_bins(bins, blocksize, (float) ratio);
						
						//int preambleSize = getPreambleSizeFor(100);
						//String preamble = generatePreambleFor(preambleSize);
						
						System.out.print("\n\n\n");
						
						String[] verify = decrypt_string(encrypted[0], blocksize, ratio, Float.parseFloat(encrypted[1]));
						
						verified = true;
						String verif_str = "";
						for (String s : verify) {
							System.out.println("VERIFYING STRING '" + s + "'");
							System.out.println("\t" + binary_to_char(sum_to_binary(Integer.parseInt(s))));
							verif_str+=binary_to_char(sum_to_binary(Integer.parseInt(s)));
						}
						
						if (verif_str.equals(phrase)) {
							//System.out.println("\n\nOK!");
							
							System.out.println("\nENCRYPTION COMPLETED AND REVERSIBILITY VERIFIED! DETAILS BELOW:\n\n>--- BEGIN ---<");
							System.out.println(encrypted[0]);
							System.out.println(encrypted[1]+"degsR\\\\"+ratio+"\\\\"+8);
							System.out.println(">--- END, PLEASE KEEP FOR YOUR RECORDS ---<\n\n");
						} else {
							verified = false;
						}
						
						
						/*
						 * NOW HERE IS WHERE WE MAKE IT INTERESTING WITH PHASE 1 ADDITION, THE BLOCK!
						 */
						
						//TODO Convert to method
						
						String[][] cm = fitToPreamble(encrypted[0], generatePreambleFor(256));
						
						String[] bigBlock = cm[0];
						
						String rawfitencryptnoshufflecu = "";
						for (String s : bigBlock) {
							//System.out.println("BLOCK > " + s);
							rawfitencryptnoshufflecu+=s;
						}
						
						System.out.println("CRYPT_BASE_PHRASE_PRE_FIT_AND_SHUFFLE > " + encrypted[0]);
						
						System.out.println("NOSHUFFLE Cu > " + rawfitencryptnoshufflecu);
						
						/*
						int[] replaces = generate_replacement_indices_for((float) 0.8, Integer.valueOf(cm[1][0]), Integer.valueOf(cm[2][0]), 256);
						
						String[] shuffled_cipher = new String[replaces.length]; 
						
						
						for (int i = 0; i < shuffled_cipher.length; i++) {
							shuffled_cipher[i] = cm[0][replaces[i]];
						}
						
						String cryptstrforshuffle = "";
						
						for (String s : shuffled_cipher) {
							//System.out.println("AFTERBLOCK > " + s);
							cryptstrforshuffle +=s;
						}
						
						System.out.println("SHUFFLE > " + cryptstrforshuffle);
						*/
						
						
						/* UNSHUFFLE STEP *?
						 */
						
						//TODO FIX SHUFFLING IN BLOCKS
						
						//String[] unshuffledcipher = unshufflecipherblock(cryptstrforshuffle, (float) ratio, Integer.parseInt(cm[1][0]), Integer.parseInt(cm[2][0]), 256 );
						
						
						String[] getCryptFromBlock = getcryptfromblock(cm[0], Integer.parseInt(cm[1][0]), Integer.parseInt(cm[2][0]));
						
						System.out.println("CRYPTFROMBLOCK > " + getCryptFromBlock[0]);
						
						String testreshufflestr = "";
						
						for (String s : getCryptFromBlock) {
							testreshufflestr += s;
						}
						
						System.out.println("CRYPT_BASE_PHRASE_FROM_GET > " + testreshufflestr);
						
						decrypted = decrypt_string(testreshufflestr, blocksize, ratio, Float.parseFloat(encrypted[1]));
						String dcassstr = "";
						System.out.println("\nDECRYPTION OUTPUT >\n");
						for (String s : decrypted) {
							System.out.println("\t" + s + " >> " + binary_to_char(sum_to_binary(Integer.parseInt(s))));
							dcassstr += binary_to_char(sum_to_binary(Integer.parseInt(s)));
						}
						System.out.println("\nASSTR > " + dcassstr);
						
						System.out.println("\n>--- END DECRYPTION OUTPUT ---<\n");
						
								
						System.out.println("START > " + cm[1][0]);
						
						System.out.println("END > " + cm[2][0]);
						System.out.println("CIPHER >>\n\n");
						System.out.println("---BEGIN:");
						System.out.println(rawfitencryptnoshufflecu);
						System.out.println("--END CIPHER--");
						System.out.println("\nYour Token > //" + encrypted[1] + "D" + ratio + "R" + cm[1][0] + "S" + cm[2][0] + "F\\\\\n\n");
						
						System.out.println("Save ? [y/n]");
						
						choice = input.next(); 
						
						if (choice.equalsIgnoreCase("y")) {
						 try {
						      FileWriter myWriter = new FileWriter("crypt.txt");
						      myWriter.write(rawfitencryptnoshufflecu.toCharArray());
						      myWriter.close();
						      myWriter = new FileWriter("key.txt");
						      myWriter.write("//" + encrypted[1] + "D" + ratio + "R" + cm[1][0] + "S" + cm[2][0] + "F\\\\");
						      myWriter.close();
						      System.out.println("Successfully wrote to the files.\n\n");
						    } catch (IOException e) {
						      System.out.println("An error occurred.\n\n");
						      e.printStackTrace();
						    }
						}
					}
					
					break;
				case "4":
					
					System.out.println("Please enter the cipher >>");
					String cipher = input.next();
					System.out.println("Please enter your decrypt key in the format '//...\\\\");
					choice = input.next();
					
					String reformatted = choice.substring(2, choice.length()-2); //Chop off
					
					System.out.println("CHOP > " + reformatted);
					
					//String crypt, int blocksize, float TRI_RATIO, float start
					
					//String[] res = decrypt_string (reformatted, r deg start)
					
					//   //38.184643D0.888R153S159F\\ - suggested format, also implies it's shrinking something in so it's a SPAN key!
					
					int blck_ct = 0;
					String[] tokens = new String[4];
					int lastindex = 0;
					
					for (int i = 0; i < reformatted.length(); i++) { //2nd pass format and transfer
						if (Character.isLetter(reformatted.charAt(i))) {

							tokens[blck_ct++] = reformatted.substring(lastindex, i);
							lastindex = i+1;
						}
					}
					
					/*
					 * tokens:
					 * 	0 - degree
					 * 	1 - ratio
					 * 	2 - start
					 * 	3 - end
					 */
					
					for (String s : tokens) {
						System.out.println("KEYTOK > " + s);
					}
					
					/*
					//String[] ciphertokens = cryptstring_to_array(cipher);
					//String[] mintokens = new String[Integer.parseInt(tokens[3])-Integer.parseInt(tokens[2])];
					//int mintokstep = 0;
					
					//for (int i = Integer.parseInt(tokens[2]); i < Integer.parseInt(tokens[3]); i++) { //copy over minimum
					//	mintokens[mintokstep++] = ciphertokens[i];
					}
					
					String recollectedmintok = "";
					
					for (String s : mintokens) recollectedmintok += s + "A"; //TODO Check if missing block labels again for whatever reason
					
					//TODO AGAIN, FIX BLOCK LABELS?! Depending on how I implement how I want it to go next I jut may not..
					*/
					
					/*
					 * decrypt_string(String crypt, int blocksize, float TRI_RATIO, float start)
					 */
					
					//TODO BIG TODO JUST MAKE THE BLOCKSIZE 8 ?!
					
					//System.out.println("MIN > " + recollectedmintok);
					
					//String[] res = decrypt_string (recollectedmintok, 8, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[0]));
				
					System.out.println("Cryptfromblockstart = " + tokens[2]);
					System.out.println("Cryptfromblockend = " + tokens[1]);
					String[] cipherasarray = cryptstring_to_array(cipher);
					
					
					
					char blockdenoter = 'A'; //TODO BLOCKPADFIXUP
					
					for (int i = 0; i < cipherasarray.length; i++) {
						
						if (!((cipherasarray[i].charAt( cipherasarray[i].length()-1) == blockdenoter))) {
							cipherasarray[i] = cipherasarray[i] + blockdenoter;
						}
					}
					
					for (String s : cipherasarray) {
						System.out.println("CIPHERTOKENTOARRAY> " + s);
					}
					
					String[] getCryptFromBlock = getcryptfromblock(cipherasarray, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
					
					System.out.println("CRYPTFROMBLOCK > " + getCryptFromBlock[0]);
					
					String getcrypt = "";
					
					for (String s : getCryptFromBlock) {
						getcrypt += s;
					
					}
					
					System.out.println("CRYPT_BASE_PHRASE_FROM_GET > " + getcrypt);
					
					System.out.println("USE RATIO " + Float.parseFloat(tokens[1]));
					System.out.println("USE BLOCK " + 8);
					System.out.println("USE DEGREE " + Float.parseFloat(tokens[0]));
					
					
					
					decrypted = decrypt_string(getcrypt, 8, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[0])); //TODO STATIC BLOCKSIZE????!! 8?!
					
					System.out.println("\nDECRYPTION OUTPUT >\n");
					dcstr = "";
					for (String s : decrypted) {
						System.out.println("\t" + s + " >> " + binary_to_char(sum_to_binary(Integer.parseInt(s))));
						dcstr += binary_to_char(sum_to_binary(Integer.parseInt(s)));
					}
					System.out.println("\nASSTR >\n" + dcstr);
					
					System.out.println("\n>--- END DECRYPTION OUTPUT ---<\n");
					
					/*
					System.out.println("\nDECRYPTION OUTPUT >\n");
					
					for (String s : res) { 
						System.out.println("\t" + s + " >> " + binary_to_char(sum_to_binary(Integer.parseInt(s))));
					}
					System.out.println("\n>--- END DECRYPTION OUTPUT ---<\n");
					*/
					break;
				default:
					break;
			}
			
			
			
		}
		input.close();
	}
	
	
	public static void main(String args[]) {
		repl();
		//generate_replacement_indices_for((float) 0.8, 28, 56, 256);
		
		//generatePreambleFor(256);
	}

}

package com.jloysch.span;

public class BIGGESTPOWERTWO {
	
	public static void main(String args[]) {
		//double num = Math.pow(10, 39);
		//double num = Math.pow(3.193, 38);
		double num = Math.pow(1000, 13);
		double biggest = 0;
		
		boolean found = false;
		int count = 0;
		
		while (!found) {
			if (Math.pow(2, count) > num) {
				count--;
				found = true;
				biggest = count;
				break;
			};
			count++;
		}
		
		System.out.println("Biggest power for " + num + "\n>>2^" + biggest + "| REMAINDER > " + (num-Math.pow(2, biggest)));
	}

}

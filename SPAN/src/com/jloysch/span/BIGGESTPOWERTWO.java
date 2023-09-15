package com.jloysch.span;

public class BIGGESTPOWERTWO {
	
	public static void main(String args[]) {
		//double num = Math.pow(10, 39);
		//double num = Math.pow(3.193, 38);
		//double num = Math.pow(10, 18);
		//double num = 39376574877.7 ;
		double num = Math.pow(8.996359, 38);
		num = Math.pow(1.3058144850188715, 23);
		num = 206.67292 * Math.pow(2, 205);
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

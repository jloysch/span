package com.jloysch.span;

public class DecimalFormulator {
	
	public static void asciify(String bits) {
		
	}
	
	public static void main(String args[]) {
	
	}
	
	public static void alt() { 
		float num = (float) 0.696818;
		
	
		
		int silly_parse = Integer.parseInt(String.valueOf(num).substring(2,String.valueOf(num).length()));
		
		//find some a^ b + c such that we make another representation.
		
		int a = 0, b = 0, c = 0;
		
		//find balanced a, balanced b then eliminate from there
		
		boolean found_balance_a_b = false;
		int temp = 0;
		
		while (!found_balance_a_b) {
			
			//System.out.println("ab " + a + "," + b);
			temp = (int) Math.pow(a, b);
			
			if (temp < silly_parse) {
				a++;
				b++;
			} else {
				a--;
				b--;
				break;
			}
			
			
		}
		
		System.out.println("Biggest a,b >> " + a + " , " + b);
		
		int current_big = (int) Math.pow(a, b);
		
		System.out.println(current_big);
		
		System.out.println("OFFSET > " + (silly_parse - current_big));
		
		c = silly_parse - current_big;
		
		float mess = (float) Math.sqrt(Math.abs(c));
		
		System.out.println("MESS > " + mess);
		
		System.out.println(silly_parse);
	}

}

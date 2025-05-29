import java.io.*;

public class Driver {
	public static void main(String [] args) {

		//No Args Constructor
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));

		
		double [] c1 = {2,1};
		int [] e1 = {2, 1};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {2, 1};
		int [] e2 = {4, 3};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);

		System.out.println("s(1) = " + s.evaluate(1));
		
		System.out.println("s(-0.5) = " + s.evaluate(-0.5));
		if(s.hasRoot(-0.5)) {
			System.out.println("-0.5 is a root of s");
		}
		else {
			System.out.println("-0.5 is not a root of s");
		}
		if(s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		}
		else {
			System.out.println("1 is not a root of s");
		}

		double[] c3 = {1, 1};
		int[] e3 = {1, 0};
		double[] c4 = {1, -1};
		int[] e4 = {1, 0};

		Polynomial p3 = new Polynomial(c3, e3);
		Polynomial p4 = new Polynomial(c4, e4);

		Polynomial diffSquare = p3.multiply(p4);

		diffSquare.saveToFile("multiply.txt");
		s.saveToFile("add.txt");	

		
		File f = new File("input.txt");
		Polynomial fileP = new Polynomial(f);
		for(int i = 0; i < fileP.coefficients.length; i++) {
			System.out.println("Coefficient: " + fileP.coefficients[i] + " Exponent: " + fileP.exponents[i]);
		}
	}
}
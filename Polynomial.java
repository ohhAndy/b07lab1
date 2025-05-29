import java.io.*;
import java.util.*;

public class Polynomial {
	
	//a
	public double[] coefficients;
	public int[] exponents;
	

	//b
	public Polynomial(){	
		this.coefficients = new double[0];
		this.exponents = new int[0];
	}
	
	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];
		for(int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
	}

	//d (File Constructor)
	public Polynomial(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			br.close();

			line = line.replace("-", "+-");
			String[] terms = line.split("\\+");


			ArrayList<Double> tempCoeff = new ArrayList<Double>();
			ArrayList<Integer> tempExpo = new ArrayList<Integer>();
			for(int i = 0; i < terms.length; i++) {
				if(terms[i].contains("x")) {
					String[] split = terms[i].split("x");

					//Coeff Cases
					if(split[0].equals("")) {
						tempCoeff.add(1.0);
					}
					else if(split[0].equals("-")) {
						tempCoeff.add(-1.0);
					}
					else {
						tempCoeff.add(Double.parseDouble(split[0]));
					}
					
					//Expo Cases
					if(split.length == 1 || split[1].equals("")) {
						tempExpo.add(1);
					}
					else {
						tempExpo.add(Integer.parseInt(split[1]));
					}
				
				}
				else {
					tempCoeff.add(Double.parseDouble(terms[i]));
					tempExpo.add(0);
				}
				
			}
			this.coefficients = new double[tempCoeff.size()];
			this.exponents = new int[tempExpo.size()];
			for(int i = 0; i < this.coefficients.length; i++) {
				this.coefficients[i] = tempCoeff.get(i);
				this.exponents[i] = tempExpo.get(i);
			}


		}
		catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException, file not found");
		}
		catch(IOException e) {
			System.out.println("IOException");
		}
		

	}

	public Polynomial add(Polynomial p) {
		HashMap<Integer, Double> terms = new HashMap<Integer, Double>();

		for(int i = 0; i < p.coefficients.length; i++) {
			terms.put(p.exponents[i], p.coefficients[i]);
		}
		for(int i = 0; i < this.coefficients.length; i++) {
			if(terms.containsKey(this.exponents[i])) {
				terms.put(this.exponents[i], terms.get(this.exponents[i]) + this.coefficients[i]);
			}
			else {
				terms.put(this.exponents[i], this.coefficients[i]);
			}
		}

		ArrayList<Double> coeffList = new ArrayList<Double>();
		ArrayList<Integer> expoList = new ArrayList<Integer>();
		
		for (Map.Entry<Integer, Double> entry : terms.entrySet()) {
			if(entry.getValue() == 0) {
				continue;
			}
			coeffList.add(entry.getValue());
			expoList.add(entry.getKey());
		}

		double[] newCoefficients = new double[coeffList.size()];
		int[] newExponents = new int[expoList.size()];

		for(int i = 0; i < coeffList.size(); i++) {
			newCoefficients[i] = coeffList.get(i);
			newExponents[i] = expoList.get(i);
		}

		return new Polynomial(newCoefficients, newExponents);
	}

	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result += Math.pow(x, this.exponents[i]) * this.coefficients[i];
		}
		return result;		
	}


	public boolean hasRoot(double x) {
		return (this.evaluate(x) == 0.0);
	}

	//c
	public Polynomial multiply(Polynomial p) {
		HashMap<Integer, Double> terms = new HashMap<Integer, Double>();
		
		for(int i = 0; i < this.coefficients.length; i++) {
			for(int j = 0; j < p.coefficients.length; j++) {
				int tempExpo = this.exponents[i] + p.exponents[j];
				double tempCoeff = this.coefficients[i] * p.coefficients[j];

				if(terms.containsKey(tempExpo)) {
					terms.put(tempExpo, terms.get(tempExpo) + tempCoeff);
				}
				else {
					terms.put(tempExpo, tempCoeff);
				}
			}
		}

		ArrayList<Double> coeffList = new ArrayList<Double>();
		ArrayList<Integer> expoList = new ArrayList<Integer>();
		
		for (Map.Entry<Integer, Double> entry : terms.entrySet()) {
			if(entry.getValue() == 0) {
				continue;
			}
			coeffList.add(entry.getValue());
			expoList.add(entry.getKey());
		}

		double[] newCoefficients = new double[coeffList.size()];
		int[] newExponents = new int[expoList.size()];

		for(int i = 0; i < coeffList.size(); i++) {
			newCoefficients[i] = coeffList.get(i);
			newExponents[i] = expoList.get(i);
		}

		return new Polynomial(newCoefficients, newExponents);

	}

	//e
	public void saveToFile(String fileName) {
		try {
			PrintWriter outFile = new PrintWriter(new FileWriter(fileName));

			String line = "";
			for(int i = 0; i < this.coefficients.length; i++) {
				if(this.exponents[i] == 0) {
					if(this.coefficients[i] > 0) {
						line += "+" + this.coefficients[i];
					}
					else {
						line += this.coefficients[i];
					}
				}
				else {
					if(this.coefficients[i] > 0) {
						line += "+" + this.coefficients[i] + "x" + this.exponents[i];
					}
					else {
						line += this.coefficients[i] + "x" + this.exponents[i];
					}
				}
			}
			if(line.charAt(0) == '+') {
				line = line.substring(1);
			}
			outFile.print(line);
			outFile.close();
		}
		catch(IOException e) {
			System.out.println("IOException");
		}
	}


}

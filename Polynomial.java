public class Polynomial {
	
	//i)
	public double[] coefficients;
	
	//ii)
	public Polynomial(){	
		this.coefficients = new double[1];
		this.coefficients[0] = 0;
	}
	
	//iii)
	public Polynomial(double[] coefficients) {
		this.coefficients = new double[coefficients.length];
		for(int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
		}
	}

	//iv)
	public Polynomial add(Polynomial p) {
		double[] result;
		if(this.coefficients.length < p.coefficients.length) {
			result = new double[p.coefficients.length];	
			for(int i = 0; i < p.coefficients.length; i++) {
				if(i >= this.coefficients.length) {
					result[i] = p.coefficients[i];
				}
				else {
					result[i] = this.coefficients[i] + p.coefficients[i];
	 			}
			}				
		}
		else {
			result = new double[this.coefficients.length];
			for(int i = 0; i < this.coefficients.length; i++) {
				if(i >= p.coefficients.length) {
					result[i] = this.coefficients[i];
				}
				else {
					result[i] = this.coefficients[i] + p.coefficients[i];
	 			}
			}		
		}
		return new Polynomial(result);
	}

	//v)
	public double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result += Math.pow(x, i) * this.coefficients[i];
		}
		return result;		
	}

	//vi
	public boolean hasRoot(double x) {
		return (this.evaluate(x) == 0);
	}

}
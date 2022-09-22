public class Polynomial {
    double[] coef;
    
	public Polynomial() {
        coef = new double[] {0};
    }
    
    public Polynomial(double arr[]) {
        int size = arr.length;
        coef = new double[size];
        for (int i = 0; i < size; i++) {
            coef[i] = arr[i];
        }
    }

    public Polynomial add(Polynomial poly) {
        int size0 = this.coef.length;
        int size1 = poly.coef.length;
        double[] new_coef = new double[Math.max(size0, size1)];
        for (int i = 0; i < size0; i++)
        {
            new_coef[i] += this.coef[i];
        }
        for (int i = 0; i < size1; i++)
        {
            new_coef[i] += poly.coef[i];
        }
        return new Polynomial(new_coef);
    }

    public double evaluate(double x) {
        int size = this.coef.length;
        double val = 0;
        for (int i = 0; i < size; i++) {
            val += this.coef[i] * Math.pow(x, i);
        }
        return val;
    }

    public boolean hasRoot(double root) {
        return (this.evaluate(root) == 0);
    }

    public static void main(String[] args) {
        
    }
}
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.Arrays;

public class Polynomial {
    double[] coef;
    int[] exp;
    
	public Polynomial() {
        coef = new double[] {0};
        exp = new int[] {0};
    }
    
    public Polynomial(double arr1[], int arr2[]) {
        int size1 = arr1.length;
        int size2 = arr2.length;
        coef = new double[size1];
        exp = new int[size2];
        for (int i = 0; i < size1; i++) {
            coef[i] = arr1[i];
            exp[i] = arr2[i];
        }
    }

    public Polynomial(File f) {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String polynomial = reader.nextLine();
                String[] parts = polynomial.split("(?=[-+])", 0);
                this.coef = new double[parts.length];
                this.exp = new int[parts.length]; 
                int index = 0;
                for (int i = 0; i < parts.length; i++) {
                    String[] split_parts = parts[i].split("x", 0);
                    if (split_parts.length == 1) { // parts[i] is a constant
                        this.coef[index] = Double.parseDouble(split_parts[0]);
                        this.exp[index] = 0;
                    }
                    else {
                        this.coef[index] = Double.parseDouble(split_parts[0]);
                        this.exp[index] = Integer.parseInt(split_parts[1]);
                    }
                    index++;
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String fname) {
        String toWrite = "";
        for (int i = 0; i < this.exp.length; i++) {
            if (i != 0) { //First coefficient
                if (this.coef[i] > 0) {
                    toWrite = toWrite + "+";
                }
            }
            toWrite = toWrite + this.coef[i];
            if (this.exp[i] == 0) { // if constant, omit x
                continue;
            }
            toWrite = toWrite + "x" + this.exp[i]; // Adds x{exp[i]}
        }
        try {
            FileWriter writer = new FileWriter(fname);
            writer.write(toWrite);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int findIndex(int[] exp, int cur_exp) {
        for (int i = 0; i < exp.length; i++) {
            if (cur_exp == exp[i])
                return i;
        }
        return -1; // Not in array
    }

    public Polynomial simplify(int[] new_exp, double[] new_coef) {
        int len = 0;
        for (int i = 0; i < new_coef.length; i++) {
            if (new_coef[i] != 0)
                len++;
        }
        double[] newer_coef = new double[len];
        int[] newer_exp = new int[len];
        int newer_index = 0;
        for (int i = 0; i < len; i++) {
            // System.out.println(i);
            if (new_coef[i] != 0) {
                newer_coef[newer_index] = new_coef[i];
                newer_exp[newer_index] = new_exp[i];
                newer_index++;
            }
        }
        return new Polynomial(newer_coef, newer_exp);
    }

    public Polynomial add(Polynomial poly) {
        int size0 = this.exp.length;
        int size1 = poly.exp.length;
        double[] new_coef = new double[size0 + size1];
        int[] new_exp = new int[size0 + size1];
        int new_index = 0;
        for (int i = 0; i < size1; i++) {
            // System.out.println(this.exp[i]);
            new_exp[new_index] = this.exp[i];
            // System.out.println(new_exp[new_index]);
            new_coef[new_index] = this.coef[i];
            new_index++;
        }
        for (int j = 0; j < size0; j++) {
            int index = findIndex(new_exp, poly.exp[j]);
            if (index == -1) {    // New exponent not in new poly
                new_exp[new_index] = poly.exp[j];
                new_coef[new_index] = poly.coef[j];
                new_index++;
            }
            else {
                new_coef[index] = this.coef[j];
            }
        }
        return simplify(new_exp, new_coef);
    }

    public Polynomial multiply(Polynomial poly) {
        int size0 = this.coef.length;
        int size1 = poly.coef.length;
        double[] new_coef = new double[size0 + size1];
        int[] new_exp = new int[size0 + size1];
        int new_index = 0;
        for (int i = 0; i < size0; i++) {
            for (int j = 0; j < size1; j++) {
                int temp_index = findIndex(new_exp, (this.exp[i] + poly.exp[j]));
                if (temp_index == -1) {
                    new_exp[new_index] = this.exp[i] + poly.exp[j];
                    new_coef[new_index] = this.coef[i] * poly.coef[j];
                    new_index++;
                }
                else {
                    new_coef[temp_index] += this.coef[i] * poly.coef[j];
                }
            }
        }
        return simplify(new_exp, new_coef);
    }

    public double evaluate(double x) {
        double sum = 0;
        for (int i = 0; i < this.coef.length; i++) {
            sum += this.coef[i] * Math.pow(x, this.exp[i]);
        }
        return sum;
    }

    public boolean hasRoot(double root) {
        return (this.evaluate(root) == 0);
    }

    // public static void main(String[] args) {
        
    // }
}
import java.io.File;
import java.util.Arrays;

public class Driver { 
    public static void main(String [] args) { 
    Polynomial p = new Polynomial(); 
    System.out.println(p.evaluate(3)); 
     double [] c1 = {6,5};
     int [] e1 = {0,3}; 
     Polynomial p1 = new Polynomial(c1, e1); 
     double [] c2 = {-2,-9}; 
     int [] e2 = {1, 4};
     Polynomial p2 = new Polynomial(c2, e2); 
     Polynomial s = p1.add(p2); 
    Polynomial t = p1.multiply(p2);
     System.out.println("s(0.1) = " + s.evaluate(0.1));
     System.out.println("t(0.1) = " + t.evaluate(0.1));
    //  System.out.println(Arrays.toString(t.coef));
    //  System.out.println(Arrays.toString(t.exp));
     if(s.hasRoot(1)) 
      System.out.println("1 is a root of s"); 
     else 
      System.out.println("1 is not a root of s");
      File f = new File("C:\\Users\\fcwer\\b07lab1\\b07lab1\\polynomial.txt");
      Polynomial v = new Polynomial(f);
       System.out.println(Arrays.toString(v.coef));
       System.out.println(Arrays.toString(v.exp));
      s.saveToFile("polynomial.txt");
    } 
} 
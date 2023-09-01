//package project1;

public class PolymorphismQ3 {

    public static void f(A x) {
        A y = x;
        y.key = x.key + 1;
    }

    public static void f(B x) {
        B y = new B();
        y.key = x.key + 2;
        x = y;
    }

    public void getBinarySequences(int length) {
        String seq = new String();
        getBinarySequences(length, seq);
    }

    private  void getBinarySequences(int length, String seq) {
        if (seq.length() == length) {
            System.out.printf("%s %n", seq.toString());
        } else if (seq.length() == 0) { 
            for (int i=1; i<=9; i++) {
            String seq0 = seq + i;  
            getBinarySequences(length, seq0);
        }
        } else if (seq.length() == length - 1) {  
            for (int i=1; i<=9; i++) {
            String seq0 = seq + i; 
            getBinarySequences(length, seq0);
            }
        } else {  
            for (int i=0; i<=9; i++){
            String seq0 = seq + i;  
            getBinarySequences(length, seq0);
        }
        }

        
    }
public int foo(int x, int y) {
            if (x==0)          
            return 0;
            else             
            { int tmp = x / 2;
            int val = foo ( tmp, y );
            if  (x % 2 ==0)              
            return (val + val); 
            else              
            return (val + val + y); 
        }
        
        }
    public static void main(String[] args) {

        PolymorphismQ3 s = new PolymorphismQ3();
        int tmp= s.foo(4,5);
System.out.println(tmp);
        /*
         * A p = new A( );
         * p.key = 3;
         * B q = new B( );
         * q.key = 10;
         * f(p);
         * System.out.println(p.key);
         * f(q);
         * System.out.println(q.key);
         * p = q;
         * f(p);
         * System.out.println(p.key);
         * }
         */
    }

    class A {
        public int key;
    }

    class B extends A {
    }
}

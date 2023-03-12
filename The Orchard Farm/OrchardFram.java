import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrchardFram {

    class Apple{
        static int days=10;
        static int qty=400;
        static int cost = qty*150/5;
    }
    class Orange{
        static int days=6;
        static int qty=280;
        static int cost = qty*250/7;
    }
    class Mango {
        static int days=15;
        static int qty=2200;
        static int cost = qty*100/8;
    }
    class Lemon {
        static int days=5;
        static int qty=1500;
        static int cost = qty*50/10;
    }
    class Coconut {
        static int days=15;
        static int qty=75;
        static int cost = qty*1600/15;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int T;
        // Scanner sc = new Scanner(new File("sample_input.txt"));
        // PrintWriter out = new PrintWriter("sample_output.txt");

        Scanner sc = new Scanner(new File("TOF_small.txt"));
        PrintWriter out = new PrintWriter("TOF_small_output.txt");

        // Scanner sc = new Scanner(new File("TOF_large.txt"));
        // PrintWriter out = new PrintWriter("TOF_large_output.txt");
        T=sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N,D,total_income=0;
            N=sc.nextInt();
            D=sc.nextInt();
            int max_allowed_days=(int)(N*0.4);
            total_income += Apple.cost*(int)(D/Apple.days);
            total_income += Orange.cost*(int)(D/Orange.days);
            total_income += Mango.cost*(int)(D/Mango.days);
            total_income += Lemon.cost*(int)(D/Lemon.days);
            total_income += Coconut.cost*(int)(D/Coconut.days);
            N-=5;
            ArrayList<Integer> temp=new ArrayList<>();
            temp.add(Apple.cost*(int)(D/Apple.days));
            temp.add(Orange.cost*(int)(D/Orange.days));
            temp.add(Mango.cost*(int)(D/Mango.days));
            temp.add(Lemon.cost*(int)(D/Lemon.days));
            temp.add(Coconut.cost*(int)(D/Coconut.days));
            while(N!=0){
                int max = Collections.max(temp);
                for (int k = 0; k < max_allowed_days-1 && N!=0; k++) {
                    total_income+=max;
                    N--;
                }
                if(N!=0)
                    temp.remove(temp.indexOf(max));
            }
            out.println("Case #"+(i+1)+": "+total_income);
        }

        out.close();
    }
}

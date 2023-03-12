import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TWSP{

    int A;
    int B;
    int C;

    public static void twsp_sort(TWSP[] twsp){
        for (int i = 0; i < twsp.length-1; i++) {
            int minidx=i;
            for (int j = i+1; j < twsp.length; j++) {
                if(twsp[j].A <= twsp[minidx].A){
                    minidx=j;
                }
            }
            TWSP temp = twsp[minidx];
            twsp[minidx] = twsp[i];
            twsp[i] = temp;
        }
        for (int i = 0; i < twsp.length-1; i++) {
            int minidx=i;
            for (int j = i+1; j < twsp.length; j++) {
                if((twsp[j].A <= twsp[minidx].A)
                    && ((twsp[j].B >= twsp[j].A) && (twsp[j].B > twsp[minidx].B))){
                    minidx=j;
                }
            }
            TWSP temp = twsp[minidx];
            twsp[minidx] = twsp[i];
            twsp[i] = temp;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        int T;
        // Scanner sc = new Scanner(new File("sample_input.txt"));
        // PrintWriter out = new PrintWriter("sample_output.txt");

        // Scanner sc = new Scanner(new File("TWSP_small.txt"));
        // PrintWriter out = new PrintWriter("TWSP_small_output.txt");

        Scanner sc = new Scanner(new File("TWSP_large.txt"));
        PrintWriter out = new PrintWriter("TWSP_large_output.txt");
        
        T=sc.nextInt();
        TWSP[] twsp = new TWSP[T];
        for (int i = 0; i < T; i++) {
            twsp[i]=new TWSP();
            twsp[i].A = sc.nextInt();
            twsp[i].B = sc.nextInt();
            twsp[i].C = sc.nextInt();
        }
        
        twsp_sort(twsp);
        for (TWSP t : twsp) {
            out.println(t.A+" "+t.B+" "+t.C);
        }
        out.close();
    }
}
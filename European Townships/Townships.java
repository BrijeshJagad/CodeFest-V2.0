import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Townships {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner sc = new Scanner(new File("sample_input.txt"));
        // PrintWriter out = new PrintWriter("sample_output.txt");

        // Scanner sc = new Scanner(new File("ET_small.txt"));
        // PrintWriter out = new PrintWriter("ET_small_output.txt");

        Scanner sc = new Scanner(new File("ET_large.txt"));
        PrintWriter out = new PrintWriter("ET_large_output.txt");

        int T;
        
        T=Integer.parseInt(sc.nextLine());
        
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(sc.nextLine());
            double total_hrs=0,accent_qty=0,regular_qty=0;
            for (int j = 0; j < N; j++) {
                String input = sc.nextLine();
                String[] temp= input.split(",");
                int[] arr = new int[]{
                    Integer.parseInt(temp[0].trim()),
                    Integer.parseInt(temp[1].trim()),
                    Integer.parseInt(temp[2].trim()),
                    Integer.parseInt(temp[3].trim())
                };
                accent_qty+=((arr[1]*3+arr[2]*4+arr[3]*6) *(double)1/3)*1.5;
                regular_qty+=((arr[1]*3+arr[2]*4+arr[3]*6) *(double)2/3)*2.25;
                total_hrs+=((arr[1]*3+arr[2]*4+arr[3]*6) *3);
            }
            out.println("case #"+(i+1)+":"+total_hrs+", "+accent_qty+", "+regular_qty);
        }
        out.close();
    }
}

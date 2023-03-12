import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Timesheet {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        int T;
        Scanner sc = new Scanner(new File("sample_input.txt"));
        PrintWriter out = new PrintWriter("sample_output.txt");

        // Scanner sc = new Scanner(new File("TT_small.txt"));
        // PrintWriter out = new PrintWriter("TT_small_output.txt");

        // Scanner sc = new Scanner(new File("TT_large.txt"));
        // PrintWriter out = new PrintWriter("TT_large_output.txt");
        
        T=Integer.parseInt(sc.nextLine());
        ArrayList<String> logs = new ArrayList<>();
        ArrayList<Employee> emp = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            logs.add(sc.nextLine());
            String arr[] = logs.get(i).split("  ");
            // System.out.println(Arrays.asList(arr));
            boolean found=false;
            for (Employee e : emp) {
                if(e.id.equalsIgnoreCase(arr[0])){
                    found=true;
                }
            }
            if(!found){
                emp.add(new Employee(arr[0]));
            }
            for (Employee e : emp) {
                if(e.id.equalsIgnoreCase(arr[0])){
                    e.datetime.add(new SimpleDateFormat("DD-MM-YYYY hh:mm:ss").parse(arr[1]));
                    e.status.add(arr[2]);
                    break;
                }
            }
        }
        
        for (Employee e : emp) {
            System.out.println(e.datetime.get(0));
            e.sort_date();
        }
        out.close();
    }

    static class Employee{
        String id;
        ArrayList<Date> datetime;
        ArrayList<String> status;
        Employee(String id){
            this.id = id;
            datetime = new ArrayList<>();
            status = new ArrayList<>();
        }
        @Override
        public String toString() {
            return this.id;
        }

        void sort_date() throws ParseException{
            
            Collections.sort(datetime,new Comparator<Date>() {
                
                @Override
                public int compare(Date o1, Date o2) {
                    return o1.compareTo(o2);
                }
            });
            System.out.println(datetime);
        }
    }
}

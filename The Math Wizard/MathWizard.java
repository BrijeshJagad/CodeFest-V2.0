import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathWizard {

    final static List<String> allowedStrings = Arrays.asList("and", "zero", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
			"seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety", "hundred");


    private static long convertWordsToNum(List<String> words) {
		long finalResult = 0;
		long intermediateResult = 0;
		for (String str : words) {
			// clean up string for easier processing
			str = str.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
			if (str.equalsIgnoreCase("zero")) {
				intermediateResult += 0;
			} else if (str.equalsIgnoreCase("one")) {
				intermediateResult += 1;
			} else if (str.equalsIgnoreCase("two")) {
				intermediateResult += 2;
			} else if (str.equalsIgnoreCase("three")) {
				intermediateResult += 3;
			} else if (str.equalsIgnoreCase("four")) {
				intermediateResult += 4;
			} else if (str.equalsIgnoreCase("five")) {
				intermediateResult += 5;
			} else if (str.equalsIgnoreCase("six")) {
				intermediateResult += 6;
			} else if (str.equalsIgnoreCase("seven")) {
				intermediateResult += 7;
			} else if (str.equalsIgnoreCase("eight")) {
				intermediateResult += 8;
			} else if (str.equalsIgnoreCase("nine")) {
				intermediateResult += 9;
			} else if (str.equalsIgnoreCase("ten")) {
				intermediateResult += 10;
			} else if (str.equalsIgnoreCase("eleven")) {
				intermediateResult += 11;
			} else if (str.equalsIgnoreCase("twelve")) {
				intermediateResult += 12;
			} else if (str.equalsIgnoreCase("thirteen")) {
				intermediateResult += 13;
			} else if (str.equalsIgnoreCase("fourteen")) {
				intermediateResult += 14;
			} else if (str.equalsIgnoreCase("fifteen")) {
				intermediateResult += 15;
			} else if (str.equalsIgnoreCase("sixteen")) {
				intermediateResult += 16;
			} else if (str.equalsIgnoreCase("seventeen")) {
				intermediateResult += 17;
			} else if (str.equalsIgnoreCase("eighteen")) {
				intermediateResult += 18;
			} else if (str.equalsIgnoreCase("nineteen")) {
				intermediateResult += 19;
			} else if (str.equalsIgnoreCase("twenty")) {
				intermediateResult += 20;
			} else if (str.equalsIgnoreCase("thirty")) {
				intermediateResult += 30;
			} else if (str.equalsIgnoreCase("forty")) {
				intermediateResult += 40;
			} else if (str.equalsIgnoreCase("fifty")) {
				intermediateResult += 50;
			} else if (str.equalsIgnoreCase("sixty")) {
				intermediateResult += 60;
			} else if (str.equalsIgnoreCase("seventy")) {
				intermediateResult += 70;
			} else if (str.equalsIgnoreCase("eighty")) {
				intermediateResult += 80;
			} else if (str.equalsIgnoreCase("ninety")) {
				intermediateResult += 90;
			} else if (str.equalsIgnoreCase("hundred")) {
				intermediateResult *= 100;
			}
		}

		finalResult += intermediateResult;
		intermediateResult = 0;
		return finalResult;
	}

    public static int evaluate(String expr) {
        
        char[] ch = expr.toCharArray();
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ')
                continue;
            
            if (ch[i] >= '0' &&
                    ch[i] <= '9') {
                StringBuilder sbuf = new StringBuilder();
                while (i < ch.length && ch[i] >= '0' && ch[i] <= '9')
                    sbuf.append(ch[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
                i--;
            } else if (ch[i] == '(')
                ops.push(ch[i]);
            else if (ch[i] == ')') {
                while (ops.peek() != '(')
                    values.push(operation(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            } else if (ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/') {
                while (!ops.empty() && checkPrecedence(ch[i], ops.peek()))
                    values.push(operation(ops.pop(), values.pop(), values.pop()));
                ops.push(ch[i]);
            }
        }
        while (!ops.empty())
            values.push(operation(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }

    public static boolean checkPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static int operation(char op, int b, int a) {
        switch (op) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
            }
        }
        return 0;
    }

    public static boolean checkStatement(String expression, int result) {
        double val=0;
        val= Double.parseDouble(expression.split("=")[1].trim());
        return result == val;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner sc = new Scanner(new File("sample_input.txt"));
        // PrintWriter out = new PrintWriter(new File("sample_output.txt"));
        
        Scanner sc = new Scanner(new File("TMW_small.txt"));
        PrintWriter out = new PrintWriter("TMW_small_output.txt");

        // Scanner sc = new Scanner(new File("TWSP_large.txt"));
        // PrintWriter out = new PrintWriter("TWSP_large_output.txt");
        
        int T=Integer.parseInt(sc.nextLine());
        String[] expressions = new String[T];
        for (int i = 0; i < T; i++) {
            expressions[i] = sc.nextLine();
            //subtract, plus, multiple, division => +,-,*,/
            expressions[i].replaceAll("plus", "+");
            expressions[i].replaceAll("subtract", "-");
            expressions[i].replaceAll("multiple", "*");
            expressions[i].replaceAll("division", "/");
            
            
            expressions[i]=expressions[i].replaceAll("equals", "=");
            
            System.out.println(expressions[i]);
            String expr = expressions[i].split("=")[0];

            Pattern p = Pattern.compile("[A-Za-z]+");
            Matcher m = p.matcher(expr);
            while(m.find()){
                // System.out.println(Arrays.asList(expr.substring(m.start(),m.end()).split(" ")));
                int k = (int) convertWordsToNum(Arrays.asList(expr.substring(m.start(),m.end()).split(" ")));
                System.out.println(k);
                
                expr=expr.replaceAll(expr.substring(m.start(),m.end()), k+"");
            }
            System.out.println(expr);
            int result = evaluate(expr);
            out.println("Case#" + (i + 1) + ": " + checkStatement(expressions[i], result));
            
        }
        out.close();
    }
}
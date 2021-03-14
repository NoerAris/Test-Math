package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class main {
	public static void main(String[] args) {
		double[] arrayDouble = new double[] {1,2,3,4,5,6,7,8,9,10};
		double mean = countMean(arrayDouble);
		System.out.println("Result mean : " + mean);
		
		String[] arrayString = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int rev = RevPolishNotation(arrayString);
        System.out.println("Result Reverse Polish Notation : " + rev);
        
		String[] bitmap = new String[] {  "01101", "11101", "00010", "01100" };
		//List<String> list = Arrays.asList(new String[]{"foo", "bar"});
        int rs = BitmapHoles(bitmap);
        System.out.println("Holes : " + rs);
        
	}
	
	//Mean
	private static double countMean(double[] m) {
		double total = 0;
		double result = 0;
		
		if (m.length > 0) {
			for (int i = 0; i < m.length; i++) {
				total = total + m[i];
			}
			result = total/m.length;
		}
		
		
		return result;
	}

	//Reverse Polish Notation
	private static int RevPolishNotation(String[] inputArray) {
		Stack<String> stack = new Stack<String>();
		int a, b;
		String choice;
		int res = 0;
		String operators = "+-*/";
		
		if (inputArray.length > 0) {
			for (int i = 0; i < inputArray.length; i++) {
				if (!operators.contains(inputArray[i])) {
					stack.push(inputArray[i]);
					continue;
				} else {
					choice = inputArray[i];
					a = Integer.parseInt(stack.pop());
					b = Integer.parseInt(stack.pop());
				
					switch (choice) {
						case "+":
							stack.push(String.valueOf(b + a));
							
							break;
						case "-":
							stack.push(String.valueOf( b -a));
							
							break;
						case "*":
							stack.push(String.valueOf(b * a));
							
							break;
						case "/":
							stack.push(String.valueOf(b / a));
							
							break;
						default:
							continue;
					}
				}
			}
			res = Integer.parseInt(stack.pop());
		}
		
		return res;
	}
	
	//Bitmap Holes
	private static int BitmapHoles(String[] strArr) {
		List<String[]> listArry = new ArrayList<String[]>();
		List<int[]> listInt = new ArrayList<int[]>();
		
		for (int i = 0; i < strArr.length; i++) {
			listArry.add(strArr[i].split(""));
			System.out.println(strArr[i]);
			String[] x = strArr[i].split("");
			int[] arr = Stream.of(x).mapToInt(Integer::parseInt).toArray();
			listInt.add(arr);
		}
		//System.out.println(listArry.get(0)[0]);
		
		//Rows
		int count = 0;
		int res = 0;
		for(int i = 0; i < listInt.size(); i++) {
			if (count >= 2) {
				res++;
			}
			count = 0;
			for(int j = 0; j < listInt.get(i).length; j++) {
				if(listInt.get(i)[j] == 0) {
					count++;
					int sz = (listInt.get(i).length)-1;
					if ((listInt.size()-1) == i && sz == j) {
						if (count >= 2) {
							res++;
						}
						count = 0;
					}
				} else {
					if (count >= 2) {
						res++;
					}
					count = 0;
				}
			}
		}
		
		//Cols
		count = 0;	
		for(int i = 0; i < listInt.get(0).length; i++) {
			if (count >= 2) {
				res++;
			}
			count = 0;
			for(int j = 0; j < listInt.size(); j++) {
				System.err.println("[" + j + "] " + "[" + i + "]");
				if(listInt.get(j)[i] == 0) {
					count++;
					int sz = (listInt.get(j).length)-1;
					if ((listInt.size()-1) == j && sz == i) {
						if (count >= 2) {
							res++;
						}
						count = 0;
					}
				} else {
					if (count >= 2) {
						res++;
					}
					count = 0;
				}
			}
		}
		return res;
	}

}

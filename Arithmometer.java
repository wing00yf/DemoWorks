package test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Arithmometer {

	public static void main(String[] args) {
		String expression = "1+2*3+4/2-3";
		
		char[] chars = expression.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<chars.length;i++) {
			if(Character.isDigit(chars[i])){
				sb.append(chars[i]);
			}else {
				sb.append(",").append(chars[i]).append(",");
			}
		}
		List<String> list = Stream.of(sb.toString().split(",")).collect(Collectors.toList());
		System.out.println(stepCalculate(list).get(0));
	}
	
	public static List<String> stepCalculate(List<String> cls){
		if(cls.size()==1) {
			return cls;
		}
		//先乘除
		for(int i=0;i<cls.size();i++) {
			if(i<cls.size() && cls.get(i).equals("*")) {
				calculate(cls,i,"*");
				i = 0;
			}
			if(i<cls.size() && cls.get(i).equals("/")) {
				calculate(cls,i,"/");
				i = 0;
			}
		}
		//再加减
		for(int i=0;i<cls.size();i++) {
			if(i<cls.size() && cls.get(i).equals("+")) {
				calculate(cls,i,"+");
				i = 0;
			}
			if(i<cls.size() && cls.get(i).equals("-")) {
				calculate(cls,i,"-");
				i = 0;
			}
		}
		
		return cls;
	}
	
	private static void calculate(List<String>cls, int i, String symbol) {
		double a = Double.valueOf(cls.get(i-1));
		double b = Double.valueOf(cls.get(i+1));
		double re = 0;
		
		switch (symbol){
        	case "+": re = a + b; break;
        	case "-": re = a - b; break;
        	case "*": re = a * b; break;
        	case "/": re = a / b; break;
        	default: throw new IllegalStateException("Unknown operator: " + symbol);
		}
		
		cls.set(i, re+"");
		cls.remove(i-1);
		cls.remove(i);
	}

}

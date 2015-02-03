package roman;

import java.util.ArrayList;
import java.util.List;

public class ConvertRoman {

	public static void main(String[] args){
		convertRoman(args[0], 
			new I_Converter() {					//toRoman
			
			@Override
			public int convert(String rome) {
				return 0;// not used
			}
			
			@Override
			public String convert(int zahl) {
				return toRoman(zahl);
			}
		}, new I_Converter() {					//toArabic
			
			@Override
			public int convert(String rome) {
				return fromRoman(rome);
			}
			
			@Override
			public String convert(int zahl) {
				return null;// not used
			}
		});
	}
	
	public static void convertRoman(String args, I_Converter toRoman, I_Converter toArab){
		if(args.matches("[0-9]+")){
			toConsole(toRoman.convert(Integer.parseInt(args)));
		}else{
			toConsole(toArab.convert(args));
		}
	}

	public static String toRoman(int zahl){
		List<Integer> summands = buildSummands(zahl);
		return "x"+ zahl;
	}

	public static List<Integer> buildSummands(int zahl){
		List<Integer> summands = new ArrayList<Integer>();
		while(zahl > 1){
			if(zahl - 1000 >= 0){
				summands.add(1000);
				zahl -= 1000;
			}else if(zahl - 500 >= 0){
				summands.add(500);
				zahl -= 500;
			}else if(zahl - 100 >= 0){
				summands.add(100);
				zahl -= 100;
			}else if(zahl - 50 >= 0){
				summands.add(50);
				zahl -= 50;
			}else if(zahl - 10 >= 0){
				summands.add(10);
				zahl -= 10;
			}else if(zahl - 5 >= 0){
				summands.add(5);
				zahl -= 5;
			}else{
				summands.add(1);
				zahl -= 1;
			}
		}
		return summands;
	}
	
	private static void toConsole(String s){
		System.out.println(s);
	}
	
	private static void toConsole(int i){
		System.out.println(i);
	}

	public static int fromRoman(String rome) {

		List<Integer> numbers = convertToNumbers(rome);
		List<Integer> values = applySubtracts(numbers);
		return add(values);
	}
	
	private static List<Integer> convertToNumbers(String rome) {
		List<Integer> numbers = new ArrayList<Integer>();
		for(char zeichen: rome.toCharArray()){
			switch (zeichen) {
			case 'I':
				numbers.add(1);
				break;
			case 'V':
				numbers.add(5);
				break;
			case 'X':
				numbers.add(10);
				break;
			case 'L':			
				numbers.add(50);
				break;
			case 'C':			
				numbers.add(100);
				break;
			case 'D':			
				numbers.add(500);
				break;
			case 'M':
				numbers.add(1000);
				break;
			}
		}
		return numbers;
	}
	
	private static List<Integer> applySubtracts(List<Integer> numbers) {
		List<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < numbers.size(); i++){
			if(i < numbers.size()-1 && numbers.get(i) < numbers.get(i+1)){
				values.add(numbers.get(i+1) - numbers.get(i));
				i++;
			}				
			else
				values.add(numbers.get(i));
		}
		return values;
	}
	
	private static int add(List<Integer> values) {
		int summe = 0;
		for(int i: values)
			summe += i;
		return summe;
	}
}

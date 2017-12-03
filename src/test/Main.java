package test;

import java.util.ArrayList;
import java.util.List;

import file.FileHandle;

public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\naplues\\Desktop\\d\\MG1733018.txt";
		List<String> strings = FileHandle.readFileToLines(filePath);

		List<String> newString = handle(strings);
		FileHandle.writeLinesToFile(filePath, newString, false);

		String ans = "BCCBBDBADDDDCACBBBAC";         //20
			  ans += "BCDAAADBACDBCDABCCAD";         //20
			  ans += "ADCCCBBABCBBCBDCBBAC";         //20
			  ans += "CBABCDBBDADDAABCBBCD";         //20
			  ans += "ABBBCBDADBACBCBDDBBC";         //20
			  ans += "DBDBCAACBABDDACBDACB";         //20

		newString = modify(newString, ans);
		print(newString);
	}

	public static void print(List<String> strings) {
		for (String s : strings) {
			System.out.println(s);
		}
	}

	public static List<String> handle(List<String> strings) {
		List<String> newString = new ArrayList<>();
		for (String s : strings) {
			s = s.replace("．", "");
			s = s.replace(".", "");
			newString.add(s);
		}
		return newString;
	}

	public static List<String> modify(List<String> strings, String answer) {
		List<String> newString = new ArrayList<>();
		for (int i = 0, j = 0; j < strings.size()/6; i += 6, j++) {
			newString.add(strings.get(i));
			newString.add(strings.get(i + 1));
			int r = 0;
			switch (answer.charAt(j)) {
			case 'A':
				r = 2;
				break;
			case 'B':
				r = 3;
				break;
			case 'C':
				r = 4;
				break;
			case 'D':
				r = 5;
				break;
			default:
				break;
			}
			for (int k = 2; k < 6; k++) {
				String s = null;
				if (r == k)
					s = "R:" + strings.get(i + k).substring(1);
				else
					s = "W:" + strings.get(i + k).substring(1);
				newString.add(s);
			}
		}
		return newString;
	}

}

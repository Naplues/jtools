package test;

import file.FileHandle;

public class Main {
	public static void main(String[] args) {
		String text = FileHandle.readFileToString("resource/test.txt");
		System.out.println(text);
		
	}
}

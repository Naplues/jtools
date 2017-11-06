package test;

import java.util.List;

import file.FileHandle;

public class Main {
	public static void main(String[] args) {
		String filePath = "resource/test.txt";
		List<String> strings = FileHandle.readFileToLines(filePath);
		filePath = "resource/a.txt";
		FileHandle.writeLinesToFile(filePath, strings,true);
	}
}

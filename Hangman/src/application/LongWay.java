package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

class LongWay{
	
	static String root = "C:/Users/franc/workspace/Hangman/src/application/";
	
	public List[] longWay() throws IOException{
		File inFile1 = new File(root + "file1.txt");
		File inFile2 = new File(root + "file2.txt");
		File inFile3 = new File(root + "file3.txt");
		File inFile4 = new File(root + "file4.txt");
		
		File[] files = new File[4];
		files[0] = inFile1;
		files[1] = inFile2;
		files[2] = inFile3;
		files[3] = inFile4;
		
		List[] stringLists = new List[4];
		
		
		for (int i = 0; i < 4; i++){
			StreamTest hello = new StreamTest();
			stringLists[i] = hello.storeWords(files[i]);
		}
		
		return stringLists;
		
	}
}


package application;

import java.io.*;
import java.util.*;


public class StreamTest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	static String root = "C:/Users/franc/workspace/Hangman/src/application/";
	File easy = new File(root + "file1.txt");
	File medium = new File(root + "file2.txt");
	File hard = new File(root + "file3.txt");
	File veryHard = new File(root + "file4.txt");
	
	
	public static List<String> storeWords(File f) throws IOException {
			
		List<String> list1 = new ArrayList<String>();
		List<String> list50 = new ArrayList<String>();	
		BufferedReader br = new BufferedReader(new FileReader(f));
				while (br.readLine() != null) {
					list1.add(br.readLine());
				}
			br.close();	
				
			Random random = new Random();
		
			for(int i = 0; i < 50; i++){
				int g = random.nextInt(list1.size()-2);
				list50.add(list1.get(g));
			}
			
			return list50;
		
		}
	
	public static String pickOne(File f) throws IOException {
		List<String> list1 = new ArrayList<String>();
		List<String> list50 = new ArrayList<String>();
		String picked;
		BufferedReader br = new BufferedReader(new FileReader(f));
			while (br.readLine() != null) {
				list1.add(br.readLine());
			}
		br.close();
			
		Random random = new Random();
		int pickOneFromFifty = random.nextInt(50);
		
	
		for(int i = 0; i < 50; i++){
			int g = random.nextInt(list1.size()-2);
			list50.add(list1.get(g));
		}
		
		picked = (String) list50.get(pickOneFromFifty);
		
		return picked;
	
	}
	
	
	public static void main(String[] args) throws IOException{
		File inFile = new File(root + "file1.txt");
		List<String> list50 = new ArrayList<String>();
		list50 = storeWords(inFile);
		System.out.print(list50);
		
	}
}
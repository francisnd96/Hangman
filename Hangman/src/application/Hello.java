package application;

import java.io.File;
import java.util.Arrays;



public class Hello {
	
	static String root = "C:/Users/franc/workspace/Hangman/src/application/";
	
	public static void yolo(int choice) throws InterruptedException{
		
long startmillis = 0;
		
		File inFile1 = new File(root + "file1.txt");
		File inFile2 = new File(root + "file2.txt");
		File inFile3 = new File(root + "file3.txt");
		File inFile4 = new File(root + "file4.txt");
		
		switch(choice){
			case 1 :
				startmillis = System.currentTimeMillis();
				Thread t = new Thread(new MyThread(inFile1,startmillis));
				t.start();
				Thread t2 = new Thread(new MyThread(inFile2,startmillis));
				t2.start();
				t2.join();
				Thread t3 = new Thread(new MyThread(inFile3,startmillis));
				t3.start();
				t3.join();
				Thread t4 = new Thread(new MyThread(inFile4,startmillis));
				t4.start();
				t4.join();
				System.out.println("Time taken: " + (startmillis-System.currentTimeMillis())*-1);
			break;
			case 2 :
				try{
					System.out.println("Sequential Approach" + "\n");
					startmillis = System.currentTimeMillis();
					LongWay attempt = new LongWay();
					System.out.println(Arrays.toString(attempt.longWay()));
					long endmillis = System.currentTimeMillis();
					System.out.println("Time taken: " + (endmillis-startmillis));
				}catch(Exception e){
					e.printStackTrace();
				}
			break;
		}
	}
}

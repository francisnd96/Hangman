package application;

import java.io.File;

public class MyThread implements Runnable {
		private File myFile;
		long startmillis;
	   
		public MyThread(File f, long l) {
			myFile = f;
			startmillis = l;
		}
	   
		public void run(){
			
			try{
				StreamTest test = new StreamTest();
				System.out.println(test.storeWords(myFile));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

package application;

import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;


public class Hangman extends Application{
	
	static int choice = 0;
	static String root = "C:/Users/franc/workspace/Hangman/src/application/";
	
	public void start(Stage stage) {
			Scene scene = new Scene(new Group());			
			stage.setTitle("Hangman Word Chooser");
			stage.setWidth(400);
			stage.setHeight(180);
			
			VBox vbox = new VBox();
			
			Label label1 = new Label("Please choose which mode you wish to choose from");
			
			
			ToggleGroup tg = new ToggleGroup();
			RadioButton rb1 = new RadioButton("Sequential");
			RadioButton rb2 = new RadioButton("Parallel");
			rb1.setToggleGroup(tg);
			rb1.setTranslateX(150.0);
			rb2.setToggleGroup(tg);
			rb2.setTranslateX(150.0);
			Button submit = new Button("Submit");
			submit.setTranslateX(150.0);
			submit.setOnAction(new EventHandler<ActionEvent>() {
			        public void handle(ActionEvent event) {
					if(rb1.isSelected()){
						choice = 2;
						stage.close();
					}
					else{
						choice = 1;
						stage.close();
					}
				}
			});
			
			
			vbox.getChildren().add(label1);
			vbox.getChildren().add(rb1);
			vbox.getChildren().add(rb2);
			vbox.getChildren().add(submit);

			((Group)scene.getRoot()).getChildren().add(vbox);
			
			stage.setScene(scene);
			stage.show();
			
		}
	
	public static void MyMethod() throws InterruptedException{
	
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
				System.out.println("Time taken: " + (startmillis + "-"  + "=" + ((startmillis-System.currentTimeMillis())*-1) ));
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
	
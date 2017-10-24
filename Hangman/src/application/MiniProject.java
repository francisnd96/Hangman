package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class MiniProject extends Application{
	
	//Labels
	Label guessesRemaining;
	private Label tfLettersGuessed = new Label();
	Label guessesLeft = new Label();
	Label enterCharRequest = new Label("Please enter a letter");
	Label charAlreadySelected  = new Label("That selection has already been made");
	Label saveMade = new Label();
	Label winOrLose = new Label("");
	Label label1 = new Label("Please choose which mode you wish to choose from");
	
	static StreamTest l = new StreamTest();
	
	//Strings
	private String gameWordEasy;
	private String gameWordMedium;
	private String gameWordHard;
	private String gameWordVeryHard;
	private String gameWord = "";
	static String word;
	String chosenDifficulty = "";
	Button playAgainButton = new Button("Play again");
	
	//Buttons
	Button easy = new Button("Easy");
	Button medium = new Button("Medium");
	Button hard = new Button("Hard");
	Button veryHard = new Button("Very Hard");
	Button loadGame = new Button("Load Last Saved Game");

	
	//Lists
	private List<Label> list = new ArrayList<Label>();
	private List<Label> listdashes = new ArrayList<Label>();
	
	//Panes
	BorderPane bp = new BorderPane();
	GridPane gridPane = new GridPane();
	GridPane gp2 = new GridPane();
	GridPane gp3 = new GridPane();
	BorderPane bp2 = new BorderPane();
	BorderPane bp3 = new BorderPane();
	VBox vb = new VBox();
	VBox vbox = new VBox();
	
	//Menu
	MenuBar menuBar = new MenuBar();
    Menu menuFile = new Menu("File");  
    MenuItem saveGame = new MenuItem("Save Game");
    MenuItem exit = new MenuItem("Exit");
	
    //Extras
  	private boolean duplicate;
  	private TextField tfGuess = new TextField();
  	private int noGuessesLeft = 8;
  	StringBuffer guessedLetters = new StringBuffer();
	ImageView iv1 = new ImageView(new Image(getClass().getResourceAsStream("stage1.png")));
	boolean correct = false;
	int choice = 0;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		Scene scene1 = new Scene(vbox); 
		Stage newStage = new Stage();
		newStage.setScene(scene1);
	    newStage.initModality(Modality.APPLICATION_MODAL);
	    newStage.setTitle("Hangman Word Chooser");
	    newStage.setWidth(400);
		newStage.setHeight(180);
		vbox.getChildren().add(label1);
		ToggleGroup tg = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Sequential");
		RadioButton rb2 = new RadioButton("Parallel");
		rb1.setToggleGroup(tg);
		rb1.setTranslateX(150.0);
		rb2.setToggleGroup(tg);
		rb2.setTranslateX(150.0);
		Button submit = new Button("Submit");
		submit.setTranslateX(150.0);
		vbox.getChildren().add(rb1);
		vbox.getChildren().add(rb2);
		vbox.getChildren().add(submit);
		submit.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent event) {
			if(rb1.isSelected()){
				choice = 2;
				try {
					Hello.yolo(choice);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newStage.close();
			}
			else{
				choice = 1;
				try {
					Hello.yolo(choice);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newStage.close();
			}
		}
	});
		
		Scene sceneWelcome = new Scene(bp2, 800, 450);
		primaryStage.setTitle("Francis' Hangman Game");
		primaryStage.setScene(sceneWelcome);
		
		//The scene where the game will be played
		Scene scene = new Scene(bp, 900, 550);
		
		//Create the UI for the "Welcome screen"
		vb.setPadding(new Insets(70, 200, 0, 50));
	    vb.setSpacing(16);
		vb.getChildren().add(easy);
		vb.getChildren().add(medium);
		vb.getChildren().add(hard);
		vb.getChildren().add(veryHard);
		vb.getChildren().add(loadGame);
		
		Label welcomeLabel = new Label("Welcome to Hangman");
		Label difficulty = new Label("Choose a difficulty:");
		welcomeLabel.setFont(new Font (50.0));
		difficulty.setFont(new Font (30.0));
		welcomeLabel.setVisible(true);
		bp2.setTop(welcomeLabel);
		bp2.setLeft(difficulty);
		bp2.setCenter(vb);
		
		bp2.setTop(welcomeLabel);
		bp2.setLeft(difficulty);
		bp2.setCenter(vb);
		
		//Create the MenuBar and Menus
		menuBar.getMenus().addAll(menuFile);
		menuFile.getItems().add(saveGame);
		menuFile.getItems().add(exit);
		
		//To ensure the player can only enter one character for his/her guess
		tfGuess.textProperty().addListener(
		        (observable,oldValue,newValue)-> {
		            if(newValue.length() >= 2) tfGuess.setText(oldValue);
		        }
		);
		
		//Selects one random word from each of 4 word files
		try{
			gameWordEasy = StreamTest.pickOne(l.easy);
			gameWordMedium = StreamTest.pickOne(l.medium);
			gameWordHard = StreamTest.pickOne(l.hard);
			gameWordVeryHard = StreamTest.pickOne(l.veryHard);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Creates the initial UI for the game
		try {
			gridPane.add(new Label("Enter a letter:"),0,0);
			gridPane.add(tfGuess, 1, 0);
			gridPane.add(new Label("Letters Guessed:"),0,1);
			gridPane.add(tfLettersGuessed, 1, 1);
			gridPane.add(new Label("Guesses Remaining: "),0,2);
			guessesLeft.setText("8");
			gridPane.add(guessesLeft, 0, 3);
			gridPane.add(playAgainButton, 0, 4);
			gridPane.add(enterCharRequest, 0, 5);
			gridPane.add(charAlreadySelected, 0, 5);
			gridPane.add(saveMade, 0, 6);
			saveMade.setVisible(false);
			charAlreadySelected.setVisible(false);
			enterCharRequest.setVisible(false);
			playAgainButton.setVisible(false);
			
			//If easy is clicked
			easy.setOnAction(new EventHandler<ActionEvent>() {
	            @Override 
	            public void handle(ActionEvent e) {
	            	newStage.showAndWait();
	            	primaryStage.setScene(scene);
	            	if(gameWord.equals("")){
	            		gameWord = gameWordEasy;
	            		word = gameWordEasy;
	            	}	
	            	chosenDifficulty = "easy";
	            	list.stream().forEach(letter -> letter.setVisible(false));
	            	list.clear();
	            	listdashes.clear();
	            	try {
						startNewHangman();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	list.clear();
	    			for(int i = 0; i < gameWord.length(); i++){
	    				char c = gameWord.charAt(i);
	    				Label mylabel = new Label(Character.toString(c));
	    				mylabel.setVisible(false);
	    				mylabel.setFont(new Font(50.0));
	    				if(listdashes.size() != gameWord.length()){
	    					Label dash = new Label("-");
	    					dash.setFont(new Font(60.0));
	    					gp2.add(dash,i,0);
	    					listdashes.add(dash);
	    				}
	    				gp2.add(mylabel, i, 0);
	    				list.add(mylabel);
	    			}	    			
	            }
	        });
			
			//If medium is clicked
			medium.setOnAction(new EventHandler<ActionEvent>() {
	            @Override 
	            public void handle(ActionEvent e) {
	            	newStage.showAndWait();
	            	primaryStage.setScene(scene);
	            	if(gameWord.equals("")){
	            		gameWord = gameWordMedium;
	            		word = gameWordMedium;
	            	}
	            	chosenDifficulty = "medium";
	            	list.stream().forEach(letter -> letter.setVisible(false));
	            	list.clear();
	            	listdashes.clear();
	            	try {
						startNewHangman();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	list.clear();	            	
	            	for(int i = 0; i < gameWord.length(); i++){
	    				char c = gameWord.charAt(i);
	    				Label mylabel = new Label(Character.toString(c));
	    				mylabel.setVisible(false);
	    				mylabel.setFont(new Font(50.0));
	    				if(listdashes.size() != gameWord.length()){
	    					Label dash = new Label("-");
	    					dash.setFont(new Font(60.0));
	    					gp2.add(dash,i,0);
	    					listdashes.add(dash);
	    				}
	    				gp2.add(mylabel, i, 0);
	    				list.add(mylabel);
	    			}	
	            }
	        });
			
			//If hard is clicked
			hard.setOnAction(new EventHandler<ActionEvent>() {
	            @Override 
	            public void handle(ActionEvent e) {
	            	newStage.showAndWait();
	            	primaryStage.setScene(scene);
	            	if(gameWord.equals("")){
	            		gameWord = gameWordHard;
	            		word = gameWordHard;
	            	}
	            	chosenDifficulty = "hard";
	            	list.stream().forEach(letter -> letter.setVisible(false));
	            	list.clear();
	            	listdashes.clear();
	            	try {
						startNewHangman();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	list.clear();
	            	for(int i = 0; i < gameWord.length(); i++){
	    				char c = gameWord.charAt(i);
	    				Label mylabel = new Label(Character.toString(c));
	    				mylabel.setVisible(false);
	    				mylabel.setFont(new Font(50.0));
	    				if(listdashes.size() != gameWord.length()){
		    				Label dash = new Label("-");
		    				dash.setFont(new Font(60.0));
		    				gp2.add(dash,i,0);
		    				listdashes.add(dash);
	    				}
	    				gp2.add(mylabel, i, 0);
	    				list.add(mylabel);
	    			}
	            }
	            
	        });
			
			//If very hard is clicked
			veryHard.setOnAction(new EventHandler<ActionEvent>() {
	            @Override 
	            public void handle(ActionEvent e) {
	            	newStage.showAndWait();
	            	primaryStage.setScene(scene);
	            	if(gameWord.equals("")){
	            		gameWord = gameWordVeryHard;
	            		word = gameWordVeryHard;
	            	}	
	            	chosenDifficulty = "veryHard";
	            	list.stream().forEach(letter -> letter.setVisible(false));
	            	list.clear();
	            	listdashes.clear();
	            	try {
						startNewHangman();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	list.clear();
	            	for(int i = 0; i < gameWord.length(); i++){
	            		char c = gameWord.charAt(i);
	    				Label mylabel = new Label(Character.toString(c));
	    				mylabel.setVisible(false);
	    				mylabel.setFont(new Font(50.0));
	    				if(listdashes.size() != gameWord.length()){
		    				Label dash = new Label("-");
		    				dash.setFont(new Font(60.0));
		    				gp2.add(dash,i,0);
		    				listdashes.add(dash);
	    				}
	    				gp2.add(mylabel, i, 0);
	    				list.add(mylabel);
	    			}
	            }
	        });
			
			//If save game is clicked
			saveGame.setOnAction(e -> {
		    	SaveData data = new SaveData();
		    	data.noGuesses = noGuessesLeft;
		    	data.sb = guessedLetters;
		    	data.picNo = 8-noGuessesLeft+1;
		    	data.word = word;
		    	data.gameWord = gameWord;
		    	saveMade.setText("Save made at: " + "\n" + new Date());
		    	saveMade.setVisible(true);
		    	try{
		    		ResourceManager.save(data, "mysave");
		    	}catch(Exception e1){
		    		e1.printStackTrace();
		    	}
		    });
			
			//If load game is clicked
			loadGame.setOnAction(e -> {
				try{
		    	SaveData data = (SaveData) ResourceManager.load("mysave");
		    	noGuessesLeft = data.noGuesses;
		    	guessesLeft.setText(Integer.toString(noGuessesLeft));
		    	guessedLetters = data.sb;
		    	tfLettersGuessed.setText(guessedLetters.toString());
		    	iv1 = new ImageView(new Image(getClass().getResourceAsStream("stage" + data.picNo + ".png")));
				bp.setBottom(iv1);
		    	System.out.println(data.word);
		    	gameWord = data.gameWord;
		    	word = data.word;
		    	primaryStage.setScene(scene);
		    	list.stream().forEach(letter -> letter.setVisible(false));
	        	list.clear();
	        	listdashes.clear();
	        	for(int i = 0; i < gameWord.length(); i++){
					char c = gameWord.charAt(i);
					Label mylabel = new Label(Character.toString(c));
					mylabel.setVisible(false);
					mylabel.setFont(new Font(50.0));
					if(listdashes.size() != gameWord.length()){
	    				Label dash = new Label("-");
	    				dash.setFont(new Font(60.0));
	    				gp2.add(dash,i,0);
	    				listdashes.add(dash);
					}
					gp2.add(mylabel, i, 0);
					list.add(mylabel);
				}
		    	for(int i = 0; i< data.word.length(); i++){
		    		if (data.word.charAt(i) == ' '){
		    			System.out.println("yup");
		    			list.get(i).setVisible(true);
						listdashes.get(i).setVisible(false);
		    		}
		    	}
				}catch(Exception e1){
					e1.printStackTrace();
				}
	    });						
			
			//Adds the GUI for playing the game to the Border Pane
			bp.setTop(menuBar);
			bp.setLeft(gridPane);
			bp.setCenter(gp2);
			bp.setBottom(iv1);
			
			
			//This resets the game and takes the user to the main welcome screen
			playAgainButton.setOnAction(e -> {
				try {
					listdashes.stream().forEach(dash -> dash.setVisible(false));
					primaryStage.setScene(sceneWelcome);
					startNewHangman();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			//This is an emergency exit for the user, taking them to the welcome screen at any point in the game
			exit.setOnAction(e -> {
				list.stream().forEach(letter -> letter.setVisible(false));
				listdashes.stream().forEach(dash -> dash.setVisible(false));
	        	list.clear();
	        	listdashes.clear();
		    	primaryStage.setScene(sceneWelcome);
		    	charAlreadySelected.setVisible(false);
		    	saveMade.setVisible(false);
		    	correct = true;
		    });
		
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		//starts the game
		tfGuess.setOnAction(e -> startHangman());
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
	public void startNewHangman() throws IOException{
		tfGuess.setDisable(false);
		
		if(chosenDifficulty.equals("easy"))
			gameWord = StreamTest.pickOne(l.easy);
		else if (chosenDifficulty.equals("medium"))
			gameWord = StreamTest.pickOne(l.medium);
		else if (chosenDifficulty.equals("hard"))
			gameWord = StreamTest.pickOne(l.hard);
		else if (chosenDifficulty.equals("veryHard"))
			gameWord = StreamTest.pickOne(l.veryHard);
			
		for(int i = 0; i < gameWord.length(); i++){
			word = gameWord;
			noGuessesLeft = 8;
			guessesLeft.setText(Integer.toString(noGuessesLeft));
			playAgainButton.setVisible(false);
			guessedLetters = new StringBuffer();
			tfLettersGuessed.setText(guessedLetters.toString());
			iv1 = new ImageView(new Image(getClass().getResourceAsStream("stage1.png")));
			bp.setBottom(iv1);
			bp.setCenter(gp2);
			winOrLose.setVisible(false);

		}
	}
	
	public void startHangman(){
		
		//enables the text field for guessing
		tfGuess.setDisable(false);

		String guess = tfGuess.getText().toLowerCase();
		tfGuess.setText("");
		
		//This checks if the letter guessed appears more than once in the word
		int count = word.length() - word.replace(guess, "").length();
		
		for(int i = 0; i < word.length(); i++){
			String charToString = Character.toString(word.charAt(i));
			if(guess.equals(charToString)){ //if the guess is correct
				correct = true;
				guessedLetters.append(guess + ",");
				tfLettersGuessed.setText(guessedLetters.toString());
				enterCharRequest.setVisible(false);
				charAlreadySelected.setVisible(false);
				list.get(i).setVisible(true);
				listdashes.get(i).setVisible(false);
				word = word.substring(0,i) + " " + word.substring(i+1,word.length());
				if(count >= 2){
					for(int j = 0; j < count-1; j++){
						int furtherOccurence = word.indexOf(guess);
						word = word.substring(0,furtherOccurence) + " " + word.substring(furtherOccurence+1,word.length());
						list.get(furtherOccurence).setVisible(true);
						listdashes.get(furtherOccurence).setVisible(false);
					}
				}
				if(word.replace(" ", "").equals("")){ // if the user has won the game
					tfGuess.setDisable(true);
					playAgainButton.setVisible(true);
					winOrLose = new Label("winner");
					winOrLose.setVisible(true);
					winOrLose.setFont(new Font(30.0));
					gp3.add(gp2, 3, 2);
					gp3.add(winOrLose, 3, 7);
					bp.setCenter(gp3);
					guessedLetters.setLength(0);
				}
				break;
			}else
				correct = false;	
		}
		if(correct == false){ //if the guess was false
			for(int i = 0; i < guessedLetters.length(); i = i+2){
				if(guess.equals(Character.toString(guessedLetters.charAt(i)))){ // if the guess has already been tried
					charAlreadySelected.setVisible(true);
					duplicate = true;
					break;
				}else{
					charAlreadySelected.setVisible(false);
					duplicate = false;
				}
			}
			if(guess.length() == 0){ // if the guess entered is blank
				enterCharRequest.setVisible(true);
				charAlreadySelected.setVisible(false);
			}else if (duplicate == true){
				charAlreadySelected.setVisible(true);
				enterCharRequest.setVisible(false);
			}else if (!Character.isLetter(guess.charAt(0))) // if the guess entered is not a letter
				enterCharRequest.setVisible(true);
			else{
				noGuessesLeft = noGuessesLeft - 1;
				guessesLeft.setText(Integer.toString(noGuessesLeft));
				enterCharRequest.setVisible(false);
				//this changes the image of the hangman as the game proceeds
				if (noGuessesLeft >= 0){
					iv1 = new ImageView(new Image(getClass().getResourceAsStream("stage" + (8-noGuessesLeft+1) + ".png")));
					bp.setBottom(iv1);
				}

			//if the player has lost the game	
			if(noGuessesLeft == 0){
				tfGuess.setDisable(true);
				playAgainButton.setVisible(true);
				winOrLose = new Label("loser" + "\n" + "your word was: " + gameWord);
				winOrLose.setVisible(true);
				winOrLose.setFont(new Font(30.0));
				gp3.add(gp2, 3, 2);
				gp3.add(winOrLose, 3, 7);
				bp.setCenter(gp3);
				guessedLetters.setLength(0);
			}
				guessedLetters.append(guess + ",");
				tfLettersGuessed.setText(guessedLetters.toString());
			}
		}
	
				
	}
		
}


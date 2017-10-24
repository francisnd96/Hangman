package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;

public class SaveData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int noGuesses = 0;
	public StringBuffer sb = new StringBuffer();
	public int picNo = 0;
	public String word;
	public String gameWord;
	public List<Label> ld = new ArrayList<Label>();

}

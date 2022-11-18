package main;

import database.Total;
import ui.StartUI;

public class Main {
	public static void main(String[] args) {
		Total total = new Total();
		StartUI frame = new StartUI(total);
		frame.setVisible(true);
		
		StartUI user1 = new StartUI(total);
		user1.setVisible(true);
		
		StartUI user2 = new StartUI(total);
		user2.setVisible(true);
	}
}

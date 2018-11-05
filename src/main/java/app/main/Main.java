package app.main;

import app.gui.BattleShipGUI;
/**
 * Entry point for the application
 * @author Artur Vitt
 *
 */
public class Main {
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	GameBattleships game = new GameBattleships();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               new BattleShipGUI(game);
            }
        });
	}
}

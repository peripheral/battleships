package app.main.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.gui.board.grid.Grid;
import app.logics.board.Board;
import app.logics.board.IBoard;
import app.main.GameBattleships;

public class GameBattleshipsTest {
	private GameBattleships sut;
	
	@BeforeEach
	void init() {
		sut = new GameBattleships();
	}	
	
	@Test
	void testGetEnemyBoardNotNull() {
		IBoard actual = sut.getEnemyBoard();
		assertNotNull(actual);
	}
	
}

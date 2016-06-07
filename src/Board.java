import java.util.Random;
import java.util.Scanner;

/**
 * Gameboard implementation for the battleship game
 * @author M065
 *
 */

public class Board {
	public char[][] boardPos;
	public Ship[] ships;
	public int boardSize;
	public int numberOfShips;
	//public int boardRow, boardCol;
	//public int boardX, boardY; //can be used to determine width and length of boards
	
	public Board() {
		initBoard();
		printBoard();
		randomizeShipLocations();
		makeSelection();
		System.out.println("\nCongratulations! You've destroyed all enemy ships!");
	}
	
	
	public boolean matches(int xPos, int yPos){
		for(Ship iter : ships){
			if(iter.getxPos() == xPos && iter.getyPos() == yPos){
				iter.setSafe(false);
				return true;
			}
		}
		return false;
	}
	
	public void makeSelection(){
		int xSelect = -1, ySelect = -1;
		Scanner input = new Scanner(System.in);	
		while(numberOfShips > 0){
			System.out.print("Enter a spot to shoot! Enter a number between 1 and "+boardSize+", then press enter.\nRow: ");
			if(input.hasNextInt()){
				xSelect = input.nextInt()-1;
			}
			System.out.print("Col: ");
			if(input.hasNextInt()){
				ySelect = input.nextInt()-1;
			}
			if(matches(xSelect, ySelect)){
				System.out.println("Hit!");
				boardPos[xSelect][ySelect] = 'X';
				numberOfShips--;
			}else{
				System.out.println("Miss!");
				boardPos[xSelect][ySelect] = 'O';
			}
			printBoard();
		}
		input.close();
	}
	
	public void randomizeShipLocations(){
		Random random = new Random();
		int xShipPos, yShipPos, counter = 0;
		for(Ship init : ships){
			xShipPos = random.nextInt(boardSize) % boardSize;
			yShipPos = random.nextInt(boardSize) % boardSize;
			if(counter > 0 && checkShipOverlap(counter, xShipPos, yShipPos)){
				while(init.isOverlap()){
					xShipPos = random.nextInt(boardSize) % boardSize;
					yShipPos = random.nextInt(boardSize) % boardSize;
					checkShipOverlap(counter, xShipPos, yShipPos);
				}
			}
			init.setxPos(xShipPos);
			init.setyPos(yShipPos);
			counter++;
		}
		//printShipLocations();
	}
	public boolean checkShipOverlap(int count, int xPos, int yPos){
		//checks if another ship already has the same coordinates
		for(int i = 0; i < count; i++){
			if(ships[i].getxPos() == xPos && ships[i].getyPos() == yPos){
				ships[count].setOverlap(true);
				return true;
			}
			ships[count].setOverlap(false);
		}
		return false;
	}
	//public void randomizeShipSize(){} to be written later for longer ships, keep in mind space available to place ships on board
	
	public void initBoard(){
		boardSize = 5;//determines size of board
		numberOfShips = 3;//determines ships on board
		boardPos = new char[boardSize][boardSize];
		ships = new Ship[numberOfShips];
		
		//failed attempt at using enhanced for-loops to cycle through 2d-array
		/*
		for(char[] boardRow : boardPos){
			for(char boardCol : boardRow){
				boardRow[boardCol] = '~';
			}
		}
		*/
		for(int boardRow = 0; boardRow < boardSize; boardRow++){
			for(int boardCol = 0; boardCol < boardSize; boardCol++){
				boardPos[boardRow][boardCol] = '~';
			}
		}
		for(int i = 0; i < numberOfShips; i++){
			ships[i] = new Ship(i+1); //+1 to start numbering ships from 1 in Ship class's constructor
		}
	}
	public void printBoard(){
		//failed attempt at using enhanced for loops to cycle through 2d-array
		
		/*
		for(char[] boardRow : boardPos){
			for(char boardCol : boardRow){
			*/
		for(int boardRow = 0; boardRow < boardSize; boardRow++){
			for(int boardCol = 0; boardCol < boardSize; boardCol++){
				System.out.print("| "+boardPos[boardRow][boardCol]+" ");
			}
			System.out.println("|");
		}
	}
	
	public void printShipLocations(){
		for(Ship render : ships){
			System.out.print("Ship "+render.getShipNumber()+": ("+render.getxPos()+","+render.getyPos()+")\n");	
		}
	}

	public char getBoardPos(int boardRow, int boardCol) {
		return boardPos[boardRow][boardCol];
	}

	public void setBoardPos(char[][] boardPos) {
		this.boardPos = boardPos;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
/*	
	public boolean checkBoardLocation(int row, int col){
		if(getBoardPos(row, col)  
	}
*/
}

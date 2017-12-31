// Name & Student id: Vickel Leung, 40005344
// COMP 249
// Assignment#: 1
// Due Date: 4th February, 2017

import java.util.Scanner;
import java.util.Random;

// codes for ships and grenades
// 0:empty slot, 1: computer ship, 2: computer grenade , 3: human ship , 4: human grenade,  5:ship hit (comp) 6: ship hit (human)
// 7: explode grenade (comp) , 8: explode grenade (human) 
// toggleGrenade: 0 = false , 1 = (human hit grenade) , 2 = (computer hit grenade) 

public class Battleship {

	// declaration of variables
	public final static int ROW = 8;
	public final static int COLUMN = 8;
	private static int grid[][] = new int [ROW][COLUMN];
	private static String gridDisplay[][] = new String[ROW][COLUMN];
	private static String gridFinal[][] = new String [ROW][COLUMN];
	private static int numOfRow = 0;
	private static int numOfCol = 0;
	private static int maxShip = 6;
	private static int maxGrenade = 4;
	private static char letter; 
	private static char num;
	private static int humanShipLeft = 6;
	private static int compShipLeft = 6;
	private int toggleHitGrenade = 0;
	private int numMissTurn = 0;
	
	// default constructor
	public Battleship()
	{
		toggleHitGrenade = 0;
		numMissTurn = 0;
	}
		
		// mutators
		public void setToggleHitGrenade( int tGrenade)
		{
			toggleHitGrenade = tGrenade;
		}
		
		// accessor
		public int getToggleHitGrenade()
		{
			return toggleHitGrenade;
		}
		
		// mutators
		public void setNumMissTurn(int missTurn)
		{
			numMissTurn = missTurn;
		}
		
		// accessor
		public int getNumMissTurn()
		{
			return numMissTurn;
		}
	
	// clear the grid by setting array to 0's
	public static void clearAndFill()
	{
		// clear the array grid with '0'
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j< COLUMN; j++)
			{
				grid[i][j] = 0;
			}
		}
		
		// fill the display array grid with '-'
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j< COLUMN; j++)
			gridDisplay[i][j] = "-";
		}
	}
	
	// to draw grid for user and update each turn
	public void drawGrid()
	{	
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j< COLUMN; j++)
			System.out.print(gridDisplay[i][j] + " ");
			System.out.println();
		}
	}
	
	// to put ship&grenade into array and display final grid
	public static void drawFinal()
	{	
		// place ship & grenade into gridUser 2d array
		for(int i = 0; i <ROW; i++)
		{
			for(int j = 0; j < COLUMN; j++)
			{
				if(grid[i][j] == 0)
				{
					gridFinal[i][j] = "-";
				}
				else if(grid[i][j] == 1 || grid[i][j] == 5)
				{
					gridFinal[i][j] = "S";
				}
				else if(grid[i][j] == 2 || grid[i][j] == 7)
				{
					gridFinal[i][j] = "G";
				}
				else if(grid[i][j] == 3 || grid[i][j] == 6)
				{
					gridFinal[i][j] = "s";
				}
				else if(grid[i][j] == 4 || grid[i][j] == 8)
				{
					gridFinal[i][j] = "g";
				}
			}
		}
		
		// print gridUser
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j < COLUMN; j++)
			{
				System.out.print(gridFinal[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// attack method, if player hit 1 or 3 then ship sink, if player hit 2 or 4 then they lose a turn
	public void attack(int x, int y, Battleship a)
	{
		// if hit empty slot, does nothing
		if(grid[x][y] == 0)
		{
			System.out.println("Nothing.");
			gridDisplay[x][y] = "*";
		}
		// if ship/grenade hitted previously
		else if (grid[x][y] == 5 || grid[x][y] == 6 || grid[x][y] == 7 || grid[x][y] == 8 )
		{
			System.out.println("Nothing.");
		}
		// if hit a ship , it will sink
		else if(grid[x][y] == 1 || grid[x][y] == 3 )
		{
			System.out.println("Ship hit.");
			if(grid[x][y] == 1)
			{
				compShipLeft--;
				gridDisplay[x][y] = "S";
				grid[x][y] = 5;
			}
			else if(grid[x][y] == 3)
			{
				humanShipLeft--;
				gridDisplay[x][y] = "s"; 
				grid[x][y] = 6;
			}
		}
		// if hit grenade, lose a turn
		else if(grid[x][y] == 2 || grid[x][y] == 4)
		{
			System.out.println("Boom! grenade.");
			a.setToggleHitGrenade(1);
			a.numMissTurn +=1;
			
			if(grid[x][y] == 2)
			{
				gridDisplay[x][y] = "G";
				grid[x][y] = 7;
			}
			else if(grid[x][y] == 4)
			{
				gridDisplay[x][y] = "g";
				grid[x][y] = 8;
			}
			
		}
	}
	// place ships of computer in random order
	public static void placeShipComp()
	{
		// get random number to place ship  
		int ship = 0;
		// while loop until computer has placed 6 ships
		while( ship < maxShip)
		{
			Random ran = new Random();
			int x = ran.nextInt(8);
			int y = ran.nextInt(8);
			// if the grid is not occupy enter loop
			if(grid[x][y] != 1 && grid[x][y] != 3 && grid[x][y] != 4)
			{
				// increment ship and place into grid as number "1"
				ship++;
				grid[x][y] = 1;
			}
		}
	}
	// place grenade of computer in random order
	public static void placeGrenadeComp()
	{
		// get random number to place grenade
		int grenade = 0;
		while(grenade < maxGrenade)
		{
			Random ran = new Random();
			int x = ran.nextInt(8);
			int y = ran.nextInt(8);
			if(grid[x][y] !=2 && grid[x][y] !=1 && grid[x][y] != 3 && grid[x][y] != 4)
			{
				grenade++;
				grid[x][y] = 2;
			}
		}
	}
	// classify the letter and number and storing it into numOfCol& numOfRow
	public static void classify(char a, char b)
	{
		switch(a)
		{
		case 'A': case 'a': numOfCol = 0; break;
		case 'B': case 'b': numOfCol = 1; break;
		case 'C': case 'c': numOfCol = 2; break;
		case 'D': case 'd': numOfCol = 3; break;
		case 'E': case 'e': numOfCol = 4; break;
		case 'F': case 'f': numOfCol = 5; break;
		case 'G': case 'g': numOfCol = 6; break;
		case 'H': case 'h': numOfCol = 7; break;
		}
		
		// classify letter and num 
		switch(b)
		{
		case '1': numOfRow = 0; break;
		case '2': numOfRow = 1; break;
		case '3': numOfRow = 2; break;
		case '4': numOfRow = 3; break;
		case '5': numOfRow = 4; break;
		case '6': numOfRow = 5; break;
		case '7': numOfRow = 6; break;
		case '8': numOfRow = 7; break;
		}
	}
	
	// classify for computer letter
	public static char classifyComp(int a)
	{
		char compLetter = ' ';
		switch(a)
		{
		case 0: compLetter = 'A'; break;
		case 1: compLetter = 'B'; break;
		case 2: compLetter = 'C'; break;
		case 3: compLetter = 'D'; break;
		case 4: compLetter = 'E'; break;
		case 5: compLetter = 'F'; break;
		case 6: compLetter = 'G'; break;
		case 7: compLetter = 'H'; break;
		}
		return compLetter;
	}
	
	// ask user input to place ship 
	public static void placeShipHuman()
	{
		// variables
		Scanner kb = new Scanner(System.in);
		String shipLocation = "";		
		int maxShip = 0;
		for(int i = 1; i<=6; i++)
		{
				// ask user input and stores variables
				System.out.print("Enter the coordinates of your ship #"+ i + ": ");
				
				// get user input
				shipLocation = kb.next();
				letter = shipLocation.charAt(0);
				num = shipLocation.charAt(1);
				 	
				// if the coordinate is outside the grid
				while( (letter >= 'I' && letter <= 'Z') || (letter >= 'i' && letter <= 'z') || (num == '0' || num == '9')  )
				{
					System.out.println("Sorry, coordinate outside of grid. Please try again.");
					System.out.print("Enter the coordinates of your ship #"+ i + ": ");
					shipLocation = kb.next();
					letter = shipLocation.charAt(0);
					num = shipLocation.charAt(1);
				}
				
				classify(letter,num);
				
					// if the coordinate already being used
					while(grid[numOfRow][numOfCol] == 3)
					{
						System.out.println("Sorry the coordinate is already being used");	
						System.out.print("Enter the coordinates of your ship #"+ i + ": ");
						shipLocation = kb.next();
						letter = shipLocation.charAt(0);
						num = shipLocation.charAt(1);
						classify(letter,num);
					}
			
				// place ship into grid
				grid[numOfRow][numOfCol] = 3;
				maxShip++;
		}
	}

	public static void placeGrenadeHuman()
	{
		maxGrenade = 0;
		
		// variables
		Scanner kb = new Scanner(System.in);
		String shipLocation = "";		
		
		for(int i = 1; i<=4; i++)
		{
				// ask user input and stores variables
				System.out.print("Enter the coordinates of your grenade #"+ i + ": ");
				
				// get user input
				shipLocation = kb.next();
				letter = shipLocation.charAt(0);
				num = shipLocation.charAt(1);
				 	
			 
				while( (letter >= 'I' && letter <= 'Z') || (letter >= 'i' && letter <= 'z') || (num == '0' || num == '9')  )
				{
					System.out.println("Sorry, coordinate outside of grid. Please try again.");
					System.out.print("Enter the coordinates of your grenade #"+ i + ": ");
					shipLocation = kb.next();
					letter = shipLocation.charAt(0);
					num = shipLocation.charAt(1);
				}
				
				classify(letter,num);
				
					// coordinate already being used
					while(grid[numOfRow][numOfCol] == 3 || grid[numOfRow][numOfCol] == 4)
					{
						System.out.println("Sorry the coordinate is already being used");	
						System.out.print("Enter the coordinates of your grenade #"+ i + ": ");
						shipLocation = kb.next();
						letter = shipLocation.charAt(0);
						num = shipLocation.charAt(1);
						classify(letter,num);
					}
			
				// place ship into grid
				grid[numOfRow][numOfCol] = 4;
				maxGrenade++;
	}
}
	
	public void humanRocket()
	{
		 Scanner kb = new Scanner(System.in);
		 String rocket  = kb.next();
		 letter = rocket.charAt(0);
		 num = rocket.charAt(1);
		 classify(letter,num);
		 
		// if outside of grid, ask user to input again
		 while( (letter >= 'I' && letter <= 'Z') || (letter >= 'i' && letter <= 'z') || (num == '0' || num == '9')  )
			{
				System.out.print("Sorry, coordinate outside of grid. Please try again.");
				rocket = kb.next();
				letter = rocket.charAt(0);
				num = rocket.charAt(1);
				classify(letter,num);
			}
	}
	
	public void computerTurn(Battleship x)
	{
		Random ran = new Random();
		int a = ran.nextInt(8);
		int b = ran.nextInt(8);
		System.out.println("Computer turn: position of their rocket\n" + classifyComp(b) + (a+1) );
		attack(a, b,x);
		drawGrid();
	}
	
	public void humanTurn(Battleship x)
	{	
		System.out.println("Human turn: Position of your rocket:");
		humanRocket();
		attack(numOfRow, numOfCol,x);
		drawGrid();
	}
	 	
	public static void main(String[] args)
	{ 
		// make 2 object of human and computer to store ships/toggleGrenade
		Battleship human = new Battleship();
		Battleship computer = new Battleship();
		System.out.println("Hi, let's play battleship!\n");

		// set ships and grenade base on user input
		Battleship.clearAndFill();
		Battleship.placeShipHuman();
		System.out.println();
		Battleship.placeGrenadeHuman();
		Battleship.placeShipComp();
		Battleship.placeGrenadeComp();
		
		System.out.println("\nOkay, the computer placed its shits and grenade at random. Let's play.");
		
		// run the game to determine the winner
		while( (humanShipLeft != 0 ) && ( compShipLeft != 0) )
		{	
			
			if(human.getToggleHitGrenade() == 1)
			{
				System.out.println("Human lose a turn due to hitting a grenade.");
				computer.computerTurn(computer);
				computer.computerTurn(computer);
				
				// reset toggle
				human.setToggleHitGrenade(0);
			}
			else if(computer.getToggleHitGrenade() == 1)
			{
				System.out.println("Computer lose a turn due to hitting a grenade.");
				human.humanTurn(human);
				human.humanTurn(human);

				// reset toggle
				computer.setToggleHitGrenade(0);
			}
			else
			{	
				human.humanTurn(human);
				human.computerTurn(computer);
			}
		}
		
		// declare winner by checking number of ships left
		if( compShipLeft == 0)
		{
			System.out.println("Congratulation, you've won!");
		}
		else if( humanShipLeft == 0)
		{
			System.out.println("Too bad, the computer won.");
		}
		else
		{
			System.out.println("It is a draw.");
		}
		
		System.out.println("Here's the position of the grid showing both players ships and grenades");
		drawFinal();
		System.out.println("There was " + human.getNumMissTurn() + " miss turn for human and " + computer.getNumMissTurn()
				+ " miss turn for computer due to hitting rocket");
	}
}

public class Sudoku {

    private int[][] _board;


    public Sudoku() {
    }


    public void setUp() {
	
	// set up with numbers guaranteed to work
	_board[0][1] = 2;
	_board[0][2] = 3;
	_board[0][3] = 5;
	_board[0][5] = 4;

	_board[1][0] = 5;
	_board[1][2] = 6;
	_board[1][3] = 8;
	_board[1][8] = 2;

	_board[2][0] = 9;
	_board[2][2] = 8;
	_board[2][5] = 3;
	_board[2][7] = 5;
	_board[2][8] = 6;

	_board[3][1] = 9;
	_board[3][2] = 7;
	_board[3][4] = 4;
	_board[3][7] = 6;

	_board[4][3] = 2;
	_board[4][5] = 9;

	_board[5][1] = 5;
	_board[5][4] = 1;
	_board[5][6] = 3;
	_board[5][7] = 9;

	_board[6][0] = 2;
	_board[6][1] = 6;
	_board[6][3] = 1;
	_board[6][6] = 8;
	_board[6][8] = 3;
	
	_board[7][0] = 3;
	_board[7][5] = 6;
	_board[7][6] = 1;
	_board[7][8] = 9;

	_board[8][3] = 9;
	_board[8][5] = 2;
	_board[8][6] = 6;
	_board[8][7] = 7;
    }


    public String toString() {
	String retStr = "    ";
	for (int i = 1; i < 10; i ++) {
	    retStr += i + "   ";
	    if (i % 3 == 0) {
		retStr += " ";
	    }
	}

    }


		    


/*
MODEL SUDOKU BOARD:

    1   2   3    4   5   6    7   8   9
  ---------------------------------------
a | 1 | 2 | 3 || 1 | 2 | 3 ||___|___|___|
b | 4 | 5 | 6 || 4 | 5 | 6 ||___|___|___|
c | 7 | 8 | 9 || 7 | 8 | 9 ||___|___|___|
  ---------------------------------------
d | 1 | 2 | 3 ||___|___|___||___|___|___|
e | 4 | 4 | 6 ||___|___|___||___|___|___|
f | 7 | 5 | 6 ||___|___|___||___|___|___|
  ---------------------------------------
g |___|___|___||___|___|___||___|___|___|
h |___|___|___||___|___|___||___|___|___|
i |___|___|___||___|___|___||___|___|___|
  ---------------------------------------

*/

    public String converter( int i ) {
	if (i == 0) {
	    return "a";
	}
	if (i == 1) {
	    return "b";
	}
	if (i == 2) {
	    return "c";
	}
	if (i == 3) {
	    return "d";
	}
	if (i == 4) {
	    return "e";
	}
	if (i == 5) {
	    return "f";
	}
	if (i == 6) {
	    return "g";
	}	
	if (i == 7) {
	    return "h";
	}	
	if (i == 8) {
	    return "i";
	}
    }
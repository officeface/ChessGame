import java.util.Scanner;

/**
 * The Chessboard class creates a two-player chess game. Players take it in
 * turns to type in moves to the console, which will then update the pieces on
 * the chessboard and print the updated version to the console. Moves will be
 * checked against the basic rules of chess and will return errors if these
 * rules are not being followed, allowing the Players to re-attempt their go.
 * 
 * @author mark
 * @version Created 01/10/17
 */
public class Chessboard {

	/**
	 * Creates the enumerated type Chessmen for all the possible chess pieces. These
	 * are the King, Queen, Bishop, Rook, Knight and Pawn. There are White and Black
	 * versions of each of these pieces.
	 */
	public enum Chessmen {
		WHITE_KING, WHITE_QUEEN, WHITE_ROOK, WHITE_BISHOP, WHITE_KNIGHT, WHITE_PAWN, BLACK_KING, BLACK_QUEEN, BLACK_ROOK, BLACK_BISHOP, BLACK_KNIGHT, BLACK_PAWN, EMPTY
	}

	/**
	 * Takes a String move and converts it into integers initI, initJ (initial row
	 * and column coordinates resp.) and finI, finJ (final row and column
	 * coordinates resp.). Will set these values to numbers outside the allowed
	 * array if the input is non-standard according to the rules of chess.
	 * 
	 * @param chessboard
	 *            An 8x8 array of chessmen. The current situation of the game.
	 * @param move
	 *            The player's input as a String.
	 * @return The initial and final positions asked for by move in index form.
	 */
	public static int[] position(Chessmen[][] chessboard, String move) {

		String initRow, initCol, finRow, finCol;

		// Check the move makes sense:
		if (move.replaceAll("\\s+", "").length() == 0) {
			initRow = "nonsense";
			initCol = "nonsense";
			finRow = "nonsense";
			finCol = "nonsense";
		} else {
			String[] components = move.split(" ");

			if (components[0].length() > 1) {
				initRow = String.valueOf(components[0].charAt(1));
				initCol = String.valueOf(components[0].charAt(0));
			} else {
				initRow = "nonsense"; // Force an error if unexpected string appears.
				initCol = "nonsense";
			}

			if (components[components.length - 1].length() > 1) {
				finRow = String.valueOf(components[components.length - 1].charAt(1));
				finCol = String.valueOf(components[components.length - 1].charAt(0));
			} else {
				finRow = "nonsense"; // Force an error if unexpected string appears.
				finCol = "nonsense";
			}
		}

		int initI = 0; // initial row number
		int initJ = 0; // initial col number
		int finI = 0; // final row number
		int finJ = 0; // final col number

		// Interpret the initial row character and turn it into a correct integer
		// reference:
		switch (initRow.toLowerCase()) {
		case "1":
			initI = 0;
			break;
		case "2":
			initI = 1;
			break;
		case "3":
			initI = 2;
			break;
		case "4":
			initI = 3;
			break;
		case "5":
			initI = 4;
			break;
		case "6":
			initI = 5;
			break;
		case "7":
			initI = 6;
			break;
		case "8":
			initI = 7;
			break;
		default:
			initI = 10; // Unacceptable input will push an error here
			break;
		}

		// Interpret the initial column character and turn it into a correct integer
		// reference:
		switch (initCol.toLowerCase()) {
		case "a":
			initJ = 0;
			break;
		case "b":
			initJ = 1;
			break;
		case "c":
			initJ = 2;
			break;
		case "d":
			initJ = 3;
			break;
		case "e":
			initJ = 4;
			break;
		case "f":
			initJ = 5;
			break;
		case "g":
			initJ = 6;
			break;
		case "h":
			initJ = 7;
			break;
		default:
			initJ = 10; // Unacceptable input will push an error here
			break;
		}

		// Interpret the final row character and turn it into a correct integer
		// reference:
		switch (finRow.toLowerCase()) {
		case "1":
			finI = 0;
			break;
		case "2":
			finI = 1;
			break;
		case "3":
			finI = 2;
			break;
		case "4":
			finI = 3;
			break;
		case "5":
			finI = 4;
			break;
		case "6":
			finI = 5;
			break;
		case "7":
			finI = 6;
			break;
		case "8":
			finI = 7;
			break;
		default:
			finI = 10; // Unacceptable input will push an error here
			break;
		}

		// Interpret the final column character and turn it into a correct integer
		// reference:
		switch (finCol.toLowerCase()) {
		case "a":
			finJ = 0;
			break;
		case "b":
			finJ = 1;
			break;
		case "c":
			finJ = 2;
			break;
		case "d":
			finJ = 3;
			break;
		case "e":
			finJ = 4;
			break;
		case "f":
			finJ = 5;
			break;
		case "g":
			finJ = 6;
			break;
		case "h":
			finJ = 7;
			break;
		default:
			finJ = 10; // Unacceptable input will push an error here
			break;
		}

		int[] posVector = new int[4];
		posVector[0] = initI;
		posVector[1] = initJ;
		posVector[2] = finI;
		posVector[3] = finJ;
		return posVector;
	}

	/**
	 * Move takes the current state of the game and a user's input (the String,
	 * move). It uses the method position to get indices that correspond to the
	 * desired move and updates the chessboard accordingly. The old position of the
	 * chess piece is then wiped.
	 * 
	 * @param chessboard
	 *            An 8x8 array of chessmen. The current situation of the game.
	 * @param move
	 *            The player's input as a String.
	 */
	public static void move(Chessmen[][] chessboard, String move) {

		int[] posVector = Chessboard.position(chessboard, move);
		int initI = posVector[0];
		int initJ = posVector[1];
		int finI = posVector[2];
		int finJ = posVector[3];

		chessboard[finI][finJ] = chessboard[initI][initJ]; // Move the piece to its new position

		chessboard[initI][initJ] = Chessmen.EMPTY; // Clears the old position

	}

	/**
	 * Takes the current chessboard and prints it to the console along with spacing
	 * and labels for identification. Rows are numbered, while columns are lettered.
	 * Standard notation for a piece's position is letter followed by number (e.g.
	 * A7 or D5).
	 * 
	 * @param chessboard
	 *            An 8x8 array of chessmen. The current situation of the game.
	 */
	public static void printBoard(Chessmen[][] chessboard) {

		System.out.println("\ta\tb\tc\td\te\tf\tg\th\n");
		for (int i = 7; i >= 0; i--) {
			System.out.printf((i + 1) + ".\t");
			for (int j = 0; j < 8; j++) {

				switch (chessboard[i][j]) {
				case WHITE_KING:
					System.out.printf("\u2654\t");
					continue;
				case WHITE_QUEEN:
					System.out.printf("\u2655\t");
					continue;
				case WHITE_ROOK:
					System.out.printf("\u2656\t");
					continue;
				case WHITE_BISHOP:
					System.out.printf("\u2657\t");
					continue;
				case WHITE_KNIGHT:
					System.out.printf("\u2658\t");
					continue;
				case WHITE_PAWN:
					System.out.printf("\u2659\t");
					continue;
				case BLACK_KING:
					System.out.printf("\u265A\t");
					continue;
				case BLACK_QUEEN:
					System.out.printf("\u265B\t");
					continue;
				case BLACK_ROOK:
					System.out.printf("\u265C\t");
					continue;
				case BLACK_BISHOP:
					System.out.printf("\u265D\t");
					continue;
				case BLACK_KNIGHT:
					System.out.printf("\u265E\t");
					continue;
				case BLACK_PAWN:
					System.out.printf("\u265F\t");
					continue;
				default:
					System.out.printf("\t");
					continue;
				}

			}
			System.out.println("\n\n");
		}
		System.out.println("\ta\tb\tc\td\te\tf\tg\th\n");
	}

	/**
	 * Checks a move against basic rules of chess for Player 1. Traditionally,
	 * Player 1 plays White. Returns true if the move is valid. Returns false if the
	 * move is invalid.
	 * 
	 * @param chessboard
	 *            An 8x8 array of chessmen. The current situation of the game.
	 * @param move
	 *            The player's input as a String.
	 * @return true if the move follows the rules, false if the move is against the
	 *         rules.
	 */
	public static boolean ruleCheckPlayer1(Chessmen[][] chessboard, String move) {
		/*
		 * In the same way that the method "move" works, we will begin by using the
		 * position method to get initial and final coordinates of the String move.
		 */

		int[] posVector = Chessboard.position(chessboard, move);
		int initI = posVector[0];
		int initJ = posVector[1];
		int finI = posVector[2];
		int finJ = posVector[3];

		// Check coordinates are within bounds:
		if (initI > 7 || initI < 0 || initJ > 7 || initJ < 0 || finI > 7 || finI < 0 || finJ > 7 || finJ < 0) {
			System.err.println("Input coordinates are invalid.  Please type them again, in the form 'a6 to c4'.");
			return false;
		}

		/*
		 * RULES PLAYER 1!! Moves must now be tested for each piece. In short, the rules
		 * that will be used are: Pawn: 1. On first go can move forward one or two
		 * places. 2. On subsequent goes can only move forward one place. 3. Cannot move
		 * forward if the place ahead of it is blocked. 4. Can only take diagonally.
		 * 
		 * Rook: 1. Can move horizontally or vertically by any distance, unless
		 * something is in the way.
		 * 
		 * Knight: 1. Must either move vertically 3, horizontally 1; or horizontally 3,
		 * vertically 1.
		 * 
		 * Bishop: 1. Can move diagonally any distance, unless something is in the way.
		 * 
		 * Queen: 1. Can move any distance in any straight line direction, unless
		 * something is in the way.
		 * 
		 * King: 1. Can move one place in any direction, unless something is in the way.
		 */

		switch (chessboard[initI][initJ]) {
		case WHITE_PAWN:
			System.out.println("White pawn selected.");

			if (finI - initI == 1 && initJ == finJ && chessboard[finI][finJ] == Chessmen.EMPTY) {
				return true; // Ordinary move
			} else if (finI - initI == 1 && Math.abs(initJ - finJ) == 1 && chessboard[finI][finJ] != Chessmen.EMPTY
					&& chessboard[finI][finJ] != Chessmen.WHITE_PAWN && chessboard[finI][finJ] != Chessmen.WHITE_KING
					&& chessboard[finI][finJ] != Chessmen.WHITE_QUEEN && chessboard[finI][finJ] != Chessmen.WHITE_BISHOP
					&& chessboard[finI][finJ] != Chessmen.WHITE_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.WHITE_ROOK) {
				return true; // Taking move
			} else if (initI == 1 && (finI - initI == 1 || finI - initI == 2) && initJ == finJ
					&& chessboard[initI + 1][initJ] == Chessmen.EMPTY
					&& chessboard[initI + 2][initJ] == Chessmen.EMPTY) {
				return true; // Starting move
			} else {
				System.err.println("Not a valid pawn move. Try again.");
				return false;
			}

		case WHITE_KNIGHT:
			System.out.println("White knight selected.");

			if ((Math.abs(initI - finI) == 2) && (Math.abs(initJ - finJ) == 1)) {
				return true;
			} else if ((Math.abs(initI - finI) == 1) && (Math.abs(initJ - finJ) == 2)) {
				return true;
			} else {
				System.err.println("Not a valid knight move. Try again.");
				return false;
			}

		case WHITE_ROOK:
			System.out.println("White rook selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.WHITE_PAWN || chessboard[finI][finJ] == Chessmen.WHITE_ROOK
					|| chessboard[finI][finJ] == Chessmen.WHITE_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.WHITE_BISHOP || chessboard[finI][finJ] == Chessmen.WHITE_QUEEN
					|| chessboard[finI][finJ] == Chessmen.WHITE_KING) {
				System.err.println("Destination cell is already occupied by a white piece. Try again.");
				return false;
			}

			// Check if path between is free:
			if (initI == finI) {
				// Rows are same, so horizontal move
				int jump = (initJ < finJ) ? 1 : -1;

				for (int j = initJ + jump; j != finJ; j += jump) {
					if (chessboard[initI][j] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your rook. Try again.");
						return false;
					}
				}
			} else if (initJ == finJ) {
				// Cols are same, so vertical move
				int jump = (initI < finI) ? 1 : -1;

				for (int i = initI + jump; i != finI; i += jump) {
					if (chessboard[i][initJ] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your rook. Try again.");
						return false;
					}
				}
			} else {
				// Not a valid move
				System.err.println("Not a valid move for a rook. Try again.");
				return false;
			}

			return true;

		case WHITE_BISHOP:
			System.out.println("White bishop selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.WHITE_PAWN || chessboard[finI][finJ] == Chessmen.WHITE_ROOK
					|| chessboard[finI][finJ] == Chessmen.WHITE_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.WHITE_BISHOP || chessboard[finI][finJ] == Chessmen.WHITE_QUEEN
					|| chessboard[finI][finJ] == Chessmen.WHITE_KING) {
				System.err.println("Destination cell is already occupied by a white piece. Try again.");
				return false;
			}

			// Check that the difference between rows and columns is the same (i.e. bishop
			// is moving diagonally)
			// Will then check that there is no collision on the path between.
			if (Math.abs(initI - finI) == Math.abs(initJ - finJ)) {

				int jumpI = (initI < finI) ? 1 : -1;
				int jumpJ = (initJ < finJ) ? 1 : -1;

				for (int i = initI + jumpI; i != finI; i += jumpI) {
					for (int j = initJ + jumpJ; j != finJ; j += jumpJ)

						if (chessboard[i][j] != Chessmen.EMPTY && Math.abs(initI - i) == Math.abs(initJ - j)) {
							System.err.println("Something is in the way of your bishop. Try again.");
							return false;
						}
				}
			} else {
				System.err.println("Not a valid Bishop move. Try again.");
				return false;
			}

			return true;

		case WHITE_QUEEN:
			System.out.println("White queen selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.WHITE_PAWN || chessboard[finI][finJ] == Chessmen.WHITE_ROOK
					|| chessboard[finI][finJ] == Chessmen.WHITE_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.WHITE_BISHOP || chessboard[finI][finJ] == Chessmen.WHITE_QUEEN
					|| chessboard[finI][finJ] == Chessmen.WHITE_KING) {
				System.err.println("Destination cell is already occupied by a white piece. Try again.");
				return false;
			}

			// Will check that there is no collision on the path between.
			if (Math.abs(initI - finI) == Math.abs(initJ - finJ)) {
				// Diagonal move
				int jumpI = (initI < finI) ? 1 : -1;
				int jumpJ = (initJ < finJ) ? 1 : -1;

				for (int i = initI + jumpI; i != finI; i += jumpI) {
					for (int j = initJ + jumpJ; j != finJ; j += jumpJ)
						if (chessboard[i][j] != Chessmen.EMPTY && Math.abs(initI - i) == Math.abs(initJ - j)) {
							System.err.println("Something is in the way of your queen. Try again.");
							return false;
						}
				}
			} else if (initI == finI) {
				// Rows are same, so horizontal move
				int jump = (initJ < finJ) ? 1 : -1;

				for (int j = initJ + jump; j != finJ; j += jump) {
					if (chessboard[initI][j] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your queen. Try again.");
						return false;
					}
				}
			} else if (initJ == finJ) {
				// Cols are same, so vertical move
				int jump = (initI < finI) ? 1 : -1;

				for (int i = initI + jump; i != finI; i += jump) {
					if (chessboard[i][initJ] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your queen. Try again.");
						return false;
					}
				}
			} else {
				System.err.println("Not a valid move for a queen. Try again.");
				return false;
			}

			return true;

		case WHITE_KING:
			System.out.println("White king selected.");

			if (Math.abs(initI - finI) == 1 && Math.abs(initJ - finJ) == 1
					&& chessboard[finI][finJ] != Chessmen.WHITE_PAWN && chessboard[finI][finJ] != Chessmen.WHITE_KING
					&& chessboard[finI][finJ] != Chessmen.WHITE_QUEEN && chessboard[finI][finJ] != Chessmen.WHITE_BISHOP
					&& chessboard[finI][finJ] != Chessmen.WHITE_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.WHITE_ROOK) {
				// Diagonal move in any direction.
				return true;
			} else if (Math.abs(initI - finI) == 1 && initJ == finJ && chessboard[finI][finJ] != Chessmen.WHITE_PAWN
					&& chessboard[finI][finJ] != Chessmen.WHITE_KING && chessboard[finI][finJ] != Chessmen.WHITE_QUEEN
					&& chessboard[finI][finJ] != Chessmen.WHITE_BISHOP
					&& chessboard[finI][finJ] != Chessmen.WHITE_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.WHITE_ROOK) {
				// up or down move.
				return true;
			} else if (Math.abs(initJ - finJ) == 1 && initI == finI && chessboard[finI][finJ] != Chessmen.WHITE_PAWN
					&& chessboard[finI][finJ] != Chessmen.WHITE_KING && chessboard[finI][finJ] != Chessmen.WHITE_QUEEN
					&& chessboard[finI][finJ] != Chessmen.WHITE_BISHOP
					&& chessboard[finI][finJ] != Chessmen.WHITE_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.WHITE_ROOK) {
				// left or right move.
				return true;
			} else {
				System.err.println("Not a valid move for a king. Try again.");
				return false;
			}

		default:
			System.err.println("Non-white piece selected during Player 1's turn. Try again.");
			return false;
		}
	}

	/**
	 * Checks a move against basic rules of chess for Player 2. Traditionally,
	 * Player 2 plays Black. Returns true if the move is valid. Returns false if the
	 * move is invalid.
	 * 
	 * @param chessboard
	 *            An 8x8 array of chessmen. The current situation of the game.
	 * @param move
	 *            The player's input as a String.
	 * @return true if the move follows the rules, false if the move is against the
	 *         rules.
	 */
	public static boolean ruleCheckPlayer2(Chessmen[][] chessboard, String move) {
		/*
		 * In the same way that the method "move" works, we will begin by using the
		 * position method to get initial and final coordinates of the String move.
		 */

		int[] posVector = Chessboard.position(chessboard, move);
		int initI = posVector[0];
		int initJ = posVector[1];
		int finI = posVector[2];
		int finJ = posVector[3];

		// Check coordinates are within bounds:
		if (initI > 7 || initI < 0 || initJ > 7 || initJ < 0 || finI > 7 || finI < 0 || finJ > 7 || finJ < 0) {
			System.err.println("Input coordinates are invalid.  Please type them again, in the form 'a6 to c4'.");
			return false;
		}

		/*
		 * RULES PLAYER 2!! Moves must now be tested for each piece. In short, the rules
		 * that will be used are: Pawn: 1. On first go can move forward one or two
		 * places. 2. On subsequent goes can only move forward one place. 3. Cannot move
		 * forward if the place ahead of it is blocked. 4. Can only take diagonally.
		 * 
		 * Rook: 1. Can move horizontally or vertically by any distance, unless
		 * something is in the way.
		 * 
		 * Knight: 1. Must either move vertically 3, horizontally 1; or horizontally 3,
		 * vertically 1.
		 * 
		 * Bishop: 1. Can move diagonally any distance, unless something is in the way.
		 * 
		 * Queen: 1. Can move any distance in any straight line direction, unless
		 * something is in the way.
		 * 
		 * King: 1. Can move one place in any direction, unless something is in the way.
		 */

		switch (chessboard[initI][initJ]) {
		case BLACK_PAWN:
			System.out.println("Black pawn selected.");
			if (initI - finI == 1 && initJ == finJ && chessboard[finI][finJ] == Chessmen.EMPTY) {
				return true; // ordinary move
			} else if (initI - finI == 1 && Math.abs(initJ - finJ) == 1 && chessboard[finI][finJ] != Chessmen.EMPTY
					&& chessboard[finI][finJ] != Chessmen.BLACK_PAWN && chessboard[finI][finJ] != Chessmen.BLACK_KING
					&& chessboard[finI][finJ] != Chessmen.BLACK_QUEEN && chessboard[finI][finJ] != Chessmen.BLACK_BISHOP
					&& chessboard[finI][finJ] != Chessmen.BLACK_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.BLACK_ROOK) {
				return true; // taking move
			} else if (initI == 6 && (initI - finI == 1 || initI - finI == 2) && initJ == finJ
					&& chessboard[initI - 1][initJ] == Chessmen.EMPTY
					&& chessboard[initI - 2][initJ] == Chessmen.EMPTY) {
				return true; // starting move
			} else {
				System.err.println("Not a valid pawn move. Try again.");
				return false;
			}

		case BLACK_ROOK:
			System.out.println("Black rook selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.BLACK_PAWN || chessboard[finI][finJ] == Chessmen.BLACK_ROOK
					|| chessboard[finI][finJ] == Chessmen.BLACK_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.BLACK_BISHOP || chessboard[finI][finJ] == Chessmen.BLACK_QUEEN
					|| chessboard[finI][finJ] == Chessmen.BLACK_KING) {
				System.err.println("Destination cell is already occupied by a black piece. Try again.");
				return false;
			}

			// Check if path between is free:
			if (initI == finI) {
				// Rows are same, so horizontal move
				int jump = (initJ < finJ) ? 1 : -1;

				for (int j = initJ + jump; j != finJ; j += jump) {
					if (chessboard[initI][j] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your rook. Try again.");
						return false;
					}
				}
			} else if (initJ == finJ) {
				// Cols are same, so vertical move
				int jump = (initI < finI) ? 1 : -1;

				for (int i = initI + jump; i != finI; i += jump) {
					if (chessboard[i][initJ] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your rook. Try again.");
						return false;
					}
				}
			} else {
				// Not a valid move
				System.err.println("Not a valid move for a rook. Try again.");
				return false;
			}

			return true;

		case BLACK_KNIGHT:
			System.out.println("Black knight selected.");

			if ((Math.abs(initI - finI) == 2) && (Math.abs(initJ - finJ) == 1)) {
				return true;
			} else if ((Math.abs(initI - finI) == 1) && (Math.abs(initJ - finJ) == 2)) {
				return true;
			} else {
				System.err.println("Not a valid knight move. Try again.");
				return false;
			}

		case BLACK_BISHOP:
			System.out.println("Black bishop selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.BLACK_PAWN || chessboard[finI][finJ] == Chessmen.BLACK_ROOK
					|| chessboard[finI][finJ] == Chessmen.BLACK_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.BLACK_BISHOP || chessboard[finI][finJ] == Chessmen.BLACK_QUEEN
					|| chessboard[finI][finJ] == Chessmen.BLACK_KING) {
				System.err.println("Destination cell is already occupied by a black piece. Try again.");
				return false;
			}

			// Check that the difference between rows and columns is the same (i.e. bishop
			// is moving diagonally)
			// Will then check that there is no collision on the path between.
			if (Math.abs(initI - finI) == Math.abs(initJ - finJ)) {

				int jumpI = (initI < finI) ? 1 : -1;
				int jumpJ = (initJ < finJ) ? 1 : -1;

				for (int i = initI + jumpI; i != finI; i += jumpI) {
					for (int j = initJ + jumpJ; j != finJ; j += jumpJ)
						if (chessboard[i][j] != Chessmen.EMPTY && Math.abs(initI - i) == Math.abs(initJ - j)) {
							System.err.println("Something is in the way of your bishop. Try again.");
							return false;
						}
				}
			} else {
				System.err.println("Not a valid bishop move. Try again.");
				return false;
			}

			return true;

		case BLACK_QUEEN:
			System.out.println("Black queen selected.");

			// Attempt to move to the same cell:
			if (initI == finI && initJ == finJ) {
				System.err.println("Attempt to move to the same cell not allowed. Try again.");
				return false;
			}

			// Check if destination is free:
			if (chessboard[finI][finJ] == Chessmen.BLACK_PAWN || chessboard[finI][finJ] == Chessmen.BLACK_ROOK
					|| chessboard[finI][finJ] == Chessmen.BLACK_KNIGHT
					|| chessboard[finI][finJ] == Chessmen.BLACK_BISHOP || chessboard[finI][finJ] == Chessmen.BLACK_QUEEN
					|| chessboard[finI][finJ] == Chessmen.BLACK_KING) {
				System.err.println("Destination cell is already occupied by a black piece. Try again.");
				return false;
			}

			// Will check that there is no collision on the path between.
			if (Math.abs(initI - finI) == Math.abs(initJ - finJ)) {
				// Diagonal move
				int jumpI = (initI < finI) ? 1 : -1;
				int jumpJ = (initJ < finJ) ? 1 : -1;

				for (int i = initI + jumpI; i != finI; i += jumpI) {
					for (int j = initJ + jumpJ; j != finJ; j += jumpJ)
						if (chessboard[i][j] != Chessmen.EMPTY && Math.abs(initI - i) == Math.abs(initJ - j)) {
							System.err.println("Something is in the way of your queen. Try again.");
							return false;
						}
				}
			} else if (initI == finI) {
				// Rows are same, so horizontal move
				int jump = (initJ < finJ) ? 1 : -1;

				for (int j = initJ + jump; j != finJ; j += jump) {
					if (chessboard[initI][j] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your queen. Try again.");
						return false;
					}
				}
			} else if (initJ == finJ) {
				// Cols are same, so vertical move
				int jump = (initI < finI) ? 1 : -1;

				for (int i = initI + jump; i != finI; i += jump) {
					if (chessboard[i][initJ] != Chessmen.EMPTY) {
						System.err.println("Something is in the way of your queen. Try again.");
						return false;
					}
				}
			} else {
				System.err.println("Not a valid move for a queen. Try again.");
				return false;
			}

			return true;

		case BLACK_KING:
			System.out.println("Black king selected.");

			if (Math.abs(initI - finI) == 1 && Math.abs(initJ - finJ) == 1
					&& chessboard[finI][finJ] != Chessmen.BLACK_PAWN && chessboard[finI][finJ] != Chessmen.BLACK_KING
					&& chessboard[finI][finJ] != Chessmen.BLACK_QUEEN && chessboard[finI][finJ] != Chessmen.BLACK_BISHOP
					&& chessboard[finI][finJ] != Chessmen.BLACK_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.BLACK_ROOK) {
				// Diagonal move in any direction.
				return true;
			} else if (Math.abs(initI - finI) == 1 && initJ == finJ && chessboard[finI][finJ] != Chessmen.BLACK_PAWN
					&& chessboard[finI][finJ] != Chessmen.BLACK_KING && chessboard[finI][finJ] != Chessmen.BLACK_QUEEN
					&& chessboard[finI][finJ] != Chessmen.BLACK_BISHOP
					&& chessboard[finI][finJ] != Chessmen.BLACK_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.BLACK_ROOK) {
				// up or down move.
				return true;
			} else if (Math.abs(initJ - finJ) == 1 && initI == finI && chessboard[finI][finJ] != Chessmen.BLACK_PAWN
					&& chessboard[finI][finJ] != Chessmen.BLACK_KING && chessboard[finI][finJ] != Chessmen.BLACK_QUEEN
					&& chessboard[finI][finJ] != Chessmen.BLACK_BISHOP
					&& chessboard[finI][finJ] != Chessmen.BLACK_KNIGHT
					&& chessboard[finI][finJ] != Chessmen.BLACK_ROOK) {
				// left or right move.
				return true;
			} else {
				System.err.println("Not a valid move for a king. Try again.");
				return false;
			}

		default:
			System.err.println("Non-black piece selected during Player 2's turn. Try again.");
			return false;
		}
	}

	/**
	 * program generates a chessboard and prints its initial state to the console.
	 * The game is designed for two players, who must enter moves of the form "a6 to
	 * b3" (case does not matter). Players must type "exit" in order to exit the
	 * program.
	 * 
	 * @param args ...
	 * 
	 */
	public static void main(String[] args) {

		Chessmen[][] chessboard = new Chessmen[8][8];

		chessboard[0][0] = Chessmen.WHITE_ROOK;
		chessboard[0][1] = Chessmen.WHITE_KNIGHT;
		chessboard[0][2] = Chessmen.WHITE_BISHOP;
		chessboard[0][3] = Chessmen.WHITE_QUEEN;
		chessboard[0][4] = Chessmen.WHITE_KING;
		chessboard[0][5] = Chessmen.WHITE_BISHOP;
		chessboard[0][6] = Chessmen.WHITE_KNIGHT;
		chessboard[0][7] = Chessmen.WHITE_ROOK;

		chessboard[7][0] = Chessmen.BLACK_ROOK;
		chessboard[7][1] = Chessmen.BLACK_KNIGHT;
		chessboard[7][2] = Chessmen.BLACK_BISHOP;
		chessboard[7][3] = Chessmen.BLACK_QUEEN;
		chessboard[7][4] = Chessmen.BLACK_KING;
		chessboard[7][5] = Chessmen.BLACK_BISHOP;
		chessboard[7][6] = Chessmen.BLACK_KNIGHT;
		chessboard[7][7] = Chessmen.BLACK_ROOK;

		for (int i = 1; i < 7; i++) {
			switch (i) {
			case 1:
				for (int j = 0; j < 8; j++) {
					chessboard[i][j] = Chessmen.WHITE_PAWN;
				}
				continue;

			case 6:
				for (int j = 0; j < 8; j++) {
					chessboard[i][j] = Chessmen.BLACK_PAWN;
				}
				continue;

			default:
				for (int j = 0; j < 8; j++) {
					chessboard[i][j] = Chessmen.EMPTY;
				}
				continue;
			}
		}

		Chessboard.printBoard(chessboard);

		/**
		 * Take user's input to allow basic moves, e.g. e1 to e5. While loop will
		 * continue until "exit" is typed.
		 */
		while (true) {

			Scanner console = new Scanner(System.in);

			// Player 1's turn. Code will repeat in a while loop until Player 1 submits a
			// valid move or terminates the program.
			while (true) {
				System.out.println("Player 1 (White) move:");
				String inputPlayer1 = console.nextLine();

				if (inputPlayer1.equalsIgnoreCase("exit")) {
					System.out.println("Program exited by Player 1.");
					System.exit(0);
				} else if (Chessboard.ruleCheckPlayer1(chessboard, inputPlayer1)) {
					Chessboard.move(chessboard, inputPlayer1);
					Chessboard.printBoard(chessboard);
					break;
				} else {
					System.err.println("");
				}
			}

			// Player 2's turn. Code will repeat in a while loop until Player 2 submits a
			// valid move or terminates the program.
			while (true) {
				System.out.println("Player 2 (Black) move:");
				String inputPlayer2 = console.nextLine();

				if (inputPlayer2.equalsIgnoreCase("exit")) {
					System.out.println("Program exited by Player 2.");
					System.exit(0);
				} else if (Chessboard.ruleCheckPlayer2(chessboard, inputPlayer2)) {
					Chessboard.move(chessboard, inputPlayer2);
					Chessboard.printBoard(chessboard);
					break;
				} else {
					System.err.println("");
				}
			}
		}
	}
}

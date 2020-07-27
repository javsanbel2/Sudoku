package com.javier.sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SudokuTest {
	
	// It has been considered the following tests:
	
	// *** INPUT ***
	// Path file incorrect
	// Format file incorrect
	// Structure incorrect
	// Number out of 9 or negative
	// Inssuficient givens
	
	// *** VALID ***
	// Duplicated value in row
	// Duplicated value in column
	// Duplicated value in Square
	
	@Test
    public void checkValidSudoku() {
		String path = "input_tests/sudoku_valid.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), true);
    }
	
	// ********* INPUT **********
	@Test
    public void checkIncorrectPath() {
		String path = "fileDoesNotExist.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }

	@Test
    public void checkIncorrectFormat() {
		String path = "asd.csv";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	// Check that the numbers are beetween [0-9]
	// INPUT: number negative
	@Test
    public void CheckNumbersOutOfBounds1() {
		String path = "input_tests/sudoku_invalid_out_bounds1.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	// Check that the numbers are beetween [0-9]
	// INPUT: number greater than 9
	@Test
    public void CheckNumbersOutOfBounds2() {
		String path = "input_tests/sudoku_invalid_out_bounds2.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	@Test
    public void checkInssuficientValues() {
		String path = "input_tests/sudoku_inssuficient_values.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	// ********* VALID **********
	@Test
    public void checkDuplicatedValuesInRow() {
		String path = "input_tests/sudoku_invalid_row.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	@Test
    public void checkDuplicatedValuesInColumn() {
		String path = "input_tests/sudoku_invalid_column.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }
	
	@Test
    public void checkDuplicatedValuesInSquare() {
		String path = "input_tests/sudoku_invalid_square.txt";
		Sudoku sudoku = new Sudoku(path);
		assertEquals(sudoku.valid_sudoku(), false);
    }

}

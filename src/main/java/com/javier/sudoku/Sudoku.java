package com.javier.sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {

	private boolean valid_input = true;
	private final int[][] sudoku;

	public Sudoku(String file_path) {
		this.sudoku = this.import_sudoku(file_path);
	}
	
	// *****************************************
	// Function to check if a given sudoku is valid
	// *****************************************
	public Boolean valid_sudoku() {
		if (!valid_input) {return false;}
		Boolean res = true;
		
		for (int i=0; i<9; i++) {
			res = this.valid_row(i) && this.valid_column(i);
			
			if (!res) {
				return false;
			}
		}
		res = this.valid_squares();
		
		if (res) {
			System.out.println("The sudoku is VALID");
		}
		return res;
	}

	// *****************************************
	// Function to check if in rows there are duplicated numbers in 
	// no-zero numbers.
	// *****************************************
	private Boolean valid_row(int column) {
		Boolean res = true;
		boolean[] duplicated = new boolean[10];
		
		for (int i=0; i<9; i++) {
			int value = this.sudoku[column][i];
			
			// If there is a value && is not duplicated
			if (value == 0) {
				continue;
			} else if (value > 0 && duplicated[value] == false) {
				duplicated[value] = true;
			} else {
				System.out.printf("[Row %d not valid]: Duplicated number %d \n", column+1, value);
				res = false;
			}
		}
		return res;
	}
	
	// *****************************************
	// Function to check if in columns there are duplicated numbers in 
	// no-zero numbers.
	// *****************************************
	private Boolean valid_column(int row) {
		Boolean res = true;
		boolean[] duplicated = new boolean[10];
		
		for (int i=0; i<9; i++) {
			int value = this.sudoku[i][row];
			
			// If there is a value && is not duplicated
			if (value == 0) {
				continue;
			} else if (value > 0 && duplicated[value] == false) {
				duplicated[value] = true;
			} else {
				System.out.printf("[Column %d not valid]: Duplicated number %d\n", row+1, value);
				res = false;
			}
		}
		return res;
	}
	
	// *****************************************
	// Function to check if in squares there are duplicated numbers in 
	// no-zero numbers.
	// *****************************************
	private Boolean valid_squares() {
		Boolean res = true;
		
		for (int i=0; i<9; i=i+3) {
			for (int j=0; j<9; j=j+3) {
				res = valid_square(i, j);
				
				if (!res) {
					return false;
				}
			}
		}
		return true;
	}
	
	// *****************************************
	// Function to check if a specific square have
	// duplicated numbers in no-zero numbers. 
	// *****************************************
	private Boolean valid_square(int i, int j) {
		Boolean res = true;
		boolean[] duplicated = new boolean[10];
		
		for (int x=i; x<i+3; x++) {
			for (int y=j; y<j+3; y++) {
				int value = this.sudoku[x][y];
				
				if (value == 0) {
					continue;
				} else if (value > 0 && duplicated[value] == false) {
					duplicated[value] = true;
				} else {
					System.out.printf(
							"[Square not valid]: Duplicated number %d in square starting in position (row,column): %d,%d \n", value, i+1, j+1);
					res = false;
				}
			}
		}
		return res;
	}
	
	// *****************************************
	// Function to print in console sudoku
	// *****************************************
	public void print_sudoku() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(this.sudoku[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	// *****************************************
	// Import sudoku from text file (CSV FORMAT)
	// *****************************************
	private int[][] import_sudoku(String file_path) {
		int[][] grid = new int[9][9];
		String line = null;
		int i = 0; // index row
		int acum_values = 0; // min 16 values
		
		// Check format file
		if (!file_path.endsWith(".txt")) {
			System.out.println("ERROR: Bad input format file");
			this.valid_input = false;
			return grid;
		}
		
		// Read document file txt
		try {
			BufferedReader br = new BufferedReader(new FileReader(file_path));

			// Read sudoku
			while ((line = br.readLine()) != null) {
				String[] row_string = line.trim().split(",");

				for (int j = 0; j < 9; j++) {
					int l = row_string.length;
					String j_value = l <= j ? "0" : row_string[j];
					grid[i][j] = j_value.contentEquals("") ? 0 : Integer.parseInt(j_value);
					
					if (grid[i][j] < 0 || grid[i][j] > 9) {
						br.close();
						System.out.println("ERROR: Numbers are not between 0 and 9");
						this.valid_input = false;
						return grid;
					} else if (grid[i][j] > 0) {
						acum_values++;
					}
				}
				i++;// next row
			};

			br.close();
			if (acum_values < 16) {
				System.out.println("ERROR: A sudoku needs at least 19 values to only have one unique solution");
				this.valid_input = false;
				return grid;
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: The file could not be found.");
			this.valid_input = false;
		} catch (IOException e) {
			System.out.println("ERROR: The file could not be read.");
			this.valid_input = false;
		}
		
		return grid;
	}

	public static void main(String[] args) {
		System.out.println("Validating sudoku...\n...");
		String fileName = args[0];
		Sudoku sudoku = new Sudoku(fileName);
		sudoku.valid_sudoku();		
	}
}

package com.ga.sudoku.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class SudokuService {

    Sudoku sudoku;
    SudokuSolver sudokuSolver;

    public String saveSudokuBoard(int puzzleNumber) {
        List<Integer> validPuzzleNumbers = Arrays.asList(1, 2, 3, 4);
        boolean valid = validPuzzleNumbers.stream().anyMatch(n -> n == puzzleNumber);
        if (!valid) {
            return "Invalid puzzle number, please input 1,2,3 or 4";
        }
        int[][] initialBoard = sudoku.createBoard(puzzleNumber);
        System.out.println("Initial Board");
        sudoku.printBoard(initialBoard);
        int[][] solvedBoard = sudokuSolver.solve(initialBoard);
        System.out.println("Solved Board");
        sudoku.printBoard(solvedBoard);
        sudoku.saveBoard(solvedBoard, puzzleNumber);
        return "Board saved successfully, Please check resources/game/puzzle" + puzzleNumber + ".solution.txt and the terminal for the initial and solved boards !";
    }
}

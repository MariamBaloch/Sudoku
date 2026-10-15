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

    public String getSudokuBoard(int puzzleNumber) {
        if (validPuzzle(puzzleNumber)) {
            return "Invalid puzzle number, please input 1,2,3 or 4";
        }
        int[][] initialBoard = sudoku.createBoard(puzzleNumber);
        return sudoku.printAndReturnBoard(initialBoard);
    }

    public String saveSudokuBoard(int puzzleNumber) {
        if (validPuzzle(puzzleNumber)) {
            return "Invalid puzzle number, please input 1,2,3 or 4";
        }

        int[][] initialBoard = sudoku.createBoard(puzzleNumber);
        int[][] solvedBoard = sudokuSolver.solve(initialBoard);

        sudoku.saveBoard(solvedBoard, puzzleNumber);
        return solvedReturn(initialBoard, solvedBoard, puzzleNumber);
    }

    public String solvedReturn(int[][] initialBoard, int[][] solvedBoard, int puzzleNumber) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Board saved successfully, Please check resources/game/puzzle" + puzzleNumber + ".solution.txt\n\n");
        returnString.append("Initial Board\n");
        returnString.append(sudoku.printAndReturnBoard(initialBoard));

        returnString.append("Solved Board\n");
        returnString.append(sudoku.printAndReturnBoard(solvedBoard));
        return returnString.toString();
    }

    public boolean validPuzzle(int puzzleNumber) {
        List<Integer> validPuzzleNumbers = Arrays.asList(1, 2, 3, 4);
        return validPuzzleNumbers.stream().noneMatch(n -> n == puzzleNumber);
    }
}

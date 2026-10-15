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
        StringBuilder returnString = new StringBuilder();
        returnString.append("Board saved successfully, Please check resources/game/puzzle").append(puzzleNumber).append(".solution.txt\n\n");

        int[][] initialBoard = sudoku.createBoard(puzzleNumber);
        returnString.append("Initial Board\n");
        returnString.append(sudoku.printAndReturnBoard(initialBoard));

        int[][] solvedBoard = sudokuSolver.solve(initialBoard);

        returnString.append("Solved Board\n");
        returnString.append(sudoku.printAndReturnBoard(solvedBoard));

        sudoku.saveBoard(solvedBoard, puzzleNumber);
        return returnString.toString();
    }

    public boolean validPuzzle(int puzzleNumber) {
        List<Integer> validPuzzleNumbers = Arrays.asList(1, 2, 3, 4);
        return validPuzzleNumbers.stream().noneMatch(n -> n == puzzleNumber);
    }
}

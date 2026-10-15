package com.ga.sudoku.controller;

import com.ga.sudoku.exceptions.InvalidCharacterException;
import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import com.ga.sudoku.service.SudokuService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sudoku")
@AllArgsConstructor
public class SudokuController {

    SudokuService sudokuService;

    @GetMapping("/board/{puzzleNumber}")
    @Operation(summary = "Returns the original puzzle board", description = "Returns and prints the pre generated board stored in resources/game  in the terminal")
    public String getSudokuBoard(@PathVariable int puzzleNumber) {
        try {
            return sudokuService.getSudokuBoard(puzzleNumber);
        } catch (SudokuFileNotFoundException | InvalidCharacterException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/solve/{puzzleNumber}")
    @Operation(summary = "Solves a puzzle", description = "Solves the pregenerated puzzle, saves it in resources/game and prints it in the terminal")
    public String saveSudokuBoard(@PathVariable int puzzleNumber) {
        try {
            return sudokuService.saveSudokuBoard(puzzleNumber);
        } catch (SudokuFileNotFoundException | InvalidCharacterException e) {
            return e.getMessage();
        }
    }
}

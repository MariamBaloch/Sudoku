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

    @GetMapping("/solve/{puzzleNumber}")
    @Operation(summary = "Solves a puzzle", description = "Solves the pregenerated puzzle and saves it in resources/game")
    public String saveSudokuBoard(@PathVariable int puzzleNumber) {
        try {
            return sudokuService.saveSudokuBoard(puzzleNumber);
        } catch (SudokuFileNotFoundException | InvalidCharacterException |
                 NumberFormatException e) {
            return e.getMessage();
        }

    }
}

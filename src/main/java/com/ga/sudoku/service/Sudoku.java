package com.ga.sudoku.service;

import com.ga.sudoku.exceptions.InvalidCharacterException;
import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class Sudoku {

    private final List<Integer> validBoardNumbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private final ResourceLoader resourceLoader;

    public Sudoku(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public int[][] createBoard(int puzzleNumber) {
        int[][] grid = new int[9][9];
        int rowIndex = 0;

        String filePath = "classpath:game/puzzle" + puzzleNumber + ".txt";
        Resource gameFile = resourceLoader.getResource(filePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(gameFile.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("-")) {
                    continue;
                }

                String[] tokens = line.split("[\\s|]+");

                for (int colIndex = 0; colIndex < 9; colIndex++) {
                    int value;
                    try {
                        value = Integer.parseInt(tokens[colIndex]);
                        if (!validBoardNumbers.contains(value)) {
                            throw new InvalidCharacterException();
                        }
                    } catch (NumberFormatException e) {
                        throw new InvalidCharacterException();
                    }
                    grid[rowIndex][colIndex] = value;
                }
                rowIndex++;
            }
        } catch (IOException e) {
            throw new SudokuFileNotFoundException("Sudoku puzzle file not found: " + e.getMessage());
        }
        return grid;
    }

    public void saveBoard(int[][] grid, int puzzleNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/game/puzzle" + puzzleNumber + ".solution.txt"))) {

            for (int row = 0; row < 9; row++) {

                for (int col = 0; col < 9; col++) {
                    writer.write(grid[row][col] + "  ");

                    if (col == 2 || col == 5) {
                        writer.write("|  ");
                    }
                }

                writer.newLine();

                if (row == 2 || row == 5) {
                    writer.write("----------------------------------");
                    writer.newLine();
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to save Sudoku board: " + e.getMessage(), e);
        }
    }

    public void printBoard(int[][] grid) {
        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {
                System.out.print(grid[row][col] + "  ");

                if (col == 2 || col == 5) {
                    System.out.print("|  ");
                }
            }

            System.out.println(" ");

            if (row == 2 || row == 5 || row == 8) {
                System.out.print("----------------------------------\n\n");
            }
        }
    }
}

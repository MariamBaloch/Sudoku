package com.ga.sudoku.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class SudokuSolver {

    Sudoku sudoku;

    public int[][] solve(int[][] board) {
        boolean changed;
        do {
            changed = false;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    if (board[i][j] == 0) {
                        String cellKey = i + "," + j;
                        Set<Integer> candidates = createCandidates(board, i, j);

                        if (candidates.size() == 1) {
                            int value = candidates.iterator().next();
                            board[i][j] = value;
                            changed = true;
                        }
                    }
                }
            }

        } while (changed);

        return board;
    }

    private static Set<Integer> createCandidates(int[][] board, int i, int j) {
        Set<Integer> candidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int k = 0; k < 9; k++) {
            candidates.remove(board[i][k]);
            candidates.remove(board[k][j]);
        }

        int startRow = (i / 3) * 3;
        int startCol = (j / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                candidates.remove(board[r][c]);
            }
        }
        return candidates;
    }
}

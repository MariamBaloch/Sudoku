package com.ga.sudoku.exceptions;

public class InvalidCharacterException extends RuntimeException {
    public InvalidCharacterException() {
        super("Board must only contain numbers from 0 to 9");
    }
}

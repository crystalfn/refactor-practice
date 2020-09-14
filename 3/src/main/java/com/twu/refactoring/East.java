package com.twu.refactoring;

public class East extends Direction {
    @Override
    public char getDirection() {
        return 'E';
    }

    @Override
    public Direction turnRight() {
        return new South();
    }

    @Override
    public Direction turnLeft() {
        return new North();
    }
}

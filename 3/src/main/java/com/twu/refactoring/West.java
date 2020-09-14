package com.twu.refactoring;

public class West extends Direction {
    @Override
    public char getDirection() {
        return 'W';
    }

    @Override
    public Direction turnRight() {
        return new North();
    }

    @Override
    public Direction turnLeft() {
        return new South();
    }
}

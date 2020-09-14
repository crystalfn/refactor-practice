package com.twu.refactoring;

public class South extends Direction {
    @Override
    public char getDirection() {
        return 'S';
    }

    @Override
    public Direction turnRight() {
        return new West();
    }

    @Override
    public Direction turnLeft() {
        return new East();
    }
}

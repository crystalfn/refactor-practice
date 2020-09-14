package com.twu.refactoring;

public class North extends Direction{
    @Override
    public char getDirection() {
        return 'N';
    }

    @Override
    public Direction turnRight() {
        return new East();
    }

    @Override
    public Direction turnLeft() {
        return new West();
    }
}

package com.blasco991.gameOfLife;

import java.util.stream.IntStream;

public class GameOfLife extends CellularAutomata {

    private GameOfLife(int n, int m) {
        super(new BoardImpl(n, m));
    }

    @Override
    protected boolean computeValue(CellularAutomata.Board board, int x, int y) {
        boolean alive = board.getValue(x, y);

        long neighbors = IntStream.rangeClosed(-1, 1)
                .mapToLong(dx -> IntStream.rangeClosed(-1, 1)
                        .filter(dy -> (dx != 0 || dy != 0) && (board.getValue(x + dx, y + dy)))
                        .count()
                ).sum();

        if (alive)
            return !(neighbors < 2 || neighbors > 3);
        else return neighbors == 3;
    }

    public static void main(String[] args) throws InterruptedException {
        new GameOfLife(10, 10).start();
    }
}
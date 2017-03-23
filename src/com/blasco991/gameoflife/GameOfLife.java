package gameoflife;


import java.util.stream.IntStream;

public class GameOfLife extends CellularAutomata {

    private GameOfLife(int n, int m) {
        super(new BoardImpl(n, m));
    }

    @Override
    protected int computeValue(Board board, int x, int y) {
        boolean alive = board.getValue(x, y);

        long neighbors = IntStream.rangeClosed(-1, 1)
                .mapToLong(dx -> IntStream.rangeClosed(-1, 1)
                        .filter(dy -> (dx != 0 || dy != 0) && (board.getValue(x + dx, y + dy)))
                        .count()
                ).sum();

        if (alive)
            if (neighbors < 2 || neighbors > 3)
                return 0;
            else
                return 1;
        else if (neighbors == 3)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        new GameOfLife(10, 10).start();
    }
}
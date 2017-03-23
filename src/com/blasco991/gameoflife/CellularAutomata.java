package gameoflife;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * CellularAutomata
 * <p>
 * Coordinating computation in a cellular automaton with CyclicBarrier
 *
 * @author Brian Goetz, Tim Peierls and Fausto Spoto
 */

public abstract class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    CellularAutomata(Board board) {
        int count = Runtime.getRuntime().availableProcessors();
        this.mainBoard = board;
        this.barrier = new CyclicBarrier(count, mainBoard::commitNewValues);
        this.workers = IntStream.range(0, count).mapToObj(
                i -> new Worker(mainBoard.getSubBoard(count, i))
        ).toArray(Worker[]::new);
    }

    protected abstract int computeValue(Board board, int x, int y);

    private class Worker implements Runnable {
        private final Board board;

        Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                IntStream.range(0, board.getMaxX()).forEach(
                        x -> IntStream.range(0, board.getMaxY()).forEach(
                                y -> board.setNewValue(x, y, computeValue(board, x, y))
                        ));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    void start() throws InterruptedException {
        Arrays.stream(workers).forEach(worker -> new Thread(worker).start());
        mainBoard.waitForConvergence();
    }

    public interface Board {
        int getMaxX();

        int getMaxY();

        boolean getValue(int x, int y);

        void setNewValue(int x, int y, int value);

        void commitNewValues();

        boolean hasConverged();

        void waitForConvergence() throws InterruptedException; // blocking

        Board getSubBoard(int numPartitions, int index);
    }
}
package com.blasco991.gameOfLife;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class BoardImpl extends AbstractBoard {
    private final int[][] current;
    private final int[][] next;
    private final CountDownLatch converged = new CountDownLatch(1);
    private final Random random = new Random();

    BoardImpl(int n, int m) {
        if (n < 1 || m < 1) throw new IllegalArgumentException();
        this.current = new int[n][m];
        this.next = new int[n][m];
        IntStream.range(0, n).forEach(
                i -> current[i] = random.ints(current[i].length, 0, 2).toArray()
        );
        /*this.next = IntStream.range(0, n).mapToObj(
                x -> random.ints(m, 0, 1).toArray()
        ).toArray(int[][]::new);*/
    }

    @Override
    public int getMaxX() {
        return current[0].length;
    }

    @Override
    public int getMaxY() {
        return current.length;
    }

    @Override
    public boolean getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= getMaxX() || y >= getMaxY())
            return false;
        else
            return current[y][x] != 0;
    }

    private String getStringValue(int x, int y) {
        return getValue(x, y) ? " " : "*";
    }

    @Override
    public void setNewValue(int x, int y, int value) {
        next[y][x] = value;
    }

    @Override
    public void commitNewValues() {
        System.out.println(IntStream.rangeClosed(0, current.length).mapToObj(i -> "-").reduce("", String::concat).concat(this.toString()));
        boolean changed = !Arrays.deepEquals(current, next);
        IntStream.range(0, current.length).forEach(i -> current[i] = Arrays.copyOf(next[i], next[i].length));
        if (!changed) converged.countDown();
    }

    @Override
    public boolean hasConverged() {
        return converged.getCount() < 1;
    }

    @Override
    public void waitForConvergence() throws InterruptedException {
        converged.await();
    }

    @Override
    public String toString() {
        return "\n" + IntStream.range(0, getMaxY()).mapToObj(
                y -> "|".concat(
                        IntStream.range(0, getMaxX()).mapToObj(
                                x -> getStringValue(x, y)
                        ).reduce("", String::concat)
                ).concat("|\n")
        ).reduce("", String::concat).concat("\n");
    }
}
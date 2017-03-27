package com.blasco991.matrixMultiplication;

import java.util.Formatter;

/**
 * Created by blasco991 on 14/03/17.
 */
public class ParallelExecution {

    public static void main(String[] args) {
        System.out.println("Performing " + Matrix.K + " random multiplications");

        long startTime = System.currentTimeMillis();
        for (int counter = 0; counter < Matrix.K; counter++)
            Matrix.randomMultiplication();
        long sequentialTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int counter = 0; counter < Matrix.K; counter++)
            Matrix.parallelRandomMultiplication();
        long parallelTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int counter = 0; counter < Matrix.K; counter++)
            Matrix.parallelStreamRandomMultiplication();
        long parallelStreamTime = System.currentTimeMillis() - startTime;

        System.out.println(new Formatter().format("Sequential time:\t\t%s ms", sequentialTime));
        System.out.println(new Formatter().format("Parallel time:\t\t\t%s ms", parallelTime));
        System.out.println(new Formatter().format("Parallel stream time:\t\t\t%s ms", parallelStreamTime));
    }
}

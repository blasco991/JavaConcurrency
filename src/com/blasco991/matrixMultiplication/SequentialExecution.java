package com.blasco991.Java.MatrixMultiplication;

/**
 * Created by blasco991 on 14/03/17.
 */
public class SequentialExecution {

    public static void main(String[] args) {

        Matrix m1 = new Matrix(4, 5);
        Matrix m2 = new Matrix(5, 8);
        Matrix m3 = m1.times(m2);
        System.out.println("m1:\n" + m1);
        System.out.println("m2:\n" + m2);
        System.out.println("m1 x m2:\n" + m3);

        System.out.println("now performing " + Matrix.K + " random multiplications");
        long startTime = System.currentTimeMillis();

        for (int counter = 0; counter < Matrix.K; counter++)
            Matrix.randomMultiplication();

        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (endTime - startTime) / 1000 + "s");
    }
}

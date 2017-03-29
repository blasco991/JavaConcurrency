package com.blasco991.matrixMultiplication;

/**
 * Created by blasco991 on 14/03/17.
 */
public class ParallelCorrectness {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Performing " + Matrix.K + " random multiplications");

        Matrix m1 = new Matrix(40, 50);
        Matrix m2 = new Matrix(m1.getN(), 80);

        Matrix parallel = m1.parallelStreamMultiply(m2);
        Matrix sequential = m1.times(m2);

        System.out.println("Arrays.deepEquals:\t\t" + parallel.arraysDeepEquals(sequential));
        System.out.println("<Array>.equals:\t\t\t" + parallel.equals(sequential));
        System.out.println("Matrix.deepEquals\t\t" + parallel.deepEquals(sequential));

        Matrix m3 = new Matrix(4, 5);
        Matrix m4 = new Matrix(m3.getN(), 4);

        Matrix parallel2 = m3.parallelMultiply(m4);
        Matrix sequential2 = m3.times(m4);

        System.out.println("\nArrays.deepEquals:\t\t" + parallel2.arraysDeepEquals(sequential2));
        System.out.println("<Array>.equals:\t\t\t" + parallel2.equals(sequential2));
        System.out.println("Matrix.deepEquals\t\t" + parallel2.deepEquals(sequential2));

        System.out.println("m3 x m4:\n" + sequential2);
        System.out.println("m3 x m4 parallel :\n" + parallel2);

    }
}

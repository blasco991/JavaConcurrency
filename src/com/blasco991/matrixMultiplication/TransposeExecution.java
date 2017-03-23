package com.blasco991.Java.MatrixMultiplication;

/**
 * Created by blasco991 on 14/03/17.
 */
public class TransposeExecution {

    public static void main(String args[]) {

        final int exec = 10000;
        final int M = 1000;

        long totalSeq = 0;
        long totalPar = 0;

        for (int i = 0; i < exec; i++) {

            if (i % 2 == 0) {
                Matrix matrix = new Matrix(M, M);
                long startTime = System.currentTimeMillis();
                Matrix parallelTranspose = new Matrix(Matrix.transposeParallel().apply(matrix.elements));
                totalPar += i > 0 ? System.currentTimeMillis() - startTime : 0;

                Matrix matrix2 = new Matrix(M, M);
                long startTime2 = System.currentTimeMillis();
                Matrix sequentialTranspose = new Matrix(Matrix.transpose().apply(matrix2.elements));
                totalSeq += i > 0 ? System.currentTimeMillis() - startTime2 : 0;
            } else {
                Matrix matrix2 = new Matrix(M, M);
                long startTime2 = System.currentTimeMillis();
                Matrix sequentialTranspose = new Matrix(Matrix.transpose().apply(matrix2.elements));
                totalSeq += i > 0 ? System.currentTimeMillis() - startTime2 : 0;
                Matrix matrix = new Matrix(M, M);
                long startTime = System.currentTimeMillis();
                Matrix parallelTranspose = new Matrix(Matrix.transposeParallel().apply(matrix.elements));
                totalPar += i > 0 ? System.currentTimeMillis() - startTime : 0;

            }

        }

        //System.out.println("Test correctness:\t" + sequentialTranspose.deepEquals(parallelTranspose));
        System.out.println("SequentialTime:\t\t" + totalSeq + " ms");
        System.out.println("ParallelTime:\t\t" + totalPar + " ms\n\n");

    }
}
package com.blasco991.gameOfLife;

public abstract class AbstractBoard implements CellularAutomata.Board {

    @Override
    public final CellularAutomata.Board getSubBoard(final int numPartitions, final int index) {
        return new AbstractBoard() {

            private final int initialY = index * (AbstractBoard.this.getMaxY() / numPartitions);

            private final int finalY = (index < numPartitions - 1) ?
                    initialY + (AbstractBoard.this.getMaxY() / numPartitions) :
                    AbstractBoard.this.getMaxY();

            @Override
            public int getMaxX() {
                return AbstractBoard.this.getMaxX();
            }

            @Override
            public int getMaxY() {
                return finalY - initialY;
            }

            @Override
            public boolean getValue(int x, int y) {
                return AbstractBoard.this.getValue(x, initialY + y);
            }

            @Override
            public void setNewValue(int x, int y, int value) {
                AbstractBoard.this.setNewValue(x, initialY + y, value);
            }

            @Override
            public void commitNewValues() {
                AbstractBoard.this.commitNewValues();
            }

            @Override
            public boolean hasConverged() {
                return AbstractBoard.this.hasConverged();
            }

            @Override
            public void waitForConvergence() throws InterruptedException {
                AbstractBoard.this.waitForConvergence();
            }
        };
    }
}
package CalculaMovimentoCavaleiro;

class IntegerPair {

    public final int x;
    public final int y;

    IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
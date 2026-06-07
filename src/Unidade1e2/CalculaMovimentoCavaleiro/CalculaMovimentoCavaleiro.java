package CalculaMovimentoCavaleiro;

import java.util.ArrayList;

public class CalculaMovimentoCavaleiro {
    int[][] tabuleiro = new int[8][8];
    int[][] listaPosicao = new int[2][];
    public void main(String[] args){

    }
    public ArrayList<IntegerPair> calculaMovimentoCavaleiro(IntegerPair posicao){
        int x = posicao.x;
        int y = posicao.y;

        //Combinações
        int[] dx = {-2, -2, 1, 1, 2, 2, -1, -1 };
        int[] dy = {-1, 1, -2, 2, -1, 1, -2, 2};
        ArrayList<IntegerPair> listaPosicao = new ArrayList<>();
        for( int i = 0; i< 8; i++){
            int novoX = dx[i] + x;
            int novoY = dy[i] + y;
            if(novoX > 0 && novoY > 0 && novoX< 8 && novoY< 8){
                listaPosicao.add(new IntegerPair(novoX, novoY));
            }
        }
        return listaPosicao;

    }
}

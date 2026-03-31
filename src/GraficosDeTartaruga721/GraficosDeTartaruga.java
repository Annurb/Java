package GraficosDeTartaruga721;

public class GraficosDeTartaruga {
    int[][] floor = new int[20][20];
    boolean riscar = false;
    int posicao = 0;
    int dx = 0, dy = 0;

    public static void main(String[] args){
        GraficosDeTartaruga test1 = new GraficosDeTartaruga();
        test1.executar(new int[]{2, 0});
        test1.executar(new int[]{5, 12});
        test1.executar(new int[]{6, 0});
        test1.executar(new int[]{3, 0});
        test1.executar(new int[]{5, 12});
        test1.executar(new int[]{6, 0});
        test1.executar(new int[]{3, 0});
        test1.executar(new int[]{5, 12});
        test1.executar(new int[]{6, 0});
        test1.executar(new int[]{3, 0});
        test1.executar(new int[]{5, 12});
        test1.executar(new int[]{1, 0});
        test1.executar(new int[]{6, 0});
        test1.executar(new int[]{9, 0});
    }
    public void executar(int[] numbers) {
        switch (numbers[0]) {
            case (1): {
                // Caneta para cima
                riscar = false;
                break;
            }
            case (2): {
                // Caneta para baixo
                riscar = true;
                break;
            }
            case (3): {
                if(posicao == 3){
                    posicao = 0;
                }
                else{
                    posicao +=1;
                }
                break;
            }
            case (4): {
                if(posicao == 0){
                    posicao = 3;
                }
                else{
                    posicao-=1;
                }
                break;
            }
            case (5): {
                int num = numbers[1] -1;
                if(riscar == true){
                for(int i = 0; i< numbers[1]; i++){
                    if(posicao == 0){
                        floor[dx+i][dy] = 1;
                    }
                    else if(posicao == 1){
                        floor[dx][dy+i] = 1;
                    }
                    else if(posicao == 2){
                        floor[dx-i][dy] = 1;
                    }
                    else{
                        floor[dx][dy-i] = 1;
                    }
                }}
                if(posicao == 0) dx+=numbers[1];
                else if(posicao == 1) dy += numbers[1];
                else if(posicao == 2) dx-= numbers[1];
                else dy-=numbers[1];
                break;
            }
            case (6): {
                String preencher = " ";
                for(int c = 0; c< 20; c++){
                    for(int l = 0; l< 20; l++){
                        if(floor[c][l] == 1) preencher = "*";
                        else preencher = " ";
                        System.out.print(preencher);
                    }
                    System.out.println();
                }
                break;
            }
            case (9): {
                break;
            }
        }
    }}
package GraficosDeTartaruga721;

import java.util.Scanner;

public class GraficosDeTartaruga1 {
    int[][] floor = new int[20][20];
    boolean riscar = false;
    int posicao = 0;
    int dx = 0, dy = 0;

    public static void main(String[] args){
        GraficosDeTartaruga test1 = new GraficosDeTartaruga();
        Scanner sc = new Scanner(System.in).useDelimiter("[,\\s]+");
        int input;

        do {
            System.out.print("Digite o comando (ex: 2 ou 5,12 ou 9): ");

            input = sc.nextInt();

            int[] numbers = new int[2];
            numbers[0] = input;

            if (input == 5) {
                numbers[1] = sc.nextInt();
            }

            test1.executar(numbers);
        }while(input != 9);
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
                    for(int i = 0; i< numbers[1]; i++){
                        if(riscar){
                            floor[dx][dy] = 1;
                    }
                        if(posicao == 0) dx++;
                        else if(posicao == 1) dy ++;
                        else if(posicao == 2) dx --;
                        else if(posicao == 3) dy --;

                        dx = Math.max(0, Math.min(19, dx));
                        dy = Math.max(0, Math.min(19, dy));
                    }
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
        }
    }}

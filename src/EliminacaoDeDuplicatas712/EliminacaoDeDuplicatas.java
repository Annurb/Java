package EliminacaoDeDuplicatas712;

import java.util.ArrayList;

public class EliminacaoDeDuplicatas {
    static int[] unicos = new int[5];
    static int contador = 0;

    public static void main(String[] args){
        EliminacaoDeDuplicatas test1 = new EliminacaoDeDuplicatas();
        EliminacaoDeDuplicatas.setAndExibeArray(12);
        EliminacaoDeDuplicatas.setAndExibeArray(12);
        EliminacaoDeDuplicatas.setAndExibeArray(78);
        EliminacaoDeDuplicatas.setAndExibeArray(100);
        EliminacaoDeDuplicatas.setAndExibeArray(100);
    }
    public static void setAndExibeArray(int number) {
        boolean find = false;
        int indice = 0;

        while (find == false && unicos[indice] != 0) {
            if(unicos[indice] == number){
                find = true;
            }
            indice++;
        }
        if(find == false){
            unicos[contador] = number;
            contador++;
        }
        for(int i = 0; i< unicos.length; i++){
            if(unicos[i] != 0) {
                System.out.print(unicos[i] + " ");
            }
        }
        System.out.println();
    }
}

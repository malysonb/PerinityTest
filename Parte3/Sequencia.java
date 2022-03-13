
/**
 * DESAFIO 3
 * Escreva um algoritmo que imprima as seguintes sequências de números:
 * (1, 1 2 3 4 5 6 7 8 9 10)
 * (3, 1 2 3 4 5 6 7 8 9 10)
 * (5, 1 2 3 4 5 6 7 8 9 10)
 * (7, 1 2 3 4 5 6 7 8 9 10)
 * e assim sucessivamente, até que o primeiro número (antes da vírgula e sempre
 * ímpar), também chegue ao limite de 10.
 */
public class Sequencia {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if(i % 2 != 0) {
                System.out.println("("+ i +", 1 2 3 4 5 6 7 8 9 10)");
            }
        }
    }
}

import java.util.Scanner;

/**
 * DESAFIO 2
 * Escreva um algoritmo que leia as idades de 2 homens e de 2 mulheres
 * (considere que as idades dos homens serão sempre diferentes entre si, bem
 * como as das mulheres). Calcule e escreva a soma das idades do homem mais
 * velho com a mulher mais nova, e o produto das idades do homem mais novo com a
 * mulher mais velha.
 */
public class Idades {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] homens = new int[2];
        int[] mulheres = new int[2];
        int hVelho, mVelho;
        int hNovo, mNovo;
        System.out.println("Insira idade do homem 1");
        homens[0] = scanner.nextInt();
        System.out.println("Insira idade do homem 2");
        homens[1] = scanner.nextInt();
        System.out.println("Insira idade da mulher 1");
        mulheres[0] = scanner.nextInt();
        System.out.println("Insira idade da mulher 2");
        mulheres[1] = scanner.nextInt();
        hVelho = homens[0] > homens[1] ? 0 : 1;
        mVelho = mulheres[0] > mulheres[1] ? 0 : 1;
        hNovo = homens[0] > homens[1] ? 1 : 0;
        mNovo = mulheres[0] > mulheres[1] ? 1 : 0;
        System.out.println(
                "Soma das idades do homem mais velho com a mulher mais nova: " + (homens[hVelho] + mulheres[mNovo]));
        System.out.println(
                "Produto das idades do homem mais novo com a mulher mais velha: " + homens[hNovo] * mulheres[mVelho]);
    }

}
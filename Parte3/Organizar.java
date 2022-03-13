import java.util.Scanner;

/**
 * Escreva um algoritmo que receba dois arrays de inteiros ordenados e retorne
 * um array que seja a união ordenada destes arrays. Desenvolva todo o algoritmo
 * desde a entrada dos vetores até a saída do vetor final, não utilize métodos
 * prontos do tipo sort().
 */
public class organizar {

    public static int[] organizarV(int[] vetor) {
        int it = 0;
        for (int i = 0; i < vetor.length; i++) {
            int prox = i + 1 >= vetor.length ? -1 : i + 1;
            if (prox == -1) {
                if (it == 0)
                break;
                else{
                    i = -1;
                    continue;
                }
            }
            if (vetor[i] > vetor[prox]) {
                int buff = vetor[prox];
                vetor[i + 1] = vetor[i];
                vetor[i] = buff;
                it++;
            }
            it = 0;
        }
        return vetor;
    }

    public static int[] unirVetores(int[] vetor1, int[] vetor2) {
        int tamanho = vetor1.length + vetor2.length;
        int[] uniao = new int[tamanho];
        for (int i = 0; i < tamanho; i++){
            int it = i < vetor1.length ? i : i - vetor1.length;
            if(i < vetor1.length){
                uniao[i] = vetor1[it];
            }
            else{
                uniao[i] = vetor2[it];
            }
        }
        return organizarV(uniao);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t1 = 0;
        int[] vetor1;
        int t2 = 0;
        int[] vetor2;
        System.out.println("Insira o tamanho do primeiro vetor: ");
        t1 = scanner.nextInt();
        vetor1 = new int[t1];
        for (int i = 0; i < t1; i++) {
            System.out.println("Insira o valor numero " + (i + 1) + " do vetor: ");
            vetor1[i] = scanner.nextInt();
        }
        System.out.println("Insira o tamanho do Segundo vetor: ");
        t2 = scanner.nextInt();
        vetor2 = new int[t2];
        for (int i = 0; i < t2; i++) {
            System.out.println("Insira o valor numero " + (i + 1) + " do vetor: ");
            vetor2[i] = scanner.nextInt();
        }
        int[] uniao = unirVetores(vetor1, vetor2);
        System.out.print("(");
        for (int i = 0; i < uniao.length; i++){
            String c = i+1 == uniao.length ? ")" : ", ";
            System.out.print(uniao[i] + c);
        }
    }
}

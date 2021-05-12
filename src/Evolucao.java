import java.util.Random;

public class Evolucao {
    static final String alvo = "METHINKS IT IS LIKE A WEASEL";
    static final char[] possibilidades = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ".toCharArray();
    static double mutacoes = 0.05;
    static int apitidaoPerfeita = alvo.length();
    private static String mutante;
    static Random rand = new Random();

    public String getAlvo() {
        return alvo;
    }

    public String getMutante() {
        return mutante;
    }

    public void setMutante(String parent) {
        Evolucao.mutante = parent;
    }

    /**
     * Cálculo da diferença de caracteres entre a string alvo e a string copia.
     *
     * Quanto maior o valorde comparação, mais proximo a string está do alvo.
     * */
    public int comparacaoStrings(String copia){
        int valorComparacao = 0;
        for(int i = 0; i < copia.length(); i++){
            if (copia.charAt(i) == alvo.charAt(i)) valorComparacao++;
        }
        return valorComparacao;
    }

    /**
     * Cálculo da taxa de mutaçõs.
     *
     *Quanto menor for a taxa de mutação mais próximo ele está da string alvo.
     * */
    public double taxaDeMutacoes(){
        return (((double) apitidaoPerfeita - comparacaoStrings(mutante)) / apitidaoPerfeita * (1 - mutacoes));
    }

    /**
     * Realização da mutação da string.
     *
     * Para cada char da string, caso o valor double gerado for menor ou igual a taxa de mutação,
     * a string de rotorno recebe um caracter aleatório entre as minhas
     * possibilidades. Caso não ela recebe o caractere naquele indice
     * */
    public String mutacao(String mutante, double taxa){
        String copiadora = "";
        for(int i = 0; i < mutante.length(); i++){
            copiadora += (rand.nextDouble() <= taxa) ?
                    possibilidades[rand.nextInt(possibilidades.length)]:
                    mutante.charAt(i);
        }
        return copiadora;
    }
}
public class Main {
    public static void main(String[] args){
        Evolucao evolucao = new Evolucao();

        //Criação de uma string inicial aleatoria (28 caracteres)
        evolucao.setMutante(evolucao.mutacao(evolucao.getAlvo(), 1));
        int iteracao = 0;
        int melhorPontuacao = 0;

        //Enquanto a melhor pontuação não for atingida
        while(melhorPontuacao != evolucao.getAlvo().length()){
            double taxaMutacoes = evolucao.taxaDeMutacoes();

            System.out.println(iteracao +": " + evolucao.getMutante() + ", pontos: " +
                    evolucao.comparacaoStrings(evolucao.getMutante()) + ", taxaMutacoes: " + taxaMutacoes);

            String melhorString = null;

            //Fazendo copias de mutações
            for(int i = 0; i < 100; i++){
                String copia = evolucao.mutacao(evolucao.getMutante(), taxaMutacoes);

                //Resultado obtido da comparação entre as strings (quantidade de letras diferentes)
                int pontos = evolucao.comparacaoStrings(copia);

                //Melhor sentença é atualizada com base na pontuação obtida
                if(pontos > melhorPontuacao){
                    melhorString = copia;
                    melhorPontuacao = pontos;
                }
            }

            /*
             caso a melhor pontuação obtida ate o momento seja maior que resultado das comparações,
              a string mutadora recebe a string mais proxima do alvo e, caso não, ela permanece a mesma
              */
            if (melhorPontuacao > evolucao.comparacaoStrings(evolucao.getMutante())){
                evolucao.setMutante(melhorString);
            } else {
                evolucao.setMutante(evolucao.getMutante());
            }
            iteracao++;
        }
        System.out.println(evolucao.getMutante() + ", " + iteracao);
    }
}


import random

def main():
    objetivo = "METHINKS IT IS LIKE A WEASEL"
    caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
    geracoes = 0
    sequencia_atual = gerar_sequencia_aleatoria(objetivo, caracteres)
    print("Geração:", geracoes)
    print(sequencia_atual)
    while sequencia_atual != objetivo:
        sequencia_atual = pegar_sequencia_mutada(
            sequencia_atual, objetivo, caracteres)
        geracoes += 1
        print("Geração:", geracoes)
        print(sequencia_atual)


def gerar_sequencia_aleatoria(objetivo, caracteres):
    sequencia = ""
    for i in range(len(objetivo)):
        sequencia += caracteres[random.randint(0, len(caracteres)-1)]
    return sequencia


def pegar_sequencia_mutada(sequencia, objetivo, caracteres):
    lista_sequencia = pegar_mutacoes_sequencia(sequencia, caracteres)
    melhor_sequencia = lista_sequencia[0]
    melhor_similaridade = pegar_posicao(melhor_sequencia, objetivo)
    for seq in lista_sequencia:
        similaridade = pegar_posicao(seq, objetivo)
        if similaridade > melhor_similaridade:
            melhor_similaridade = similaridade
            melhor_sequencia = seq
    return melhor_sequencia


def pegar_mutacoes_sequencia(sequencia, caracteres):
    lista_sequencia = []
    for i in range(100):
        lista_sequencia.append(sequencia_mutada(sequencia, caracteres))
    return lista_sequencia


def sequencia_mutada(sequencia, caracteres):
    resultado = ""
    for i in range(len(sequencia)):
        if random.randint(0, 100) <= 5:
            resultado += caracteres[random.randint(0, len(caracteres)-1)]
        else:
            resultado += sequencia[i]
    return resultado


def pegar_posicao(sequencia, objetivo):
    posicao = 0
    for i in range(len(objetivo)):
        if objetivo[i] == sequencia[i]:
            posicao += 1
    return posicao

main()
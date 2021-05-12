#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<climits>
char alvo[] = "METHINKS IT IS LIKE A WEASEL";
char letras[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
#define QTE_LETRAS (sizeof(letras) - 1)
#define MUTAR 20
#define COPIAS 100
 
 
 
// retorna inteiro aleatório de 0 a n-1
int int_rand(int n){
	int r = rand(), rand_max = RAND_MAX-(RAND_MAX % n);
	while(r >= rand_max){
        r = rand();
    }
    r = r/ (rand_max/n);
    return r;
}
 
// quantidade de caracters diferentes entre a string a e b
int comparar(char *a, char *b){
	int i, sum = 0;
	for (i = 0; a[i]; i++)
		sum += (a[i] != b[i]);
	return sum;
}
// cada char de b tem 5% de chance de mutar
// e 95% de chance de não mutar
void mutar(char *a, char *b){
	int i;
    for(i = 0; i < 28; i++){
        if(int_rand(MUTAR) > 0){
            b[i] = a[i];
        }else{
            b[i] = letras[int_rand(QTE_LETRAS)];
        }
    }
    // indica o término da string
    b[i] = '\0';
}
 
int main(){
	int i, dif_letras, iteracao = 0, melhor_resp = INT_MAX, melhor_id;
	char copia_string[100][29];
    
	// crio uma string inicial
	for (i = 0; i < 28; i++){
		copia_string[0][i] = letras[int_rand(QTE_LETRAS)];
    }
    copia_string[0][i] = '\0';
 
	while(melhor_resp != 0){
        // faço cópias da string inicial e, para cada char,
        // eu dou uma chance de 5% desse char mutar
		for (i = 1; i < COPIAS; i++)
			mutar(copia_string[0], copia_string[i]);
 
		// índice da melhor resposta
		melhor_id = 0;
    melhor_resp = INT_MAX;
    for (i = 0; i < COPIAS; i++) {
			dif_letras = comparar(alvo, copia_string[i]);  // quantidade de letras diferentes
	// atualizo qual a menor diferença entre as strings até o momento
        if(dif_letras < melhor_resp) {
					melhor_resp = dif_letras;
					melhor_id = i;
				}
		}
 
		// a primeira cópia recebe sempre a string que mais se parece com
        // o alvo (ou seja, que tem a menor diferença de chars em relação ao alvo) 
        strcpy(copia_string[0], copia_string[melhor_id]);
        
		printf("geração %d, pontos %d: %s\n", iteracao, (28 - melhor_resp), copia_string[0]);
        iteracao++;
	} 
 
	return 0;
}
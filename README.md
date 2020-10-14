# Jantar dos Filósofos em Java

Projeto Eclipse com a implementação Java do Jantar dos Filósofos. O objetivo desta implementação é demonstrar a ocorrência de impasse (deadlock) e inanição (starvation).

O problema do Jantar dos Filófosos é descrito no capítulo 2, seção 2.5.1 de [(TANENBAUM E BOS, 2016)](#tanenbaum-e-bos-2016). É recomendável que o interessado leia atentamente esta seção para entender os fundamentos do problema e das soluções propostas. O vídeo a seguir explica brevemente o Jantar dos Filósofos e as soluções propostas.

[![](http://img.youtube.com/vi/LhkDV3cECNY/0.jpg)](http://www.youtube.com/watch?v=LhkDV3cECNY "Programação Concorrente: Jantar dos Filósofos, Impasse e Inanição")

## Organização do Projeto
O projeto fornece uma implementação abstrata de filósofo (classe `base.Filosofo`) contendo número de identificação do filósofo e contador de porções que comeu.

Três subclasses de `base.Filosofo` são disponibilizadas:
- `semaforos.FilosofoImpasse`: filósofo sujeito a impasse (deadlock).
- `semaforos.FilosofoInanicao`: filósofo sujeito a inanição (starvation).
- `semaforos.FilosofoFuncional`: filósofo funcional, mas não livre de eventual inanição.

Para criação do jantar e execução dos filósofos, as classes abaixo são disponibilizadas. Conforme os nomes sugerem, cada uma delas cria um jantar com as implementações de filósofos sujeitos a impasse, inanição, ou funcional descritas acima.
- `semaforos.JantarImpasse`: jantar com impasse (deadlock).
- `semaforos.JantarInanicao`: jantar com inanição (starvation).
- `semaforos.JantarFuncional`: jantar funcional.

## Execução da implementação
Para instanciar e rodar um jantar, basta executar uma das classes de `Jantar` acima.

A versão `JantarImpasse` produzirá um impasse (deadlock) e portanto você deverá encerrar o programa forçadamente.

Já as versões `JantarInanicao` e `JantarFuncional` ficam executando até ser pressionada qualquer tecla no teclado. Quando isso ocorrer, os filósofos são encerrados e o programa imprime quantas porções cada um deles comeu.

## Autor
 - [**Fernando dos Santos**](mailto:fernando.santos@udesc.br) (professor no curso de Engenharia de Software da [UDESC Alto Vale](https://www.udesc.br/ceavi)).


 ## Referências
 ###### (TANENBAUM E BOS, 2016)
 TANENBAUM, A. S.; BOS, H. **Sistemas operacionais modernos**. 4. ed. São Paulo : Pearson, 2016.

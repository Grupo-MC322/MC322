# Modelo
## Blocos (interface)
2, 4, 8, 16 ... 2048
vazio
bloco preto (que fica por 3 rodadas)
bloco bomba azul (tem um número e tem que ser juntado em 3 rodadas se não vira um "buraco" por 3 rodadas)
bloco 1 (tem que ser juntado com outro bloco 1 e vira 2)
bloco deleta roxo (tem um - e deleta quem ele se juntar)
bloco x2 (multiplica quem ele juntar por 2)

Vai ter uma função que junta os dois caso o bloco adjacente seja igual. Se for vazio, vai ir para o lado e vai chamar a função de novo. Se for fora do tabuleiro retorna.
Vai ter um int que vai dizer o número do bloco.

## Tabuleiro 
Possui uma matriz que vai conter os blocos.
Fazer o construtor para pegar o tamanho da matriz.


# Controle
Controle, que faz a comunicação entre o View e o Modelo.

# View
Coisas da interface visual.

Perguntar configurações do jogo antes de começar.
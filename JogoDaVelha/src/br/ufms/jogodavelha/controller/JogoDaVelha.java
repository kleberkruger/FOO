package br.ufms.jogodavelha.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Point2D;

/**
 * Classe responsável pela lógica do Jogo da Velha.
 *
 * @author Gedson Faria e Kleber Kruger.
 */
public abstract class JogoDaVelha {

    private final char matriz[][];
    private final int placar[];
    private boolean computadorJogando;
    private char jogadorAtual;
    private int posicoesVagas;

    private volatile boolean emPausa;

    /**
     *
     */
    public JogoDaVelha() {
        this(true, 'x');
    }

    /**
     *
     * @param computadorJogando
     * @param jogadorInicial
     */
    public JogoDaVelha(boolean computadorJogando, char jogadorInicial) {
        jogadorInicial = Character.toLowerCase(jogadorInicial);

        if (jogadorInicial != 'x' && jogadorInicial != 'o') {
            throw new IllegalArgumentException("Marcação inválida");
        }
        this.matriz = new char[3][3];
        this.placar = new int[2];
        this.computadorJogando = computadorJogando;

        inicializar(jogadorInicial);
    }

    /**
     * Método que inicializa as estruturas de dados para um novo jogo.
     */
    private void inicializar(char jogadorInicial) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = ' ';
            }
        }

        this.jogadorAtual = jogadorInicial;
        this.posicoesVagas = 9;
    }

    private void pontuar(char vencedor) {
        if (vencedor == 'x') {
            placar[0] = ++placar[0];
        } else if (vencedor == 'o') {
            placar[1] = ++placar[1];
        }
        placarAlterado(placar[0], placar[1]);
    }

    /**
     * Verifica se a posição lin,col já está ocupada com X ou O.
     *
     * @param lin é a linha da matriz
     * @param col é a coluna da matriz
     * @return verdadeiro se a matriz está preenchida, falso caso contrário.
     */
    boolean isOcupado(int lin, int col) {
        return matriz[lin][col] == 'x' || matriz[lin][col] == 'o';
    }

    /**
     * Verifica se alguém ganhou o jogo.
     *
     * @param letra pode ser 'X' ou 'O'.
     * @return verdadeiro se letra ganhou o jogo, senão falso.
     */
    boolean ganhou(char letra) {
        return // linhas
                (matriz[0][0] == letra && matriz[0][1] == letra && matriz[0][2] == letra)
                || (matriz[1][0] == letra && matriz[1][1] == letra && matriz[1][2] == letra)
                || (matriz[2][0] == letra && matriz[2][1] == letra && matriz[2][2] == letra)
                || // colunas     
                (matriz[0][0] == letra && matriz[1][0] == letra && matriz[2][0] == letra)
                || (matriz[0][1] == letra && matriz[1][1] == letra && matriz[2][1] == letra)
                || (matriz[0][2] == letra && matriz[1][2] == letra && matriz[2][2] == letra)
                || // diagonais     
                (matriz[0][0] == letra && matriz[1][1] == letra && matriz[2][2] == letra)
                || (matriz[0][2] == letra && matriz[1][1] == letra && matriz[2][0] == letra);
    }

    public void jogar(int lin, int col) {
        if (emPausa) {
            return;
        }

        if (lin < 0 || lin > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Posição inválida");
        } else if (isOcupado(lin, col)) {
            throw new IllegalArgumentException("Posição já marcada");
        }

        comitarJogada(lin, col, jogadorAtual);
        if (ganhou(jogadorAtual)) {
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        emPausa = true;
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded((WorkerStateEvent event) -> {
                jogadorGanhou(jogadorAtual);
                pontuar(jogadorAtual);
                reiniciar(jogadorAtual);
                alterarJogador();
                emPausa = false;
            });
            new Thread(sleeper).start();
        } else if (posicoesVagas == 0) {
            terminouEmVelha();
            reiniciar(jogadorAtual);
            alterarJogador();
        } else {
            alterarJogador();
        }

        if (computadorJogando) {
            Point2D p = setComputador();
            if (p != null) {
                comitarJogada((int) p.getX(), (int) p.getY(), jogadorAtual);
                if (ganhou(jogadorAtual)) {
                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                emPausa = true;
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded((WorkerStateEvent event) -> {
                        jogadorGanhou(jogadorAtual);
                        pontuar(jogadorAtual);
                        reiniciar(jogadorAtual);
                        alterarJogador();
                        emPausa = false;
                    });
                    new Thread(sleeper).start();

                } else if (posicoesVagas == 0) {
                    terminouEmVelha();
                    reiniciar(jogadorAtual);
                    alterarJogador();
                } else {
                    alterarJogador();
                }
            } else {
                terminouEmVelha();
                reiniciar(jogadorAtual);
            }
        }

    }

    protected void comitarJogada(int lin, int col, char c) {
        char matrizAnterior[][] = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matriz[i], 0, matrizAnterior[i], 0, 3);
        }
        matriz[lin][col] = jogadorAtual;
        posicoesVagas--;

        matrizAlterada(matrizAnterior, matriz);
    }

    private void alterarJogador() {
        jogadorAtual = jogadorAtual == 'x' ? 'o' : 'x';
    }

    private void reiniciar(char jogador) {
        char matrizAnterior[][] = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matriz[i], 0, matrizAnterior[i], 0, 3);
        }
        jogoReiniciado();
        inicializar(jogador);
        matrizAlterada(matrizAnterior, matriz);
    }

    private Point2D setComputador() {
        int maxLin = 0, maxCol = 0, valor, max = -2;
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                if (!isOcupado(lin, col)) {
                    matriz[lin][col] = 'o';
                    valor = minimax(false); // vez do usuario
                    matriz[lin][col] = ' ';
                    if (valor > max) {
                        max = valor;
                        maxLin = lin;
                        maxCol = col;
                    }
                }
            }
        }
        if (max == -2) {
            return null;
        } else {
            return new Point2D(maxLin, maxCol);
        }
    }

    /**
     * Calcula o valor de uma jogada
     *
     * @param vezComputador é true na jogada do Computador.
     * @return o valor maximo da jogada na vez do Computador e o valor mínimo na
     * vez do Usuário.
     */
    int minimax(boolean vezComputador) {
        int valor, max = -2, min = 2;
        if (ganhou('o')) {
            return 1;   // máximo para o computador
        }
        if (ganhou('x')) {
            return -1;  // minimo para o usuário
        }
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                if (!isOcupado(lin, col)) {
                    matriz[lin][col] = vezComputador ? 'o' : 'x';
                    valor = minimax(!vezComputador);
                    matriz[lin][col] = ' ';
                    if (valor > max) {
                        max = valor;
                    }
                    if (valor < min) {
                        min = valor;
                    }
                }
            }
        }
        // significa que todas posicoes estão ocupadas e max não foi atualizado
        // no if dentro dos laços for
        if (max == -2) {
            return 0;
        }
        if (vezComputador) {
            return max;
        } else {
            return min;
        }
    }

    protected abstract void jogoReiniciado();

    protected abstract void matrizAlterada(char[][] matrizAnterior, char[][] matrizAtual);

    protected abstract void placarAlterado(int pontosX, int pontosO);

    protected abstract void terminouEmVelha();

    protected abstract void jogadorGanhou(char jogador);
}

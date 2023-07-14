/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogodavelha;

import javax.swing.JOptionPane;

/*
 * @author KennedyMotta.
 */
public class JogodaVelha {

    public static char tabuleiro[][] = new char[3][3];

    public static void main(String[] args) {

        //Instanciar os Jogadores com Nomes!
        Player p1 = new Player();
        String p1name = null;
        while (p1name == null || p1name.trim().isEmpty()) {
            p1name = JOptionPane.showInputDialog(null, "Digite o Nome do Primeiro Jogador!");
        }

        p1.setName(p1name);

        Player p2 = new Player();
        String p2name = null;
        while (p2name == null || p2name.trim().isEmpty()) {
            p2name = JOptionPane.showInputDialog(null, "Digite o Nome do Segundo Jogador!");
        }
        p2.setName(p2name);

        //Escolher o Tipo de Gameplay, X ou O.
        boolean tipoValido = false;

        //Loop de Verificação de Tipo de Gameplay.
        while (tipoValido == false) {
            p1.setType(JOptionPane.showInputDialog(null, p1.getName() + ", escolha o seu estilo de Jogo: O ou X?"));
            //Conversão de Caractere Char para Uppercase
            char p1Type = p1.getType().charAt(0);
            char Upp1Type = Character.toUpperCase(p1Type);
            //Switch para definir o tipo do segundo jogador, ou seja, contrário do primeiro!
            switch (Upp1Type) {
                case 'X':
                    p2.setType("O");
                    tipoValido = true;
                    break;

                case 'O':
                    p2.setType("X");
                    tipoValido = true;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Entrada Inválida, tente novamente!");
            }
        }
        //Fim do Loop de Verificação.

        JOptionPane.showMessageDialog(null, p2.getName() + " ficou com: " + p2.getType());

        //Início do Jogo
        boolean jogador1 = true;
        boolean vencedor = false;
        boolean darvelha = false;

        iniciarTabuleiro();
        mostrarTabuleiro();

        JOptionPane.showMessageDialog(null, p1.getName() + " Começa!");

        //Enquanto ninguém vencer ou dar velha Loop de Gameplay.
        while (vencedor || darvelha != true) {

            //Turno do Jogador 1
            while (jogador1 == true) {

                JOptionPane.showMessageDialog(null, p1.getName() + " É o seu Turno!");
                int i, j;
                i = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Linha Desejada para Jogar! Vai de 0 a 2."));
                j = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora a Coluna Desejada para Jogar! Vai de 0 a 2."));
                if (tabuleiro[i][j] == '-') {
                    tabuleiro[i][j] = p1.getType().toUpperCase().charAt(0);

                    mostrarTabuleiro();

                    if (verificarJogadaVitoria() == true) {
                        vencedor = true;
                        JOptionPane.showMessageDialog(null, "O jogo terminou, " + p1.getName() + " Venceu!");
                        System.exit(0);
                    }
                    if (verificarVelha() == true) {
                        darvelha = true;
                        JOptionPane.showMessageDialog(null, "O jogo terminou em empate!");
                        System.exit(0);
                    }
                    jogador1 = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Jogada Inválida! Tente Novamente!");
                    jogador1 = true;
                }
            }

            //Turno do Jogador 2
            while (jogador1 == false) {
                JOptionPane.showMessageDialog(null, p2.getName() + " É o seu Turno!");
                int i, j;
                i = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Linha Desejada para Jogar! Vai de 0 a 2."));
                j = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora a Coluna Desejada para Jogar! Vai de 0 a 2."));
                verificarVelha();
                if (tabuleiro[i][j] == '-') {
                    tabuleiro[i][j] = p2.getType().charAt(0);
                    mostrarTabuleiro();
                    if (verificarJogadaVitoria() == true) {
                        vencedor = true;
                        JOptionPane.showMessageDialog(null, "O jogo terminou, " + p2.getName() + " Venceu!");
                        System.exit(0);
                    }
                    if (verificarVelha() == true) {
                        darvelha = true;
                        JOptionPane.showMessageDialog(null, "Deu velha! O Jogo Terminou");
                        System.exit(0);
                    }
                    jogador1 = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Jogada Inválida! Tente Novamente!");
                    jogador1 = false;
                }
            }

        }
    }

    //Métodos!
    //inicializa o tabuleiro.
    public static void iniciarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = '-';
            }

        }
    }

    //mostra o tabuleiro no console
    public static void mostrarTabuleiro() {
        System.out.println("Tabuleiro: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Verifica se há uma trinca para vitória
    public static boolean verificarJogadaVitoria() {
        boolean trincalinha = false;
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != '-') {
                trincalinha = true;
            }
        }

        boolean trincacoluna = false;
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j] && tabuleiro[0][j] != '-') {
                trincacoluna = true;
            }
        }

        boolean trincadiagonal = false;
        if (tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O' || tabuleiro[0][0] == 'X'
                && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X' || tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O'
                && tabuleiro[2][0] == 'O' || tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == 'X') {
            trincacoluna = true;
        }
        return trincalinha || trincacoluna || trincadiagonal;
    }

    //Verifica o empate, ou famosa VELHA.
    public static boolean verificarVelha() {
        boolean deuvelha = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == '-') {
                    deuvelha = false;
                }
            }
        }
        return deuvelha;
    }
}

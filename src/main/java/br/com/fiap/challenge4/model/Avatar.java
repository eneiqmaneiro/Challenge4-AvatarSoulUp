package br.com.fiap.challenge4.model;

public class Avatar {

    private int pontos;

    public Avatar() {
        this.pontos = 0;
    }

    public void apresentarAvatar() {
        System.out.println("Olá! Eu sou o seu avatar da SoulUp! \nmeu propósito é te ajudar com a sua experiência no APP.\n" +
                "Por exemplo, posso ajudar com a navegação, te ensinando onde encontrar cada funcionalidade\ne também posso " +
                "te mostrar suas notificações mais recentes assim que você receber uma nova!");
        System.out.println("Você possui " + pontos + " pontos.");
    }

    public void verNotificacao(String mensagem) {
        System.out.println("--- VER NOTIFICAÇÃO MAIS RECENTE ---");
        System.out.println("Ultima notificação: " + mensagem);
    }

    public void recomendarGrupo(String grupo) {
        System.out.println("--- RECOMENDANDO UM GRUPO SEMELHANTE AOS SEUS GOSTOS ---");
        System.out.println("Talvez você queira conhecer o grupo: " + grupo);
    }

    public void relatarProblema(String problema) {

        System.out.println("--- RELATÓRIO DE PROBLEMA ---");
        System.out.println("Problema relatado: " + problema);
        System.out.println("Seu problema foi enviado para a equipe de suporte.");
    }
}

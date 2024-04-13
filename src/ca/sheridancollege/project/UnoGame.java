/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Arshpreet Singh
 * 
 #team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnoGame {

    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.playGame();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        deck.shuffle();

        List<Player> players = new ArrayList<>();
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
            System.out.println(player.getName() + " hand: " + player.getHand());
        }

        Card currentCard = deck.drawCard();
        System.out.println("Initial card on the pile: " + currentCard);

        int currentPlayerIndex = 0;
        while (!isAnyWinner(players)) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn");
            System.out.println("Current card on the pile: " + currentCard);
            System.out.println(currentPlayer.getName() + "'s hand: " + currentPlayer.getHand());

            if (canPlay(currentPlayer, currentCard)) {
                Card playedCard = currentPlayer.playCard(currentCard);
                System.out.println(currentPlayer.getName() + " plays: " + playedCard);
                currentCard = playedCard;
            } else {
                System.out.println(currentPlayer.getName() + " draws a card.");
                currentPlayer.drawCard(deck);
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        System.out.println("Game over! Winner: " + getWinner(players).getName());
        scanner.close();
    }

    private boolean canPlay(Player player, Card currentCard) {
        for (Card card : player.getHand()) {
            if (card.matches(currentCard)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyWinner(List<Player> players) {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private Player getWinner(List<Player> players) {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player;
            }
        }
        return null; 
    }
}
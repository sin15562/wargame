/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */

   import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public Card playCard(Card currentCard) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the index of the card you want to play (0 to " + (hand.size() - 1) + "): ");
        int index = scanner.nextInt();

        while (index < 0 || index >= hand.size() || !hand.get(index).matches(currentCard)) {
            System.out.println("Invalid choice. Try again.");
            index = scanner.nextInt();
        }

        return hand.remove(index);
    }
}
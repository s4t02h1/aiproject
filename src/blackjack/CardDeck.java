package blackjack;

import java.util.ArrayList;

public class CardDeck {
	private int max;
	private ArrayList<Card> deck;
	public CardDeck(int max) {
		this.max = max;
		deck = new ArrayList<Card>();
		initialize();

	}
	public void initialize () {
		deck.clear();
		for (int i = 0; i< max; i++) {
			Card c = new Card(i + 1);
			deck.add(c);
		}
	}
	public int size() {
		return deck.size();
	}
	Card indexOf(int k) {
		return deck.get(k);
	}
	public Card next() {
		int n = size();
		if(n==0)	return null;
		int p = (int)(Math.random()*n);
		Card c = deck.remove(p);
		return c;

	}

}

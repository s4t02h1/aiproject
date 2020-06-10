package blackjack;

import java.util.ArrayList;

public class Player {
	protected CardDeck deck;
	protected ArrayList<Card> hand;
	protected String name;
	protected String icon;
	public Player(CardDeck deck, String name, String icon) {
		this.deck = deck;
		this.name = name;
		this.icon = icon;
		hand = new ArrayList<Card>();
		initialize();
	}
	public void initialize() {
		hand.clear();
		for(int i=0; i<2 ; i++) {
			addACard();
		}
	}
	public void addACard() {
		 Card c = deck.next();
		 if(c != null) {
			 hand.add(c);
		 }
	}
	public int score() {
		int sum = 0;
		for(Card c : hand) {
			sum += c.score();
		}
		return sum;
	}
	public String toString() {
		StringBuilder sbuf = new StringBuilder();
		sbuf.append(icon);
		sbuf.append(name);
		sbuf.append("\t[");
		sbuf.append(score());
		sbuf.append("]\t");
		return sbuf.toString();
	}
	void cardStr(StringBuilder sbuf) {
		for(Card c : hand) {
			sbuf.append(c);
			sbuf.append("\t");
		}
	}
	/*
	public static void main(String[] args) {
		CardDeck deck = new CardDeck(52);
		Player p = new Player("Player", "■", deck);
		p.addACard();
		System.out.println(p);
	}
	*/

}

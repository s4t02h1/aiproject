package blackjack;

public class Dealer extends Player {

	public Dealer(CardDeck deck, String name, String icon) {
		super(deck, name, icon);

	}
	public void over16() {
		while(score() <= 16) {
			addACard();
		}
	}
	public String half() {
		StringBuilder sbuf = new StringBuilder();
		sbuf.append(icon);
		sbuf.append(name);
		sbuf.append("\t[?]\t");
		sbuf.append(score());
		sbuf.append("****************<->");
		return sbuf.toString();

	}
	void halfStr(StringBuilder sbuf) {
		Card c = hand.get(0);
		sbuf.append(c);
		sbuf.append("\t");

	}

}

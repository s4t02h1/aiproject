package BlackJackMaster;

import blackjack.Card;
import blackjack.Player;

public class BlackJackMaster {

	private static Player player = new Player();
	private static Player dealer = new Player();
	private static Deck deck = new Deck();

	private static final String EVEN = "引き分けです";
	private static final String PLAYER_WIN = "プレーヤーの勝ち";
	private static final String DEALER_WIN = "ディーラーの勝ち";
	private static String resultStr = "";

	public static void main(String[] args) {
		startGame();
		judge();

	}

	private static void startGame() {
		String startStr = "プレーヤーのカード : ";

		for (int i = 1; i <= 2; i++) {
			Card playerCard = deck.dealCard();
			player.addCardAndCalc(playerCard);
			deck.deleteCard(playerCard);

			Card dealerCard = deck.dealCard();
			dealer.addCardAndCalc(dealerCard);
			deck.deleteCard(dealerCard);

			startStr += playerCard.getMark() + "の" + playerCard.getName() + " ";
		}
		System.out.println(startStr);

		playerGame(true);
	}

	private static void playerGame(boolean isFirst) {
		if (player.isHit()) {
			Card card = deck.dealCard();
			player.addCardAndCalc(card);
			deck.deleteCard(card);
			System.out.println("引いたカード : " + card.getMark() + "の" + card.getName());
		}

		if (player.getCalcResult() > 21) {
			player.setHit(false);
		} else if (player.getCalcResult() == 21) {
			if (player.getCardList().size() == 2) {
				player.setBlackJack(true);
			}
			player.setHit(false);
		} else {
			player.setHit(hitOrStand());
		}

		if (isFirst) {
			dealerGame();
		} else if (dealer.isHit()) {
			dealerGame();
		} else if (player.ishit()) {
			playerGame(false);
		}
	}

	private static void dealerGame() {

		if (dealer.isHit()) {
			Card card = deck.dealCard();
			dealer.addCardAndCalc(card);
			deck.deleteCard(card);
		}

		if (dealer.getCalcResult() > 21) {
			dealer.setHit(false);
		} else if (dealer.getCalcResult() == 21) {
			if (dealer.getCardList().size() == 2) {
				dealer.setBlackJack(true);
			}
			dealer.setHit(false);
		} else if (dealer.getCalcResult() >= 17) {
			dealer.setHit(false);
		} else {
			dealer.setHit(true);
		}
		
		if (player.isHit()) {
			playerGame(false);
		} else if (dealer.isHit()) {
			dealerGame();
		}
	}
	
	public static boolean hitOrStand() {
		
	}

}

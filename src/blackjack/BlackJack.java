package blackjack;

import blackjack.mylib.Util;

public class BlackJack {
	private CardDeck deck;
	private Dealer dealer;
	private Player player;

	static final String DEALER_ICON = "□";
	static final String PLAYER_ICON = "■";
	static final String BANNER = "\n■□■□■□■□■□■□■□■□■□■□■□■□■□■□";
	static final int CARDS = 52;
	static final String Q_CONTINUE = "続けますか？(Enter/n)";
	static final int LIMIT = 17;
	static final String Q_DEAL = "更にカードを引きますか？(Enter/n)";
	static final String EVEN = "引き分け";
	static final String WIN = "あなたの勝ち";
	static final String LOST = "ディーラーの勝ち";
	static final String ANIMATION_ICON = "□■";
	static final int TIMES = 25;
	static final int DELAY = 20;

	public BlackJack(String dname, String pname) {
		deck = new CardDeck(CARDS);
		dealer = new Dealer(deck, dname, DEALER_ICON);
		player = new Player(deck, pname, PLAYER_ICON);

	}

	public void initialize() {
		if (deck.size() < LIMIT) deck.initialize();
		dealer.initialize();
		player.initialize();

	}
	public void show() {
		System.out.println();
		System.out.println(dealer);
		System.out.println(player);
	}
	public void play() {
		String str;
		do{
			System.out.println(BANNER);
			initialize();
			this.showHalf();
			deal();
			dealer.over16();
			animation(TIMES, DELAY, ANIMATION_ICON);
			this.show();
			this.judge();
			System.out.println(Q_CONTINUE);
		}while((str = Util.getString(Q_CONTINUE)) == null);
	}
	public void showHalf() {
		System.out.println();
		System.out.println(dealer.half());
		System.out.println(player);
	}
	public void deal() {
		for (int i = 0;player.score() < 21;i++) {

			String str = Util.getString(Q_DEAL);
			if(str != null) {
				break;
			}
			else
				player.addACard();
				this.showHalf();
		}
	}
	public void judge() {
		int d = dealer.score();
		int p = player.score();
		if (d > 21 && (p > 21 || d == p)) {
			System.out.println("引き分け");
		}
		else if (p <= 21 && (d > 21 || d < p)) {
			System.out.println("あなたの勝ち");
		}else {
			System.out.println("ディーラーの勝ち");
		}
	}
	public void animation(int times, int delay, String icon) {
		System.out.println();
		 for(int i=0; i<10; i++){
             System.out.print("☆☆");
             delay(100);
		 	}
		 System.out.println(); // 改行
		}
		static void delay(int t){
				try {
						Thread.sleep(t);
				    } catch (InterruptedException e) {
				    	     e.printStackTrace();
				    }
		}

}

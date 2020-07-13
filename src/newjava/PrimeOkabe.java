//自分が作ったのこれですー

	//与えられた値が素数かbooleanで返すメソッド
	public static Boolean isPrimNumber(int argNumber) {
		if(argNumber <= 1){
			return false;
		}
		for(int i = 2 ;i < argNumber ;i++) {
			if(argNumber % i == 0) {
				return false;
			}
		}
		return true;
		}

	//与えられた範囲の値の値で素数を返すメソッド
	public static ArrayList<Integer> getPrimNumberList(int argStart,int argEnd ) {
		ArrayList<Integer>  rePNumList =new ArrayList<Integer>();
		for(int i = argStart; i <= argEnd ; i++ ) {
			if(checkPrimNumber(i) == true){
				rePNumList.add(i + argStart);
			}
		}
		return rePNumList;
	}

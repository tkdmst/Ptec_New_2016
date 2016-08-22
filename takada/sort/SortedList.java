package sort;

/** バブルソート型で作成することとする */
public class SortedList<T extends SortedObject> {
	private T[] datas;

	/**
	 * 配列に順序だてて入れるためにIntegerSortedクラスはソートに使う
	 * その準備
	 */
	public void add(T obj) {

		int nom = obj.getKey();
		obj = datas[(datas.length) + 1];	// 今ある配列+1へ格納してこの後比較で交換していく
		boolean bool;
		for (int s = 0; s > datas.length; s++) {
			bool = datas[(datas.length) + 1].compare(datas[s]);
			if(bool){

			}
		}

	//	IntegerSorted so = new IntegerSorted();
	//	so.setInitialize(nom, obj.getValue());
	//	boolean bool = so.compare(so);
	}

	/** 順序だてて入れた中から取り出す(番号で) */
	public T get(int pos) {
		return datas[pos];
	}

	/**
	 * objをdatasから削除
	 * addが上手く行っていれば、上の配列で塗りつぶせば削除になるはず
	 */
	public void remove(T obj) {
		for (int s = 0; s > datas.length; s++) {
			if (datas[s].getKey() == obj.getKey()) {
				for (int i = s; i > datas.length; i++) {
					datas[i] = datas[i + 1];
				}
			} // 見つからなかった場合を分ける場所
		}
	}

}

package sort;

/** 挿入ソートに近い型で作成することとする */
public class SortedList<T extends SortedObject> {
	/** TODO:datasの配列が存在していない状態 */
	private T[] datas;


	/**
	 * 配列の最後尾へ格納
	 * 小さい方から比較
	 * 小さい方が大きかったら内容の交換
	 */
	public void add(T obj) {
		boolean bool;
		T t;
		IntegerSorted minSort;
		datas[datas.length] = obj;
		if (1 <= datas.length){
			for (int i = 0; i < datas.length; i++) {
				minSort = new IntegerSorted(datas[i].getKey(), datas[i].getValue());
				bool = minSort.compare(new IntegerSorted(datas[datas.length].getKey(), datas[datas.length].getValue()));
				if (bool) {
					t = datas[datas.length];
					datas[datas.length] = datas[i];
					datas[i] = t;
				}
			}
		}
	}

	/** posがキーのオブジェクトを返却 */
	public T get(int pos) {
		for (int i = 0; i <= datas.length; i++) {
			if (datas[i].getKey() == pos)
				return datas[i];
		}
		return null;
	}

	/**
	 * objをdatasから削除
	 * １つ上の配列で上書きする
	 */
	public void remove(T obj) {
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].getKey() == obj.getKey()) {
				for (int s = i; s < datas.length; s++) {
					datas[s] = datas[s + 1];
				}
			}
		}
	}

}

package sort;

/** 配列の箱は固定で、溢れたら大きい数字を弾き出す
 * 選択ソートに近い形 */
public class SortedList<T extends SortedObject> {
	private T[] datas;
	int tableNom;

	@SuppressWarnings("unchecked")
	public void initialize() {
		tableNom = 5;
		datas = (T[]) new SortedObject[tableNom];
	}

	public T[] getDatas() {
		return datas;
	}

	/**
	 * 配列に空きがあるならその場所に代入して終了
	 * 配列に空きがないなら大きいTを弾き出すために比較を続ける
	 */
	public void add(T obj) {
		boolean bool = true;
		SortedObject minSort;
		T prepareT;
		for (int i = 0; i < tableNom; i++) {
			if (datas[i] != null) {
				minSort = new IntegerSorted(datas[i].getKey(), datas[i].getValue());
				bool = minSort.compare(new IntegerSorted(obj.getKey(), obj.getValue()));
				if (bool) {
					prepareT = datas[i];
					datas[i] = obj;
					obj = prepareT;
				} // objに大きいTをはじき出して、objと次の数字を比べている
			} else {
				datas[i] = obj;
				break;
			}
		}
	}

	/** posがキーのオブジェクトを返却 */
	public T get(int pos) {
		for (int i = 0; i < tableNom; i++) {
			if (datas[i].getKey() == pos) {
				System.out.println("キー："+datas[i].getKey() + ", 値：" + datas[i].getValue());
				return datas[i];
			}
		}
		return null;
	}

	/**
	 * objをdatasから削除
	 * １つ上の配列で上書きする
	 */
	public void remove(T obj) {
		try {
			for (int i = 0; i < tableNom; i++) {
				if (datas[i].getKey() == obj.getKey()) {
					for (int s = i; s < tableNom - 1; s++) {
						if (datas[s + 1] != null) {
							datas[s] = datas[s + 1];
						} else {
							datas[i] = (T)null;
						}
					}
					datas[tableNom - 1] = (T)null;
					break;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("remove failed!");
		}
	}
}

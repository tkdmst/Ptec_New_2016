package sort;

/** 配列の箱は固定で、溢れたら大きい数字を弾き出す */
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
	 * 配列が空ならそこへ代入する
	 * 空が無かった場合はdropOutDataへとび、大きな数字を弾き飛ばしに行く
	 * 空が合った場合はcompareDataへとび、ソートする
	 */
	public void add(T obj) {
		int nullNom = 0;
		for (nullNom = 0; nullNom < tableNom; nullNom++) {
			if (datas[nullNom] == null) {
				datas[nullNom] = obj;
				break;
			}
		}
		if (nullNom == tableNom) {
			dropOutData(nullNom, obj);
		} else {
			compareData(nullNom, obj);
		}
	}

	/** objが何処にも入れなかったため 大きい数字をはじき出す */
	private void dropOutData(int nullNom, T obj) {
		boolean bool = true;
		IntegerSorted minSort;
		T prepareT;
		for (int i = 0; i < nullNom; i++) {
			minSort = new IntegerSorted(datas[i].getKey(), datas[i].getValue());
			bool = minSort.compare(new IntegerSorted(obj.getKey(), obj.getValue()));
			if (bool) {
				prepareT = datas[i];
				datas[i] = obj;
				obj = prepareT;
			}
		}
	}

	/** nullNomへobjが格納されているため 比較をする */
	private void compareData(int nullNom, T obj) {
		boolean bool = true;
		IntegerSorted minSort;
		T prepareT;
		for (int i = 0; i < nullNom; i++) {
			minSort = new IntegerSorted(datas[i].getKey(), datas[i].getValue());
			bool = minSort.compare(new IntegerSorted(datas[nullNom].getKey(), datas[nullNom].getValue()));
			if (bool) {
				prepareT = datas[nullNom];
				datas[nullNom] = datas[i];
				datas[i] = prepareT;
			}
		}
	}

	/** posがキーのオブジェクトを返却 */
	public T get(int pos) {
		for (int i = 0; i < tableNom; i++) {
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
		try {
			for (int i = 0; i < tableNom; i++) {
				if (datas[i].getKey() == obj.getKey()) {
					for (int s = i; s < tableNom - 1; s++) {
						if (datas[i + 1] == null) {
							datas[i] = null;
							System.out.println("ずもも" + s);
						}
						datas[s] = datas[s + 1];
					}
					break;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("remove failed!");
		}
	}
}

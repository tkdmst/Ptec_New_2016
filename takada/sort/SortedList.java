package sort;

public class SortedList<T extends SortedObject> {
	private T[] datas;
	int tableNom = 1;

	@SuppressWarnings("unchecked")
	public void initialize() {
		datas = (T[]) new SortedObject[tableNom];
	}

	public T[] getDatas() {
		return datas;
	}

	/**
	 * 配列に空きがないならば、新しい配列add[]へ入れ替えて別メソッドへ
	 * 最初の代入datas[0]が空きならば代入して終了
	 * @param obj SortedObjectを引きついだ仮型引数を利用している
	 */
	public void add(T obj) {
		boolean boolNullCheck = true;
		@SuppressWarnings("unchecked")
		T[] add = (T[]) new SortedObject[datas.length + 1];
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] != null) {
				add[i] = datas[i];
			} else {
				datas[i] = obj;
				boolNullCheck = false;
			}
		}
		if (boolNullCheck) {
			add[add.length - 1] = obj;
			datas = addIn(add).clone();
		}
	}

	/**
	 * datasを格納したadd[]内の操作を行う
	 * 比較、入れ替え
	 *
	 * @param add
	 *            新しく作った配列
	 * @return T[] その配列を返却
	 */
	private T[] addIn(T[] add) {
		boolean compareCheck = true;
		for (int s = 0; s < add.length; s++) {
			SortedObject minSort = new IntegerSorted(add[s].getKey(), add[s].getValue());
			compareCheck = minSort
					.compare(new IntegerSorted(add[add.length - 1].getKey(), add[add.length - 1].getValue()));
			if (compareCheck) {
				T prepareT = add[s];
				add[s] = add[add.length - 1];
				add[add.length - 1] = prepareT;
			}
		}
		return add;
	}

	/** posがキーのオブジェクトを返却 */
	public T get(int pos) {
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].getKey() == pos) {
				System.out.println("キー：" + datas[i].getKey() + ", 値：" + datas[i].getValue());
				return datas[i];
			}
		}
		return null;
	}

	/**
	 * 引数と同じキーを持つデータがあったら削除する
	 * del[]はdatas[]-1のデータ数を持つため、格納し直しクローンで移す
	 */
	public void remove(T obj) {
		boolean existCheck = true;
		@SuppressWarnings("unchecked")
		T[] del = (T[]) new SortedObject[datas.length - 1];
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].getKey() == obj.getKey()) {
				existCheck = false;
				continue;
			}
			if (existCheck)
				del[i] = datas[i];
			else
				del[i - 1] = datas[i];
		}
		datas = del.clone();
	}

	// 表示用メソッド
	public void printOut() {
		for (int i = 0; i < datas.length; i++)
			System.out.print(datas[i].getKey() + " ");
		System.out.println();
	}
}


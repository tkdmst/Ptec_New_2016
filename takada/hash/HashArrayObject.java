package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Objectの仕様
 *
 * @see HashAbstract
 */
public class HashArrayObject extends HashAbstract {

	private List<Model>list;

	HashArrayObject(int num){
		super.table(num);
		list = new ArrayList<Model>();
	}

	// 割り算メソッド
	public int cutHash(int hashnom) {
		return Math.abs(hashnom % tableNum);
	}

	// 格納メソッドの作成
	public String setHash(Model model) {
		int hashNum = cutHash(model.getHash());
		if (hashArray[hashNum] != null)
			list = hashArray[hashNum];
		list.add(model);
		hashArray[hashNum] = list;
		return "[" + hashNum + "]に格納";
	}

	// 検索メソッドの作成
	public String whereKey(Model model) {
		try {
			int hashNum = cutHash(model.getHash());
			for (Model keyList : hashArray[hashNum])
				if (model.getKey().equals(keyList.getKey()))
					return ">検索結果 >> " + keyList.getString();
		} catch (NullPointerException e) {
			return ">検索結果 >> なし";
		}
		return ">検索結果 >> なし";
	}

	// 削除メソッドの作成
	public String listRemove(Model model) {
		boolean flag = true;
		int hashNum = cutHash(model.getHash());
		if (hashArray[hashNum] != null)
			list = hashArray[hashNum];
		for (Model keyList : list) {
			if (model.getKey().equals(keyList.getKey())) {
				list.remove(keyList);
				flag = false;
				break;
			}
		}
		hashArray[hashNum] = list;

		if (flag) {
			return ">削除結果 >> 存在しません";
		}
		return ">削除結果 >> 削除しました";
	}

}

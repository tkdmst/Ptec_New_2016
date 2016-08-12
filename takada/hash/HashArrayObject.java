package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Objectの仕様
 *
 * @see HashAbstract
 */
public class HashArrayObject extends HashAbstract {
	// TODO:tableは配列。要素はList。List[]hashArray = new xxxList[tableNum];

	protected List<Model>[] hashArray;
	List<Model>list;

	// テーブルの作成 総称配列はできないのでそのまま警告抑制を
	@SuppressWarnings("unchecked")
	HashArrayObject() {
		hashArray = new ArrayList[tableNum];
		list = new ArrayList<Model>();
	}

	// 割り算メソッド
	public int cutHash(int hashnom) {
		return Math.abs(hashnom % tableNum);
	}

	// Modelオブジェクトを配列に格納する
	public String setHash(Model model) {
		try {
			int i = cutHash(model.getHash());
			list = hashArray[i];
			list.add(model);
			return "[" + i + "]に格納";

		} catch (ArrayIndexOutOfBoundsException e) { // 不正な配列にアクセス と、これに引っかかる。
			e.printStackTrace();
			return null;
		}
	}

	// 検索メソッドの作成
	public String whereKey(Model model) {
		try {
			int i = cutHash(model.getHash());
			for (Model keyList : hashArray[i]) {
				if (model.getKey().equals(keyList.getKey()))
					return ">検索結果 >> " + keyList.getString();
			}
			System.out.println(">検索終了");
		} catch (ArrayIndexOutOfBoundsException e) {
			return "検索結果なし";
		}
		return "検索結果なし";
	}

}

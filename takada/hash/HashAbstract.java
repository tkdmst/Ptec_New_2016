package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * アブストラクトのクラス<br/>
 *
 * @see HashArrayObject
 */
public abstract class HashAbstract {
	// TODO : table数を決定する関数を作る。
	// TODO : abstract classに変更してください。
	// TODO : メンバ変数として、テーブル数を保持。
	// TODO : コンストラクタでテーブル数を決定。protected final int tableNum;
	// TODO : tableは配列。要素はList。List[]hashArray = new xxxList[tableNum];

	protected int tableNum;
	protected List<Model>[] hashArray;

	@SuppressWarnings("unchecked")
	public void table(int tableNum) {
		this.tableNum = tableNum;
		hashArray = new ArrayList[tableNum];
	}

	public abstract String setHash(Model model);

	public abstract String whereKey(Model model);

}

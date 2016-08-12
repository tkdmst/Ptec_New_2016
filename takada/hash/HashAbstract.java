package hash;

/**
 * アブストラクトのクラス<br/>
 *
 * @see HashArrayString
 * @see HashArrayObject
 * @see HashArrayBuilder
 */
public abstract class HashAbstract {
	// TODO : table数を決定する関数を作る。
	// TODO : abstract classに変更してください。
	// TODO : メンバ変数として、テーブル数を保持。
	// TODO : コンストラクタでテーブル数を決定。protected final int tableNum;

	protected int tableNum;

	public void tableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public abstract String setHash(Model model);

	public abstract String whereKey(Model model);

}

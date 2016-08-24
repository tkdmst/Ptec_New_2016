package sort;

public class IntegerSorted implements SortedObject {

	/** ユニークであるキー値 数値のみ */
	private int key;
	/** 文字である値 */
	private String value;

	IntegerSorted(int key, String value) {
		this.key = key;
		this.value = value;
	}

	/** キーの取得 */
	public int getKey() {
		return key;
	}
	/** バリューの取得 */
	public String getValue() {
		return value;
	}

	/** クラス内のキー > xのキー だとtrueを返却 逆はfalse */
	public boolean compare(SortedObject x) {
		return this.key > x.getKey();
	}
}

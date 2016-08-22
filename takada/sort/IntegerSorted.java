package sort;

public class IntegerSorted implements SortedObject {

	/** ユニークであるキー値 数値のみ */
	private int key;
	/** 文字である値 */
	private String value;

	IntegerSorted(int key, String value){
		this.key = key;
		this.value = value;
	}

	/** ゲッター */
	public int getKey(){
		return key;
	}
	public String getValue(){
		return value;
	}

	/** 選択中のキー > 配列最後のキー だとtrueを返却 比較のみ */
	public boolean compare(SortedObject x){
		return this.key > x.getKey();
	}
}

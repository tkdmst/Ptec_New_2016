package sort;

public class IntegerSorted implements SortedObject {

	private int key;
	private String value;

	/** セッター代わり */
	public void setInitialize(int nom,String value){
		this.key = nom;
		this.value = value;
	}

	public int getKey(){
		return key;
	}
	public String getValue(){
		return value;
	}

	/** 今のクラスのkeyの方が持ってきたkeyより大きいとtrue
	 * 大きい値を残していく
	 */
	public boolean compare(SortedObject x){
		this.key = (this.key > x.getKey()) ? x.getKey()  :this.key;
		return this.key > x.getKey();
	}
}

package socDB;

import java.io.Serializable;

/**
 * Modelクラスはデータを格納するクラスです。<br>
 * シリアライズ化しています。
 *
 */
public class Model implements Serializable {
	private final StringBuilder value;
	// クラスのバージョンを識別する番号
	private static final long serialVersionUID = 15654643L;

	/**
	 * 入力された文字をモデルへ格納します。
	 *
	 * @param value モデルクラスに格納する文字列
	 */
	public Model(String value) {
		this.value = new StringBuilder(value);
	}

	/**
	 * toStringはあらかじめコンパイルされるメソッドです。<br>
	 * モデルクラスに格納した文字列をString型で返します。
	 *
	 * @return クラスに格納した文字列
	 *
	 */
	@Override
	public String toString() {
		return value.toString();
	}
}
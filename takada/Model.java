package socDB;

import java.io.Serializable;

public class Model implements Serializable {
	private final StringBuilder value;
	// クラスのバージョンを識別する番号
	private static final long serialVersionUID = 15654643L;

	// コンストラクター
	public Model(String value) {
		this.value = new StringBuilder(value);
	}

	// toString
	@Override
	public String toString() {
		return value.toString();
	}
}
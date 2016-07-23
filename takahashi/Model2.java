package Socket_Class2;

import java.io.Serializable;

public class Model2 implements Serializable {

	private static final long serialVersionUID = 1L;
	public StringBuilder value;

	public Model2(String value) {
		this.value = new StringBuilder(value);
	}

	public void reverse() {
		value.reverse();
	}

	public String toString() {
		return value.toString();
	}
}

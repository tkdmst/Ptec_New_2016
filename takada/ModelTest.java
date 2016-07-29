package socDB;

import junit.framework.TestCase;

public class ModelTest extends TestCase {
	private Model model = null;

	/**
	 * Modelクラスにnullを与えたとき、クラスの中身はnullになる
	 */
	public void testToString() {
		assertNull(null, model);
	}

	/**
	 * Modelクラスに値を与えたとき、クラスの中身はちゃんと入っている
	 */
	public void testInModel() {
		model = new Model("sample");
		assertNotNull(model);
	}

	/**
	 * Modelクラスに値を与えたとき、クラスの中身は与えたものと同じ
	 */
	public void testOutModel() {
		model = new Model("sample");
		assertEquals("sample", model.toString());

	}

}

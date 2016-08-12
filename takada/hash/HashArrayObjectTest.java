package hash;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HashArrayObjectTest {

	public static HashArrayObject sut;
	public static Model model;
	public static Model modelFail;

	@BeforeClass
	public static void initialize() {
		sut = new HashArrayObject();
		sut.tableNum(5);
		model = new Model("sample");
		model.setValue("sampleValue");
		modelFail = new Model("hoge");
	}

	@Test
	public void testCutHash() {
		int actual = 1234567 % 5;
		int expected = sut.cutHash(1234567);
		assertThat(actual, is(expected));
	}

	@Test
	public void testSetHash() {
		String actual = sut.setHash(model);
		String expected = "[" + sut.cutHash("sample".hashCode()) + "]に格納";
		System.out.println(sut.hashArray[sut.cutHash("sample".hashCode())]);
		assertThat(actual, is(expected));
	}

	@Test
	public void testWhereKey成功版() {
		String actual = sut.whereKey(model);
		String expected = ">検索結果 >> sampleValue";
		assertThat(actual, is(expected));
	}


	@Test
	public void testWhereKey失敗版(){
		String actual = sut.whereKey(modelFail);
		String expected = "検索結果なし";
		assertThat(actual, is(expected));
	}

}

package hash;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HashArrayObjectTest {

	public static HashArrayObject sut;
	public static Model model;
	public static Model modelFail;
	public static String actual, expected;

	@BeforeClass
	public static void initialize() {
		sut = new HashArrayObject(5);
		model = new Model("sample");
		model.setValue("sampleValue");
		modelFail = new Model("hoge");
	}

	@Test
	public void testCutHashハッシュコード発行() {
		int actual = 1234567 % 5;
		int expected = sut.cutHash(1234567);
		assertThat(actual, is(expected));
	}

	@Test
	public void testSetHash格納() {
		actual = sut.setHash(model);
		expected = "[" + sut.cutHash("sample".hashCode()) + "]に格納";
		System.out.println(sut.hashArray[sut.cutHash("sample".hashCode())]);
		assertThat(actual, is(expected));
	}

	@Test
	public void testWhereKey検索成功からの削除() {
		actual = sut.whereKey(model);
		expected = ">検索結果 >> 名前：sample, 性別：sampleValue";
		assertThat(actual, is(expected));
		actual =sut.listRemove(model);
		expected = ">削除結果 >> 削除しました";
		assertThat(actual, is(expected));
	}


	@Test
	public void testWhereKey検索失敗(){
		actual = sut.whereKey(modelFail);
		expected = ">検索結果 >> なし";
		assertThat(actual, is(expected));
	}

	@Test
	public void testlistRemove削除失敗(){
		actual =sut.listRemove(modelFail);
		expected = ">削除結果 >> 存在しません";
		assertThat(actual, is(expected));
	}
}
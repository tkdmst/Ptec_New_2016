package sort;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**  */
@SuppressWarnings("unchecked")
public class SortedListTest<T extends SortedObject> {

	public static void main(String[] args) {
		System.out.println("メイン");
		JUnitCore.main(SortedListTest.class.getName());
	}

	public static SortedList<SortedObject> sut;
	public static StringBuilder sb;
	public static String actual, expected;

	@Before
	public void up() throws Exception{
		sut = new SortedList<SortedObject>();
		sb = new StringBuilder();
		sut.initialize();
	}

	@Test
	public void testadd１個目() throws Exception {
		sut.add(new IntegerSorted(3, "three"));

		actual = getData配列の取得();
		assertThat(actual, is("3 "));

	}
	@Test
	public void testadd２個目() throws Exception{
		sut.add(new IntegerSorted(3, "three"));
		sut.add(new IntegerSorted(7, "seven"));

		actual = getData配列の取得();
		assertThat(actual, is("3 7 "));

	}
	@Test
	public void testadd溢れず() throws Exception{
		sut.add(new IntegerSorted(3, "three"));
		sut.add(new IntegerSorted(7, "seven"));
		sut.add(new IntegerSorted(4, "four"));
		sut.add(new IntegerSorted(8, "eight"));
		sut.add(new IntegerSorted(51, "n"));
		sut.add(new IntegerSorted(9, "nine"));
		sut.add(new IntegerSorted(1, "one"));
		sut.add(new IntegerSorted(2, "two"));

		actual = getData配列の取得();
		assertThat(actual, is("1 2 3 4 7 8 9 51 "));
	}

	@Test
	public void testRemove１件のみ削除() {
		SortedObject obj = new IntegerSorted(7, "seven");
		sut.add(obj);
		sut.remove(obj);

		actual = getData配列の取得();
		assertThat(actual, is(""));
	}

	@Test
	public void testRemove３件からの削除() {
		SortedObject obj = new IntegerSorted(7, "seven");
		sut.add(new IntegerSorted(4, "four"));
		sut.add(new IntegerSorted(8, "eight"));
		sut.add(obj);

		sut.remove(obj);

		actual = getData配列の取得();
		assertThat(actual, is("4 8 "));
	}

	@Test
	public void testRemove６件からの削除() {
		SortedObject obj = new IntegerSorted(7, "seven");
		sut.add(new IntegerSorted(9, "nine"));
		sut.add(new IntegerSorted(1, "one"));
		sut.add(new IntegerSorted(2, "two"));
		sut.add(new IntegerSorted(4, "four"));
		sut.add(new IntegerSorted(8, "eight"));
		sut.add(obj);

		sut.remove(obj);

		actual = getData配列の取得();
		assertThat(actual, is("1 2 4 8 9 "));
	}


	public String getData配列の取得() {
		T[] ex = (T[]) sut.getDatas();
		for (int i = 0; i < ex.length; i++) {
			if (ex[i] == null)
				continue;
			sb.append(ex[i].getKey() + " ");
		}
		return sb.toString();
	}
}

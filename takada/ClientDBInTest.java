package socDB_JUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import socDB.ClientDB;
import socDB.Model;

@RunWith(Enclosed.class)
public class ClientDBInTest {
	private static ClientDB sut;
	private static Connection con;
	private static Statement stmt, stmtSide;
	private static ResultSet rsetSide;
	private static String sql;

	@BeforeClass
	public static void setUpClass() throws Exception {
		sut = ClientDB.instance();
		sut.initialize();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/howdb", "root", "");
		stmt = con.createStatement();
		stmtSide = con.createStatement();
	}

	@RunWith(Theories.class)
	public static class 最初に0から1件を入れるテスト {

		@DataPoint
		public static Model MODEL_VALUE_1 = new Model("0 -> 1");

		/*
		 * 前提：指定クラスの初期化, クラスでのコネクション
		 * 引数：Modelクラスの文字列
		 * 結果：最終更新で 1:sample の結果を呼ぶ
		 */
		@Theory
		public void 単品Write(Model model) throws Exception {
			stmt.executeUpdate("Delete from socDB");
			String actual = "1 : " + model.toString();
			sut.write(model);

			sql = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
			rsetSide = stmtSide.executeQuery(sql);
			rsetSide.next();
			String expected = rsetSide.getString("nom") + " : " + rsetSide.getString("message");

			assertThat(actual, is(expected));

		}

	}


	@RunWith(Theories.class)
	public static class 次に1と5と7が入っていてそこに5件入れる {

		@BeforeClass
		public static void setUp() throws Exception {
			stmt.executeUpdate("Delete from socDB");
			stmt.executeUpdate("Insert into socDB values(1,'hoge',NOW()),(5,'hoge',NOW()),(7,'hoge',NOW())");
		}


		@DataPoints
		public static int[] NOM_PARAMs = { 2, 3, 4, 6, 8 };
		@DataPoint
		public static Model MODEL_VALUE = new Model("sampleTest2");

		/**
		 * 前提：指定クラスの初期化, クラスでのコネクション<br>
		 * 条件：テーブルを空にした後、1,5,7が入っているテーブルにデータを格納する。<br>
		 * 引数：Modelクラスの文字列<br>
		 * 結果：1秒眠らせることで、格納したデータを引き出せるように<br>
		 */
		@Theory
		public void 間隔Write(int p, Model model) throws Exception {
			String actual = "実装値",
					expected = "結果値";
			Thread.sleep(1000);
			actual = p + " : " + model.toString();
			sut.write(model);

			sql = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
			rsetSide = stmtSide.executeQuery(sql);
			while (rsetSide.next())
				expected = rsetSide.getString("nom") + " : " + rsetSide.getString("message");

			System.out.println(actual+", "+expected);
			assertThat(actual, is(expected));
		}
	}

	/*
	 * 最後の処理
	 * 一回のみ
	 */
	@AfterClass
	public static void tearDown() throws Exception {
		sut.close();
		if (con != null)
			con.close();
		if (stmt != null)
			stmt.close();
		if (stmtSide != null)
			stmtSide.close();
		if (rsetSide != null)
			rsetSide.close();
	}
}

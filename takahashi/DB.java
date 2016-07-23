package Socket_Class2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import NEW_DB_1.Sql_view;

public class DB {
	private Connection conn = null;
	private Statement stmt = null;
	private Statement stmt2 = null;
	private ResultSet rset = null;
	private long total = 0;
	private Sql_view sv;
	private long start;

	private java.sql.PreparedStatement pstmt = null;

	private String sqlStr_insert;
	private String sqlStr_select;
	private String sqlStr_maxtime;
	private String sqlStr;
	private String sql_insert2;

	public Connection initilize() throws ClassNotFoundException, SQLException {
		// JDBCドライバーのロード
		Class.forName("com.mysql.jdbc.Driver");
		// MySQL接続
		conn = DriverManager.getConnection("jdbc:mysql://localhost/DB_1", "root", "");
		conn.setAutoCommit(false);
		// SQL実行stmt
		stmt = conn.createStatement();
		return conn;
	}

	// 書き込み
	public void write(Model2 model) throws SQLException, InterruptedException {

		// プリコンパイル
		sqlStr_insert = "insert db_1  (id,message,time) values (?,?,CURRENT_TIMESTAMP)";
		// パラメーター
		pstmt = conn.prepareStatement(sqlStr_insert);

		sqlStr = "select max(id) from db_1";
		rset = stmt.executeQuery(sqlStr);
		rset.next();
		int id = rset.getInt("max(id)") + 1;

		pstmt.setInt(1, id);
		pstmt.setString(2, model.toString());
		pstmt.executeUpdate();

		conn.commit();
	}

	// 読み込み
	public Model2 read() throws SQLException, ClassNotFoundException {
		// プリコンパイル
		sqlStr_select = "select * from db_1 where time =  ?";
		// パラメーター
		pstmt = conn.prepareStatement(sqlStr_select);
		// 更新日が最新のものをセレクト
		sqlStr_maxtime = "select max(time) from db_1";
		rset = stmt.executeQuery(sqlStr_maxtime);
		rset.next();
		String time = rset.getString("max(time)");
		// DBが空のとき
		if (time == null) {
			System.out.println("データはありません.書き込みしてください ");
			return null;
		}
		pstmt.setString(1, time);
		rset = pstmt.executeQuery();
		rset.next();
		String ptr = "id:" + rset.getInt("id") + "\t" + "message:" + "" + rset.getString("message") + "\t" + "time:"
				+ rset.getString("time");
		return new Model2(ptr);
	}

	// クローズする
	public void closed() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}

			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

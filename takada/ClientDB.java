package socDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDB implements Access {
	private Connection con;
	private PreparedStatement write_pstmt;
	private Statement stmt;
	private ResultSet rset;
	private int nom = 1;
	private String sql;

	// 初期化 ドライバーとの接続
	public void initialize() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/howdb", "root", "");
			con.setAutoCommit(false);
			write_pstmt = con.prepareStatement("Insert into socDB values(?,?,NOW())");
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Exception("JDBCドライバのロードに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ドライバーでの失敗");
		}
	}

	// 書き込み
	public void write(Model model) throws Exception {
		try {
			boolean flag = false;
			while (true) {
				sql = "exists(Select* from socDB where nom=" + nom + ")";
				rset = stmt.executeQuery("Select " + sql);
				while (rset.next()) {
					if (rset.getString(sql).equals("0"))
						flag = true;
				}
				if (flag)
					break;
				nom++;
			}
			write_pstmt.setInt(1, nom);
			write_pstmt.setString(2, model.toString());
			write_pstmt.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception();
		}
	}

	// 読み込み
	public Model read() throws Exception {
		sql = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
		try {
			rset = stmt.executeQuery(sql);
			while (rset.next())
				sql = "キー値:" + nom + ",文章：" + rset.getString("message") + ",最終更新日：" + rset.getString("upd_date");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception();
		}
		return new Model(sql);
	}

	// クローズ
	public void close() {
		try {
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
			if (write_pstmt != null)
				write_pstmt.close();
			if (rset != null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

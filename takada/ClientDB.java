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

	// singletonパターンで
	private static ClientDB clidb;

	// instance()のためにprivateでインスタンス化を不可に
	private ClientDB() {
		clidb = null;
	}

	// instance()を呼び出したときだけインスタンス化できる
	public static ClientDB instance() {
		if (clidb == null)
			clidb = new ClientDB();
		return clidb;
	}

	// 初期化 ドライバーとの接続
	public void initialize() throws AccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/howdb", "root", "");
			con.setAutoCommit(false);
			write_pstmt = con.prepareStatement("Insert into socDB values(?,?,NOW())");
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new AccessException("JDBCドライバのロードに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccessException("ドライバーでの失敗");
		}
	}

	// 書き込み
	public void write(Model model) throws AccessException {
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
			stmt.executeQuery("Select* from socDB where nom=" + nom + " for update" );
			write_pstmt.setInt(1, nom);
			write_pstmt.setString(2, model.toString());
			write_pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (Exception e1) {
				throw new AccessException("ロールバックエラー");
			}
			e.printStackTrace();
			throw new AccessException("書き込みエラー");
		}
	}

	// 読み込み
	public Model read() throws AccessException {
		sql = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
		try {
			rset = stmt.executeQuery(sql);
			while (rset.next())
				sql = "キー値:" + nom + ",文章：" + rset.getString("message") + ",最終更新日：" + rset.getString("upd_date");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccessException("読み込みエラー");
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

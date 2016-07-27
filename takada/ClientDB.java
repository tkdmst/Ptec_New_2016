package socDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClientDBクラスはこのプログラムの主な処理を担当しています。<br>
 * Accessの行き着く先です。 ※関連項目参照<br>
 * Singletonにしてあることで簡単にはインスタンス化できなくなっています。
 *
 * @see Access
 */
public class ClientDB implements Access {
	private Connection con;
	private PreparedStatement write_pstmt;
	private Statement stmt;
	private ResultSet rset;
	private int nom = 1;
	private String sql;

	// singletonパターンに必須
	private static ClientDB clidb;

	/** コンストラクタではインスタンス化できないようになっています。 */
	private ClientDB() {
		clidb = null;
	}

	/**
	 * instanceメソッドを呼び出したときだけインスタンス化可能になります。
	 *
	 * @return clidb インスタンス化
	 */
	public static ClientDB instance() {
		if (clidb == null)
			clidb = new ClientDB();
		return clidb;
	}

	/**
	 * initializeは初期化を行います。<br>
	 * ドライバーとの接続、プリペアの作成、ステートメントの作成、オートコミットの停止など
	 */
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

	/**
	 * writeはDBに書き込みをします。<br>
	 * 書き込む手段として、PKの番号は小さい順に総当りで探し、その番号をロックしてから書き込みを行います。<br>
	 * コミットもこのメソッドで行います。失敗時にはロールバックをします。<br>
	 * 空いているレコードを探す処理からロックまでをsynchronizedにしました。
	 *
	 * @param model
	 *            Modelクラスの利用
	 * @throws AccessException
	 *             エラー時にメッセージをつけて処理
	 */
	public void write(Model model) throws AccessException {
		try {
			boolean flag = false;
			synchronized (this) {
				while (true) {
					sql = "Select* from socDB where nom=" + nom;
					rset = stmt.executeQuery(sql);
					if (!rset.next())
						flag = true;
					if (flag)
						break;
					nom++;
				}
				stmt.executeQuery(sql + " for update");
			}
			write_pstmt.setInt(1, nom);
			write_pstmt.setString(2, model.toString());
			write_pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
				throw new AccessException("ロールバックエラー");
			}
			e.printStackTrace();
			throw new AccessException("書き込みエラー");
		}
	}

	/**
	 * readは最新更新日のレコードの読み込みをします。<br>
	 * 読み込んだデータはModelクラスの形で帰します。<br>
	 *
	 * @return Model(sql) 得たsqlをModelクラスに引き渡す
	 */
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

	/** closeはクローズ処理を行います。 */
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

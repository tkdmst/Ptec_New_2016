package socDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

<<<<<<< HEAD
=======
/**
 * ClientDBクラスはこのプログラムの主な処理を担当しています。<br>
 * Accessの行き着く先です。 ※関連項目参照<br>
 * Singletonにしてあるため、簡単にはインスタンス化できなくなっています。
 *
 * @see Access
 */
>>>>>>> origin/master
public class ClientDB implements Access {
	private Connection con;
	private PreparedStatement write_pstmt;
	private Statement stmt, stmtRead;
	private ResultSet rset, rsetRead;
	private int nom;
	private String sql, sqlRead;
	// singletonパターン
	private static ClientDB clidb;
	// private Object lock = new Object();

<<<<<<< HEAD
	// singletonパターンで
	private static ClientDB clidb;

	// instance()のためにprivateでインスタンス化を不可に
=======
	/** コンストラクタではインスタンス化できないようになっています。 */
>>>>>>> origin/master
	private ClientDB() {
		clidb = null;
	}

<<<<<<< HEAD
	// instance()を呼び出したときだけインスタンス化できる
=======
	/**
	 * instanceメソッドを呼び出したときだけインスタンス化可能になります。
	 *
	 * @return インスタンス化を可能にする
	 */
>>>>>>> origin/master
	public static ClientDB instance() {
		if (clidb == null)
			clidb = new ClientDB();
		return clidb;
	}

<<<<<<< HEAD
	// 初期化 ドライバーとの接続
=======
	/**
	 * initializeは初期化を行います。<br>
	 * ドライバーとの接続、プリペアの作成、ステートメントの作成、オートコミットの停止など
	 */
>>>>>>> origin/master
	public void initialize() throws AccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/howdb", "root", "");
			con.setAutoCommit(false);
			write_pstmt = con.prepareStatement("Insert into socDB values(?,?,NOW())");
			stmt = con.createStatement();
			stmtRead = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new AccessException("JDBCドライバのロードに失敗しました。");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccessException("ドライバーでの失敗");
		}
	}

<<<<<<< HEAD
	// 書き込み
	public void write(Model model) throws AccessException {
=======
	/**
	 * writeはDBに書き込みをします。<br>
	 * 書き込む手段として、PKの番号は小さい順に総当りで探し書き込みを行います。<br>
	 * コミットもこのメソッドで行います。失敗時にはロールバックをします。<br>
	 * メソッドそのものをsynchronizedにしました。
	 *
	 * @param model
	 *            Modelクラスの利用
	 * @throws AccessException
	 *             エラー時にメッセージをつけて処理
	 * @see AutoExecuter
	 */
	public synchronized void write(Model model) throws AccessException {
>>>>>>> origin/master
		try {
			boolean flag = false;
			nom = 1;
			// synchronized (lock) {
			while (true) {
				sql = "Select* from socDB where nom=" + nom;
				rset = stmt.executeQuery(sql);
				if (!rset.next())
					flag = true;
				if (flag)
					break;
				nom++;
			}
			write_pstmt.setInt(1, nom);
			write_pstmt.setString(2, model.toString());
			write_pstmt.executeUpdate();
			con.commit();
<<<<<<< HEAD
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (Exception e1) {
=======
			// }
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
>>>>>>> origin/master
				throw new AccessException("ロールバックエラー");
			}
			e.printStackTrace();
			throw new AccessException("書き込みエラー");
		}
	}

<<<<<<< HEAD
	// 読み込み
	public Model read() throws AccessException {
		sql = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
		try {
			rset = stmt.executeQuery(sql);
			while (rset.next())
				sql = "キー値:" + nom + ",文章：" + rset.getString("message") + ",最終更新日：" + rset.getString("upd_date");
=======
	/**
	 * readは最新更新日のレコードの読み込みをします。<br>
	 * 読み込んだデータはModelクラスの形で帰します。<br>
	 *
	 * @return 見つけたレコードをModelクラスに引き渡す
	 */
	public Model read() throws AccessException {
		sqlRead = "Select* from socDB where upd_date=(Select Max(upd_date) from socDB)";
		try {
			rsetRead = stmtRead.executeQuery(sqlRead);
			while (rsetRead.next())
				sqlRead = "キー値:" + rsetRead.getInt("nom") + ",文章：" + rsetRead.getString("message")
						+ ",最終更新日：" + rsetRead.getString("upd_date");
>>>>>>> origin/master
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccessException("読み込みエラー");
		}
		return new Model(sqlRead);
	}

	/** closeはクローズ処理を行います。 */
	public void close() {
		try {
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
			if (stmtRead != null)
				stmtRead.close();
			if (write_pstmt != null)
				write_pstmt.close();
			if (rset != null)
				rset.close();
			if (rsetRead != null)
				rsetRead.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("クローズ内でのエラー");
		}
	}
}

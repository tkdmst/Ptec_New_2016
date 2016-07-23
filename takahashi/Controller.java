package Socket_Class2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

	private View view;
	private DB db;
	// DB接続、SQL発行に必要となるインターフェースの宣言
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Controller(View view, DB db) {
		this.view = view;
		this.db = db;
	}

	public void proc() throws Exception {

		// JDBCドライバーのロード
		Class.forName("com.mysql.jdbc.Driver");
		try {
			while (true) {

				Model2 model2 = view.input();

				db.write(model2);

				model2 = db.read();

				view.display(model2);
			}
		} finally {
			if (stmt != null) {
				// ステートメントをクローズ
				stmt.close();
			}

			if (conn != null) {
				// 接続をクローズ
				conn.close();
			}
		}
	}

}

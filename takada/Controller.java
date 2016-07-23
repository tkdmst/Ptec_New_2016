package socDB;

import java.sql.SQLException;

//test
public class Controller {
	private final View view;
	private final ClientDB cDB;

	// コンストラクタ
	Controller(View view, ClientDB cDB) {
		this.view = view;
		this.cDB = cDB;
	}

	// クライアントの主な動き方 入力、書き込み、読み込み、表示
	public void proc() throws Exception {
		while (true) {
			try {
				System.out.println(">開始 入力で新規 stopで終了");
				Model model = view.input();
				if (model.toString().equals("stop"))
					break;
				cDB.write(model);
				System.out.println(">write 終了");
				model = cDB.read();
				view.display(model);
				System.out.println(">read終了");
			} catch (SQLException e) {
				throw new Exception("proc内の致命的なエラー");
			}
		}
	}
}

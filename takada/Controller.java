package socDB;

public class Controller {
	private final View view;
	private final Access ac;

	// コンストラクタ
	Controller(View view, Access ac) {
		this.view = view;
		this.ac = ac;
	}

	// クライアントの主な動き方 入力、書き込み、読み込み、表示
	public void proc() throws Exception {
		while (true) {
			try {
				System.out.println(">開始 入力で新規 stopで終了");
				Model model = view.input();
				if (model.toString().equals("stop"))
					break;
				ac.write(model);
				System.out.println(">write 終了");
				model = ac.read();
				view.display(model);
				System.out.println(">read終了");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new Exception("proc内の致命的なエラー");
			}
		}
	}
}

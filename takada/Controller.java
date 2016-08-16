package socDB;

<<<<<<< HEAD
=======
/**
 * Controllerクラスは処理の大きな動き方が記載されています。
 */
>>>>>>> origin/master
public class Controller {
	private final View view;
	private final Access ac;

<<<<<<< HEAD
	// コンストラクタ
=======
	/** 引数としてViewクラスとAccessクラスを持ってくる。 */
>>>>>>> origin/master
	Controller(View view, Access ac) {
		this.view = view;
		this.ac = ac;
	}

<<<<<<< HEAD
	// クライアントの主な動き方 入力、書き込み、読み込み、表示
=======
	/**
	 * procでは 入力、書き込み、読み込み、表示の処理をコールします。<br>
	 * プログラムの停止が打ち込まれた時もこのメソッドで停止をコールします。
	 *
	 * @throws AccessException
	 *             エラー時にメッセージをつけて処理
	 */
>>>>>>> origin/master
	public void proc() throws AccessException {
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
			} catch (AccessException e) {
<<<<<<< HEAD
				System.out.println(e.getMessage());
				throw new AccessException("proc内の致命的なエラー");
=======
				e.printStackTrace();
				throw new AccessException(e.getMessage());
>>>>>>> origin/master
			}
		}
	}
}

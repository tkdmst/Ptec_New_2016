package socDB;

/**
 * メインクラスは実行クラスです。
 */
public class Mein {
	private static Access db = ClientDB.instance();
<<<<<<< HEAD
=======
	private static AutoExecuter exe = new AutoExecuter();
	private static Thread th;
	private static Controller cotroller;
>>>>>>> origin/master

	/**
	 * mainメソッドでは初期処理からスレッドの作成・スタートまでの主幹処理を行います。<br>
	 * エラーが発生してもスレッドの終了を待ち、クローズ処理は行えるようになっています。
	 *
	 * @param args
	 *            不使用
	 */
	public static void main(String[] args){
		try {
			db.initialize();
<<<<<<< HEAD
			db.read();
			View view = new View();
			view.initialize();
			Controller cotroller = new Controller(view, db);
=======
			View view = new View();
			view.initialize();
			cotroller = new Controller(view, db);
			System.out.println();
			th = new Thread(exe);
			th.start();
>>>>>>> origin/master
			cotroller.proc();
		} catch (AccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally { // 終了すべく２つの関数を呼び出す
			exe.threadEnd();
			try {
				th.join();
			} catch (InterruptedException e) {
				System.out.println("joinエラー 終了できず");
				e.printStackTrace();
			}
			db.close();
			System.out.println(">終了");
<<<<<<< HEAD
			db.close();
=======
>>>>>>> origin/master
		}
	}
}
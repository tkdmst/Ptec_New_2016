package socDB;

public class AutoExecuter implements Runnable {
	private boolean runner = true;

	public void run() {
		Access db = ClientDB.instance();
		try {
			while (runner) {
				db.write(new Model("小休止3秒"));
				Thread.sleep(3000);
			}
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// スレッドの終了を呼ぶメソッド
	public void threadEnd() {
		runner = false;
	}
}

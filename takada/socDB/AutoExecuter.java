package socDB;

/**
 * AutoExecuterはスレッドクラスで書き込みとスレッド終了のメソッドがあります。
 */
public class AutoExecuter implements Runnable {
	private boolean runner = true;

	/**
	 * runは3秒に一回、writeメソッドを利用して小休止3秒と書き込みます。
	 *
	 * @see ClientDB
	 */
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

	/** threadEndはスレッドの終了をコールするメソッドです。 */
	public void threadEnd() {
		runner = false;
	}
}

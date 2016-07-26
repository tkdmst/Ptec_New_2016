package socDB;

public class Mein {
	private static Access db = ClientDB.instance();

	public static void main(String[] args) {
		AutoExecuter exe = new AutoExecuter();
		try {
			db.initialize();
			Thread th = new Thread(exe);
			th.start();
		} catch (AccessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			View view = new View();
			view.initialize();
			Controller cotroller = new Controller(view, db);
			cotroller.proc();
		} catch (NullPointerException e) {
			System.out.println("nullPo");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println(">終了");
			exe.threadEnd();
			db.close();
		}
	}
}

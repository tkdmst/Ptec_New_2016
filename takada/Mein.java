package socDB;

public class Mein {
	private static Access db = ClientDB.instance();

	public static void main(String[] args) {
		try {
			db.initialize();
			db.read();
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
			db.close();
		}
	}
}

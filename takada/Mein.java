package socDB;

public class Mein {
	private static Access db = new ClientDB();
	//private static Access soc = new ClientSocket(String hostname, int portNo);

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

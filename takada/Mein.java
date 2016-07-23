package socDB;

public class Mein {
	private static ClientDB cDB = new ClientDB();

	public static void main(String[] args) {
		try {
			cDB.initialize();
			cDB.read();
			View view = new View();
			view.initialize();
			Controller cotroller = new Controller(view, cDB);
			cotroller.proc();
		} catch (NullPointerException e) {
			System.out.println("nullPo");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println(">終了");
			cDB.close();
		}
	}
}

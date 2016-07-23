
package Socket_Class2;

import java.sql.SQLException;

public class Main_Serialize {

	public static void main(String[] args) {

//		String hostname = args[0];
//		int port = Integer.parseInt(args[1]);
		// ClientSocket socket = new ClientSocket(hostname,port);
		// socket.intilize();
		DB db = new DB();
		try {
			db.initilize();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		View view = new View();
		view.intilize();

		Controller ctrl = new Controller(view, db);
		try {

			ctrl.proc();

		} catch (Exception e) {
			System.out.println("終了");
			e.printStackTrace();
		}
	}

}

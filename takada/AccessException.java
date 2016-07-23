package socDB;

public class AccessException extends Exception {
	public AccessException() {
		super();
		System.out.println("行方不明エラー");
	}

	public AccessException(String message) {
		super(message);
	}
}

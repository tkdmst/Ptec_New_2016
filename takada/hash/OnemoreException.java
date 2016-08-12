package hash;

public class OnemoreException extends Exception {

	OnemoreException(){
		super("メインメニューに戻ります");
	}

	OnemoreException(String err){
		super(err);
	}

}


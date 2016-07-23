
package Socket_Class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {

	BufferedReader keyboard;
	String str;

	public void intilize() {
		keyboard = new BufferedReader(new InputStreamReader(System.in));
	}

	public void display(Model2 model2) {
		System.out.println("受信:" + model2.toString());
	}

	public Model2 input() {
		try {
			System.out.print("送信:");
			str = keyboard.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new Model2(str);
	}
}

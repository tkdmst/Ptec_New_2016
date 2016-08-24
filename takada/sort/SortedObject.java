package sort;

public interface SortedObject {
	// getterを指示より増やした
	public int getKey();
	public String getValue();

	public boolean compare(SortedObject x);

}

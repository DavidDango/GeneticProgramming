package Tree;

public interface Condition {
	public boolean eval(double[] in);
	public String print();
	public Condition copy();
}

package Tree;

public interface Functions {
	public double eval(double[] in);
	public Functions copy();
	public String print();
	public boolean isFunction();
	public boolean isTerminal();
	public Functions getLeft();
	public Functions getRight();
	public void setLeft(Functions left);
	public void setRight(Functions left);
}
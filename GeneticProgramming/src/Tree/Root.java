package Tree;

public class Root implements Functions {
	private Functions root;
	
	public Root() {
		root = new NullTerminal();
	}

	@Override
	public double eval(double[] in) {
		return root.eval(in);
	}

	@Override
	public Root copy() {
		Root temp = new Root();
		temp.setRoot(root);
		return temp;
	}

	public void setRoot(Functions function) {
		this.root = function;
	}

	@Override
	public String print() {
		return root.print();
	}
	
	public Functions getRoot() {
		return root;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

	@Override
	public Functions getLeft() {
		return null;
	}

	@Override
	public Functions getRight() {
		return null;
	}
	
	public void setLeft(Functions f) {
	}
	
	public void setRight(Functions f) {
	}
}

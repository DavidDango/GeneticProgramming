package Tree;

public abstract class AbstractFunctions implements Functions {
	Functions leftNode;
	Functions rightNode;
	
	public boolean isFunction() {
		return true;
	}
	
	public boolean isTerminal() {
		return false;
	}
	
	public void setLeft(Functions f) {
		leftNode = f;
	}
	
	public void setRight(Functions f) {
		rightNode = f;
	}
	
	public Functions getRight() {
		return rightNode;
	}
	
	public Functions getLeft() {
		return leftNode;
	}
}
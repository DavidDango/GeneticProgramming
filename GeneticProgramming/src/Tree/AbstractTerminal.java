package Tree;

public abstract class AbstractTerminal implements Functions {
	
	public boolean isFunction() {
		return false;
	}
	
	public boolean isTerminal() {
		return true;
	}
	
	public Functions getRight() {
		return new NullTerminal();
	}
	
	public Functions getLeft() {
		return new NullTerminal();
	}
	
	public void setLeft(Functions f) {
	}
	
	public void setRight(Functions f) {
	}
}

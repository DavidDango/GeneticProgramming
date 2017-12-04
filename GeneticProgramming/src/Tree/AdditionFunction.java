package Tree;

public class AdditionFunction extends AbstractFunctions {
	
	public AdditionFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}
	
	@Override
	public double eval(double[] in) {
		return (leftNode.eval(in) + rightNode.eval(in));
	}

	@Override
	public AdditionFunction copy() {
		AdditionFunction temp = new AdditionFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(+ " + leftNode.print() + " " + rightNode.print() + ")";
	}
}
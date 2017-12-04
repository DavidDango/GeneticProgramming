package Tree;

public class SubstractionFunction extends AbstractFunctions {
	
	public SubstractionFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}

	@Override
	public double eval(double[] in) {
		return (leftNode.eval(in) - rightNode.eval(in));
	}
	
	@Override
	public SubstractionFunction copy() {
		SubstractionFunction temp = new SubstractionFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(- " + leftNode.print() + " " + rightNode.print() + ")";
	}
}

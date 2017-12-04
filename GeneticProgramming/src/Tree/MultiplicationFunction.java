package Tree;

public class MultiplicationFunction extends AbstractFunctions {
	
	public MultiplicationFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}

	@Override
	public double eval(double[] in) {
		return (leftNode.eval(in)*rightNode.eval(in));
	}
	
	@Override
	public MultiplicationFunction copy() {
		MultiplicationFunction temp = new MultiplicationFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(* " + leftNode.print() + " " + rightNode.print() + ")";
	}
}

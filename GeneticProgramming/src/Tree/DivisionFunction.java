package Tree;

public class DivisionFunction extends AbstractFunctions {
	
	public DivisionFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}

	@Override
	public double eval(double[] in) {
		if(rightNode.eval(in) == 0) {
			return 0;
		}
		else {
			return (leftNode.eval(in)/rightNode.eval(in));
		}
	}
	
	@Override
	public DivisionFunction copy() {
		DivisionFunction temp = new DivisionFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(/ " + leftNode.print() + " "+ rightNode.print() + ")";
	}
}
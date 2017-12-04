package Tree;

public class MaxFunction extends AbstractFunctions {
	
	public MaxFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}
	
	@Override
	public double eval(double[] in) {
		if(leftNode.eval(in) > rightNode.eval(in)) {
			return leftNode.eval(in);
		}
		else {
			return rightNode.eval(in);
		}
	}

	@Override
	public MaxFunction copy() {
		MaxFunction temp = new MaxFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(Max " + leftNode.print() + " " + rightNode.print() + ")";
	}
}

package Tree;

public class MinFunction extends AbstractFunctions {
	
	public MinFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}
	
	@Override
	public double eval(double[] in) {
		if(leftNode.eval(in) < rightNode.eval(in)) {
			return leftNode.eval(in);
		}
		else {
			return rightNode.eval(in);
		}
	}

	@Override
	public MinFunction copy() {
		MinFunction temp = new MinFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(Min " + leftNode.print() + " " + rightNode.print() + ")";
	}
}
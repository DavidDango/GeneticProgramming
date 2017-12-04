package Tree;

public class CosineFunction extends AbstractFunctions {
	
	public CosineFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
	}
	
	@Override
	public double eval(double[] in) {
		return Math.cos(leftNode.eval(in) + rightNode.eval(in));
	}
	
	@Override
	public CosineFunction copy() {
		CosineFunction temp = new CosineFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(Cos " + leftNode.print() + " " + rightNode.print() + ")";
	}
}
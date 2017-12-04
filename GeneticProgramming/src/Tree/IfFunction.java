package Tree;

public class IfFunction extends AbstractFunctions {
	private Condition condition;
	
	public IfFunction() {
		this.rightNode = new NullTerminal();
		this.leftNode = new NullTerminal();
		this.condition = new NullCondition();
	}
	
	@Override
	public double eval(double[] in) {
		if(condition.eval(in)) {
			return leftNode.eval(in);
		}
		else {
			return rightNode.eval(in);
		}
	}
	
	public void setCondition(Condition c) {
		this.condition = c;
	}

	@Override
	public IfFunction copy() {
		IfFunction temp = new IfFunction();
		temp.setLeft(this.leftNode.copy());
		temp.setRight(this.rightNode.copy());
		temp.setCondition(condition.copy());
		return temp;
	}

	@Override
	public String print() {
		return "(if " + condition.print() + " " + leftNode.print() + " " + rightNode.print() + ")";
	}
}
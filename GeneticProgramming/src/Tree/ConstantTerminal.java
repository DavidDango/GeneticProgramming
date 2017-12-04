package Tree;

public class ConstantTerminal extends AbstractTerminal {
	private double val;
	
	public ConstantTerminal(double value) {
		val = value;
	}
	
	@Override
	public double eval(double[] in) {
		return val;
	}
	
	@Override
	public ConstantTerminal copy() {
		return new ConstantTerminal(val);
	}

	@Override
	public String print() {
		return Double.toString(val);
	}
}

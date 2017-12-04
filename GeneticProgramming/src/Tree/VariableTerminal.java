package Tree;

public class VariableTerminal extends AbstractTerminal {
	private int index;
	
	public VariableTerminal(int i) {
		index = i;
	}
	
	@Override
	public double eval(double[] in) {
		return in[index];
	}
	
	@Override
	public VariableTerminal copy() {
		return new VariableTerminal(index);
	}

	@Override
	public String print() {
		return "x" + Integer.toString(index);
	}
}
package Tree;

public class NullTerminal extends AbstractTerminal {

	@Override
	public double eval(double[] in) {
		return 0;
	}

	@Override
	public NullTerminal copy() {
		return new NullTerminal();
	}

	@Override
	public String print() {
		return "";
	}
}

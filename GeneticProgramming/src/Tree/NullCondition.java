package Tree;

public class NullCondition implements Condition {
	@Override
	public boolean eval(double[] in) {
		return false;
	}

	@Override
	public String print() {
		return "";
	}

	@Override
	public Condition copy() {
		return new NullCondition();
	}
}

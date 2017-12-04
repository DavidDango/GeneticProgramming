package Tree;

public class HigherThan implements Condition {
	private int var1;
	private int var2;
	
	@Override
	public boolean eval(double[] in) {
		if(in[var1] > in[var2]) {
			return true;
		}
		return false;
	}

	@Override
	public String print() {
		return ">" + " x" + Integer.toString(var1) + " x" + Integer.toString(var2);
	}

	@Override
	public Condition copy() {
		HigherThan temp = new HigherThan();
		temp.setVar1(var1);
		temp.setVar2(var2);
		return temp;
	}

	public void setVar2(int var) {
		this.var1 = var;
	}

	public void setVar1(int var) {
		this.var1 = var;
	}
}

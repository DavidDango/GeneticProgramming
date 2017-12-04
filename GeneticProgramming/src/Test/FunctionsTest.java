package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Tree.*;

class FunctionsTest {
	Root root;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		root = new Root();
		AdditionFunction temp = new AdditionFunction();
		temp.setLeft(new ConstantTerminal(10));
		temp.setRight(new VariableTerminal(0));
		MultiplicationFunction temp2 = new MultiplicationFunction();
		temp2.setLeft(temp);
		temp2.setRight(new VariableTerminal(1));
		root.setRoot(temp2);
	}

	@Test
	void testEval() {
		assertEquals(root.eval(new double[] {0, 0}), 0);
		assertEquals(root.eval(new double[] {1, 1}), 11);
		assertEquals(root.eval(new double[] {2, 3}), 36);
	}
	
	@Test
	void testCopy() {
		Root newRoot = root.copy();
		root.setRoot(new ConstantTerminal(1));
		assertEquals(newRoot.eval(new double[] {0, 0}), 0);
		assertEquals(newRoot.eval(new double[] {1, 1}), 11);
		assertEquals(newRoot.eval(new double[] {2, 3}), 36);
		assertEquals(root.eval(new double[] {0, 0}), 1);
		assertEquals(root.eval(new double[] {1, 1}), 1);
		assertEquals(root.eval(new double[] {2, 3}), 1);
		System.out.println(root.print());
		System.out.println(newRoot.print());
	}
}

package Run;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import Genetic.GeneticAlgorithm;

public class RunAlgorithm {
	public static void main(String[] args) throws Exception {
		Random generator = new Random(4);
		int inputs = 1;
		double[][] testInput = new double[100][inputs];
		double[] testOutput = new double[100];
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < inputs; j++) {
				testInput[i][j] = (generator.nextDouble() - 0.5)*2;
			}
			testOutput[i] = testFunction(testInput[i]);
		}
		GeneticAlgorithm gen = new GeneticAlgorithm(generator, 1024, inputs, 0.001);
		double currentBest = Double.MAX_VALUE;
		System.out.println("The algorithm will now proceed to attempt to find a solution for x^2 + x + 1");
		System.out.println("50% of the population is created using crossover, 25% through Mutation and 25% direct reproduction");
		System.out.println("Each time a better answer is reached, it will be displayed on the command shell");
		System.out.println("");
		TimeUnit.SECONDS.sleep(5);
		while(!gen.evaluateFitness(testInput, testOutput)) {
			gen.roulette(generator);
			if(gen.currentBest() < currentBest) {
				currentBest = gen.currentBest();
				gen.getWinner();
				System.out.println("");
			}
		}
		System.out.println("Solution found!");
		gen.getWinner();
		System.out.println("The solution's fitness is: " + gen.currentBest());
	}
	
	public static double testFunction(double[] in) {
		double out;
		out = Math.pow(in[0], 2) + in[0] + 1;
		return out;
	}
}

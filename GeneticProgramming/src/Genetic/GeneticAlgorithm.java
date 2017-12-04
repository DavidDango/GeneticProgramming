package Genetic;

import java.util.Arrays;
import java.util.Random;

import Tree.*;

public class GeneticAlgorithm {
	private Root[] roots;
	private double[] fitness;
	private int populationSize;
	private Root[] matingPool;
	private Root[] reproductionPool;
	private Root[] mutationPool;
	private int matingPoolSize;
	private int reproductionPoolSize;
	private int mutationPoolSize;
	private int numberOfBits;
	private double maxError;
	private int gen;
	
	public GeneticAlgorithm(Random generator, int populationSize, int in, double acceptable) {
		roots = new Root[populationSize];
		for(int i = 0; i < populationSize; i++) {
			roots[i] = new Root();
		}
		this.populationSize = populationSize;
		fitness = new double[populationSize];
		this.numberOfBits = in;
		matingPoolSize = populationSize;
		reproductionPoolSize = populationSize*1/4;
		mutationPoolSize = populationSize*1/4;
		this.maxError = acceptable;
		for(int i = 0; i < populationSize; i++) {
			roots[i] = createRoot(generator);
		}
		gen = 0;
	}
	
	public boolean evaluateFitness(double[][] in, double[] expected) throws Exception {
		boolean acceptableFitness = false;
		for(int i = 0; i < populationSize; i++) {
			double temp  = 0;
			for(int j = 0; j < in.length; j++) {
				temp = temp + Math.pow(roots[i].eval(in[j]) - expected[j], 2);
			}
			fitness[i] = Math.sqrt(temp);
			if(fitness[i] < maxError) {
				acceptableFitness = true;
			}
		}
		sortAll();
		return acceptableFitness;
	}
	
	private void sortAll() {
		Root[] newRoots = new Root[populationSize];
		double[] newFitnessList = new double[populationSize];
		for(int i = 0; i < populationSize; i++) {
			int min = 0;
			for(int j = 0; j < populationSize; j++) {
				if(fitness[j] < fitness[min]) {
					min = j;
				}
			}
			newFitnessList[i] = fitness[min];
			newRoots[i] = roots[min];
			fitness[min] = Double.MAX_VALUE;
		}
		fitness = newFitnessList;
		roots = newRoots;
	}
	
	public void roulette(Random generator) {
		double[] cumulativeFitness = new double[populationSize];
		cumulativeFitness[0] = fitness[0];
		for (int i = 1; i < populationSize; i++) {
            cumulativeFitness[i] = cumulativeFitness[i - 1] + fitness[i];
        }
		matingPool = new Root[matingPoolSize];
		for (int i = 0; i < matingPoolSize; i++) {
            double randomFitness = generator.nextDouble() * cumulativeFitness[populationSize - 1];
            int index = Arrays.binarySearch(cumulativeFitness, randomFitness);
            if (index < 0) {
                index = Math.abs(index + 1);
            }
            matingPool[i] = roots[index].copy();
        }
		reproductionPool = new Root[reproductionPoolSize];
		for (int i = 0; i < reproductionPoolSize; i++) {
            double randomFitness = generator.nextDouble() * cumulativeFitness[populationSize - 1];
            int index = Arrays.binarySearch(cumulativeFitness, randomFitness);
            if (index < 0) {
                index = Math.abs(index + 1);
            }
            reproductionPool[i] = roots[index];
        }
		mutationPool = new Root[mutationPoolSize];
		for (int i = 0; i < mutationPoolSize; i++) {
            double randomFitness = generator.nextDouble() * cumulativeFitness[populationSize - 1];
            int index = Arrays.binarySearch(cumulativeFitness, randomFitness);
            if (index < 0) {
                index = Math.abs(index + 1);
            }
            mutationPool[i] = roots[index];
        }
		populateRoots(generator);
		gen++;
	}

	private void populateRoots(Random generator) {
		Root[] temp = new Root[populationSize];
		for(int i = 0; (i*2) + 1 < matingPoolSize; i++) {
			temp[i] = crossover(matingPool[i*2], matingPool[(i*2) + 1], generator);
		}
		for(int i = 0; i < reproductionPoolSize; i++) {
			temp[i + (matingPoolSize/2)] = reproductionPool[i];
		}
		for(int i = 0; i < mutationPoolSize; i++) {
			temp[i + reproductionPoolSize + (matingPoolSize/2)] = mutate(mutationPool[i], generator);
		}
	}
	
	private Root mutate(Root root1, Random generator) {
		int index = countMutability(root1.getRoot());
		index = generator.nextInt(index);
		Root newRoot = root1.copy();
		newRoot.setRoot(mutateRoot(root1.getRoot(), generator, index));
		return newRoot;
	}

	private Functions mutateRoot(Functions function, Random generator, int index) {
		if(index == 0) {
			return createRoot(generator).getRoot();
		}
		if(index - countMutability(function.getLeft()) <= 0) {
			function.setLeft(mutateRoot(function.getLeft(), generator, index - 1));
			return function;
		}
		else {
			function.setRight(mutateRoot(function.getRight(), generator, index - 1 - countMutability(function.getLeft())));
			return function;
		}
	}

	private int countMutability(Functions function) {
		if(function.isFunction()) {
			return 1 + countMutability(function.getLeft()) + countMutability(function.getRight());
		}
		return 1;
	}

	private Root crossover(Root root1, Root root2, Random generator) {
		int index = countSimilitudes(root1.getRoot(), root2.getRoot());
		Root temp = root1.copy();
		if(index > 1) {
			index = generator.nextInt(index - 1);
			replace(temp.getRoot(), root2.getRoot(), index);
		}
		return temp;
	}

	private void replace(Functions function1, Functions function2, int index) {
		if(index ==  0) {
			function1.setLeft(function2.getLeft());
		}
		else if(index - countSimilitudes(function1.getLeft(), function2.getLeft()) < 0) {
			replace(function1.getLeft(), function2.getLeft(), index - 1);
		}
		else if(index - countSimilitudes(function1.getLeft(), function2.getLeft()) == 0) {
			function1.setRight(function2.getRight());
		}
		else {
			replace(function1.getRight(), function2.getRight(), index - 1 - countSimilitudes(function1.getLeft(), function2.getLeft()));
		}
	}

	private int countSimilitudes(Functions function1, Functions function2) {
		if(function1.isFunction() && function2.isFunction()) {
			return 1 + countSimilitudes(function1.getLeft(), function2.getLeft()) + countSimilitudes(function1.getRight(), function2.getRight());
		}
		return 1;
	}

	private Root createRoot(Random generator) {
		Root newRoot = new Root();
		if(generator.nextBoolean()) {
			newRoot.setRoot(createTerminal(generator));
		}
		else {
			int temp = generator.nextInt(8);
			if(temp == 0) {
				AdditionFunction newFunction = new AdditionFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 1) {
				CosineFunction newFunction = new CosineFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 2) {
				DivisionFunction newFunction = new DivisionFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 3) {
				IfFunction newFunction = new IfFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newFunction.setCondition(getNewCondition(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 4) {
				MaxFunction newFunction = new MaxFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 5) {
				MinFunction newFunction = new MinFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else if(temp == 6) {
				MultiplicationFunction newFunction = new MultiplicationFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
			else {
				SubstractionFunction newFunction = new SubstractionFunction();
				newFunction.setLeft(createTerminal(generator));
				newFunction.setRight(createTerminal(generator));
				newRoot.setRoot(newFunction);
			}
		}
		return newRoot;
	}
	
	private Condition getNewCondition(Random generator) {
		HigherThan newCondition = new HigherThan();
		newCondition.setVar1(generator.nextInt(numberOfBits));
		newCondition.setVar2(generator.nextInt(numberOfBits));
		return newCondition;
	}

	private Functions createTerminal(Random generator) {
		if(generator.nextBoolean()) {
			return new ConstantTerminal((generator.nextDouble() - 0.5)*20);
		}
		else {
			return new VariableTerminal(generator.nextInt(numberOfBits));
		}
	}
	

	public void getWinner() {
		System.out.println("Tree: " + roots[0].print());
		System.out.println("Generation number: " + gen);
	}

	public double currentBest() {
		return fitness[0];
	}
}

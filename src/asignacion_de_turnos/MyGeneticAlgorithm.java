package asignacion_de_turnos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;
import aima.core.util.CancelableThread;

public class MyGeneticAlgorithm<A> extends GeneticAlgorithm<A> {
	protected double crossoverProbability;
	protected boolean twoChildren;
	protected boolean destructive;
	protected boolean twoCrossPoint;
	protected boolean torneo;

	public MyGeneticAlgorithm(int individualLength, Collection<A> finiteAlphabet, double mutationProbability,
			double crossoverProbability, boolean children, boolean destructive, boolean twoCrossPoint, boolean torneo) {
		super(individualLength, finiteAlphabet, mutationProbability);
		this.crossoverProbability = crossoverProbability;
		this.twoChildren = children;
		this.destructive = destructive;
		this.twoCrossPoint = twoCrossPoint;
		this.torneo = torneo;
	}

	@Override
	public Individual<A> geneticAlgorithm(Collection<Individual<A>> initPopulation, FitnessFunction<A> fitnessFn,
			GoalTest goalTest, long maxTimeMilliseconds) {
		Individual<A> bestIndividual = null;

		// Create a local copy of the population to work with
		List<Individual<A>> population = new ArrayList<Individual<A>>(initPopulation);
		// Validate the population and setup the instrumentation
		validatePopulation(population);
		updateMetrics(population, 0, 0L);

		long startTime = System.currentTimeMillis();

		// repeat
		int itCount = 0;
		do {
			population = nextGeneration(population, fitnessFn);
			bestIndividual = retrieveBestIndividual(population, fitnessFn);

			updateMetrics(population, ++itCount, System.currentTimeMillis() - startTime);

			// until some individual is fit enough, or enough time has elapsed
			if (maxTimeMilliseconds > 0L && (System.currentTimeMillis() - startTime) > maxTimeMilliseconds)
				break;
			if (CancelableThread.currIsCanceled())
				break;
		} while (!goalTest.isGoalState(bestIndividual));

		notifyProgressTracers(itCount, population);
		// return the best individual in population, according to FITNESS-FN
		return bestIndividual;
	}

	@Override
	protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		// new_population <- empty set
		List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());
		// for i = 1 to SIZE(population) do

		for (int i = 0; i < population.size(); i++) {
			Individual<A> x;
			Individual<A> y;

			if (torneo) {
				x = seleccionPorTorneo(population, fitnessFn);
				y = seleccionPorTorneo(population, fitnessFn);
			} else {
				// x <- RANDOM-SELECTION(population, FITNESS-FN)
				x = randomSelection(population, fitnessFn);
				// y <- RANDOM-SELECTION(population, FITNESS-FN)
				y = randomSelection(population, fitnessFn);
			}
			// child <- REPRODUCE(x, y)
			List<Individual<A>> children = new ArrayList<Individual<A>>(2);
			if (random.nextDouble() < crossoverProbability) {
				if (twoChildren) {
					if (!twoCrossPoint)
						children = reproduceTwoKiddos(x, y);
					else
						children = reproduceTwoKiddos2Puntos(x, y);
					// if (small random probability) then child <- MUTATE(child)
					if (random.nextDouble() <= mutationProbability) {
						mutacionIntercambio(children.get(0));
						mutacionIntercambio(children.get(1));
					}
					if (destructive)
						newPopulation.addAll(children);
					else { // non-destructing
						if (fitnessFn.apply(children.get(0))
								+ fitnessFn.apply(children.get(1)) > (fitnessFn.apply(x) + fitnessFn.apply(y))) {
							newPopulation.addAll(children);
						} else {

							newPopulation.add(x);
							newPopulation.add(y);
						}
					}
				} else
					newPopulation.add(reproduce(x, y));
			} else {
				newPopulation.add(x);
				newPopulation.add(y);
			}

		}

		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}

	// function REPRODUCE(x, y) returns two individuals
	// inputs: x, y, parent individuals
	protected List<Individual<A>> reproduceTwoKiddos(Individual<A> x, Individual<A> y) {
		// n <- LENGTH(x);
		// Note: this is = this.individualLength
		// c <- random number from 1 to n
		int c = randomOffset(individualLength);
		// return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c+1, n))
		List<A> childRepresentation = new ArrayList<A>();
		childRepresentation.addAll(x.getRepresentation().subList(0, c));
		childRepresentation.addAll(y.getRepresentation().subList(c, individualLength));
		List<A> childRepresentation2 = new ArrayList<A>();
		childRepresentation2.addAll(y.getRepresentation().subList(0, c));
		childRepresentation2.addAll(x.getRepresentation().subList(c, individualLength));

		Individual<A> child = new Individual<A>(childRepresentation);
		Individual<A> child2 = new Individual<A>(childRepresentation);
		List<Individual<A>> children = new ArrayList<Individual<A>>(2);
		children.add(child);
		children.add(child2);
		return children;
	}

	protected List<Individual<A>> reproduceTwoKiddos2Puntos(Individual<A> x, Individual<A> y) {
		// n <- LENGTH(x);
		// Note: this is = this.individualLength
		// c <- random number from 1 to n
		int c = randomOffset(individualLength);
		int d = randomOffset(individualLength);
		int max = Math.max(c, d);
		int min = Math.min(d, c);

		// return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c+1, n))
		List<A> childRepresentation = new ArrayList<A>();
		childRepresentation.addAll(x.getRepresentation().subList(0, min));
		childRepresentation.addAll(y.getRepresentation().subList(min, max));
		childRepresentation.addAll(x.getRepresentation().subList(max, individualLength));
		List<A> childRepresentation2 = new ArrayList<A>();
		childRepresentation2.addAll(y.getRepresentation().subList(0, min));
		childRepresentation2.addAll(x.getRepresentation().subList(min, max));
		childRepresentation2.addAll(y.getRepresentation().subList(max, individualLength));

		Individual<A> child = new Individual<A>(childRepresentation);
		Individual<A> child2 = new Individual<A>(childRepresentation);
		List<Individual<A>> children = new ArrayList<Individual<A>>(2);
		children.add(child);
		children.add(child2);
		return children;
	}

	protected Individual<A> mutate(Individual<A> child) {
		int mutateOffset = randomOffset(individualLength);
		int alphaOffset = randomOffset(finiteAlphabet.size());

		List<A> mutatedRepresentation = new ArrayList<A>(child.getRepresentation());

		mutatedRepresentation.set(mutateOffset, finiteAlphabet.get(alphaOffset));

		Individual<A> mutatedChild = new Individual<A>(mutatedRepresentation);

		return mutatedChild;
	}

	protected Individual<A> seleccionPorTorneo(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		Individual<A> selectedA = population.get(random.nextInt(population.size()));
		Individual<A> selectedB = population.get(random.nextInt(population.size()));
		double fitnessA = fitnessFn.apply(selectedA);
		double fitnessB = fitnessFn.apply(selectedB);
		return fitnessA > fitnessB ? selectedA : selectedB;
	}

	protected Individual<A> mutacionIntercambio(Individual<A> child) {
		int mutateOffset = randomOffset(individualLength-1);
		List<A> mutatedRepresentation = new ArrayList<A>(child.getRepresentation());

		mutatedRepresentation.set(mutateOffset+1, child.getRepresentation().get(mutateOffset));
		mutatedRepresentation.set(mutateOffset, child.getRepresentation().get(mutateOffset+1));

		Individual<A> mutatedChild = new Individual<A>(mutatedRepresentation);

		return mutatedChild;
	}

	protected int randomOffset(int length) {
		return random.nextInt(length);
	}
}

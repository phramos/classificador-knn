package utils;

import model.Flower;
import model.Neighbor;
import model.Prediction;

import java.util.*;

/**
 * Created by pedro on 16/02/16.
 */
public class KnnClassifier {

	private HashSet<Flower> trainingSet;
	private HashSet<Flower> testSet;
	private Integer trainingSetPercentage;
	private HashSet<Prediction> predictions;
	private Double accuracy;
	private Integer amountOfNeighbors;

	public KnnClassifier(HashSet<Flower> dataSet, Integer trainingSetPercentage) {

		this.trainingSet = new HashSet<Flower>();
		this.testSet = new HashSet<Flower>();
		this.predictions = new HashSet<Prediction>();
		this.trainingSetPercentage = trainingSetPercentage;
		this.amountOfNeighbors = 3;

		splitData(dataSet, trainingSetPercentage);
		classifyTestSet();
//		for (Flower flower : dataSet) {
//			if (getRandomInt() < trainingSetPercentage)
//				this.trainingSet.add(flower);
//			else
//				this.testSet.add(flower);
//		}
	}

	public KnnClassifier(HashSet<Flower> dataSet, Integer trainingSetPercentage, Integer amountOfNeighbors) {

		this.trainingSet = new HashSet<Flower>();
		this.testSet = new HashSet<Flower>();
		this.predictions = new HashSet<Prediction>();
		this.trainingSetPercentage = trainingSetPercentage;
		this.amountOfNeighbors = amountOfNeighbors;

		splitData(dataSet, trainingSetPercentage);
		classifyTestSet();

	}

	private void classifyTestSet() {
		generatePredictions();
		calculateAccuracy();
	}

	public HashSet<Flower> getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(HashSet<Flower> trainingSet) {
		this.trainingSet = trainingSet;
	}

	public HashSet<Flower> getTestSet() {
		return testSet;
	}

	public void setTestSet(HashSet<Flower> testSet) {
		this.testSet = testSet;
	}

	public Integer getTrainingSetPercentage() {
		return trainingSetPercentage;
	}

	public void setTrainingSetPercentage(Integer trainingSetPercentage) {
		this.trainingSetPercentage = trainingSetPercentage;
	}

	public HashSet<Prediction> getPredictions() {
		return predictions;
	}

	public void setPredictions(HashSet<Prediction> predictions) {
		this.predictions = predictions;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	public Integer getAmountOfNeighbors() {
		return amountOfNeighbors;
	}

	public void setAmountOfNeighbors(Integer amountOfNeighbors) {
		this.amountOfNeighbors = amountOfNeighbors;
	}

	public static Integer getRandomInt(Integer maxValue) {
		return (int)(Math.random()*maxValue);
	}

	private Integer getRandomInt() {
		return (int)(Math.random()*100);
	}

	private void splitData(HashSet<Flower> dataSet, Integer trainingSetPercentage) {

		Integer maxItemsTraning;
		Integer maxItemsTest;

		boolean forTraining;

		maxItemsTraning = (int)(dataSet.size() * (trainingSetPercentage/100.0));
		maxItemsTest = dataSet.size() - maxItemsTraning;


		for (Flower flower : dataSet) {

			forTraining = getRandomInt() < 50 ? true : false;

			if (this.trainingSet.size() < maxItemsTraning && forTraining ) {

				this.trainingSet.add(flower);

			} else {
				if (this.testSet.size() < maxItemsTest)
					this.testSet.add(flower);
				else
					this.trainingSet.add(flower);

			}
		}

	}

	private Double euclideanDistance(Flower flower1, Flower flower2){

		Double distance = 0.0;

		distance += Math.pow(flower1.getPetalLenght() - flower2.getPetalLenght(), 2);
		distance += Math.pow(flower1.getPetalWidth() - flower2.getPetalWidth(), 2);
		distance += Math.pow(flower1.getSepalLenght() - flower2.getSepalLenght(), 2);
		distance += Math.pow(flower1.getSepalWidth() - flower2.getSepalWidth(), 2);

		return Math.sqrt(distance);

	}

	public HashSet<Neighbor> getNearestNeighbors(Flower testInstance, Integer numberOfNeighbors) {

		ArrayList<Neighbor> neighbors = new ArrayList<Neighbor>();
		HashSet<Neighbor> nearestNeighbors = new HashSet<>();

		for (Flower trainingInstance: this.trainingSet) {

			Double distance = euclideanDistance(testInstance, trainingInstance);

			neighbors.add(new Neighbor(trainingInstance, distance));

		}

		Collections.sort(neighbors);

		for (int i = 0 ; i < numberOfNeighbors ; ++i) {
			nearestNeighbors.add(neighbors.get(i));
		}
		return nearestNeighbors;
	}

	public Prediction getPrediction(Flower testInstance, HashSet<Neighbor> nearestNeighbors) {

		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		Iterator iterator = nearestNeighbors.iterator();

		//Compute the vote of each neighbor
		while (iterator.hasNext())  {
			Neighbor neighbor = (Neighbor) iterator.next();
			if (votes.containsKey(neighbor.getFlower().getType()))
				votes.put(neighbor.getFlower().getType(), votes.get(neighbor.getFlower().getType()) + 1 );
			else
				votes.put(neighbor.getFlower().getType(), 1);
		}


		String mostVoted = "";
		Integer totalVotes = 0;
		//Get the most voted
		for (Map.Entry<String, Integer> entry : votes.entrySet()){

			if (entry.getValue() > totalVotes) {
				mostVoted = entry.getKey();
				totalVotes = entry.getValue();
			}

		}

		Double votesPercentage = (100.0 / nearestNeighbors.size()) * totalVotes;
		return new Prediction(testInstance.getType(), mostVoted, votesPercentage);

	}

	//Generate all the predictions for the testSet based on the k nearest neighbors in the trainingSet
	private void generatePredictions() {

		for(Flower testInstance : this.testSet) {

			HashSet<Neighbor> nn = getNearestNeighbors(testInstance, this.amountOfNeighbors);
			Prediction prediction = getPrediction(testInstance, nn);

			this.predictions.add(prediction);

		}

	}

	//Calculate the classification's accuracy
	private void calculateAccuracy() {

		Integer correctPredictions = 0;

		for(Prediction prediction : this.predictions)
			if (prediction.isCorrect())
				correctPredictions++;

		this.accuracy = 100.0 / this.predictions.size() * correctPredictions;

	}

	public void printResult() {

		System.out.println("____________________KNN DATA____________________");
		System.out.println("Test set size: " + this.testSet.size());
		System.out.println("Training set size: " + this.trainingSet.size());

		System.out.println("____________________PREDICTION____________________");
		System.out.println("Amount of nearest neighbors used to predict: " + this.amountOfNeighbors);

		for (Prediction prediction : this.predictions) {
			System.out.println(prediction);
		}

		System.out.println("Accuracy: " + this.accuracy);

	}

	public Object[][] getPedrictionData() {

		Object data[][] = new Object[this.predictions.size()][3];
		int i = 0;

		for (Prediction prediction : predictions) {

			data[i][0] = prediction.getCorrectValue();
			data[i][1] = prediction.getPrecitedValue();
			data[i][2] = (prediction.isCorrect() ? "CORRECT" : "INCORRECT");
//			data[0][i] = prediction.getCorrectValue();
//			data[1][i] = prediction.getPrecitedValue();
//			data[2][i] = (prediction.isCorrect() ? "CORRECT" : "INCORRECT");

			++i;
		}

		return data;
	}
}

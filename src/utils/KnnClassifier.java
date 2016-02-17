package utils;

import model.Flower;
import model.Neighbor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by pedro on 16/02/16.
 */
public class KnnClassifier {

	private HashSet<Flower> trainingSet;
	private HashSet<Flower> testSet;
	private Integer trainingSetPercentage;

	public KnnClassifier(HashSet<Flower> dataSet, Integer trainingSetPercentage) {

		this.trainingSet = new HashSet<Flower>();
		this.testSet = new HashSet<Flower>();
		this.trainingSetPercentage = trainingSetPercentage;
		splitData(dataSet, trainingSetPercentage);

//		for (Flower flower : dataSet) {
//			if (getRandomInt() < trainingSetPercentage)
//				this.trainingSet.add(flower);
//			else
//				this.testSet.add(flower);
//		}

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

	public ArrayList<Neighbor> getNearestNeighbors(Flower testInstance, Integer numberOfNeighbors) {

		ArrayList<Neighbor> neighbors = new ArrayList<Neighbor>();

		for (Flower trainingInstance: this.trainingSet) {

			Double distance = euclideanDistance(testInstance, trainingInstance);

			neighbors.add(new Neighbor(trainingInstance, distance));

		}



		return null;
	}

//	public static Double euclideanDistance(Flower flower1, Flower flower2){
//
//		Double distance = 0.0;
//
//		distance += Math.pow(flower1.getPetalLenght() - flower2.getPetalLenght(), 2);
//		distance += Math.pow(flower1.getPetalWidth() - flower2.getPetalWidth(), 2);
//		distance += Math.pow(flower1.getSepalLenght() - flower2.getSepalLenght(), 2);
//		distance += Math.pow(flower1.getSepalWidth() - flower2.getSepalWidth(), 2);
//
//		return Math.sqrt(distance);
//
//	}
}

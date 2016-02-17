package model;

/**
 * Created by pedro on 16/02/16.
 */
public class Neighbor implements Comparable<Neighbor> {
	private Flower flower;
	private Double distance;

	public Neighbor(Flower flower, Double distance) {
		this.flower = flower;
		this.distance = distance;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Neighbor o) {
		if (this.distance == o.distance)
			return 0;

		if (this.distance > o.distance)
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return this.distance.toString();
	}
}

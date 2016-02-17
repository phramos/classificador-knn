package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by fauno on 1/31/16.
 */

public class Flower {

    private Double sepalLenght;
    private Double sepalWidth;
    private Double petalLenght;
    private Double petalWidth;
    private String type;

    public Flower(Double sepalLenght, Double sepalWidth, Double petalLenght, Double petalWidth, String type) {
        this.sepalLenght = sepalLenght;
        this.sepalWidth = sepalWidth;
        this.petalLenght = petalLenght;
        this.petalWidth = petalWidth;
        this.type = type;
    }

    public Flower(String sepalLenght, String sepalWidth, String petalLenght, String petalWidth, String type) {
        this.sepalLenght = Double.parseDouble(sepalLenght);
        this.sepalWidth = Double.parseDouble(sepalWidth);
        this.petalLenght = Double.parseDouble(petalLenght);
        this.petalWidth = Double.parseDouble(petalWidth);
        this.type = type;
    }

    public Double getSepalLenght() {
        return sepalLenght;
    }

    public void setSepalLenght(Double sepalLenght) {
        this.sepalLenght = sepalLenght;
    }

    public Double getSepalWidth() {
        return sepalWidth;
    }

    public void setSepalWidth(Double sepalWidth) {
        this.sepalWidth = sepalWidth;
    }

    public Double getPetalLenght() {
        return petalLenght;
    }

    public void setPetalLenght(Double petalLenght) {
        this.petalLenght = petalLenght;
    }

    public Double getPetalWidth() {
        return petalWidth;
    }

    public void setPetalWidth(Double petalWidth) {
        this.petalWidth = petalWidth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String result = "\nsepalLenght " + sepalLenght +
                        "\nsepalWidth" + sepalWidth +
                        "\npetalLenght" + petalLenght +
                        "\npetalWidth" + petalWidth +
                        "\ntype" + type ;

        return result;
    }

    public static HashSet<Flower> readListFromCsv(String filePath){
        String csvFile = filePath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
//        ArrayList<Flower> flowers =  new ArrayList<Flower>();
        HashSet<Flower> flowers = new HashSet<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] flower = line.split(cvsSplitBy);
                if (flower[0].isEmpty() || flower[1].isEmpty() || flower[2].isEmpty() || flower[3].isEmpty() || flower[4].isEmpty()) {
                    System.out.println("Vazio");
                }
                flowers.add(new Flower(flower[0], flower[1], flower[2], flower[3], flower[4]));


                //    System.out.println(flower);
            }
//            System.out.println(flowers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return flowers;
    }
}

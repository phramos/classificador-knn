import model.Flower;
import utils.KnnClassifier;
import view.KnnClassifierGUI;

import java.util.HashSet;

/**
 * Created by fauno on 2/17/16.
 */
public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        KnnClassifierGUI gui = new KnnClassifierGUI();

        //Display the window.
       // gui.pack();
        gui.setVisible(true);
    }

    public static void main(String args[]) {
        HashSet<Flower> flowers = Flower.readListFromCsv("arquivos/iris.data");
        KnnClassifier knn = new KnnClassifier(flowers,67);
        knn.printResult();

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });

    }

}

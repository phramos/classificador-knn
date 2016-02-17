import model.Flower;
import utils.KnnClassifier;
import view.KnnClassifierGUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Created by fauno on 2/17/16.
 */
public class Main {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("K Nearest Neighbors classifier");
        JMenuBar menuBar = new JMenuBar();

        /*
        * Criação do Menu
        * */
        //Set the menubar
        frame.setJMenuBar(menuBar);

        // Define e adiciona dois menus drop down na barra de menus
        JMenu optionsMenu = new JMenu("Options");
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(optionsMenu);
        menuBar.add(aboutMenu);

        // Cria e adiciona um item simples para o menu
        JMenuItem chooseData = new JMenuItem("Choose Data File");
        JMenuItem openAction = new JMenuItem("Open");

        // Cria e aiciona um CheckButton como um item de menu
        JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");

        // Cria e adiciona um RadioButton como um item de menu
        JRadioButtonMenuItem radioActionEuclidean = new JRadioButtonMenuItem(
                "Euclidean Distance");
        radioActionEuclidean.setSelected(true);
        JRadioButtonMenuItem radioButtonManhattan = new JRadioButtonMenuItem(
                "Manhattan Distance");

        // Cria um ButtonGroup e adiciona os dois radio Button
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioActionEuclidean);
        bg.add(radioButtonManhattan);

        optionsMenu.add(chooseData);
        optionsMenu.addSeparator();
        optionsMenu.add(radioActionEuclidean);
        optionsMenu.add(radioButtonManhattan);

        /*
        *Prediction table configuration
         */
        HashSet<Flower> flowers = Flower.readListFromCsv("arquivos/iris.data");
        KnnClassifier knn = new KnnClassifier(flowers,67);

        String[] columns = {"Value", "Prediction", "Correct"};

        JTable table = new JTable(knn.getPedrictionData(), columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        /*
        * Labels Configuration
        * */
        JLabel labelHeading = new JLabel("Predictions");
        labelHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
        labelHeading.setHorizontalAlignment(JLabel.CENTER);


        /*
        * South panel configuration
        * */
        String accuracy =  "Accuracy: " + knn.getAccuracy() + "%";
        JLabel labelAccuracy = new JLabel(accuracy);

        String trainingSize =  "Training set size: " + knn.getTrainingSet().size();
        JLabel labelTraining = new JLabel(trainingSize);

        String testSize =  "Test set size: " + knn.getTestSet().size();
        JLabel labelTest = new JLabel(testSize);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridLayout(1,3));
        panelSouth.add(labelAccuracy);
        panelSouth.add(labelTest);
        panelSouth.add(labelTraining);

        /*
        * Frame configuration
        * */
        frame.setSize(800, 600);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().add(labelHeading, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);




        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    public static void main(String args[]) {
//        HashSet<Flower> flowers = Flower.readListFromCsv("arquivos/iris.data");
//        KnnClassifier knn = new KnnClassifier(flowers,67);
//        knn.printResult();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

}

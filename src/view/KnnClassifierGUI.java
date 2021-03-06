package view;

import model.Flower;
import model.ui.PredictionTableModel;
import utils.KnnClassifier;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * Created by fauno on 2/17/16.
 */
public class KnnClassifierGUI extends JFrame{

    JMenuBar menuBar;
    JTable predictionTable;
    JScrollPane scrollPane;
    JFrame mainContent;

    public KnnClassifierGUI(){


        buildMenu();
        setTitle("K Nearest Neighbors Classifier");
        setSize(800, 600);

        //Open in the Center of The Screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

     //   setLayout(new GridLayout(1, 0));

        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


        setPredictionTable();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void buildMenu() {

        // Cria uma barra de menu para o JFrame
        this.menuBar = new JMenuBar();

        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);

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
        // Cria e aiciona um RadioButton como um item de menu
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
        optionsMenu.addSeparator();





    }

    private void setPredictionTable() {

                HashSet<Flower> flowers = Flower.readListFromCsv("arquivos/iris.data");
                KnnClassifier knn = new KnnClassifier(flowers, 90);
                //knn.printResult();
                //  String[] columnNames = {"Real Value", "Predicted Value", "Prediction"};
                predictionTable = new JTable(new PredictionTableModel(knn.getPedrictionData()));
                predictionTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
                predictionTable.setFillsViewportHeight(true);
                predictionTable.add(scrollPane);
        //mainContent = new JScrollPane(predictionTable);
//                add(mainContent);


    }

}

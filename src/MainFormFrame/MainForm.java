package MainFormFrame;

import UI.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainForm extends javax.swing.JFrame implements ActionListener {

    //<editor-fold defaultstate="collapsed" desc="Var">
    int totalX = 20;
    int totalY = 18;
    JTextField[][] board = new JTextField[totalX][totalY];

    int currentIndex = 0;
    int numberOfEntries = 0;
    boolean isNew = false;
    SpringLayout layout = new SpringLayout();

    JLabel lblTitle, lblLegend, lblGasTitle, lblLocation, lblDate, lblTime, lblRecordLevel;

    JTextField txtRecordLevel;

    JButton btnClose, btnLoadFile, btnSulphurDioxide, btnNitrogenDioxide, btnCarbonMonoxide, btnObstructions, btnExport, btnDangerous, btnConcerning, btnAcceptable, btnExportRPT;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UI">
    public MainForm() throws IOException {
        setSize(900, 600);
        setLocation(300, 200);

        getContentPane().setBackground(new Color(54, 54, 54));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });

        setLayout(layout);

        BuildGameBoardButtons();

        lblTitle = JFrame.BuildJLabel("Ian's Industrial", 8, 6, 300, 50, layout, this);
        lblTitle.setForeground(Color.YELLOW);
        lblTitle.setFont(new Font("Comic Sans", Font.BOLD, 25));

        lblGasTitle = JFrame.BuildJLabel("Gas Title", 19, 80, 180, 30, layout, this);
        lblGasTitle.setFont(new Font("Comic Sans", Font.BOLD, 15));
        lblGasTitle.setForeground(Color.YELLOW);

        lblLocation = JFrame.BuildJLabel("", 260, 13, 300, 30, layout, this);
        lblLocation.setFont(new Font("Comic Sans", Font.BOLD, 14));
        lblLocation.setForeground(Color.YELLOW);

        lblDate = JFrame.BuildJLabel("", 568, 13, 190, 30, layout, this);
        lblDate.setFont(new Font("Comic Sans", Font.BOLD, 14));
        lblDate.setForeground(Color.YELLOW);

        lblTime = JFrame.BuildJLabel("", 700, 13, 130, 30, layout, this);
        lblTime.setFont(new Font("Comic Sans", Font.BOLD, 14));
        lblTime.setForeground(Color.YELLOW);

        lblLegend = JFrame.BuildJLabel("", 22, 155, 120, 36, layout, this);
        lblLegend.setFont(new Font("Comic Sans", Font.BOLD, 18));
        lblLegend.setForeground(Color.YELLOW);

        lblRecordLevel = JFrame.BuildJLabel("Record Level:", 190, 515, 110, 36, layout, this);
        lblRecordLevel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        lblRecordLevel.setForeground(Color.YELLOW);

        txtRecordLevel = JFrame.BuildJTextField("", this, 70, 20, 525, 295, layout, this);

        btnDangerous = JFrame.BuildJButton("Dangerous", this, 137, 30, 190, 130, layout, this);
        btnDangerous.setForeground(Color.WHITE);
        btnDangerous.setFont(new Font("Comic Sans", Font.BOLD, 16));
        btnDangerous.setBackground(new Color(139, 0, 0));
        btnDangerous.setOpaque(true);

        btnConcerning = JFrame.BuildJButton("Concerning", this, 137, 30, 225, 130, layout, this);
        btnConcerning.setForeground(Color.WHITE);
        btnConcerning.setFont(new Font("Comic Sans", Font.BOLD, 16));
        btnConcerning.setBackground(new Color(234, 170, 0));
        btnConcerning.setOpaque(true);

        btnAcceptable = JFrame.BuildJButton("Acceptable", this, 137, 30, 260, 130, layout, this);
        btnAcceptable.setForeground(Color.WHITE);
        btnAcceptable.setFont(new Font("Comic Sans", Font.BOLD, 16));
        btnAcceptable.setBackground(new Color(0, 139, 0));
        btnAcceptable.setOpaque(true);

        btnSulphurDioxide = JFrame.BuildJButton("Sulphur Dioxide", this, 137, 25, 320, 130, layout, this);
        btnSulphurDioxide.setBackground(new Color(255, 0, 0));
        btnSulphurDioxide.setForeground(Color.YELLOW);

        btnNitrogenDioxide = JFrame.BuildJButton("Nitrogen Dioxide", this, 137, 25, 350, 130, layout, this);
        btnNitrogenDioxide.setBackground(new Color(255, 0, 0));
        btnNitrogenDioxide.setForeground(Color.YELLOW);

        btnCarbonMonoxide = JFrame.BuildJButton("Carbon Monoxide", this, 137, 25, 380, 130, layout, this);
        btnCarbonMonoxide.setBackground(new Color(255, 0, 0));
        btnCarbonMonoxide.setForeground(Color.YELLOW);

        btnObstructions = JFrame.BuildJButton("Obstructions", this, 137, 25, 410, 130, layout, this);
        btnObstructions.setBackground(new Color(255, 0, 0));
        btnObstructions.setForeground(Color.YELLOW);

        btnLoadFile = JFrame.BuildJButton("Load File", this, 137, 25, 510, 130, layout, this);
        btnLoadFile.setBackground(new Color(255, 0, 0));
        btnLoadFile.setForeground(Color.YELLOW);

        btnExport = JFrame.BuildJButton("Export RAF", this, 109, 30, 510, 730, layout, this);
        btnExport.setBackground(new Color(255, 0, 0));
        btnExport.setForeground(Color.YELLOW);

        btnExportRPT = JFrame.BuildJButton("Export DAT", this, 109, 30, 510, 600, layout, this);
        btnExportRPT.setBackground(new Color(255, 0, 0));
        btnExportRPT.setForeground(Color.YELLOW);

        btnClose = JFrame.BuildJButton("Close", this, 109, 30, 510, 850, layout, this);
        btnClose.setBackground(new Color(255, 0, 0));
        btnClose.setForeground(Color.YELLOW);

        setVisible(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Functions">
    public void BuildGameBoardButtons() {
        //Iterates through the entire 2D Board array
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                //Calculated the X and Y coordinates of the current array element based upon the current iteration numbers.
                //Formula for calculation is: {Current Iteration} * {SpaceBetweenElements} + {Buffer From Edge}
                int xPos = x * 20 + 60;
                int yPos = y * 29 + 200;

                //Builds button at provided screen position
                board[x][y] = JFrame.BuildJTextField("", this, 30, 20, xPos, yPos, layout, this);

                //Adds listener to button to handle colour changes when pressed.
                board[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        //Stores the pressed button so it can be referenced
                        JButton source = (JButton) actionEvent.getSource();

                        //If colour of current button is not still on white(already used), returns out of method.
                        if (source.getBackground() != Color.white) {
                        }
                    }
                });
            }
        }
    }

    public JButton BuildAButton(int xPos, int yPos) {

        JButton myButton = new JButton();

        myButton.setPreferredSize(new Dimension(30, 20));
        layout.putConstraint(SpringLayout.NORTH, myButton, yPos, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, myButton, xPos, SpringLayout.WEST, this);

        myButton.setBackground(Color.WHITE);

        add(myButton);
        return myButton;
    }

    public static void InfoBox(String InfoMessage, String TitleBar) {
        JOptionPane.showMessageDialog(null, InfoMessage, "Warning!" + TitleBar, JOptionPane.WARNING_MESSAGE);
    }

    public void MouseEntered(MouseEvent evt) {

        int y;
        int x;
        for (x = 0; x < board.length; x++) {
            for (y = 0; y < board[x].length; y++) {

                String text_value = board[x][y].getText();
                board[y][x].addMouseListener(new MouseAdapter() {

                    boolean yes;

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        //if (mouseClicked(){

                        //txtRecordLevel.setText(text_value);
                        //}
                    }
                });
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Buttons">
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnClose) {
            System.exit(0);
        }

        if (e.getSource() == btnNitrogenDioxide) {

            lblGasTitle.setText("Nitrogen Dioxide NO2");

            String null_value1 = "0";
            String null_value2 = "1";

            String acceptable2 = "2";
            String acceptable3 = "3";
            String acceptable4 = "4";
            String acceptable5 = "5";
            String acceptable6 = "6";
            String acceptable7 = "7";
            String acceptable8 = "8";

            String concernig9 = "9";
            String concernig10 = "10";
            String concernig11 = "11";
            String concernig12 = "12";
            String concernig13 = "13";
            String concernig14 = "14";
            String concernig15 = "15";
            String concernig16 = "16";
            String concernig17 = "17";
            String concernig18 = "18";
            String concernig19 = "19";

            String dangerous = "20";


            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {

                    String text_value = board[x][y].getText();
                    boolean n1 = text_value.equals(null_value1);
                    boolean n2 = text_value.equals(null_value2);

                    boolean a2 = text_value.equals(acceptable2);
                    boolean a3 = text_value.equals(acceptable3);
                    boolean a4 = text_value.equals(acceptable4);
                    boolean a5 = text_value.equals(acceptable5);
                    boolean a6 = text_value.equals(acceptable6);
                    boolean a7 = text_value.equals(acceptable7);
                    boolean a8 = text_value.equals(acceptable8);

                    boolean c9 = text_value.equals(concernig9);
                    boolean c10 = text_value.equals(concernig10);
                    boolean c11 = text_value.equals(concernig11);
                    boolean c12 = text_value.equals(concernig12);
                    boolean c13 = text_value.equals(concernig13);
                    boolean c14 = text_value.equals(concernig14);
                    boolean c15 = text_value.equals(concernig15);
                    boolean c16 = text_value.equals(concernig16);
                    boolean c17 = text_value.equals(concernig17);
                    boolean c18 = text_value.equals(concernig18);
                    boolean c19 = text_value.equals(concernig19);

                    boolean d = text_value.equals(dangerous);

                    if (n1 | n2) {
                        board[x][y].setBackground(Color.DARK_GRAY);
                        board[x][y].setForeground(Color.DARK_GRAY);
                    }
                    if (a2 | a3 | a4 | a5 | a6 | a7 | a8) {
                        board[x][y].setBackground(Color.green);
                        board[x][y].setForeground(Color.green);
                    }
                    if (c9 | c10 | c11 | c12 | c13 | c14 | c15 | c16 | c17 | c18 | c19) {
                        board[x][y].setBackground(Color.yellow);
                        board[x][y].setForeground(Color.yellow);
                    }
                    if (d) {
                        board[x][y].setBackground(Color.red);
                        board[x][y].setForeground(Color.red);
                    } else {
                        //board[x][y].setBackground(Color.red);
                        //board[x][y].setForeground(Color.red);
                    }
                }
            }
        }

        if (e.getSource() == btnCarbonMonoxide) {

            lblGasTitle.setText("Carbon Monoxide CO");

            String null_value1 = "0";
            String null_value2 = "1";
            String null_value3 = "2";

            String acceptable3 = "3";
            String acceptable4 = "4";
            String acceptable5 = "5";
            String acceptable6 = "6";
            String acceptable7 = "7";
            String acceptable8 = "8";
            String acceptable9 = "9";
            String acceptable10 = "10";
            String acceptable11 = "11";

            String concernig12 = "12";
            String concernig13 = "13";
            String concernig14 = "14";
            String concernig15 = "15";
            String concernig16 = "16";
            String concernig17 = "17";
            String concernig18 = "18";
            String concernig19 = "19";
            String concernig20 = "20";
            String concernig21 = "21";

            String dangerous = "22";


            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {

                    String text_value = board[x][y].getText();
                    boolean n1 = text_value.equals(null_value1);
                    boolean n2 = text_value.equals(null_value2);
                    boolean n3 = text_value.equals(null_value3);

                    boolean a3 = text_value.equals(acceptable3);
                    boolean a4 = text_value.equals(acceptable4);
                    boolean a5 = text_value.equals(acceptable5);
                    boolean a6 = text_value.equals(acceptable6);
                    boolean a7 = text_value.equals(acceptable7);
                    boolean a8 = text_value.equals(acceptable8);
                    boolean a9 = text_value.equals(acceptable9);
                    boolean a10 = text_value.equals(acceptable10);
                    boolean a11 = text_value.equals(acceptable11);

                    boolean c12 = text_value.equals(concernig12);
                    boolean c13 = text_value.equals(concernig13);
                    boolean c14 = text_value.equals(concernig14);
                    boolean c15 = text_value.equals(concernig15);
                    boolean c16 = text_value.equals(concernig16);
                    boolean c17 = text_value.equals(concernig17);
                    boolean c18 = text_value.equals(concernig18);
                    boolean c19 = text_value.equals(concernig19);
                    boolean c20 = text_value.equals(concernig20);
                    boolean c21 = text_value.equals(concernig21);

                    boolean d = text_value.equals(dangerous);

                    if (n1 | n2 | n3) {
                        board[x][y].setBackground(Color.DARK_GRAY);
                        board[x][y].setForeground(Color.DARK_GRAY);
                    }
                    if (a3 | a4 | a5 | a6 | a7 | a8 | a9 | a10 | a11) {
                        board[x][y].setBackground(Color.green);
                        board[x][y].setForeground(Color.green);
                    }
                    if (c12 | c13 | c14 | c15 | c16 | c17 | c18 | c19 | c20 | c21) {
                        board[x][y].setBackground(Color.yellow);
                        board[x][y].setForeground(Color.yellow);
                    }
                    if (d) {
                        board[x][y].setBackground(Color.red);
                        board[x][y].setForeground(Color.red);
                    } else {
                        //board[x][y].setBackground(Color.red);
                        //board[x][y].setForeground(Color.red);
                    }
                }
            }
        }

        if (e.getSource() == btnSulphurDioxide) {

            lblGasTitle.setText("Sulphur Dioxide SO2");

            String null_value1 = "0";
            String null_value2 = "1";

            String acceptable3 = "3";
            String acceptable4 = "4";
            String acceptable5 = "5";
            String acceptable6 = "6";
            String acceptable7 = "7";
            String acceptable8 = "8";
            String acceptable9 = "9";

            String concerning10 = "10";
            String concerning11 = "11";
            String concernig12 = "12";
            String concernig13 = "13";
            String concernig14 = "14";
            String concernig15 = "15";
            String concernig16 = "16";
            String concernig17 = "17";
            String concernig18 = "18";
            String concernig19 = "19";

            String dangerous = "20";

            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {

                    String text_value = board[x][y].getText();
                    boolean n1 = text_value.equals(null_value1);
                    boolean n2 = text_value.equals(null_value2);

                    boolean a3 = text_value.equals(acceptable3);
                    boolean a4 = text_value.equals(acceptable4);
                    boolean a5 = text_value.equals(acceptable5);
                    boolean a6 = text_value.equals(acceptable6);
                    boolean a7 = text_value.equals(acceptable7);
                    boolean a8 = text_value.equals(acceptable8);
                    boolean a9 = text_value.equals(acceptable9);

                    boolean c10 = text_value.equals(concerning10);
                    boolean c11 = text_value.equals(concerning11);
                    boolean c12 = text_value.equals(concernig12);
                    boolean c13 = text_value.equals(concernig13);
                    boolean c14 = text_value.equals(concernig14);
                    boolean c15 = text_value.equals(concernig15);
                    boolean c16 = text_value.equals(concernig16);
                    boolean c17 = text_value.equals(concernig17);
                    boolean c18 = text_value.equals(concernig18);
                    boolean c19 = text_value.equals(concernig19);

                    boolean d = text_value.equals(dangerous);

                    if (n1 | n2) {
                        board[x][y].setBackground(Color.DARK_GRAY);
                        board[x][y].setForeground(Color.DARK_GRAY);
                    }
                    if (a3 | a4 | a5 | a6 | a7 | a8 | a9 | c10 | c11) {
                        board[x][y].setBackground(Color.green);
                        board[x][y].setForeground(Color.green);
                    }
                    if (c12 | c13 | c14 | c15 | c16 | c17 | c18 | c19) {
                        board[x][y].setBackground(Color.yellow);
                        board[x][y].setForeground(Color.yellow);
                    }
                    if (d) {
                        board[x][y].setBackground(Color.red);
                        board[x][y].setForeground(Color.red);
                    } else {
                        //board[x][y].setBackground(Color.red);
                        //board[x][y].setForeground(Color.red);
                    }
                }
            }
        }

        if (e.getSource() == btnObstructions) {

            lblGasTitle.setText("Obstructions");

            String null_value1 = "0";

            String acceptable1 = "1";

            String concerning2 = "2";

            String dangerous3 = "3";
            String dangerous4 = "4";
            String dangerous5 = "5";
            String dangerous6 = "6";
            String dangerous7 = "7";
            String dangerous8 = "8";
            String dangerous9 = "9";
            String dangerous10 = "10";
            String dangerous11 = "11";
            String dangerous12 = "12";
            String dangerous13 = "13";
            String dangerous14 = "14";
            String dangerous15 = "15";
            String dangerous16 = "16";
            String dangerous17 = "17";
            String dangerous18 = "18";
            String dangerous19 = "19";
            String dangerous20 = "20";


            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {

                    String text_value = board[x][y].getText();
                    boolean n1 = text_value.equals(null_value1);

                    boolean a1 = text_value.equals(acceptable1);

                    boolean c2 = text_value.equals(concerning2);

                    boolean d3 = text_value.equals(dangerous3);
                    boolean d4 = text_value.equals(dangerous4);
                    boolean d5 = text_value.equals(dangerous5);
                    boolean d6 = text_value.equals(dangerous6);
                    boolean d7 = text_value.equals(dangerous7);
                    boolean d8 = text_value.equals(dangerous8);
                    boolean d9 = text_value.equals(dangerous9);
                    boolean d10 = text_value.equals(dangerous10);
                    boolean d11 = text_value.equals(dangerous11);
                    boolean d12 = text_value.equals(dangerous12);
                    boolean d13 = text_value.equals(dangerous13);
                    boolean d14 = text_value.equals(dangerous14);
                    boolean d15 = text_value.equals(dangerous15);
                    boolean d16 = text_value.equals(dangerous16);
                    boolean d17 = text_value.equals(dangerous17);
                    boolean d18 = text_value.equals(dangerous18);
                    boolean d19 = text_value.equals(dangerous19);
                    boolean d20 = text_value.equals(dangerous20);

                    if (n1) {
                        board[x][y].setBackground(Color.DARK_GRAY);
                        board[x][y].setForeground(Color.DARK_GRAY);
                    }
                    if (a1) {
                        board[x][y].setBackground(Color.green);
                        board[x][y].setForeground(Color.green);
                    }
                    if (c2) {
                        board[x][y].setBackground(Color.yellow);
                        board[x][y].setForeground(Color.yellow);
                    }
                    if (d3 | d4 | d5 | d6 | d7 | d8 | d9 | d10 | d11 | d12 | d13 | d14 | d15 | d16 | d17 | d18 | d19 | d20) {
                        board[x][y].setBackground(Color.red);
                        board[x][y].setForeground(Color.red);
                    } else {

                    }
                }
            }
        }

        if (e.getSource() == btnExport) {

            FileDialog file = new FileDialog(this, "Choose file location for save", FileDialog.SAVE);
            file.setDirectory("C:\\");
            file.setFile(".RAF");
            file.setVisible(true);
            String filePath = file.getDirectory() + file.getFile();

            if (filePath.isEmpty()) {
                return;
            }
            if (filePath.toUpperCase().endsWith(".RAF") == false) {
                filePath += ".RAF";
            }

            FileManager.WarehouseData data = new FileManager.WarehouseData();
            data.WarehouseName = lblLocation.getText();
            data.Date = lblDate.getText();
            data.Time = lblTime.getText();
            data.WarehouseLayout = board;

            FileManager.SaveToRAFFile(filePath, data);
        }
        if (e.getSource() == btnLoadFile) {

            FileDialog file_dialog = new FileDialog(this, "Choose file to load.", FileDialog.LOAD);
            //Sets default opening directory path
            file_dialog.setDirectory("C:\\");
            //Sets default file extension for file path.
            file_dialog.setFile(".csv");
            //Sets the file dialog visible on screen and waits for it to be closed before proceeding.
            file_dialog.setVisible(true);
            //Combines file path and name from selected file into a single string to be used by file management.
            String filePath = file_dialog.getDirectory() + file_dialog.getFile();
            //If no file was chosen or cancel/close button pressed, return out of method.
            if (filePath.isEmpty()) {
                return;
            }

            FileManager.WarehouseData data;

            if (filePath.toUpperCase().endsWith(".CSV")) {
                data = FileManager.ReadFromTxtFile(filePath);
            } else if (filePath.toUpperCase().endsWith(".RAF")) {
                data = FileManager.ReadFromRAFFile(filePath);
            } else {
                return;
            }

            //Set headers data based upon the data model.
            lblLocation.setText(data.WarehouseName);
            lblDate.setText(data.Date);
            lblTime.setText(data.Time);
            //Iterate through grid data from data model and copy details into matching locations of the 2D text field array
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    board[x][y].setText(data.WarehouseLayout[x][y].getText());
                }
            }
        }

        if (e.getSource() == btnExportRPT){

            FileDialog file = new FileDialog(this, "Choose file location for save", FileDialog.SAVE);
            file.setDirectory("C:\\");
            file.setFile(".RPT");
            file.setVisible(true);
            String filePath = file.getDirectory() + file.getFile();

            if (filePath.isEmpty()) {
                return;
            }
            if (filePath.toUpperCase().endsWith(".RPT") == false) {
                filePath += ".RPT";
            }

                FileManager.WarehouseData data = new FileManager.WarehouseData();
                data.WarehouseName = lblLocation.getText();
                data.Date = lblDate.getText();
                data.Time = lblTime.getText();
                data.WarehouseLayout = board;

                FileManager.SaveToRPT(filePath, data);
            }
        }
    }

    //</editor-fold>

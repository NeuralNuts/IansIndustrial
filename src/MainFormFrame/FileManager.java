package MainFormFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class FileManager {

    //<editor-fold defaultstate="collapsed" desc="Save txtFile">
    public static void SaveToTxtFile(String filePath, WarehouseData data)
    {
        try
        {
            //Created buffered writer and connects to provided file location.
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            //Writes header dats on first 2 lines of file.
            writer.write(data.WarehouseName + "\n");
            writer.write(data.Date + "\n");
            writer.write(data.Time + "\n");
            //Iterates through the 2D array and places each row of the array in a single line in comma delimited format before moving to next line.
            for (int y = 0; y < data.WarehouseLayout[0].length; y++)
            {
                for (int x = 0; x < data.WarehouseLayout.length; x++)
                {
                    writer.write(data.WarehouseLayout[x][y].getText() + ",");
                }
                writer.newLine();
            }
            //Closes writer when done
            writer.close();
        }
        catch (Exception ex)
        {

        }
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Read txtFile">
    public static WarehouseData ReadFromTxtFile(String filePath)
    {
        //Ceate new data model object
        WarehouseData data = new WarehouseData();
        try
        {
            //Created buffered reader and connects to provided file location.
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            //Reads first 2 lines of file and puts them in the data model fields for the program headers.
            data.WarehouseName = reader.readLine();
            data.Date = reader.readLine();
            data.Time = reader.readLine();
            //Prepares data model to receive grid data.
            data.WarehouseLayout = new JTextField[20][18];

            //creates variables to manage each line read and keep track of line number
            String line;
            int lineNumber = 0;
            //Reads nect line and stores it in line variable before checking whether it is null/empty to determine whether loop runs.
            while ((line = reader.readLine()) != null)
            {
                //Splits line based upon comma locations
                String[] temp = line.split(",");
                //Places each line segment in sequence into the grid of the data model using the lineNUmber variable to determine which row it should be
                //writing to.
                for (int i = 0; i < data.WarehouseLayout.length; i++)
                {
                    data.WarehouseLayout[i][lineNumber]= new JTextField();
                    data.WarehouseLayout[i][lineNumber].setText(temp[i]);
                }
                //Increases line number at end of loop to track how many lines have been processed so far.
                lineNumber++;
            }
            //Closes the reader.
            reader.close();
        }
        catch(Exception ex)
        {
            //Returns the data model at whatever state it is currently at.
            return data;
        }
        //Returns the completed and filled data model
        return data;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Save rafFile">
    public static void SaveToRAFFile(String filePath, WarehouseData data)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath,"rw");

            raf.seek(0);
            raf.writeUTF(data.WarehouseName);
            raf.seek(20);
            raf.writeUTF(data.Date);
            raf.seek(20);
            raf.writeUTF(data.Time);

            int counter = 0;


            for (int x = 0; x < data.WarehouseLayout.length; x++)
            {
                for (int y = 0; y < data.WarehouseLayout[x].length; y++)
                {
                    int start = counter * 50 + 100;
                    raf.seek(start);
                    raf.writeInt(x);
                    raf.seek(start + 10);
                    raf.writeInt(y);
                    raf.seek(start + 20);
                    raf.writeUTF(data.WarehouseLayout[x][y].getText());
                    counter++;
                }
            }
        }
        catch (Exception e)
        {

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Read rafFile">
    public static WarehouseData ReadFromRAFFile(String filepath)
    {
        WarehouseData data = new WarehouseData();
        data.WarehouseLayout = new JTextField[18][20];

        try
        {
            RandomAccessFile raf = new RandomAccessFile(filepath, "r");

            raf.seek(0);
            data.WarehouseName = raf.readUTF();
            raf.seek(2);
            data.Date = raf.readUTF();
            raf.seek(3);
            data.Time = raf.readUTF();

            int counter = 0;

            while(counter * 18L + 20 < raf.length())
            {
                int start = counter * 18 + 20;
                raf.seek(start);
                int xPos = raf.readInt();
                raf.seek(start);
                int yPos = raf.readInt();
                raf.seek(start + 20);
                String value = raf.readUTF();

                data.WarehouseLayout[xPos][yPos] = new JTextField(value);
                counter++;
            }

            return data;
        }
        catch(Exception e)
        {
            return new WarehouseData();
        }
    }
    //</editor-fold>

    public static void SaveToRPT(String filePath, WarehouseData data) {

        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (int y = 0; y < data.WarehouseLayout[0].length; y++)
            {
                for (int x = 0; x < data.WarehouseLayout.length; x++)
                {

                    int n = data.WarehouseLayout.length;
                    writer.write(data.WarehouseLayout[x][y].getText() + ",");
                    Boolean layout_value = Boolean.valueOf(String.valueOf(data.WarehouseLayout[x][y].getBackground()));
                    Color layout_valueC = (data.WarehouseLayout[x][y].getBackground());

                    String count = data.WarehouseLayout[x][y].getText();
                    Color green = Color.green;
                    Color red = Color.red;
                    Color yellow = Color.yellow;

                    if(layout_valueC.equals(green) == Boolean.TRUE.equals(layout_value)){

                        layout_value + 1;
                    }
                    else {

                        writer.write("G");
                    }


                }
                writer.newLine();
            }
            //Closes writer when done
            writer.close();
        }
        catch (Exception ex)
        {

        }
    }
}

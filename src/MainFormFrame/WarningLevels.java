package MainFormFrame;

import java.awt.*;

public class WarningLevels {

    public boolean Accept(MainForm main_form, boolean b){
        for (int x = 0; x < main_form.board.length; x++) {
            for (int y = 0; y < main_form.board[x].length; y++) {
                 b = Boolean.parseBoolean(main_form.board[x][y].getText());
                int value = 0;
                if(b == true){
                    main_form.board[x][y].setBackground(Color.GREEN);
                }
            }
        }
        return true;
    }
}

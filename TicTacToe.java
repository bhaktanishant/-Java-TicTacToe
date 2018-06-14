import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class TicTacToe extends JFrame{

    private boolean xTurn = true;

    private Integer[][] winPositions = {
        {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7},
        {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}
    };

    private ArrayList<Integer> xPositions = new ArrayList();
    private ArrayList<Integer> oPositions = new ArrayList();

    private Button[] buttons = new Button[9];

    public static void main(String[] args) {
        new TicTacToe();
    }

    public TicTacToe(){
        super("TicTacToe");
        
        setLayout(new GridLayout(3, 3));
        setSize(500, 500);

        for (int i = 1; i <= 9; i++) {
            Button button = new Button();
            button.setId(i);
            buttons[i-1] = button;
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent event){
                    Button button = (Button)event.getSource();
                    if (button.notClicked()) {
                        button.clicked();
                        if (xTurn) {
                            button.setText("X");
                            xPositions.add(button.getId());
                        } else {
                            button.setText("O");
                            oPositions.add(button.getId());
                        }
                        button.setEnabled(false);
                        xTurn = !xTurn;
                        for (Integer[] var : winPositions) {
                            boolean xWon = true;
                            boolean oWon = true;
                            for (Integer position : var) {
                                if (!xPositions.contains(position)) {
                                    xWon = false;   
                                }
                                if (!oPositions.contains(position)) {
                                    oWon = false;
                                }
                            }
                            if (xWon || oWon) {
                                java.util.List<Integer> pressed = Arrays.asList(var);
                                for (Button butt : buttons) {
                                    if (pressed.contains(butt.getId())) {
                                        butt.setEnabled(true);
                                    }else{
                                        butt.setEnabled(false);   
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            });
            add(button);
        }
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Button extends JButton{

    private int id;
    private boolean notClicked = true;

    public void setId(int id){
        this.id = id;
    }

    public void clicked(){
        this.notClicked = false;
    }

    public int getId(){
        return this.id;
    }

    public boolean notClicked(){
        return this.notClicked;
    }
}
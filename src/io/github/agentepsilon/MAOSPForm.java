package io.github.agentepsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Evan on 1/3/2015.
 */
public class MAOSPForm {
    private JButton print;
    private JButton clear;
    private JTextField fieldName;
    private JTextField fieldID;
    private JPanel contentPane;
    private JCheckBox printBackgroundScantron;
    private JRadioButton a0RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a1RadioButton;

    public MAOSPForm() {
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fieldName.setText("");
                fieldID.setText("");
                a0RadioButton.setSelected(true);
            }
        });
        print.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String team;
                if(a0RadioButton.isSelected()){
                    team = "0";
                }else if(a1RadioButton.isSelected()){
                    team = "1";
                }else if(a2RadioButton.isSelected()){
                    team ="2";
                }else{
                    System.err.println("No team selected!!! Choosing team 0.");
                    team = "0";
                }
                ScantronPrinter.printScantron(new ScantronPrinter(fieldName.getText(), fieldID.getText(), team, printBackgroundScantron.isSelected()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BPS Scantron Printer");
        frame.setContentPane(new MAOSPForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frame.setLocation((int)width/2 - frame.getWidth()/2,(int)height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}

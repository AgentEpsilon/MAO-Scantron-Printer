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
    private JTextField fieldDivision;

    public MAOSPForm() {
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                fieldName.setText("");
                fieldID.setText("");
            }
        });
        print.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ScantronPrinter.printScantron(new ScantronPrinter(fieldName.getText(), fieldID.getText(), fieldDivision.getText(), printBackgroundScantron.isSelected()));
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

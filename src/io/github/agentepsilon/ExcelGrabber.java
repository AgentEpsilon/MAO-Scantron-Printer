package io.github.agentepsilon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Evan on 1/19/2015.
 */
public class ExcelGrabber {
    public static void main(String[] args){
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xls, *.xlsx)", "xls", "xlsx"));
        if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) try {
            Workbook wb = WorkbookFactory.create(fc.getSelectedFile());
            Sheet s = wb.getSheetAt(0);
            for(Row r : s){
                ArrayList<Cell> arrayList = new ArrayList<Cell>(3);
                for(Cell c : r) {
                    switch(c.getColumnIndex()){
                        case 0: arrayList.add(0,c);
                            break;
                        case 1: arrayList.add(1,c);
                            break;
                        case 2: arrayList.add(2,c);
                            break;
                        default: break;
                    }
                }
                if(arrayList.size()==3) {
                    String idnum = ((int) arrayList.get(2).getNumericCellValue()) + "";
                    while (idnum.length() < 5) {
                        idnum = "0" + idnum;
                    }
                    ScantronPrinter.printScantron(new ScantronPrinter(arrayList.get(0).getStringCellValue(), idnum, arrayList.get(1).getStringCellValue(), false));
                }else{
                    JOptionPane.showMessageDialog(null, "Row " + r.getRowNum() + " has incorrect number of cells!\nRemember, its [Name], [Division], [ID].");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}

package io.github.agentepsilon;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

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
                Cell name = null, id = null, div = null;
                for(Cell c : r) {
                    switch(c.getColumnIndex()){
                        case 0: name = c;
                            break;
                        case 1: id = c;
                            break;
                        case 2: div = c;
                            break;
                        default: break;
                    }
                }
                String idnum = ((int) id.getNumericCellValue())+"";
                while(idnum.length()<5){idnum="0"+idnum;}
                ScantronPrinter.printScantron(new ScantronPrinter(name.getStringCellValue(), idnum, div.getStringCellValue(), false));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}

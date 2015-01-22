package io.github.agentepsilon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;

/**
 * Created by Evan Miller.
 */
public class ScantronPrinter implements Printable {

    private final String name;
    private final String id;
    private final String team;
    private final boolean pbs;

    public ScantronPrinter(String name, String id, String team, boolean pbs) {
        this.name = name;
        this.id = id;
        this.team = team;
        this.pbs = pbs;
    }

    public ScantronPrinter(String name, String id){
        this(name, id, "", false);
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex>0){return NO_SUCH_PAGE;}

        if(pbs) {
            BufferedImage img = null;

            try {
                img = ImageIO.read(ScantronPrinter.class.getResource("/Scan.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            g.drawImage(img, 0, 0, null);
        }

        g.setFont(g.getFont().deriveFont(16f));

        g.drawString(name, dpiCon(950), dpiCon(2840));
        g.drawString("Berkeley Prep", dpiCon(1050), dpiCon(2940));

        g.setFont(new Font("Courier New", Font.PLAIN, 20));
        g.drawString("4050"+id+team, 217, 330);

        return PAGE_EXISTS;
    }

    public int dpiCon(int x){
        return x*72/300;
    }

    public static void printScantron(ScantronPrinter sp){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(sp);
        boolean doPrint = job.printDialog();
        if(doPrint){
            try{
                job.print();
            }catch(PrinterException e){
                e.printStackTrace();
            }
        }
    }
}

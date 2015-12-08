/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ricecompression;

import java.util.Arrays;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYDatasetTableModel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Oriol
 */
public class RiceCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RiceCompression rice = new RiceCompression();
        XYSeries data = new XYSeries("RICE");
        for(int i = -1023; i < 1024; i++){
            String riceCode = rice.compress(32, i);
            data.add(i, riceCode.length());
        }        
        XYSeriesCollection collection = new XYSeriesCollection(data);
        JFreeChart grafica = ChartFactory.createXYLineChart("RICE","Número a codificar","Longitud del codi Rice", collection,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel Panel = new ChartPanel(grafica);
        JFrame Ventana = new JFrame("JFreeChart");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public String compress(int m, int n){
        String riceCode;
        int nBitsM = (int) (Math.log10(m)/Math.log10(2));
        if(n < 0) riceCode = "0"; //Valor negatiu
        else riceCode = "1"; //Valor negatiu
        int q = Math.abs(n)/m;
        char[] array = new char[q];
        Arrays.fill(array, '1');
        if(array.length > 0) riceCode = riceCode.concat(String.valueOf(array)); //Si el quocient es major a 0
        riceCode = riceCode.concat("0");
        int r = Math.abs(n)%m;
        String rBinary = String.format("%"+nBitsM+"s", Integer.toBinaryString(r)).replace(' ', '0');
        riceCode = riceCode.concat(rBinary);
        return riceCode;
    }
}

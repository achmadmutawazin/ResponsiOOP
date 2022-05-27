/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author achmadmutawazin
 */
public class MovieController {
    MovieView MV;
    MovieModel MM;
    public String data;
    public MovieController(MovieModel MM,MovieView MV ) {
        this.MV = MV;
        this.MM = MM;
        
        if (MM.getData()!=0){
            String mdata[][] = MM.read();
            MV.table.setModel((new JTable(mdata, MV.columnName)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data is Empty");
        }
        
        MV.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String title = MV.getJudul();
                double plot = Double.parseDouble(MV.getAlur());
                double character = Double.parseDouble(MV.getPenokohan());
                double acting = Double.parseDouble(MV.getAkting());
                double score = (plot+character+acting)/3;
                MM.input(title, plot, character, acting, score);
         
                String mdata[][] = MM.read();
                MV.table.setModel((new JTable(mdata, MV.columnName)).getModel());
            }
        });
        
        MV.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String title = MV.getJudul();
                double plot = Double.parseDouble(MV.getAlur());
                double character = Double.parseDouble(MV.getPenokohan());
                double acting = Double.parseDouble(MV.getAkting());
                double score = (plot+character+acting)/3;
                MM.update(title, plot, character, acting, score);

                String mdata[][] = MM.read();
                MV.table.setModel((new JTable(mdata, MV.columnName)).getModel());
            }
        });
        
        MV.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                MV.tfTitle.setText("");
                MV.tfPlot.setText("");
                MV.tfChararter.setText("");
                MV.tfActing.setText("");
            }
        });
        
       MV.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                
                int row = MV.table.getSelectedRow();
                data = MV.table.getValueAt(row, 0).toString();
                String dataU[] = new String[4];
                dataU[0] = MV.table.getValueAt(row, 0).toString();
                dataU[1] = MV.table.getValueAt(row, 1).toString();
                System.out.println(data);  
            }
           });
       MV.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               int input = JOptionPane.showConfirmDialog(null,
                "Do you want to delete data :  " + data + "?", "Choose Option", JOptionPane.YES_NO_OPTION);

            if (input == 0) {
                MM.delete(data);
                String mdata[][] = MM.read();
                MV.table.setModel((new JTable(mdata, MV.columnName)).getModel());
            } else {
                JOptionPane.showMessageDialog(null, "Delete Failed");
            }
                }
        });
       
    }
    
}

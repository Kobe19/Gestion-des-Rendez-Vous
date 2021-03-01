/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionRdvPsyPatient;

import static GestionRdvPsyPatient.Accueil.Button2;
import database.ConnectionManager;
import GestionRdvPsyPatient.calendar.DateEvaluator;
import com.toedter.calendar.JCalendar;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.sql.ResultSet;


/**
 *
 * @author Daniel FOGUE et Yvan Doal
 */
public class Agenda extends javax.swing.JFrame {

    /**
     * Creates new form Agenda
     */
    
    Statement st;
    Connection con;
    PreparedStatement pst;
    ResultSet rs = null;
    DefaultTableModel model = new DefaultTableModel();
    public Agenda() {
        initComponents();
        updateTable();
        
         this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
               Exit();
            }
            
        });
        
        String content = connexion.typeuser.getSelectedItem().toString();
         if (content.equals("SIMPLE UTILISATEUR")){
              
              Button2.setEnabled(false);
              Button3.setEnabled(false);
              Button4.setEnabled(false);
              
        }
        
        model.addColumn("id_seance");
        model.addColumn("Date");
        model.addColumn("Creneau");
        
        try{
            st = ConnectionManager.getConnection(ConnectionManager.databaseConfig).createStatement();
            ResultSet rs =st.executeQuery("SELECT distinct `id_seance`,`Date`,`creneau`  FROM `seance`");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("id_seance"), rs.getString("Date"), rs.getString("creneau")});    
        }
        } catch(Exception e){
            System.err.print(e);
        }
       
        table.setModel(model);
    }
    
    
    
    private void updatescreen(){
        Agenda A = new Agenda(); 
        A.setVisible(true);
        this.hide();
    }
    private void updateTable(){
        
        try{
            con = ConnectionManager.getConnection(ConnectionManager.databaseConfig);
             String sql = "Select * from seance";
             pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    
        
    }
     
    
     private void deplace(int i, int srow){
        try{
            comboid.setText(model.getValueAt(i,0).toString());
        try{
            srow = table.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(srow, 1));
            combodate.setDate(date);
            
        }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
        }
        String subject1 = model.getValueAt(i,2).toString();
            switch(subject1){
                case "8h00 - 8h30":
                     combocreneau.setSelectedIndex(0);
                     break;
                case "8h30 - 9h00":
                     combocreneau.setSelectedIndex(1);
                     break;
                case "9h00 - 9h30":
                     combocreneau.setSelectedIndex(2);
                     break;
                case "9h30 - 10h00":
                     combocreneau.setSelectedIndex(3);
                     break;
                case "10h00 - 10h30":
                     combocreneau.setSelectedIndex(4);
                     break;     
                case "10h30 - 11h00":
                     combocreneau.setSelectedIndex(5);
                     break;
                case "11h00 - 11h30":
                     combocreneau.setSelectedIndex(6);
                     break;     
                case "11h30 - 12h00":
                     combocreneau.setSelectedIndex(7);
                     break;     
                case "14h00 - 14h30":
                     combocreneau.setSelectedIndex(8);
                     break;     
                case "14h30 - 15h00":
                     combocreneau.setSelectedIndex(9);
                     break;     
                case "15h00 - 15h30":
                     combocreneau.setSelectedIndex(10);
                     break;     
                case "15h30 - 16h00":
                     combocreneau.setSelectedIndex(11);
                     break;     
                case "16h00 - 16h30":
                     combocreneau.setSelectedIndex(12);
                     break;     
                case "16h30 - 17h00":
                     combocreneau.setSelectedIndex(13);
                     break;     
                case "17h00 - 17h30":
                     combocreneau.setSelectedIndex(14);
                     break;     
                case "17h30 - 18h00":
                     combocreneau.setSelectedIndex(15);
                     break;     
                case "18h00 - 18h30":
                     combocreneau.setSelectedIndex(16);
                     break;     
                case "18h30 - 19h00":
                     combocreneau.setSelectedIndex(17);
                     break;     
                case "19h00 - 19h30":
                     combocreneau.setSelectedIndex(18);
                     break;     
                case "19h30 - 20h00":
                     combocreneau.setSelectedIndex(19);
                     break;     
      
            }
        
        }catch (Exception e){System.err.println(e);
         JOptionPane.showMessageDialog(null,"erreur de deplacement"+e.getLocalizedMessage());       
                }
        
        
    }
     void Exit(){
        int a=JOptionPane.showConfirmDialog(null, "Etes vous sure de vouloir quitter cliquez ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION){
            this.dispose();
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Button1 = new javax.swing.JButton();
        combodate = new com.toedter.calendar.JDateChooser();
        Button2 = new javax.swing.JButton();
        combocreneau = new javax.swing.JComboBox<>();
        Button3 = new javax.swing.JButton();
        Button4 = new javax.swing.JButton();
        comboid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cabinet Psychiatrie Projet BDD");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Identifiant", "Date", "creneau"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        Button1.setText("Ajouter");
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        combodate.setDateFormatString("yyyy-MM-dd ");
        combodate.setMinSelectableDate(new java.util.Date(-62135769489000L));
        combodate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combodateMouseClicked(evt);
            }
        });

        Button2.setText("Supprimer");
        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2ActionPerformed(evt);
            }
        });

        combocreneau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8h00 - 8h30", "8h30 - 9h00", "9h00 - 9h30", "9h30 - 10h00", "10h00 - 10h30 ", "10h30 - 11h00", "11h00 - 11h30", "11h30 - 12h00", "14h00 - 14h30", "14h30 - 15h00", "15h00 - 15h30", "15h30 - 16h00", "16h00 - 16h30", "16h30 - 17h00", "17h00 - 17h30", "17h30 - 18h00", "18h00 - 18h30", "18h30 - 19h00", "19h00 - 19h30", "19h30 - 20h00" }));
        combocreneau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocreneauActionPerformed(evt);
            }
        });

        Button3.setText("Modifier");
        Button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3ActionPerformed(evt);
            }
        });

        Button4.setText("Actualiser");
        Button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button4ActionPerformed(evt);
            }
        });

        comboid.setBackground(new java.awt.Color(0, 0, 0));
        comboid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboidActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Date");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Creneau Horaire");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(Button2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                        .addComponent(Button3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(Button4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel13)
                        .addGap(50, 50, 50)
                        .addComponent(combodate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel15)
                        .addGap(31, 31, 31)
                        .addComponent(combocreneau, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(127, 127, 127))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(comboid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combocreneau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addComponent(combodate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button1)
                    .addComponent(Button2)
                    .addComponent(Button3)
                    .addComponent(Button4))
                .addGap(2, 2, 2)
                .addComponent(comboid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        combodate.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton2.setText("Deconnexion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu2.setText("Rendez vous");

        jMenu6.setText("Prendre Rendez vous");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu2.add(jMenu6);

        jMenu7.setText("Consulter Agenda");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        jMenu10.setText("Accueil");
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2ActionPerformed
        // TODO add your handling code here:
        
        try {
            if (JOptionPane.showConfirmDialog(null, "confirmer la modification", "modification", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
                String ftext = comboid.getText();
               
                st.executeUpdate("DELETE FROM seance WHERE id_seance='"+ftext+"'");
               
                //textid.setText(" ");
                combodate.setDateFormatString("");
                combocreneau.setSelectedIndex(0);
                
                updateTable();
                updatescreen();
                
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_Button2ActionPerformed

    private void combocreneauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocreneauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combocreneauActionPerformed

    private void Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1ActionPerformed
        try {
            
            
            // TODO add your handling code here:
            
           // String ftext = comboid.getText();
            String fcreneau = combocreneau.getSelectedItem().toString();            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fdate = sdf.format(combodate.getDate());
           
            
            
            String SelectQuery = "select * from `seance` where `date`='"+fdate+"' and `creneau`='"+fcreneau+"'";
            //System.out.println(SelectQuery);
            rs = st.executeQuery(SelectQuery);
            //System.out.println(rs.next());
            if(rs.next()== true){
                JOptionPane.showMessageDialog(null, "Ce creneau n'est pas disponible");
            }
            else{
                try {
                    PreparedStatement ps;
                    
                    String query = "INSERT INTO `seance`(`date`, `creneau`) VALUES (?,?)";
                    
                    ps = ConnectionManager.getConnection(ConnectionManager.databaseConfig).prepareStatement(query);
                    
                   //ps.setString(1, ftext);
                    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //String fdate = sdf.format(combodate.getDate());
                    ps.setString(1, fdate);
                    ps.setString(2, fcreneau);
                    
                    
                    
                    if (ps.executeUpdate()>0){
                        JOptionPane.showMessageDialog(null, "nouvelle seance ajoutée");
                        
                      //  textid.setText("");
                        combodate.setDateFormatString("");
                        combocreneau.setSelectedItem(0);
                        
                        updateTable();
                        updatescreen();
                        
                        
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                }
            
            
              
            
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
       
        
    }//GEN-LAST:event_Button1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        
        try{
            int i=table.getSelectedRow(); int srow = table.getSelectedRow(); deplace(i, srow);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"erreur de deplacement"+e.getLocalizedMessage());
        }
    }//GEN-LAST:event_tableMouseClicked

    private void Button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "confirmer la modification", "modification", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fdate = sdf.format(combodate.getDate());
            try {
                String ftext = comboid.getText();
                st.executeUpdate("UPDATE seance SET date='"+fdate+"',creneau='"+combocreneau.getSelectedItem()+"' WHERE id_seance='"+ftext+"'");
            } catch (SQLException ex) {
                Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            // textid.setText("");
            SimpleDateFormat sdf2 = new SimpleDateFormat("");
            String fdate2 = sdf2.format(combodate.getDate());
            combodate.setDateFormatString(fdate2);
            combocreneau.setSelectedIndex(0);
            
            updateTable();
            updatescreen();
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
            
    }//GEN-LAST:event_Button3ActionPerformed

    private void Button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button4ActionPerformed
        // TODO add your handling code here:
        
        Agenda A = new Agenda(); 
        A.setVisible(true);
        this.hide();
    }//GEN-LAST:event_Button4ActionPerformed

    private void combodateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combodateMouseClicked
        // TODO add your handling code here:
        
        


    }//GEN-LAST:event_combodateMouseClicked

    private void comboidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboidActionPerformed

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        // TODO add your handling code here:
        Agenda p = new Agenda();
        p.setVisible(true);
        this.hide();
        
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        
        Prise_rdvs p = new Prise_rdvs();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        // TODO add your handling code here:
        
        Accueil A= new Accueil();
        A.setVisible(true);
        this.hide();

    }//GEN-LAST:event_jMenu10MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        connexion p = new connexion();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JButton Button3;
    private javax.swing.JButton Button4;
    private javax.swing.JComboBox<String> combocreneau;
    private com.toedter.calendar.JDateChooser combodate;
    private javax.swing.JTextField comboid;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}

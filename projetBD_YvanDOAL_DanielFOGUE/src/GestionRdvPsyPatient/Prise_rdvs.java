/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionRdvPsyPatient;


import GestionRdvPsyPatient.calendar.DateEvaluator;
import database.ConnectionManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Daniel FOGUE et Yvan Doal
 */
public class Prise_rdvs extends javax.swing.JFrame {

    
    Connection con=null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;
    DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form Prise_rdvs
     */
    public Prise_rdvs() {
        initComponents();
        combobox();
        combobox2();
        combobox1();
         this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
               Exit();
            }
            
        });
        String content = connexion.typeuser.getSelectedItem().toString();
        String u_username = connexion.textFieldName.getText();
        if (content.equals("SIMPLE UTILISATEUR")){
              fieldcomportement.setEnabled(false);
              fieldposture.setEnabled(false);
              fieldprix.setEnabled(false);
              }
    
       
       
        model.addColumn("id_seance");
        model.addColumn("date");
        model.addColumn("creneau");
        model.addColumn("id_patient");
        model.addColumn("nom");
        model.addColumn("prenom");
        model.addColumn("Type de consultation");
        model.addColumn("Indicateur");
        model.addColumn("Reglement");
        model.addColumn("Prix");
        model.addColumn("Posture");
        model.addColumn("comportement");
        model.addColumn("rdvnum");
        
      
        if(content.equals("SIMPLE UTILISATEUR")){
         try {
                
                    st = ConnectionManager.getConnection(ConnectionManager.databaseConfig).createStatement();
            
            ResultSet rs =st.executeQuery("SELECT consulter.id_seance, seance.date, seance.creneau, consulter.id_patient,`nom`,`prenom`,`id_type_consultation`,`indicateur`,`reglement`,`prix`,`posture`,`comportement`, rdv_N FROM `utilisateur`,`consulter`,`seance`,`patient` WHERE seance.id_seance = consulter.id_seance and patient.id_patient = consulter.id_patient and utilisateur.username='"+u_username+"' and  utilisateur.username = patient.email");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("id_seance"),rs.getString("date"),rs.getString("creneau"),rs.getString("id_patient"), rs.getString("nom"),rs.getString("prenom"),rs.getString("id_type_consultation"), rs.getString("indicateur"),rs.getString("reglement"), rs.getString("prix"), rs.getString("posture"), rs.getString("comportement"), rs.getString("rdv_N")});    
        }
        } catch(Exception e){
            System.err.print(e);
        }
         tble.setModel(model);
                } else if(content.equals("ADMIN")){
        try{
            st = ConnectionManager.getConnection(ConnectionManager.databaseConfig).createStatement();
            
            ResultSet rs =st.executeQuery("SELECT consulter.id_seance, seance.date, seance.creneau, consulter.id_patient, `nom`,`prenom`,`id_type_consultation`,`indicateur`,`reglement`,`prix`,`posture`,`comportement`,rdv_N FROM `consulter`,`seance`,`patient` WHERE seance.id_seance = consulter.id_seance and patient.id_patient = consulter.id_patient");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("id_seance"),rs.getString("date"),rs.getString("creneau"),rs.getString("id_patient"), rs.getString("nom"),rs.getString("prenom"),rs.getString("id_type_consultation"), rs.getString("indicateur"),rs.getString("reglement"), rs.getString("prix"), rs.getString("posture"), rs.getString("comportement"), rs.getString("rdv_N")});    
        }
        } catch(Exception e){
            System.err.print(e);
        }
        
       
        tble.setModel(model);
    }}
     private void updatescreen(){
        Prise_rdvs P = new Prise_rdvs(); 
        P.setVisible(true);
        this.hide();
    }
    private void updateTable(){
         String content = connexion.typeuser.getSelectedItem().toString();
         String u_username = connexion.textFieldName.getText();
        if(content.equals("SIMPLE UTILISATEUR")){
        try{
            String sql = "SELECT consulter.id_seance, seance.date, seance.creneau, consulter.id_patient,`nom`,`prenom`,`id_type_consultation`,`indicateur`,`reglement`,`prix`,`posture`,`comportement` FROM `utilisateur`,`consulter`,`seance`,`patient` WHERE seance.id_seance = consulter.id_seance and patient.id_patient = consulter.id_patient and utilisateur.username='"+u_username+"' and  utilisateur.username = patient.email";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tble.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        }else if(content.equals("ADMIN")){
        try{
            String sql = "SELECT consulter.id_seance, seance.date, seance.creneau, consulter.id_patient, `nom`,`prenom`,`id_type_consultation`,`indicateur`,`reglement`,`prix`,`posture`,`comportement` FROM `consulter`,`seance`,`patient` WHERE seance.id_seance = consulter.id_seance and patient.id_patient = consulter.id_patient";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tble.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void combobox(){
        try {
             con = ConnectionManager.getConnection(ConnectionManager.databaseConfig);
             String sql = "Select * from patient";
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery();
             while(rs.next()){
                 String combo = rs.getString("id_patient");
                 combopatient.addItem(combo);
             }
        }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
    }
    }
    
    public void combobox2(){
        try {
             con = ConnectionManager.getConnection(ConnectionManager.databaseConfig);
             String sql = "Select * from seance";
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery();
             while(rs.next()){
                 String combo = rs.getString("id_seance");
                 comboseance.addItem(combo);
             }
        }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
    }
    }
    
    
    private void deplace(int i){
        try{
        comboseance.setSelectedItem(model.getValueAt(i,0).toString());
        combopatient.setSelectedItem(model.getValueAt(i,3).toString());
        combotype.setSelectedItem(model.getValueAt(i,6).toString());
        comboindicateur.setSelectedItem(model.getValueAt(i,7).toString());
        comboreglement.setSelectedItem(model.getValueAt(i,8).toString());
        fieldprix.setText(model.getValueAt(i,9).toString());
        fieldposture.setText(model.getValueAt(i,10).toString());
        fieldcomportement.setText(model.getValueAt(i,11).toString());
        
        }catch (Exception e){System.err.println(e);
         JOptionPane.showMessageDialog(null,"erreur de deplacement"+e.getLocalizedMessage());       
                }
        
    }
    
    public void combobox1(){
        try {
             con = ConnectionManager.getConnection(ConnectionManager.databaseConfig);
             String sql = "Select * from type_consultation";
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery();
             while(rs.next()){
                 String combo = rs.getString("id_type_consultation");
                 combotype.addItem(combo);
             }
        }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
    }
    }
    void Exit(){
        int a=JOptionPane.showConfirmDialog(null, "Etes vous sure de vouloir quitter cliquez ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if(a==JOptionPane.YES_OPTION){
            this.dispose();
        }    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        combodate1 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        combodate2 = new com.toedter.calendar.JDateChooser();
        Button1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tble = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboindicateur = new javax.swing.JComboBox<>();
        combopatient = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboreglement = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        fieldprix = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fieldposture = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldcomportement = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        combotype = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboseance = new javax.swing.JComboBox<>();
        rdvnum = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cabinet Psychiatrie Projet BDD");

        jLabel12.setFont(new java.awt.Font("Bookman Old Style", 3, 36)); // NOI18N
        jLabel12.setText("Gestion de rendez vous  ");

        jLabel13.setText("Du :");

        combodate1.setDateFormatString("yyyy-MM-dd ");

        jLabel14.setText("Au : ");

        combodate2.setDateFormatString("yyyy-MM-dd ");

        Button1.setText("Rechercher");
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        jButton4.setText("Deconnexion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(164, 164, 164)
                        .addComponent(jLabel13))
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combodate1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combodate2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Button1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button1)
                            .addComponent(combodate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(34, 34, 34)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(combodate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jButton4))
                .addGap(21, 21, 21))
        );

        combodate1.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());
        combodate2.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());

        tble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Identifiant Patient", "Patient", "Identifiant Seance", "Seance", "Reglement", "Indicateur", "Prix", "Posture", "Comportement"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tble.getTableHeader().setReorderingAllowed(false);
        tble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbleMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbleMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbleMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tble);
        if (tble.getColumnModel().getColumnCount() > 0) {
            tble.getColumnModel().getColumn(0).setMinWidth(10);
        }

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Patient");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Indicateur si anxiete ");

        comboindicateur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        combopatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combopatientActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Reglement");

        comboreglement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Espèce", "Cheque", "Carte Bleue" }));

        jButton1.setText("Ajouter");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modifier");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Actualiser");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Prix");

        fieldprix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldprixActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("€");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Posture");

        fieldposture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldpostureActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Comportement");

        fieldcomportement.setColumns(20);
        fieldcomportement.setRows(5);
        jScrollPane2.setViewportView(fieldcomportement);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Type de consultation");

        combotype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combotypeActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("(1 : Standard ,");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" 2 : Anxieté )");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Seance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)
                        .addComponent(combopatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel16)
                                                    .addGap(27, 27, 27)
                                                    .addComponent(comboseance, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(324, 324, 324)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(522, 522, 522)
                                        .addComponent(jLabel2)))
                                .addGap(210, 210, 210))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(combotype, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(42, 42, 42)
                                        .addComponent(comboreglement, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(rdvnum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboindicateur, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(146, 146, 146)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton5)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(fieldprix, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                                        .addComponent(fieldposture, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(28, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jButton1)
                .addGap(207, 207, 207)
                .addComponent(jButton2)
                .addGap(213, 213, 213)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(combopatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(comboseance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(comboreglement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48)
                .addComponent(jLabel5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboindicateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(combotype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(rdvnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldprix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldposture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Rendez vous");

        jMenu13.setText("Prendre Rendez vous");
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu13MouseClicked(evt);
            }
        });
        jMenu6.add(jMenu13);

        jMenu14.setText("Consulter Agenda");
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenu6.add(jMenu14);

        jMenuBar1.add(jMenu6);

        jMenu15.setText("Accueil");
        jMenu15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu15MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu15);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu13MouseClicked
        // TODO add your handling code here:

        Prise_rdvs p = new Prise_rdvs();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu13MouseClicked

    private void jMenu14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MouseClicked
        // TODO add your handling code here:
        Agenda p = new Agenda();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu14MouseClicked

    private void jMenu15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu15MouseClicked
        // TODO add your handling code here:

        Accueil A= new Accueil();
        A.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu15MouseClicked

    private void combotypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combotypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combotypeActionPerformed

    private void fieldpostureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldpostureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldpostureActionPerformed

    private void fieldprixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldprixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldprixActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String fseance = comboseance.getSelectedItem().toString();
        System.out.println(fseance);
        
        try {
            if (JOptionPane.showConfirmDialog(null, "confirmer la modification", "modification", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
                st.executeUpdate("UPDATE consulter SET id_seance='"+fseance+"',id_patient='"+combopatient.getSelectedItem()+"',id_type_consultation='"+combotype.getSelectedItem()+"', indicateur='"+comboindicateur.getSelectedItem()+"', reglement='"+comboreglement.getSelectedItem()+"', prix='"+fieldprix.getText()+"', posture='"+fieldposture.getText()+"', comportement='"+fieldcomportement.getText()+"' WHERE id_seance='"+fseance+"' AND id_patient='"+combopatient.getSelectedItem()+"' AND id_type_consultation='"+combotype.getSelectedItem()+"'");
              
                
                
                comboseance.setSelectedItem("");
                combopatient.setSelectedItem("");
                combotype.setSelectedItem("");
                fieldprix.setText("");
                comboreglement.setSelectedItem("");
                fieldposture.setText("");
                fieldcomportement.setText("");
                comboindicateur.setSelectedItem("");

                updatescreen();
                updateTable();
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        try {
            if (JOptionPane.showConfirmDialog(null, "Voulez vous vraiment annuler ce rendez vous ?", "Annuler rendez vous", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){

                st.executeUpdate("DELETE From consulter WHERE id_seance ='"+comboseance.getSelectedItem()+"'AND id_patient='"+combopatient.getSelectedItem()+"'AND id_type_consultation="+combotype.getSelectedItem());
                updateTable();
                updatescreen();
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }

        }catch (SQLException ex) {
            Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            
            
            String fseance = comboseance.getSelectedItem().toString();
            String fpatient = combopatient.getSelectedItem().toString();
            String fprix = fieldprix.getText();
            String freglement = comboreglement.getSelectedItem().toString();
            String fposture = fieldposture.getText();
            String fcomportement = fieldcomportement.getText();
            String findicateur = comboindicateur.getSelectedItem().toString();
            String ftype = combotype.getSelectedItem().toString();

            ResultSet rs1;

            try {
                String SelectQuery = "SELECT count(*) FROM consulter WHERE consulter.id_seance ='"+fseance+"'HAVING COUNT(*)='"+3+"'";
                //System.out.println(SelectQuery);
                rs = st.executeQuery(SelectQuery);
                //System.out.println(rs.next());
            } catch (SQLException ex) {
                Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if(rs.next()== true){
                    JOptionPane.showMessageDialog(null, "Trop de personne sur ce creneau");
                }
                else{
                    try {
                        String SelectQuery1 = "SELECT * FROM consulter WHERE consulter.id_seance ='"+fseance+"'and id_patient='"+fpatient+"'";
                        //System.out.println(SelectQuery);
                        rs1 = st.executeQuery(SelectQuery1);
                        //System.out.println(rs.next());
                        if(rs1.next()== true){
                            JOptionPane.showMessageDialog(null, "Vous êtes déja sur ce rendez vous");
                        }
                        else{
                            try{
                                String u_username = connexion.textFieldName.getText();
                                PreparedStatement ps;

                                String query3 = "INSERT INTO `consulter`(`id_seance`, `id_patient`,`id_type_consultation`, `reglement`, `indicateur`, `prix`, `posture`, `comportement`) VALUES (?,?,?,?,?,?,?,?)";
                                ps = ConnectionManager.getConnection(ConnectionManager.databaseConfig).prepareStatement(query3);

                                ps.setString(1, fseance);
                                String content = connexion.typeuser.getSelectedItem().toString();
                                if (content.equals("SIMPLE UTILISATEUR")) {
                                    String SelectQuery8 = "SELECT * FROM patient WHERE patient.email ='" + u_username + "'and id_patient='" + fpatient + "'";
                                    ResultSet rs2 = st.executeQuery(SelectQuery8);
                                    if (rs2.next() == false) {
                                        JOptionPane.showMessageDialog(null, "Veuillez selectionner votre identifiant s'il vous plait");
                                    } else {
                                        ps.setString(2, fpatient);
                                    }
                                } else if (content.equals("ADMIN")) {
                                    ps.setString(2, fpatient);
                                }
                                ps.setString(3, ftype);
                                ps.setString(4, freglement);
                                ps.setString(5, findicateur);
                                ps.setString(6, fprix);
                                ps.setString(7, fposture);
                                ps.setString(8, fcomportement);

                                if (ps.executeUpdate()>0){
                                    JOptionPane.showMessageDialog(null, "Nouveau Rendez vous passé");

                                    comboseance.setSelectedItem("");
                                    combopatient.setSelectedItem("");
                                    fieldprix.setText("");
                                    comboreglement.setSelectedItem("");
                                    fieldposture.setText("");
                                    fieldcomportement.setText("");
                                    comboindicateur.setSelectedItem("");
                                    combotype.setSelectedItem("");

                                    updateTable();
                                    updatescreen();
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }     } catch (SQLException ex) {
                    Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (Exception ex) {
                Logger.getLogger(Prise_rdvs.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combopatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combopatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combopatientActionPerformed

    private void tbleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tbleMouseReleased

    private void tbleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbleMousePressed

    private void tbleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleMouseExited

    }//GEN-LAST:event_tbleMouseExited

    private void tbleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tbleMouseEntered

    private void tbleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbleMouseClicked
        // TODO add your handling code here:

        try{
            int i=tble.getSelectedRow(); deplace(i);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"erreur de deplacement"+e.getLocalizedMessage());
        }

    }//GEN-LAST:event_tbleMouseClicked

    private void Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1ActionPerformed
        // TODO add your handling code here:
         String content = connexion.typeuser.getSelectedItem().toString();
         String u_username = connexion.textFieldName.getText();
        try {
                if (content.equals("SIMPLE UTILISATEUR")){
            model.setRowCount(0);
            {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fdate1 = sdf.format(combodate1.getDate());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String fdate2 = sdf2.format(combodate2.getDate());
                rs =st.executeQuery("SELECT  consulter.id_seance, seance.date, seance.creneau, consulter.id_patient,`nom`,`prenom`,consulter.id_type_consultation,`indicateur`,`reglement`,`prix`,`posture`,`comportement`,rdv_N FROM `utilisateur`,`consulter`,`seance`,`patient`, type_consultation WHERE patient.id_patient = consulter.id_patient and seance.id_seance = consulter.id_seance and type_consultation.id_type_consultation = consulter.id_type_consultation and utilisateur.username='"+u_username+"' and  utilisateur.username = patient.email and seance.`date` between'"+fdate1+"' and '"+fdate2+"'");
            } while(rs.next()){
                Object [] client ={rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)};
                model.addRow(client);
            }if(model.getRowCount() == 0){
                JOptionPane.showMessageDialog(null,"Aucune correspondance a votre recherche");
            }
        }else if(content.equals("ADMIN")){
            model.setRowCount(0);
            {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fdate1 = sdf.format(combodate1.getDate());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String fdate2 = sdf2.format(combodate2.getDate());
                rs =st.executeQuery("SELECT DISTINCT consulter.id_seance, seance.date, seance.creneau, consulter.id_patient,`nom`,`prenom`,consulter.id_type_consultation,`indicateur`,`reglement`,`prix`,`posture`,`comportement`,rdv_N FROM `utilisateur`,`consulter`,`seance`,`patient`, type_consultation WHERE patient.id_patient = consulter.id_patient and seance.id_seance = consulter.id_seance and type_consultation.id_type_consultation = consulter.id_type_consultation and seance.`date` between'"+fdate1+"' and '"+fdate2+"'");
            } while(rs.next()){
                Object [] client ={rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)};
                model.addRow(client);
            }if(model.getRowCount() == 0){
                JOptionPane.showMessageDialog(null,"Aucune correspondance a votre recherche");
            }
        }
        }

        catch (Exception ex) {
            Logger.getLogger(Archive.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_Button1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        connexion p = new connexion();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Prise_rdvs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prise_rdvs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prise_rdvs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prise_rdvs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prise_rdvs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button1;
    private com.toedter.calendar.JDateChooser combodate1;
    private com.toedter.calendar.JDateChooser combodate2;
    private javax.swing.JComboBox<String> comboindicateur;
    private javax.swing.JComboBox<String> combopatient;
    private javax.swing.JComboBox<String> comboreglement;
    private javax.swing.JComboBox<String> comboseance;
    private javax.swing.JComboBox<String> combotype;
    private javax.swing.JTextArea fieldcomportement;
    private javax.swing.JTextField fieldposture;
    private javax.swing.JTextField fieldprix;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField rdvnum;
    private static javax.swing.JTable tble;
    // End of variables declaration//GEN-END:variables
}

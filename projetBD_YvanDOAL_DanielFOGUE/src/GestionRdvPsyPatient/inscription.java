/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionRdvPsyPatient;

import database.ConnectionManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel FOGUE et Yvan Doal
 */

public class inscription extends javax.swing.JFrame {

    /**
     * Creates new form inscription
     */
    Connection con=null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;
    DefaultTableModel model = new DefaultTableModel();
    public inscription() {
        initComponents();
         this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
               Exit();
            }
            
        });
        combobox();
        String content = connexion.typeuser.getSelectedItem().toString();
        if (content.equals("SIMPLE UTILISATEUR")){
              Menu6.setEnabled(false);
              Menu5.setEnabled(false);
              Menu8.setEnabled(false);}
        
        model.addColumn("Identifiant");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Telephone");
        model.addColumn("Adresse");
        model.addColumn("Moyen de connaissance");
        model.addColumn("Email");
        model.addColumn("Type de patient");
        
        
        
        try{
            
            st = ConnectionManager.getConnection(ConnectionManager.databaseConfig).createStatement();
            ResultSet rs =st.executeQuery("SELECT patient.id_patient,patient.nom, patient.prenom, patient.telephone, patient.adresse, patient.moyen_connaissance, patient.email, type_patient.intitule FROM Patient, type_patient WHERE patient.id_type_patient = type_patient.id_type_patient");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("id_patient"),rs.getString("nom"), rs.getString("prenom"),rs.getString("telephone"), rs.getString("adresse"),rs.getString("moyen_connaissance"), rs.getString("email"), rs.getString("intitule")});    
        }
        } catch(Exception e){
            System.err.print(e);
        }
       
        tble.setModel(model);
        
    }
    
    public void combobox(){
        try {
             con = ConnectionManager.getConnection(ConnectionManager.databaseConfig);
             String sql = "Select * from type_patient";
             pst = con.prepareStatement(sql);
             rs = pst.executeQuery();
             while(rs.next()){
                 String combo = rs.getString("id_type_patient");
                 combopatient.addItem(combo);
             }
        }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
    }
    }

    private void updatescreen(){
        inscription A = new inscription(); 
        A.setVisible(true);
        this.hide();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fieldnom = new javax.swing.JTextField();
        fieldprenom = new javax.swing.JTextField();
        fieldadresse = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fieldtelephone = new javax.swing.JTextField();
        fieldmail = new javax.swing.JTextField();
        combomoyen = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        combopatient = new javax.swing.JComboBox<>();
        fieldpass = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboniveau = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tble = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu10 = new javax.swing.JMenu();
        Menu5 = new javax.swing.JMenu();
        Menu6 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        Menu8 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cabinet Psychiatrie Projet BDD");

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 3, 24)); // NOI18N
        jLabel1.setText("Création d'un nouveau Patient");

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
                .addGap(125, 125, 125)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(86, 86, 86))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nom");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Prenom");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Adresse");

        fieldprenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldprenomActionPerformed(evt);
            }
        });

        fieldadresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldadresseActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Télephone");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Moyen Connaissance");

        combomoyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Autre patient", "Docteur", "bouche à oreille ", "Pages jaunes", "Internet" }));
        combomoyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combomoyenItemStateChanged(evt);
            }
        });

        jButton1.setText("Enregistrer Patient");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("reinitialiser");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Type de Patient");

        combopatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combopatientActionPerformed(evt);
            }
        });

        fieldpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldpassActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("password");

        jButton3.setText("ajouter profession");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("(1 : Enfant    2 : Ado    3 : Homme    4 : Femme    5 : couple)");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Niveau d'accès");

        comboniveau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple Utilisateur", "Admin" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))))
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldpass, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(fieldadresse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(105, 105, 105)
                        .addComponent(combomoyen, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fieldprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel12)))
                        .addGap(105, 105, 105)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(combopatient, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldnom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldtelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldmail, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)
                        .addGap(49, 49, 49)
                        .addComponent(comboniveau, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(135, 135, 135))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldprenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(fieldnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(fieldtelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldadresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(combomoyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(fieldmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(combopatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(comboniveau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(77, 77, 77))
        );

        tble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Identifiant", "Nom", "Prenom", "Telephone", "Adresse", "Moyen de connaissance", "Email", "Type Patient", "Title 9"
            }
        ));
        jScrollPane1.setViewportView(tble);

        jMenu10.setText("Patient");

        Menu5.setText("Ajouter un patient");
        Menu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu5MouseClicked(evt);
            }
        });
        jMenu10.add(Menu5);

        Menu6.setText("Liste des professions");
        Menu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu6MouseClicked(evt);
            }
        });
        jMenu10.add(Menu6);

        jMenuBar1.add(jMenu10);

        jMenu9.setText("Rendez vous");

        jMenu13.setText("Prendre Rendez vous");
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu13MouseClicked(evt);
            }
        });
        jMenu9.add(jMenu13);

        jMenu14.setText("Consulter Agenda");
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenu9.add(jMenu14);

        jMenuBar1.add(jMenu9);

        Menu8.setText("Archive");
        Menu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu8MouseClicked(evt);
            }
        });
        jMenuBar1.add(Menu8);

        jMenu12.setText("Accueil");
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu12);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
               fieldnom.setText("");
               fieldprenom.setText("");
               fieldadresse.setText("");
               
               fieldtelephone.setText("");
               combomoyen.setSelectedItem(0);
               fieldmail.setText("");
               combomoyen.setSelectedItem(0);
               fieldpass.setText("");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fieldprenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldprenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldprenomActionPerformed

    private void fieldadresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldadresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldadresseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
     
                
       
        String fnom = fieldnom.getText();
        String fprenom = fieldprenom.getText();
        String fadresse = fieldadresse.getText();
        String ftelephone = fieldtelephone.getText();
        String fmoyen = combomoyen.getSelectedItem().toString();
        String fmail = fieldmail.getText();
        String fcombo = combopatient.getSelectedItem().toString();
        String fusername = fieldmail.getText();
        String fpassword = fieldpass.getText();
        String niveauA = comboniveau.getSelectedItem().toString();
        
      
         
        PreparedStatement ps;
        String query1 = "INSERT INTO `patient`(`nom`, `prenom`, `adresse`, `telephone`, `moyen_connaissance`, `email`, `id_type_patient`) VALUES (?,?,?,?,?,?,?)";
        
        
        try {
            ps = ConnectionManager.getConnection(ConnectionManager.databaseConfig).prepareStatement(query1);
            
            ps.setString(1, fnom);
            ps.setString(2, fprenom);
            ps.setString(3, fadresse);
            ps.setString(4, ftelephone);
            ps.setString(5, fmoyen);
            ps.setString(6, fmail);
            ps.setString(7, fcombo);
            
            
            if (ps.executeUpdate()>0){
               JOptionPane.showMessageDialog(null, "Nouveau patient ajouté");
               
               fieldnom.setText("");
               fieldprenom.setText("");
               fieldadresse.setText("");
               fieldtelephone.setText("");
               combomoyen.setSelectedItem(0);
               fieldmail.setText("");
               combomoyen.setSelectedItem(0);
               fieldpass.setText("");
            }
           
                
            
        } catch (SQLException ex) {
            Logger.getLogger(inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
         String query2 = "INSERT INTO `utilisateur`(`username`, `password`,`Type`) VALUES (?,?,?)";
        try {
            ps = ConnectionManager.getConnection(ConnectionManager.databaseConfig).prepareStatement(query2);
            
             ps.setString(1, fusername);
             ps.setString(2, fpassword);
             ps.setString(3, niveauA);
             
             if (ps.executeUpdate()>0){
               JOptionPane.showMessageDialog(null, "Nouvel utilisateur ajouté");
               
               updatescreen();
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combomoyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combomoyenItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_combomoyenItemStateChanged

    private void combopatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combopatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combopatientActionPerformed

    private void fieldpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldpassActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
         listes_professions p = new listes_professions();
         p.setVisible(true);
         this.hide();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Menu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu8MouseClicked
        // TODO add your handling code here:
        Archive p = new Archive();
        p.setVisible(true);
        this.hide();

    }//GEN-LAST:event_Menu8MouseClicked

    private void Menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu5MouseClicked
        // TODO add your handling code here:
        inscription p = new inscription();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_Menu5MouseClicked

    private void Menu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu6MouseClicked
        // TODO add your handling code here:
        listes_professions p = new listes_professions();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_Menu6MouseClicked

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

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        // TODO add your handling code here:

        Accueil A= new Accueil();
        A.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenu12MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        connexion p = new connexion();
        p.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inscription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu5;
    private javax.swing.JMenu Menu6;
    private javax.swing.JMenu Menu8;
    private javax.swing.JComboBox<String> combomoyen;
    private javax.swing.JComboBox<String> comboniveau;
    private javax.swing.JComboBox<String> combopatient;
    private javax.swing.JTextField fieldadresse;
    private javax.swing.JTextField fieldmail;
    private javax.swing.JTextField fieldnom;
    private javax.swing.JTextField fieldpass;
    private javax.swing.JTextField fieldprenom;
    private javax.swing.JTextField fieldtelephone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tble;
    // End of variables declaration//GEN-END:variables
}

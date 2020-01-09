/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import connect.DBException;
import connect.ulti1;
import connect.validate;
import dao.HocVienDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HocVien;
import model.NguoiHoc;

/**
 *
 * @author Tong Duy Hai
 */
public class quanLyHocVien1 extends javax.swing.JInternalFrame {
    ArrayList<HocVien> ls = new ArrayList<>();
//    ArrayList<NguoiHoc> ls = new ArrayList<>();
    DefaultTableModel model;
    int index;
    int maKH;
    String MaNH;
    String role;
    /**
     * Creates new form quanLyHocVien
     */
    public quanLyHocVien1(int s, String r) throws DBException {
        initComponents();
        buttonGroup1.add(rdbAll);
        buttonGroup1.add(rdbChuaNhapDiem);
        buttonGroup1.add(rdbDaNhapDiem);
        maKH = s;
        role = r;
//        MaNH = d;
        
        aTT();
        aCB();
        cbbKhoaHoc();
//        cbbMaHV();
        
        cbbNguoiHoc.setSelectedIndex(-1);
        cbbKhoaHoc.setSelectedIndex(-1);  

    }


       
    private void aTT() throws DBException{
        ls = HocVienDB.getAll();
        model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        
        for (HocVien hv : ls){
//            if (hv.getMaKH() == maKH){
                model.addRow(new Object[] {hv.getMaHV(),hv.getMaKH(), hv.getMaNH(), hv.getDiem()});
            
        }
        
        if (rdbAll.isSelected()){
            model.setRowCount(0);
            for (HocVien hv : ls){
                if (hv.getDiem()!= -1 && hv.getMaKH() == maKH){
                    model.addRow(new Object[] {hv.getMaHV(), hv.getMaNH(),hv.getDiem()});
                }
            }
        }
        if (rdbDaNhapDiem.isSelected()){
            model.setRowCount(0);
            for (HocVien hv : ls){
                if (hv.getDiem()== -1 && hv.getMaKH() == maKH){
                    model.addRow(new Object[] {hv.getMaHV(), hv.getMaNH(), hv.getDiem()});
                }
            }
        }
    }
    
private void aCB(){
cbbNguoiHoc.removeAllItems();
    try {
        String sql = "Select MANGUOIHOC from NGUOIHOC";
        Connection con = ulti1.linkToSQL();
        PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
//            ArrayList <NguoiHoc> ls = new ArrayList<>();
            
            while (rs.next()) {
                String MANGUOIHOC = rs.getString("MANGUOIHOC");
                cbbNguoiHoc.addItem(MANGUOIHOC);
            }
    } catch (Exception e) {
    }
    }
private void showInfor(){
    
        HocVien hv = ls.get(index);
        int selectRow = tblHocVien.getSelectedRow();
        
//        String cbbMaHocVien = (String) model.getValueAt(selectRow, 1);
//        for(int i = 0; i <this.cbbMaHocVien.getItemCount();i++){
//            if(this.cbbMaHocVien.getItemAt(i).equalsIgnoreCase(cbbMaHocVien)){
//                this.cbbMaHocVien.setSelectedIndex(i);
//            }
//            
//        }
        
        String cbbkhoaHoc =  model.getValueAt(selectRow, 1).toString();
        for(int i = 0; i < cbbKhoaHoc.getItemCount(); i++){
            if(cbbKhoaHoc.getItemAt(i).toString().equalsIgnoreCase(cbbkhoaHoc)){
                cbbKhoaHoc.setSelectedIndex(i);
            }
        }
        String cbbNguoiHoc =  model.getValueAt(selectRow, 2).toString();
        for(int i = 0; i < this.cbbNguoiHoc.getItemCount(); i++){
            if(this.cbbNguoiHoc.getItemAt(i).toString().equalsIgnoreCase(cbbNguoiHoc)){
                this.cbbNguoiHoc.setSelectedIndex(i);
            }
        }
        String diem = String.valueOf(hv.getDiem());
        txtDiem.setText(diem);

    }
private void cbbKhoaHoc(){
    cbbKhoaHoc.removeAllItems();
    try {
        String sql = "Select MAKHOAHOC from KHOAHOC";
        Connection con = ulti1.linkToSQL();
        PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
//            ArrayList <NguoiHoc> ls = new ArrayList<>();
            
            while (rs.next()) {
                String MAKHOAHOC = rs.getString("MAKHOAHOC");
                cbbKhoaHoc.addItem(MAKHOAHOC);
            }
    } catch (Exception e) {
    }
}

 
//    for(NguoiHoc nh : sl){
//        if(nh.getMaNH() !=  MaNH){
//            cbbNguoiHoc.addItem(nh.getMaNH());
//        }
//        
//    }
//}
//private void cbbMaHV(){
//    cbbMaHocVien.removeAllItems();
//    try {
//        String sql = "Select MAHOCVIEN from HOCVIEN";
//        Connection con = ulti1.linkToSQL();
//        PreparedStatement ps = con.prepareCall(sql);
//            ResultSet rs = ps.executeQuery();
//            
////            ArrayList <NguoiHoc> ls = new ArrayList<>();
//            
//            while (rs.next()) {
//                String MAHOCVIEN = rs.getString("MAHOCVIEN");
//                cbbMaHocVien.addItem(MAHOCVIEN);
//            }
//    } catch (Exception e) {
//    }
//}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cbbNguoiHoc = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        cbbKhoaHoc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        rdbAll = new javax.swing.JRadioButton();
        rdbDaNhapDiem = new javax.swing.JRadioButton();
        rdbChuaNhapDiem = new javax.swing.JRadioButton();
        btnUpdate = new javax.swing.JButton();

        setClosable(true);
        setTitle("QUẢN LÝ HỌC VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbbNguoiHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNguoiHocActionPerformed(evt);
            }
        });

        txtDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cbbKhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhoaHocActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Mã khóa học");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Mã người học");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Điểm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbKhoaHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbNguoiHoc, 0, 411, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnAdd)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(182, 182, 182))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtDiem)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cbbNguoiHoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbbKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Học viên khác");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Học viên trong khóa học");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học viên", "Mã khóa học", "Mã người học", "Điểm"
            }
        ));
        tblHocVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHocVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHocVien);

        rdbAll.setText("Tất cả");
        rdbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbAllActionPerformed(evt);
            }
        });

        rdbDaNhapDiem.setText("Đã nhập điểm");
        rdbDaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDaNhapDiemActionPerformed(evt);
            }
        });

        rdbChuaNhapDiem.setText("Chưa nhập điểm");
        rdbChuaNhapDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbChuaNhapDiemActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rdbAll)
                        .addGap(18, 18, 18)
                        .addComponent(rdbDaNhapDiem)
                        .addGap(18, 18, 18)
                        .addComponent(rdbChuaNhapDiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbAll)
                    .addComponent(rdbDaNhapDiem)
                    .addComponent(rdbChuaNhapDiem)
                    .addComponent(btnUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(470, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNguoiHocActionPerformed
//        ArrayList<HocVien> hv = ls;
        if (cbbNguoiHoc.getSelectedIndex() > -1){
//            txtDiem.setText(hv.getDiem());
        }
        else{
            txtDiem.setText("");
                   }
            // TODO add your handling code here:
    }//GEN-LAST:event_cbbNguoiHocActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
           try {
        if (cbbNguoiHoc.getSelectedIndex() > -1){
            if (txtDiem.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter student's score!");
            }
            else{
                if (validate.checkDouble(txtDiem.getText(), 0)){
                    HocVien hv = new HocVien();
//                        if (cbbNguoiHoc.getSelectedItem().equals(hv.getMaNH())){
                            hv.setMaNH(cbbNguoiHoc.getSelectedItem().toString());
                            hv.setMaKH(Integer.valueOf(cbbKhoaHoc.getSelectedItem().toString()));

                            hv.setDiem(Double.valueOf(txtDiem.getText()));

                       
                                HocVienDB.addHV(hv);
                                aTT();
                                aCB();
                                cbbKhoaHoc();
                                JOptionPane.showMessageDialog(this, "SUCCESS!");


                            }
//                        }
                    }
                }
            
        } catch (DBException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        }
               // TODO add your handling code here:
               // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
        if (cbbNguoiHoc.getSelectedIndex() > -1){
            if (txtDiem.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter student's score!");
            }
            else{
                if (validate.checkDouble(txtDiem.getText(), 0)){
                    HocVien hv = new HocVien();
//                        if (cbbNguoiHoc.getSelectedItem().equals(hv.getMaNH())){
                            hv.setMaNH(cbbNguoiHoc.getSelectedItem().toString());
                            hv.setMaKH(Integer.valueOf(cbbKhoaHoc.getSelectedItem().toString()));

                            hv.setDiem(Double.valueOf(txtDiem.getText()));

                       
                                HocVienDB.updateHV(hv);
                                aTT();
                                aCB();
                                cbbKhoaHoc();
                                JOptionPane.showMessageDialog(this, "SUCCESS!");


                            }
//                        }
                    }
                }
            
        } catch (DBException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(quanLyHocVien1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void rdbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAllActionPerformed
            try {
            aTT();
        } catch (DBException e) {

        }   // TODO add your handling code here:
    }//GEN-LAST:event_rdbAllActionPerformed

    private void rdbDaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDaNhapDiemActionPerformed
              try {
            aTT();
        } catch (DBException e) {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_rdbDaNhapDiemActionPerformed

    private void rdbChuaNhapDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbChuaNhapDiemActionPerformed
        try {
            // TODO add your handling code here:
            aTT();
        } catch (DBException e) {

        }          // TODO add your handling code here:
    }//GEN-LAST:event_rdbChuaNhapDiemActionPerformed

    private void cbbKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhoaHocActionPerformed

        if (cbbKhoaHoc.getSelectedIndex() > -1){
//            txtDiem.setText(hv.getDiem());
        }
        else{
            txtDiem.setText("");
                   }        // TODO add your handling code here:
    }//GEN-LAST:event_cbbKhoaHocActionPerformed

    private void tblHocVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHocVienMouseClicked
           index = tblHocVien.getSelectedRow();
           showInfor();
      if(evt.getClickCount() == 2 && index != -1){
          tblHocVien.setSelectionMode(0);
      }    // TODO add your handling code here:
    }//GEN-LAST:event_tblHocVienMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbKhoaHoc;
    private javax.swing.JComboBox<String> cbbNguoiHoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbAll;
    private javax.swing.JRadioButton rdbChuaNhapDiem;
    private javax.swing.JRadioButton rdbDaNhapDiem;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
}

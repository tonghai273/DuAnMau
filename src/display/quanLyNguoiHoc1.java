/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import connect.DBException;
import connect.ulti1;
import connect.validate;
import dao.nguoiHocDB;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NguoiHoc;

/**
 *
 * @author Tong Duy Hai
 */
public class quanLyNguoiHoc1 extends javax.swing.JInternalFrame {
    ArrayList<NguoiHoc> ls = new ArrayList<>();
    DefaultTableModel model;
    int index;
    String maNV;
    String role;
    /**
     * Creates new form quanLyNguoiHoc
     */
    public quanLyNguoiHoc1(String m,String r) throws DBException {
        initComponents();
        maNV = m;
        role = r;
        ATT();
        buttonGroup1.add(rdbNu);
        buttonGroup1.add(rdbNam);
//        offButton();
   
    }


     private void ATT()  throws DBException{
        ls = nguoiHocDB.getAll();
        
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for(NguoiHoc nh : ls){
            String sex;
            if(nh.isGioitinh()== true){
                sex = "Nam";
            }
            else{
                sex = "Nữ";
            }
            model.addRow(new Object[]{nh.getMaNH(), nh.getHoTen(), nh.getNgaySinh(), sex, nh.getPhone(), nh.getEmail(),nh.getGhiChu(), nh.getMaNV(), nh.getNgayDangKi()});
            
        }
    }
    private void showInfor(){
        NguoiHoc nh = ls.get(index);
        txtMaNguoiHoc.setText(nh.getMaNH());
        txtName.setText(nh.getHoTen());
        if(ls.get(index).isGioitinh()==true){
            rdbNam.setSelected(true);
        }
        else{
            rdbNu.setSelected(true);
        }
        txtNgaySinh.setText(nh.getNgaySinh());
        txtPhone.setText(nh.getPhone());
        txtEmail.setText(nh.getEmail());
        txtGhiChu.setText(nh.getGhiChu());
    }
    private void Delete(){
        txtMaNguoiHoc.setText("");
        txtName.setText("");
        txtNgaySinh.setText("");
        rdbNam.setSelected(false);
        rdbNu.setSelected(false);
        txtPhone.setText("");
        txtGhiChu.setText("");
        txtEmail.setText("");
   
        
        jTable1.clearSelection();
    }
//    private void onButton(){
//        btnThem.setEnabled(false);
//        btnUpdate.setEnabled(true);
//        btnDelete.setEnabled(true);
//        btnSave.setEnabled(true);
//
//        
//        txtMaNguoiHoc.setEditable(false);
//    }
//    private void offButton(){
//        btnThem.setEnabled(true);
//        btnUpdate.setEnabled(false);
//        btnDelete.setEnabled(false);
//        btnSave.setEnabled(false);
//
//        txtMaNguoiHoc.setEditable(true);
//    }
    private boolean checkNull(){
        if (txtMaNguoiHoc.getText().equals("") || txtName.getText().equals("") || txtNgaySinh.getText().equals("") || txtPhone.getText().equals("") || txtEmail.getText().equals("") || txtGhiChu.getText().equals("")){
            JOptionPane.showMessageDialog(this, "please fill all the information!");
            return false;
        }
        else{
            return true;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtMaNguoiHoc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        setClosable(true);
        setTitle("QUẢN LÝ NGƯỜI HỌC");

        jLabel1.setText("Mã người học");

        jLabel2.setText("Họ và tên");

        jLabel3.setText("Giới tính");

        rdbNam.setText("Nam");

        rdbNu.setText("Nữ");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Email");

        jLabel7.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnThem.setText("Mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Sync.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NH", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Ghi chú", "Mã nhân viên", "Ngày đăng kí"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnSearch)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdbNam)
                                        .addGap(40, 40, 40)
                                        .addComponent(rdbNu))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtMaNguoiHoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                                        .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbNam)
                            .addComponent(rdbNu))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
 try{
            if (checkNull()){
                if (validate.checkLength(txtMaNguoiHoc.getText(), 7)){
                    if (validate.checkDuplicate("MANGUOIHOC", "NGUOIHOC", txtMaNguoiHoc.getText())){
                        if (validate.checkName(txtName.getText())){
                            if (validate.checkDate(txtNgaySinh.getText(), 1)){
                                if (validate.checkPhone(txtPhone.getText())){
                                    if (validate.checkMail(txtEmail.getText())){
                                        NguoiHoc nh = new NguoiHoc();
                                        nh.setMaNH(txtMaNguoiHoc.getText());
                                        nh.setHoTen(txtName.getText());
                                        nh.setNgaySinh(txtNgaySinh.getText());
                                        boolean sex;
                                        if(rdbNam.isSelected()){
                                            sex = true;
                                        }
                                        else{
                                            sex = false;
                                        }
                                        nh.setGioitinh(sex);
                                        nh.setPhone(txtPhone.getText());
                                        nh.setEmail(txtEmail.getText());
                                        nh.setGhiChu(txtGhiChu.getText());
                                        nh.setMaNV(maNV);

                                        nguoiHocDB.addNH(nh);
                                        JOptionPane.showMessageDialog(this, "SUCCESS!");
                                        ATT();
                                        index = 0;
//                                        onButton();
                                        for (int i = 0; i < ls.size(); i++){
                                            if (txtMaNguoiHoc.getText().equals((String) jTable1.getValueAt(i, 0))){
                                                index = i;
                                                jTable1.setRowSelectionInterval(index, index);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch(DBException | HeadlessException e ){
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(quanLyNguoiHoc1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(quanLyNguoiHoc1.class.getName()).log(Level.SEVERE, null, ex);
        }          // TODO add your handling code here:          // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       int chk = 0;
        String text = txtSearch.getText();
        for (int i = 0; i < jTable1.getRowCount(); i++){
            if (text.equalsIgnoreCase((String) jTable1.getValueAt(i, 0))){
                index = i;
                jTable1.setRowSelectionInterval(index, index);
                showInfor();
                chk++;
            }
        }
        if (chk == 0){
            JOptionPane.showMessageDialog(this, "STUDENT NOT FOUND!");
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try{
            if (checkNull()){
                if (validate.checkName(txtName.getText())){
                    if (validate.checkDate(txtNgaySinh.getText(), 1)){
                        if (validate.checkPhone(txtPhone.getText())){
                            if (validate.checkMail(txtEmail.getText())){
                                NguoiHoc nh = ls.get(index);
                                nh.setHoTen(txtName.getText());
                                nh.setNgaySinh(txtNgaySinh.getText());
                                boolean sex;
                                        if(rdbNam.isSelected()){
                                            sex = true;
                                        }
                                        else{
                                            sex = false;
                                        }
                                        nh.setGioitinh(sex);
                                nh.setPhone(txtPhone.getText());
                                nh.setEmail(txtEmail.getText());
                                nh.setGhiChu(txtGhiChu.getText());
                                nh.setMaNV(maNV);

                                nguoiHocDB.UpDateNH(nh);

                                JOptionPane.showMessageDialog(this, "SUCCESS!");
                                ATT();
                            }
                        }
                    }
                }
            }
        }catch(DBException | HeadlessException e ){
            
        } catch (IOException ex) {
            Logger.getLogger(quanLyNguoiHoc1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(quanLyNguoiHoc1.class.getName()).log(Level.SEVERE, null, ex);
        }
         // TODO add your handling code here:
//         NguoiHoc nh = ls.get(index);
//         try {
//            String sql = "update NGUOIHOC set MANGUOIHOC =? HOTEN = ?,NGAYSINH = ?, GIOITINH = ?, DIENTHOAI = ?, EMAIL =?, GHICHU = ?,MANV =? where MANGUOIHOC = ?";
//            Connection con = ulti1.linkToSQL();
//            PreparedStatement ps = con.prepareCall(sql);
//            ps.setString(1, nh.getMaNH());
//            ps.setString(2, nh.getHoTen());
//            ps.setString(3, nh.getNgaySinh());
//            ps.setInt(4, nh.getGioitinh());
//            ps.setString(5, nh.getPhone());
//            ps.setString(6, nh.getEmail());
//            ps.setString(7, nh.getGhiChu());
//            ps.setString(8,nh.getMaNH());
//            
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//
//        } catch (DBException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        }
         // TODO add your handling code here:
//         NguoiHoc nh = ls.get(index);
//         try {
//            String sql = "update NGUOIHOC set MANGUOIHOC =? HOTEN = ?,NGAYSINH = ?, GIOITINH = ?, DIENTHOAI = ?, EMAIL =?, GHICHU = ?,MANV =? where MANGUOIHOC = ?";
//            Connection con = ulti1.linkToSQL();
//            PreparedStatement ps = con.prepareCall(sql);
//            ps.setString(1, nh.getMaNH());
//            ps.setString(2, nh.getHoTen());
//            ps.setString(3, nh.getNgaySinh());
//            ps.setInt(4, nh.getGioitinh());
//            ps.setString(5, nh.getPhone());
//            ps.setString(6, nh.getEmail());
//            ps.setString(7, nh.getGhiChu());
//            ps.setString(8,nh.getMaNH());
//            
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//
//        } catch (DBException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(quanLyNguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
     Delete();
             // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
  if(role.equals("0")){
         JOptionPane.showMessageDialog(this, "Không sử dụng được chức năng này!", "WARNING", JOptionPane.WARNING_MESSAGE);
         
     }
     else{
         NguoiHoc nh = ls.get(index);
     
        try {
            nguoiHocDB.DeleteNH(nh);
            JOptionPane.showMessageDialog(this, "Thành Công!");
            ATT();
            Delete();
        } catch (DBException | HeadlessException e) {
        }
     }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      index = jTable1.getSelectedRow();
      showInfor();
      if(evt.getClickCount() == 2 && index != -1){
          jTable1.setSelectionMode(0);
      }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaNguoiHoc;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

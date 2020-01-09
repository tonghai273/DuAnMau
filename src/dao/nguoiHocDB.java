/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBException;
import connect.ulti1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiHoc;

/**
 *
 * @author Binh Chiron
 */
public class nguoiHocDB {
      private static NguoiHoc getNguoiHoc(ResultSet rs) throws SQLException{
        String MaNH = rs.getString(1);
        String hoTen = rs.getString(2);
        String ngaySinh = rs.getString(3);
        boolean gioiTinh = rs.getBoolean(4);
        String phone = rs.getString(5);
        String email = rs.getString(6);
        String ghiChu = rs.getString(7);
        String MaNV = rs.getString(8);
        String ngayDangKi = rs.getString(9);
        
        
        
        NguoiHoc nh = new NguoiHoc();
        nh.setMaNH(MaNH);
        nh.setHoTen(hoTen);
        nh.setNgaySinh(ngaySinh);
        nh.setGioitinh(gioiTinh);
        nh.setPhone(phone);
        nh.setEmail(email);
        nh.setGhiChu(ghiChu);
        nh.setMaNV(MaNV);
        nh.setNgayDangKi(ngayDangKi);
        
        
        return nh;
        
        
        
        
    }
    public static ArrayList <NguoiHoc> getAll() throws DBException{
        String sql = "Select * from NguoiHoc";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <NguoiHoc> ls = new ArrayList<>();
            
            while (rs.next()) {
                ls.add(getNguoiHoc(rs));
                
            }
            rs.close();
            return ls;
        } catch (Exception e) {
            throw new DBException(e);
        }
        finally{
            ulti1.disCon();
        }
        
    }
    public static NguoiHoc getNH(String MaNH) throws DBException{
        String sql = "Select * from NguoiHoc where MaNH = ?";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NguoiHoc nv = getNguoiHoc(rs);
                rs.close();
                return nv;
                
            }
            else{
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        finally{
            ulti1.disCon();
        }
        
    }

    public static void addNH(NguoiHoc nh) throws DBException{
        String sql = "insert into NGUOIHOC (MANGUOIHOC,HOTEN,NGAYSINH,GIOITINH,DIENTHOAI,EMAIL,GHICHU,MANHANVIEN) values (?,?,?,?,?,?,?,?)";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1,nh.getMaNH());
            ps.setString(2, nh.getHoTen());
            ps.setString(3, nh.getNgaySinh());
            ps.setBoolean(4, nh.isGioitinh());
            ps.setString(5, nh.getPhone());
            ps.setString(6, nh.getEmail());
            ps.setString(7, nh.getGhiChu());
            ps.setString(8, nh.getMaNV());
            
            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        finally{
            ulti1.disCon();
        }
        
    }
    
    public static void UpDateNH(NguoiHoc nh) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        
        
        String sql = "update NGUOIHOC set MANGUOIHOC =?, HOTEN = ?,NGAYSINH = ?, GIOITINH = ?, DIENTHOAI = ?, EMAIL =?, GHICHU = ?,MANHANVIEN =? where MANGUOIHOC = ?";
        Connection con = ulti1.linkToSQL();

        try {
//            String sql = "update NGUOIHOC set MANGUOIHOC =? HOTEN = ?,NGAYSINH = ?, GIOITINH = ?, DIENTHOAI = ?, EMAIL =?, GHICHU = ?,MANV =? where MANGUOIHOC = ?";
//            Connection con = ulti1.linkToSQL();
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, nh.getMaNH());
            ps.setString(2, nh.getHoTen());
            ps.setString(3, nh.getNgaySinh());
            ps.setBoolean(4, nh.isGioitinh());
            ps.setString(5, nh.getPhone());
            ps.setString(6, nh.getEmail());
            ps.setString(7, nh.getGhiChu());
             ps.setString(8, nh.getMaNV());
            ps.setString(9,nh.getMaNH());
   
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("eeeee"+e);
            throw new DBException(e);
        }
        finally{
            ulti1.disCon();
        }
    }
    
    public static void DeleteNH(NguoiHoc nh) throws DBException{
        String sql = "delete NGUOIHOC where MANGUOIHOC = ?";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nguoiHocDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, nh.getMaNH());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new DBException(e);
        }
        finally{
            ulti1.disCon();
        }
    }
    public static ArrayList <String> getNgayDK() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select distinct YEAR(NGAYDANGKI) from NGUOIHOC";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <String> ls = new ArrayList<>();
            
            while (rs.next()){
                ls.add(rs.getString(1));
            }
            
            rs.close();
            return ls;
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
}

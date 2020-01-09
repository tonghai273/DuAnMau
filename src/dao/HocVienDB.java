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
import static javax.management.Query.value;

import model.HocVien;

/**
 *
 * @author Binh Chiron
 */
public class HocVienDB {
    private static HocVien getHocVien(ResultSet rs) throws DBException, SQLException{
        String maHV = rs.getString(1);
        int maKH = rs.getInt(2);
        String maNH = rs.getString(3);
        double Diem = rs.getDouble(4);
        
        
        
        HocVien hv = new HocVien();
        hv.setMaHV(maHV);
        hv.setMaKH(maKH);
        hv.setMaNH(maNH);
        hv.setDiem(Diem);
        
        
        return hv;
        
    }
        public static ArrayList <HocVien> getAll() throws DBException{
        String sql = "select * from HOCVIEN join NGUOIHOC on HOCVIEN.MANGUOIHOC = NGUOIHOC.MANGUOIHOC";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <HocVien> ls = new ArrayList<>();
            
            while(rs.next()){
                ls.add(getHocVien(rs));
            }
            
            rs.close();
            return ls;
        }catch(Exception e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
          public static HocVien getHV(String maHV) throws DBException{
        String sql = "select * from HOCVIEN join NGUOIHOC on HOCVIEN.MANGUOIHOC = NGUOIHOC.MANGUOIHOC where MAHOCVIEN = ?";
        Connection con = null;
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, maHV);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                HocVien hv = getHocVien(rs);
                rs.close();
                return hv;
            }
            else{
                rs.close();
                return null;
            }
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
            public static void addHV(HocVien hv) throws DBException, IOException, FileNotFoundException, FileNotFoundException, ClassNotFoundException{
        String sql = "insert into HOCVIEN (MAKHOAHOC,MANGUOIHOC,DIEMTRUNGBINH) values (?,?,?)";
        Connection con = ulti1.linkToSQL();
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(2, hv.getMaNH());
            ps.setInt(1, hv.getMaKH());
            ps.setDouble(3, hv.getDiem());
            
            ps.executeUpdate();
        }catch(Exception e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static void updateHV(HocVien hv) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "update HOCVIEN set DIEMTRUNGBINH = ? where MANGUOIHOC = ?";
        Connection con = ulti1.linkToSQL();
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(2, hv.getMaNH());
            ps.setDouble(1, hv.getDiem());
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static void deleteHV(int maHV) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "delete HOCVIEN where MAHOCVIEN = ?";
        Connection con = ulti1.linkToSQL();
        try {
            con = ulti1.linkToSQL();
        } catch (IOException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, maHV);
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
}

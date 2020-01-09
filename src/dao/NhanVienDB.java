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
import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDB {
    private static NhanVien getNhanVien(ResultSet rs) throws SQLException{
        String maNV = rs.getString(1);
        String mK = rs.getString(2);
        String hoTen = rs.getString(3);
        String role = rs.getString(4);
        
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setmK(mK);
        nv.setHoTen(hoTen);
        nv.setRole(role);
        
        return nv;
    }
    
    public static ArrayList <NhanVien> getAll() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select * from NHANVIEN";
        ArrayList <NhanVien> ls = new ArrayList<>();
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                NhanVien nv = getNhanVien(rs);
                ls.add(nv);
            }
            rs.close();
            return ls;
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static NhanVien getNV(String maNV) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select * from NHANVIEN where MANHANVIEN = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                NhanVien nv = getNhanVien(rs);
                rs.close();
                return nv;
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
    
    public static void addNV(NhanVien nv) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "insert into NHANVIEN (MANHANVIEN, MATKHAU, HOTEN, VAITRO) values (?,?,?,?)";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getmK());
            ps.setString(3, nv.getHoTen());
            ps.setString(4, nv.getRole());
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static void updateNV(NhanVien nv) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "update NHANVIEN set MATKHAU = ?, HOTEN = ?, VAITRO = ? where MANHANVIEN = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, nv.getmK());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getRole());
            ps.setString(4, nv.getMaNV());
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static void updatePass(String pass, String maNV) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "update NHANVIEN set MATKHAU = ? where MANHANVIEN = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, pass);
            ps.setString(2, maNV);
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
    
    public static void delNV(NhanVien nv) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "delete NHANVIEN where MANHANVIEN = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, nv.getMaNV());
            
            ps.executeUpdate();
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
            ulti1.disCon();
        }
    }
}
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
import model.khoaHoc;

/**
 *
 * @author Tong Duy Hai
 */
public class khoaHocDB {
    public static ArrayList <khoaHoc> getAll() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select * from KHOAHOC";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList <khoaHoc> ls = new ArrayList<>();
            while(rs.next()){
                khoaHoc kh = new khoaHoc();
                kh.setMaKH(rs.getInt("MAKHOAHOC"));
                kh.setMaCD(rs.getString("MACHUYENDE"));
                kh.setHocPhi(rs.getFloat("HOCPHI"));
                kh.setTime(rs.getInt("THOILUONG"));
                kh.setNgayKG(rs.getString("NGAYKHAIGIANG"));
                kh.setGhiChu(rs.getString("GHICHU"));
                kh.setMaNV(rs.getString("MANHANVIEN"));
                kh.setNgayTao(rs.getString("NGAYTAO"));
                ls.add(kh);
            }
            rs.close();
            return ls;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
        return null;
    }
    public static void addKH(khoaHoc kh) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "insert into KHOAHOC (MACHUYENDE,HOCPHI,THOILUONG,NGAYKHAIGIANG,GHICHU,MANHANVIEN,NGAYTAO) values (?,?,?,?,?,?,?)";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaCD());
            ps.setDouble(2, kh.getHocPhi());
            ps.setInt(3, kh.getTime());
            ps.setString(4, kh.getNgayKG());
            ps.setString(5, kh.getGhiChu());
            ps.setString(6, kh.getMaNV());
            ps.setString(7, kh.getNgayTao());
            
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
    }
    public static void xoaKH(khoaHoc kh){
        String sql = "delete KHOAHOC where MAKHOAHOC = ?";
        try{
            Connection con = ulti1.linkToSQL();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, kh.getMaKH());
            
            ps.executeUpdate();
        }catch(Exception e){
            
        }finally{
            ulti1.disCon();
        }
    }
    public static void capNhatKhoaHoc(khoaHoc kh) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "update KHOAHOC set MACHUYENDE = ?, HOCPHI = ?, THOILUONG = ?, NGAYKHAIGIANG = ?, GHICHU = ?, MANHANVIEN = ?, NGAYTAO = ? where MAKHOAHOC = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, kh.getMaCD());
            ps.setDouble(2, kh.getHocPhi());
            ps.setInt(3, kh.getTime());
            ps.setString(4, kh.getNgayKG());
            ps.setString(5, kh.getGhiChu());
            ps.setString(6, kh.getMaNV());
            ps.setString(7, kh.getNgayTao());
            ps.setInt(8, kh.getMaKH());
            
            ps.executeUpdate();
        }catch(Exception e){
            
        }finally{
            ulti1.disCon();
        }
    }
    public static ArrayList <String> getMaKH() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select MAKHOAHOC from KHOAHOC";
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
    public static ArrayList <String> getNgayKG() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select distinct YEAR(NGAYKHAIGIANG) from KHOAHOC";
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
    public static boolean kiemTraNV(String manv){
        String sql = "select * from NHANVIEN where MANHANVIEN ='"+manv+"' ";
        try{
            Connection con = ulti1.linkToSQL();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList <String> list = new ArrayList<>();
            while (rs.next()){
                return true;
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
        return false;
    }
        
}

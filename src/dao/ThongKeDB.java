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

/**
 *
 * @author Phuong Anh
 */
public class ThongKeDB {
    public static ArrayList <Object []> getNguoiHoc() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "{call  sp_ThongKeNguoiHoc}";
                    Connection con = ulti1.linkToSQL();

        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <Object []> ls = new ArrayList<>();
            
            while (rs.next()){
                ls.add(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            
            rs.close();
            return ls;
        }catch(SQLException e){
           throw new DBException(e);
        }finally{
                       ulti1.disCon();

        }
    }
    
    public static ArrayList <Object[]> getBangDiem(int maKH) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "{call sp_BangDiem (?)}";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, maKH);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <Object[]> ls = new ArrayList<>();
            
            while (rs.next()){
                String hL;
                if (rs.getDouble(3) < 0){
                    hL = "Chưa có điểm!";
                }
                else if (rs.getDouble(3) < 3) {
                    hL = "Kém";
                }
                else if (rs.getDouble(3) < 5) {
                    hL = "Yếu";
                }
                else if (rs.getDouble(3) < 6.5) {
                    hL = "Trung bình";
                }
                else if (rs.getDouble(3) < 7.5) {
                    hL = "Khá";
                }
                else if (rs.getDouble(3) < 9.5) {
                    hL = "Giỏi";
                }
                else {
                    hL = "Xuất sắc";
                }
                
                ls.add(new Object[] {rs.getString(1), rs.getString(2), rs.getDouble(3), hL});
            }
            
            rs.close();
            return ls;
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
                        ulti1.disCon();

        }
    }
    
    public static ArrayList <Object[]> getThongKeDiem() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "{call sp_ThongKeDiem}";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <Object[]> ls = new ArrayList<>();
            
            while (rs.next()){
                ls.add(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5)});
            }
            
            rs.close();
            return ls;
        }catch(SQLException e){
            throw new DBException(e);
        }finally{
                        ulti1.disCon();

        }
    } 
    
    public static ArrayList <Object[]> getDoanhThu(int year) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "{call sp_ThongKeDoanhThu (?)}";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            
            ArrayList <Object[]> ls = new ArrayList<>();
            
            while (rs.next()){
                ls.add(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
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

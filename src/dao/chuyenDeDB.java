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
import model.chuyenDe;

/**
 *
 * @author Tong Duy Hai
 */
public class chuyenDeDB {
    public static ArrayList<chuyenDe> getAll() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select * from CHUYENDE";
        ArrayList <chuyenDe> ls = new ArrayList<>();
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                chuyenDe cd = new chuyenDe();
                cd.setMaCD(rs.getString("MACHUYENDE"));
                cd.setTenCD(rs.getString("TENCHUYENDE"));
                cd.setHocPhi(Double.parseDouble(rs.getString("HOCPHI")));
                cd.setTime(Integer.parseInt(rs.getString("THOILUONG")));
                cd.setMoTa(rs.getString("MOTA"));
                cd.setImage(rs.getString("HINH"));
                ls.add(cd);
            }
            rs.close();
            return ls;
        }
        catch(Exception e){
            
        }finally{
            ulti1.disCon();
        }
        return null;
    }
    public static void updateCD(chuyenDe cd) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "update CHUYENDE set TENCHUYENDE = ?, HOCPHI = ?, THOILUONG = ?,MOTA = ?, HINH = ? where MACHUYENDE = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cd.getTenCD());
            ps.setDouble(2, cd.getHocPhi());
            ps.setInt(3, cd.getTime());
            ps.setString(4, cd.getMoTa());
            ps.setString(5, cd.getImage());
            ps.setString(6, cd.getMaCD());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
    }
    public static void addCD(chuyenDe cd){
        String sql = "insert into CHUYENDE (MACHUYENDE, TENCHUYENDE, HOCPHI, THOILUONG, MOTA, HINH) values (?,?,?,?,?,?)";
        try{
            Connection con = ulti1.linkToSQL();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cd.getMaCD());
            ps.setString(2, cd.getTenCD());
            ps.setDouble(3, cd.getHocPhi());
            ps.setInt(4, cd.getTime());
            ps.setString(5, cd.getMoTa());
            ps.setString(6, cd.getImage());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
    }
    public static void xoaCD(chuyenDe cd) throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "delete CHUYENDE where MACHUYENDE = ?";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, cd.getMaCD());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
    }
    public static ArrayList<String> getMaCD() throws DBException, IOException, FileNotFoundException, ClassNotFoundException{
        String sql = "select MACHUYENDE from CHUYENDE";
        Connection con = ulti1.linkToSQL();
        try{
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList <String> list = new ArrayList<>();
            
            while (rs.next()){
                list.add(rs.getString("MACHUYENDE"));
            }
            rs.close();
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
        return null;
    }
    public static boolean kiemTraTrungChuyenDe(String macd){
        String sql = "select * from CHUYENDE where MACHUYENDE ='"+macd+"' ";
        try{
            Connection con = ulti1.linkToSQL();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList <String> list = new ArrayList<>();
            while (rs.next()){
                return false;
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ulti1.disCon();
        }
        return true;
    }
}

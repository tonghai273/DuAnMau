/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * author 500PING-LT@
 */
public class validate {

    public static boolean checkInt(String s) {
        try {
            int number = Integer.valueOf(s);
            if (number <= 0) {
                JOptionPane.showMessageDialog(null, "Thời gian phải lớn hơn 0");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Phải nhập thời gian");
            return false;
        }
    }

    /**
     * @param s - Chuoi can kiem tra
     * @param i - Chuc nang kiem tra, neu i = 0 se kiem tra diem con i != 0 thi se kiem tra gia tien
     * @return 
     */
    public static boolean checkDouble(String s, int i) {
        try {
            Double number = Double.valueOf(s);

            if (i == 0) {
                if (number < 0 || number > 10) {
                    JOptionPane.showMessageDialog(null, "Điểm từ 1 đến 10");
                    return false;
                } else {
                    return true;
                }
            } else {
                if (number <= 0) {
                    JOptionPane.showMessageDialog(null, "Số phải lớn hơn 0");
                    return false;
                } else {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Phải nhập điểm");
            } else {
                JOptionPane.showMessageDialog(null, "Phải nhập học phí");
            }
            return false;
        }
    }

    public static boolean checkMail(String s) {
        String mailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (s.matches(mailRegex)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Phải nhập email");
            return false;
        }
    }

    /**
     * @param s - Chuoi can kiem tra
     * @param i - Chuc nang kiem tra, neu i = 0 se kiem tra ngay phai lon hon ngay hien tai, i = 1 se kiem tra tuoi >= 16, con lai thi chi kiem tra dinh dang ngay
     * @return 
     */
    public static boolean checkDate(String s, int i) {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date d1 = sdf.parse(s);
            Date yesterday = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));

            switch (i) {
                case 0:
                    if (d1.before(yesterday)) {
                        JOptionPane.showMessageDialog(null, "Some date must equal or higher than today!");
                        return false;
                    } else {
                        return true;
                    }
                case 1:
                    int age = new Date().getYear() - d1.getYear();

                    if (age < 16) {
                        JOptionPane.showMessageDialog(null, "Tuổi phải lớn hơn 16");
                        return false;
                    } else {
                        return true;
                    }
                default:
                    return true;
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Nhập ngày định dạng (yyyy-MM-dd)");
            return false;
        }
    }

    public static boolean checkLength(String s, int length) {
        if (s.length() != length) {
            JOptionPane.showMessageDialog(null, s + " quá ngắn!, cần ít nhất "+length+" kí tự");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPass(String x1) {
        if (x1.length() < 3) {
            JOptionPane.showMessageDialog(null, "Mật khẩu cần ít nhất 3 kí tự");
            return false;
        } 
                return true;
            }
        
    


    public static boolean checkDuplicate(String key, String data, String check) throws DBException, IOException, FileNotFoundException, ClassNotFoundException {
        String sql = "select " + key + " from " + data;
        Connection con = ulti1.linkToSQL();
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;

            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase(check)) {
                    JOptionPane.showMessageDialog(null, check + " đã tồn tại!");
                    i++;
                    break;
                }
            }

            rs.close();
            return i == 0;

        } catch (HeadlessException | SQLException e) {
            throw new DBException(e);
        } finally {
            ulti1.disCon();
        }
    }

    public static boolean checkPhone(String s) {
        String phoneRegex = "0\\d{9,10}";

        if (s.matches(phoneRegex)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Số điện thoại bắt đầu từ 0 và có 10 hoặc 11 số");
            return false;
        }
    }

    public static boolean checkName(String s) {
        String nameRegex = "^\\p{L}+[\\p{L}\\p{Pd}\\p{Zs}']*\\p{L}+$|^\\p{L}+$";
        if (!s.matches(nameRegex)) {
            JOptionPane.showMessageDialog(null, "Tên chỉ nhập chữ và dấu cách!");
            return false;
        } else {
            return true;
        }
    }
}

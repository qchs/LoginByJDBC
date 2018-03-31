package LoginByJDBC.src;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Lenovo on 2018/3/31.
 * 事务 TEST
 */
public class Demo4 {
    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        PreparedStatement ps=null;
        ResultSet rs=null;


        try {
            //读取属性内容
            FileReader reader=new FileReader("D:\\Java\\src2\\LoginByJDBC\\src\\server.properties");
            Properties pros=new Properties();
            pros.load(reader);
            reader.close();

            String server = pros.getProperty("server");
            String url=pros.getProperty("url");
            String user=pros.getProperty("user");
            String password=pros.getProperty("password");

            //注册驱动
            Class.forName(server);
            //获取数据库连接
            conn= DriverManager.getConnection(url,user,password);
            //关闭自动提交
            conn.setAutoCommit(false);
            //sql语句框架
            //插入
            String sql_ins="insrt into userinfo(userName,pwd) values (?,?)";
            //sql语句预编译
            ps=conn.prepareStatement(sql_ins);
            //            //对占位符赋值
            ps.setString(1,"yan1");
            ps.setString(2,"86000000");
            int c = ps.executeUpdate();
//            System.out.println(c);
//            ps.setString(1,"yan2");
//            ps.setString(2,"86000000");
//
//            c+=ps.executeUpdate();
            System.out.println(c);
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}

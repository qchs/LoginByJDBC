package LoginByJDBC.src;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Lenovo on 2018/3/31.
 * CRUD TEST
 */
public class Demo3 {
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
            System.out.println(conn);
            //sql语句框架
            //插入
            String sql_ins="insert into userinfo(userName,pwd) values (?,?)";
            //sql语句预编译
            ps=conn.prepareStatement(sql_ins);
            //            //对占位符赋值
            ps.setString(1,"yan1");
            ps.setString(2,"86000000");
            int c = ps.executeUpdate();
            System.out.println(c);
            ps.setString(1,"yan2");
            ps.setString(2,"86000000");

            c+=ps.executeUpdate();
            System.out.println(c);

//          更新
            String sql_upd="update userinfo set userName=? where pwd=?";
            ps=conn.prepareStatement(sql_upd);
            ps.setString(1,"yan2");
            ps.setString(2,"86000000");

            int c3=ps.executeUpdate();
            System.out.println(c3);

            //删除
            String sql_del="delete from userinfo where username=?";
            ps=conn.prepareStatement(sql_del);
            ps.setString(1,"yan2");
            int c4=ps.executeUpdate();
            System.out.println(c4);
//            stmt=conn.createStatement();
//            String sql="insert into userinfo(userName,pwd) values (?,?)";
//
//            int c1 = stmt.executeUpdate(sql);
//            System.out.println(c1);
//
////            String sql2="insert into userinfo(userName,pwd) values ('yan4','677546')";
//            c1 += stmt.executeUpdate(sql);
//            System.out.println(c1);

        } catch (Exception e) {
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

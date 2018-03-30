package LoginByJDBC.src;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Lenovo on 2018/3/30.
 */
public class Demo {
    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
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
            //获取数据库操作对象
            stmt=conn.createStatement();
            //执行sql语句
            //增删改：
            String sql_ins="insert into userinfo values (\"cai \",\"bgsn\");";
            int c =stmt.executeUpdate(sql_ins);
            System.out.println(c);

            String sql_del=" delete from userinfo where userName=\"cai\"";
             int c1=stmt.executeUpdate(sql_del);
            System.out.println(c1);

            String sql_upd="update userinfo set userName=\"f\" where pwd=\"bgsn\"";
            int c2 = stmt.executeUpdate(sql_upd);
            System.out.println(c2);
            //查询：
            String sql="select * from userinfo";
            rs=stmt.executeQuery(sql);
            System.out.println(rs);
            //处理查询结果集；
            while (rs.next()){
                String userName=rs.getString(1);
                String pwd=rs.getString(2);
                System.out.println(userName+"\t"+pwd);
            }


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

            if (stmt != null) {
                try {
                    stmt.close();
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

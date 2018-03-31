package LoginByJDBC.src;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Lenovo on 2018/3/30.
 * 解决sql注入问题，采用定框架、预编译的方法
 */
public class Demo2 {
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
//            String sql_in="select userName from userinfo where userName=? and pwd=?";
            String sql_in="select * from userinfo where pwd like ?";
            //sql语句预编译
            ps=conn.prepareStatement(sql_in);

//            System.out.println("欢迎登陆！");
//            Scanner sc=new Scanner(System.in);
//            System.out.println("请输入用户名：");
//            String userName = sc.next();
//            sc.useDelimiter("\n");
//            System.out.println("请输入密码：");
//            String pwd=sc.next();
////            pwd="asd\' or \'aa\'=\'aa";
//            System.out.println(pwd);
//
//            //对占位符赋值
            ps.setString(1,"%f%");
//            ps.setString(2,pwd);
//            //查询：
//            rs=ps.executeQuery();
//            System.out.println(rs);
//
//            if (rs.next()) {
//                System.out.println("登录成功");
//            }else {
//                System.out.println("登录失败");
//            }
            //不使用占位符，就是想打印出所有数据，ps返回的数据集有吗
            rs = ps.executeQuery();
            System.out.println(sql_in);

            while (rs.next()){
                System.out.println(rs.getString("pwd"));
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

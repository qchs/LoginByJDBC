package LoginByJDBC.src;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Lenovo on 2018/3/30.
 */
public class Demo {
    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        int c=0;
        System.out.println("欢迎注册！");
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = sc.next();
        System.out.println("请输入密码:");
        String pwd=sc.next();

        String t="insert into userinfo values ("+"\""+userName+"\""+","+"\""+pwd+"\""+")";
        System.out.println(t);
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


//            String sql_ins="insert into userinfo values (\"wang\",\"ejjfhidf\")";
            String sql_ins="insert into userinfo values ('"+userName+"','"+pwd+"')";
            c =stmt.executeUpdate(sql_ins);
            System.out.println(c);
            if (c == 1) {
                System.out.println("恭喜注册成功！");
            }else {
                System.out.println("注册失败！");

            }

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

            while (rs.next()){
                String userName1=rs.getString(1);
                String pwd1=rs.getString(2);
//                if (pwd1.equals(pwd) && userName1.equals(userName)) {
//                    System.out.println("登录成功");
//                    break;
//                }
                System.out.println(userName1+"\t"+pwd1);
            }


            System.out.println("欢迎登陆！");
            sc=new Scanner(System.in);
            System.out.println("请输入用户名：");
            userName = sc.next();
            sc.useDelimiter("\n");
            System.out.println("请输入密码：");
            pwd=sc.next();
//            pwd="asd\' or \'aa\'=\'aa";
            System.out.println(pwd);


            String login="select userName from userinfo where userName='"+userName+"' and pwd='"+pwd   +"'";
//            select userName from userinfo where userName='rff' and pwd='as' or 'ff'='ff'
//            即等同于select userName from userinfo where 'ff'='ff' （恒为真，这就是sql注入）
            //处理查询结果集；
            System.out.println(login);
            rs=stmt.executeQuery(login);
//            if (rs.next()) {
//                System.out.println("登陆成功！");
//            }else {
//                System.out.println("登录失败！");
//            }


            while (rs.next()){
                String userName1=rs.getString(1);
                String pwd1=rs.getString(2);
                if (pwd1.equals(pwd) && userName1.equals(userName)) {
                    System.out.println("登录成功");
                    break;
                }
                System.out.println(userName1+"\t"+pwd1);
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

import java.io.FileReader;
import java.util.Properties;

/**
 * Created by Lenovo on 2018/3/30.
 */
public class Demo {
    public static void main(String[] args) {
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





        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

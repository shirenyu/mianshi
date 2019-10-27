package 字符流;

import java.io.*;

public class TestCharStreamBuf {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("f:/test.txt")));
        BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(new File("f:/test_CharStreamBuf.txt")));
        String str;
        while((str = bufferedReader.readLine())!=null){ //根据文件的的换行符readLine，如果没有换行符，尽管这个文件很大，readLine读取的也是一行
            System.out.println(str);
            System.out.println("=====");
            bufferedWriter.write(str.getBytes());
        }
        bufferedReader.close();
        bufferedWriter.close();

    }
}

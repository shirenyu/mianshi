package 字节流;


import java.io.*;

public class TestBuf {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("f:/test.txt"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);//此时文件流已经全部被读进到了BufferedInputStream
        byte[] bytes = new byte[bufferedInputStream.available()]; //使用InputStream的available()方法得到stream的长度
//        bufferedInputStream.read(bytes);//都是写到一个数组中，可以根据fileInputStream的长度来声明byte[]数组的长度。
        int n=0;
        while((n = bufferedInputStream.read(bytes))!=-1){
            System.out.println(n);
            System.out.println(new String(bytes));
        }
        /*
        System.out.println(new String(bytes));
        fileInputStream.close();
        bufferedInputStream.close();

        FileOutputStream fileOutputStream = new FileOutputStream(new File("f:/testfuf_out.txt"));
        BufferedOutputStream bufferedWriter = new BufferedOutputStream(fileOutputStream);
        bufferedWriter.write(bytes);
        fileOutputStream.close();
        bufferedInputStream.close();*/
    }
}

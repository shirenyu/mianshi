package 字符流;

import java.io.*;

public class Test_flush {

    public static void main(String[] args) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("f:/text_flush.txt")));
        byte[] bytes = new byte[]{1,2,3};
        try {
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bos.close();
        }
    }
}

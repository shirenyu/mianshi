package 字节流;

import java.io.*;

public class Test {
    public static void main(String[] args) {
//        InputStream(基类) PipedInputStream/ByteArrayInputStream/FileInputStream（材料） FilterInputStream(即可是材料也是抽象装饰类) BufferedInpuStream(具体装饰类)
        try {
            InputStream fis = new FileInputStream(new File(""));
            InputStream inputStream = new BufferedInputStream(fis); //把FileInputStream装饰成BuffedInputStream

            OutputStream outputStream = new FileOutputStream(new File(""));
            byte[] b=null;
            inputStream.read(b);
            outputStream.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package guiguGC;

import static java.lang.Thread.sleep;

public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
/*        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("(TOTAL_MEMORY(-Xms)="+totalMemory+")");
        System.out.println("(MAXMEMORY(-Xmx)="+maxMemory+")");*/

        System.out.println("======HelloGC");
        byte[] byteArray = new byte[100*1024*1024];
//        sleep(Integer.MAX_VALUE);
    }
}

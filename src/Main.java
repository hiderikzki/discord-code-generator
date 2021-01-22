import thread.GenerationThread;
import util.LogUtil;

public class Main {

    public static void main(String[] args) {
        try {
            GenerationThread mainThread = new GenerationThread();
            mainThread.start();
            LogUtil.addLog("Started GenerationThread!");
        } catch(IllegalThreadStateException itse) {
            itse.getStackTrace();
            itse.getCause();
        }
    }
}

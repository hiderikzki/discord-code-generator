package thread;

import util.LogUtil;
import util.PathUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main Generation Thread for Links
 */
public class GenerationThread extends Thread {
    public static List<String> generatedArray = new ArrayList<>();
    private final ExecutorService mainExecutorService = Executors.newCachedThreadPool();
    private final ExecutorService pooledExecutorService = Executors.newFixedThreadPool(4);
    private int arrayIndex = 0;

    private final String[] array = {
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",

            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "P",
            "U", "V", "W", "X", "Y", "Z",

            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    @Override
    public void run() {
        pooledExecutorService.execute(() -> mainExecutorService.execute(() -> codeGeneration(24, 100, 100, true)));
    }

    private void codeGeneration(int length, int batches, int generationsPerBatch, boolean printToFile) {
        pooledExecutorService.execute(() -> mainExecutorService.execute(() -> {
            try {
                // String Builder for generated code!
                final StringBuilder stringBuffer = new StringBuilder(length);
                final String customPath = PathUtil.getCustomDesktop("CodeGenerator");

                for (int batch = 0; batch < batches; batch++) {
                    for (int generated = 0; generated < generationsPerBatch; generated++) {
                        LogUtil.addLog("Batch: " + batch + " | Count: " + generated);

                        for (int stringLength = 0; stringLength < length; stringLength++) {
                            stringBuffer.append(array[choseRandomFromArray(array)]);
                        }

                        if(!generatedArray.contains(stringBuffer.toString()))
                            generatedArray.add(stringBuffer.toString());
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }

                LogUtil.addLog("Generation done!");

                if(generatedArray.size() < 1)
                    return;

                if (printToFile) {
                    File directory = new File(customPath);

                    if (directory.mkdirs()) {
                        LogUtil.addDebugLog("Added Directory! (" + customPath + ")");
                    } else {
                        LogUtil.addDebugLog("Directory already exists! (" + customPath + ")");
                    }

                    File output = new File(customPath + "/Generated.txt");

                    if (output.createNewFile()) {
                        LogUtil.addDebugLog("Created File! (" + customPath + "/Generated.txt)");
                    } else {
                        LogUtil.addDebugLog("File already exists! (" + customPath + "/Generated.txt)");
                        output.delete();
                        output.createNewFile();
                        LogUtil.addDebugLog("Deleted and Created File! (" + customPath + "/Generated.txt)");
                    }

                    FileWriter fileWriter = new FileWriter(output);

                    for (String string : generatedArray) {
                        fileWriter.write("https://discord.gift/" + string + "\n");
                        arrayIndex++;
                        LogUtil.addLog("Dumped " + arrayIndex + "/" + generatedArray.size());
                    }

                    LogUtil.addLog("Dump Done!");
                } else {
                    for (String string : generatedArray) {
                        LogUtil.addLog("https://discord.gift/" + string);
                        arrayIndex++;
                    }
                }

                LogUtil.addLog("Generation finished!");

                Runtime.getRuntime().exit(80085);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
    }

    private int choseRandomFromArray(String[] array) {
        final Random random = new Random();
        random.setSeed(new Random().nextInt());
        return random.nextInt(array.length);
    }
}

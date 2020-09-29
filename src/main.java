import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You need to transfer some information through a slow datalink as fast as possible. You consider using a file archiver to compress data before sending.
 * <p>
 * The first line of input contains an integer dataSize - the size of data you need to transfer in bytes. 1 <= dataSize <= 10000.
 * The second line of input contains an integer transferSpeed - the speed of data transfer through the link, in bytes per second. 1 <= transferSpeed <= 10.
 * The third line of input contains an integer N - the number of archivers you are considering. 1 <= N <= 2.
 * The following N lines contain information about archivers, each line describing one archiver with two space-separated integers: processingSpeed - the speed of data processing (both compression and extraction) in bytes per second, and compressionRate - the rate of compression achieved by the archiver (i.e. the size of compressed data divided by the size of original data), in percent. 1 <= processingSpeed <= 100, 1 <= compressionRate <= 99.
 * <p>
 * Output the minimal time you'll need to send the data through the datalink, including compression and extraction time, rounded up to the nearest integer. Round up only the final answer, not intermediary calculations results.
 * <p>
 * Example
 * <p>
 * input
 * <p>
 * 1000
 * 10
 * 2
 * 100 50
 * 60 20
 * <p>
 * output
 * <p>
 * 40
 * <p>
 * Sending the data uncompressed will take 1000/10 = 100 seconds.
 * Using first archiver will take 1000/100 + 1000*0.5/10 + 1000*0.5/100 = 65 seconds.
 * Using second archiver will take 1000/60 + 1000*0.2/10 + 1000*0.2/60 = 39 1/3 seconds, rounding up to 40 seconds.
 */
class myCode {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int dataSize = Integer.valueOf(br.readLine());
        int transferSpeed = Integer.valueOf(br.readLine());
        int archiversCount = Integer.valueOf(br.readLine());
        List<String> archivers = new ArrayList<>();
        for (int i = 1; i <= archiversCount; i++) {
            archivers.add(br.readLine());
        }
        System.out.println(getMinimalTransferTime(archivers, dataSize, transferSpeed));

        // Test function
        //getMinimalTransferTime_shouldWorks();
    }

    public static int getMinimalTransferTime(List<String> archivers, int dataSize, int transferSpeed) {
        Set<Float> processingTimes = new HashSet();
        for (String line : archivers) {
            String[] tokens = line.split(" ");
            float processingSpeed = Float.parseFloat(tokens[0]);
            float compressionRate = Float.parseFloat(tokens[1]);
            processingTimes.add(dataSize / processingSpeed + (dataSize * compressionRate / 100) / transferSpeed + (dataSize * compressionRate / 100) / processingSpeed);
        }
        return Math.round(processingTimes.stream().min(Float::compareTo).get());
    }

    public static void getMinimalTransferTime_shouldWorks() {
        int dataSize = Integer.parseInt("1000");
        int transferSpeed = Integer.parseInt("10");
        List<String> archivers = new ArrayList<>();
        archivers.add("100 50");
        archivers.add("60 20");
        float result = getMinimalTransferTime(archivers, dataSize, transferSpeed);
        System.out.println("Result: " + result  + " expected 40 "+ (result == 40?"success":"fails"));
    }
}


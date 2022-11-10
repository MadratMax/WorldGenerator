package root;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Logger {

    public static void printLog(String message) {
        System.out.println("\n[INFO] " + message);
    }

    public static void printProgress(String processName, long startTime, long total, long current) {
        long eta = current == 0 ? 0 :
                (total - current) * (System.currentTimeMillis() - startTime) / current;

        String etaHms = current == 0 ? "N/A" :
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder(140);
        int percent = (int) (((float)current / (float)total) * 100);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(processName + "... %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                .append(String.format(" %d/%d", current, total, etaHms));

        System.out.print(string);
    }

    public static void addLine() {
        System.out.println("/n");
    }

    public static void woodcutterProgress(int x, int y, int treesCut) {
        StringBuilder string = new StringBuilder(140);
        string
                .append('\r')
                .append("[trees cut " + treesCut + "]")
                .append(String.format(" %d/%d", x, y));

        System.out.print(string);
    }
}

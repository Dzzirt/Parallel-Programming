import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzzirt on 07.12.2016.
 */
public class PiCalculator {

    private String processPath = "C:\\Common\\JavaProjects\\Parallel-Programming\\lab3\\";

    private List<Process> subCalcs = new ArrayList<>(2);
    private List<Reader> subCalcsOutput = new ArrayList<>(2);

    private int m_innerDots = 0;
    private int m_dots = 0;

    private List<String> m_output = new ArrayList<>(2);

    private void waitForProcessesFinish() {
        try {
            for (Process p : subCalcs) {
                p.waitFor();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processSubCalculatorsResult() {
        for (Reader reader : subCalcsOutput) {
            String line;
            try {
                if ((line = ((BufferedReader) reader).readLine()) != null) {
                    String[] splitted = line.split(" ");
                    m_innerDots += Integer.valueOf(splitted[1]);
                    m_output.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public double calculate(int dots) {
        try {
            m_dots = dots;
            int halfDots = dots / 2;
            int appendix = dots % 2 == 0 ? 0 : 1;

            Process proc1 = new ProcessBuilder(
                    "java", "-jar", processPath + "subCalculator1.jar", String.valueOf(halfDots + appendix)).start();
            Process proc2 =  new ProcessBuilder(
                    "java", "-jar", processPath + "subCalculator2.jar", String.valueOf(halfDots)).start();

            subCalcsOutput.add(new BufferedReader(new InputStreamReader(proc1.getInputStream())));
            subCalcsOutput.add(new BufferedReader(new InputStreamReader(proc2.getInputStream())));

            subCalcs.add(proc1);
            subCalcs.add(proc2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        waitForProcessesFinish();
        processSubCalculatorsResult();
        return 4f * m_innerDots / m_dots;
    }

    public String getDetailedResult() {
        String result = "";
        for (String line : m_output) {
            result += line + '\n';
        }
        return result;
    }
}

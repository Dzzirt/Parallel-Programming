import java.io.IOException;
import java.util.Random;

/**
 * Created by Dzzirt on 03.11.2016.
 */
public class BankClient {

    private Bank m_bank;
    private Thread m_session;
    private Random m_random;
    private int m_id;

    public BankClient(Bank bank, int id) {
        m_bank = bank;
        m_id = id;
        m_random = new Random();
    }

    public void startSession() {
        m_session = new Thread(() -> {
            try {
                startUpdateLoop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        m_session.start();
    }

    public Thread getSession() {
        return m_session;
    }

    public int getId() {
        return m_id;
    }

    private int getSleepDuration() {
        // TODO: check correctness of running application with no sleep, even in CBank
        // 1000 .. 3999
        return (1000 + m_random.nextInt(3000));
    }

    private int getBalanceChangeValue() {
        return m_random.nextInt(200) - 100;
    }

    private void startUpdateLoop() throws IOException {
        m_random.setSeed(m_id);
        while (true) {
            try {
                int sleepDuration = getSleepDuration();
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m_bank.updateClientBalance(this, getBalanceChangeValue());
        }

    }
}

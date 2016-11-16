import java.util.Random;

/**
 * Created by Dzzirt on 03.11.2016.
 */
public class BankClient {

    private Bank m_bank;

    private Random m_random;
    private int m_id;

    public BankClient(Bank bank, int id) {
        m_bank = bank;
        m_id = id;
        m_random = new Random();
        new Thread(this::startUpdateLoop).start();
    }

    public int getId() {
        return m_id;
    }

    private int getSleepDuration() {
        // TODO: check correctness of running application with no sleep, even in CBank
//        m_random.setSeed(m_id);
        // 1000 .. 3999
        return (1000 + m_random.nextInt(3000)) * (m_id + 1);
    }

    private int getBalanceChangeValue() {
        return m_random.nextInt(200) - 100;
    }

    private void startUpdateLoop() {
        while (true) {
            try {
                Thread.sleep(getSleepDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m_bank.updateClientBalance(this, getBalanceChangeValue());
        }

    }
}

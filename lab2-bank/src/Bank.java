import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

/**
 * Created by Dzzirt on 03.11.2016.
 */
public class Bank {

    private int m_totalBalance;
    private Main.Primitives m_primitive;
    private ArrayList<BankClient> m_clients;
    private Semaphore m_semaphore;
    public Bank(Main.Primitives primitive) {
        m_clients = new ArrayList<>();
        m_totalBalance = 0;
        m_primitive = primitive;
        m_semaphore = new Semaphore(1, true);
    }

    public BankClient createClient() {
        BankClient bankClient = new BankClient(this, m_clients.size());
        m_clients.add(bankClient);
        return bankClient;
    }

    public Collection<BankClient> getClients() {
        return m_clients;
    }

    public void updateClientBalance(BankClient client, int value) throws IOException {
        switch (m_primitive) {
            case SEMAPHORE:
                try {
                    m_semaphore.acquire();
                    updateClientBalanceImpl(client, value);
                    m_semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case SYNCHRONIZED: {
                synchronized (this) {
                    updateClientBalanceImpl(client, value);
                }
                break;
            }
            case NONE:
                updateClientBalanceImpl(client, value);
                break;
        }
    }

    private void updateClientBalanceImpl(BankClient client, int value) throws IOException {
        int totalBalance = getTotalBalance();
        System.out.println("Client " + client.getId() +
                " initiates reading total balance. Total = " + totalBalance + ".");

        someLongOperations();
        totalBalance += value;

        int newTotal = getTotalBalance() + value;
        System.out.println("Client " + client.getId() +
                " updates his balance with " + value +
                " and initiates setting total balance to " +
                totalBalance + ". Must be: " + newTotal + ".");

        if (totalBalance < 0) {
            throw new IOException("Operation cancelled. Balance is negative.");
        }



        // Check correctness of transaction through actual total balance
        if (totalBalance != getTotalBalance() + value) {
            throw new IOException("Error!");
        }
        setTotalBalance(totalBalance);
    }

    private int getTotalBalance() {
        return m_totalBalance;
    }

    private void setTotalBalance(int totalBalance) {
        m_totalBalance = totalBalance;
    }

    private void someLongOperations() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

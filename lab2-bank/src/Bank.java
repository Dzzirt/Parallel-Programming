import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Dzzirt on 03.11.2016.
 */
public class Bank {

    private int m_totalBalance;

    private ArrayList<BankClient> m_clients;

    public Bank() {
        m_clients = new ArrayList<>();
        m_totalBalance = 0;
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
        int totalBalance = getTotalBalance();
        System.out.println("Client " + client.getId() +
                " initiates reading total balance. Total = " + totalBalance + ".");
        if (totalBalance < 0) {
            throw new IOException("Operation cancelled. Balance is negative.");
        }
        someLongOperations();
        totalBalance += value;

        int newTotal = getTotalBalance() + value;
        System.out.println("Client " + client.getId() +
                " updates his balance with " + value +
                " and initiates setting total balance to " +
                totalBalance + ". Must be: " + newTotal + ".");

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

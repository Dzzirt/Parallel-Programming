/**
 * Created by Dzzirt on 03.11.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        bank.createClient().startSession();
        bank.createClient().startSession();
        //Wait for multiple objects
        for (BankClient bankClient : bank.getClients()) {
            bankClient.getSession().join();
        }
    }
}

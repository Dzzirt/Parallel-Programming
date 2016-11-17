/**
 * Created by Dzzirt on 03.11.2016.
 */
public class Main {
    public enum Primitives {
        SEMAPHORE,
        SYNCHRONIZED,
        NONE
    }
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Run program with 'help' parameter to get view help");
            return;
        }
        Primitives primitives = null;
        if (args[0].equals("semaphore")) {
            primitives = Primitives.SEMAPHORE;
        } else if (args[0].equals("synchronized")) {
            primitives = Primitives.SYNCHRONIZED;
        } else if (args[0].equals("none")) {
            primitives = Primitives.NONE;
        } else if (args[0].equals("help")) {
            System.out.println("Available parameters: 'semaphore' 'synchronized' 'none' 'help'");
            return;
        }

        Bank bank = new Bank(primitives);
        bank.createClient().startSession();
        bank.createClient().startSession();
        //Wait for multiple objects
        for (BankClient bankClient : bank.getClients()) {
            bankClient.getSession().join();
        }
    }
}

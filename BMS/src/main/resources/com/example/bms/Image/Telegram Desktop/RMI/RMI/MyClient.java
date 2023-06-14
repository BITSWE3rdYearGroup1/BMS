import java.rmi.*;
import java.util.*;

public class MyClient {
    public static void main(String args[]) {
        try {

            String url = "rmi://10.161.162.41:1099/abrham";
            SampleServer remoteObject = (SampleServer) Naming.lookup(url);

            System.out.println("remote object accessed!");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the First Number: ");
            Double i = scanner.nextDouble();
            System.out.println("Enter the Second Number: ");
            Double j = scanner.nextDouble();
            System.out.println("The Sum of The Numbers is = " + remoteObject.add(i, j));

        } catch (Exception e) {

            System.out.println(e);
        }

    }

}
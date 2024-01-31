import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class MulticastServer {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.12.0.0");
            MulticastSocket socket = new MulticastSocket();
            socket.setTimeToLive(1);

            String[] words = {"Bugatti", "Mazda", "Mercedes", "Mustang", "Mclaren"};
            Random random = new Random();

            while (true) {
                String word = words[random.nextInt(words.length)];
                byte[] buf = word.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}






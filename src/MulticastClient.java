import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class MulticastClient {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.12.0.0");
            MulticastSocket socket = new MulticastSocket(4446);
            socket.joinGroup(group);

            byte[] buf = new byte[256];
            Map<String, Integer> wordCount = new HashMap<>();

            System.out.println("Listening for multicast messages...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                wordCount.put(received, wordCount.getOrDefault(received, 0) + 1);

                System.out.println("Received word: " + received + ", Count: " + wordCount.get(received));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


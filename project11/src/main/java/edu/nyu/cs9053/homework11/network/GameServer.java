package edu.nyu.cs9053.homework11.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * User: blangel
 *
 * A NIO implementation of a NetworkGameProvider.
 *
 * The server takes the following commands:
 * <pre>
 *     foes Difficulty
 * </pre>
 * <pre>
 *     move
 * </pre>
 * where the String "foes Easy" would be a call to {@linkplain NetworkGameProvider#getRandomNumberOfNextFoes(String)}
 * with "Easy"
 * and a call using String "move" would be a call to {@linkplain NetworkGameProvider#getRandomNextMove()}
 */
public class GameServer implements NetworkGameProvider, Runnable {

    public static final String SERVER_HOST = "localhost";

    public static final int SERVER_PORT = 8080;

    private static final int READ_SIZE = 128;
    private static final int WRITE_SIZE = 48;


    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        server.run();
    }

    private final Random random;
    private final Selector selector;
    private final ServerSocketChannel channel;
    private final ByteBuffer readModule;
    private final ByteBuffer writeModule;
    private final Map<SocketChannel, String> resultsBuffers;

    public GameServer() throws IOException {
        random = new Random();
        channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        resultsBuffers = new HashMap<>();
        readModule = ByteBuffer.allocate(READ_SIZE);
        writeModule = ByteBuffer.allocate(WRITE_SIZE);

    }

    @Override
    public String getRandomNumberOfNextFoes(String difficulty) {
        switch (difficulty) {
            case "1":
                return String.valueOf(random.nextInt(2));
            case "2":
                return String.valueOf(random.nextInt(3));
            case "3":
                return String.valueOf(random.nextInt(4));
            default:
                throw new AssertionError("Invalid Difficulty Value");
        }
    }

    @Override
    public String getRandomNextMove() {
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                return "Up";
            } else {
                return "Down";
            }
        } else {
            return "Left";
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                getStream();
            } catch (IOException ioe) {
                System.out.printf("%s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void getStream() throws IOException {
        if (selector.select() == 0) {
            return;
        }
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            try {
                if (key.isAcceptable()) {
                    SocketChannel client = channel.accept();
                    System.out.println("Connection Established");
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    readModule.clear();
                    client.read(readModule);
                    readModule.flip();
                    String request = processBufferToString(readModule, StandardCharsets.UTF_8);
                    if (request.contains("operation")) {
                        resultsBuffers.put(client, processMessage(request));
                    }
                } else if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    String message = resultsBuffers.get(client);
                    if (message != null) {
                        processRequest(client, message);
                        resultsBuffers.remove(client);
                    }
                }
            } finally {
                keyIterator.remove();
            }
        }
    }

    private String processBufferToString(ByteBuffer byteBuffer, Charset charset) {
        byte[] bytes;
        if (byteBuffer.hasArray()) {
            bytes = byteBuffer.array();
        } else {
            byteBuffer.rewind();
            bytes = new byte[byteBuffer.remaining()];
        }
        return new String(bytes, charset);
    }

    private String processMessage(String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(message.split("\n")[0].substring(message.indexOf(":") + 1, message.indexOf(",")));
        builder.append(",");
        message = message.split("\n")[0].split(",")[1];
        builder.append(message.substring(message.indexOf(":") + 1, message.indexOf("}")));
        return builder.toString();
    }

    private void processRequest(SocketChannel socketChannel, String request) throws IOException {
        String result;
        if (GameClient.NEXT_MOVE.equals(request.split(",")[0])) {
            result = String.format(getRandomNextMove() + "%n");
            System.out.println("Move Action Sent");
        } else {
            result = String.format(getRandomNumberOfNextFoes(request.split(",")[1]) + "%n");
            System.out.println("Foes Action Sent");
        }
        writeModule.clear();
        writeModule.put(result.getBytes());
        writeModule.flip();
        while (writeModule.hasRemaining()) {
            socketChannel.write(writeModule);
        }
    }
}

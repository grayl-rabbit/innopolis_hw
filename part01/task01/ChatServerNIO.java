package main.java.part01.lesson09.task01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс сервера чата.
 * @author L
 */
public class ChatServerNIO implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private byte[] buffer = new byte[2048];
    private CharBuffer cb = CharBuffer.allocate(2048);
    private Charset ch = Charset.forName("UTF-8");
    private CharsetDecoder decoder = ch.newDecoder();

    Map<SelectionKey, ByteBuffer> connections = new HashMap<>();

    public ChatServerNIO(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    public void run() {

        while (true) {
            try {
                if (selector.isOpen()) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    for (SelectionKey sk : keys) {
                        if (!sk.isValid()) {
                            continue;
                        }
                        //connect
                        if (sk.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
                            SocketChannel sc = serverSocketChannel.accept();
                            sc.configureBlocking(false);
                            SelectionKey skr = sc.register(selector, SelectionKey.OP_READ);
                            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                            //first enter nick
                            String nick = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                            connections.put(skr, byteBuffer);
                        }
                        //get msg
                        else if (sk.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            int read;
                            ByteBuffer byteBuffer = connections.get(sk);
                            String s = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                            System.out.println(s);
                            byteBuffer.clear();
                            try {
                                read = socketChannel.read(byteBuffer);
                            } catch (IOException e) {
                                closeChannel(sk);
                                break;
                            }
                            if (read == -1) {
                                closeChannel(sk);
                                break;
                            } else if (read > 0) {
                                byteBuffer.flip();
                                byteBuffer.mark();
                                if (decodeAndCheck(read, byteBuffer))
                                    break;
                                byteBuffer.reset();
                                final int pos = byteBuffer.position();
                                final int lim = byteBuffer.limit();

                                Set<Map.Entry<SelectionKey, ByteBuffer>> entries = connections.entrySet();
                                for (Map.Entry<SelectionKey, ByteBuffer> entry : entries) {
                                    SelectionKey selectionKey = entry.getKey();
                                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                                    ByteBuffer entryBuffer = entry.getValue();
                                    entryBuffer.position(pos);
                                    entryBuffer.limit(lim);
                                }
                            }
                        } else if (sk.isWritable()) {
                            ByteBuffer bb = connections.get(sk);
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            try {
                                int result = socketChannel.write(bb);
                                if (result == -1) {
                                    closeChannel(sk);
                                }
                            } catch (IOException e2) {
                                closeChannel(sk);
                            }
                            if (bb.position() == bb.limit()) {
                                sk.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                    keys.clear();
                } else break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * декодирует поток байтов и превращает в поток символов.
     *
     * @param read - сколько байтов прочитали из сокета.
     * @param ba   - ByteBuffer to decode
     * @return true - сервер выключен
     */
    private boolean decodeAndCheck(int read, ByteBuffer ba) {
        cb.clear();
        decoder.decode(ba, cb, false);
        cb.flip();
        if ("quit\n".equals(cb.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Метод закрывает канал сокета, снимает со списка активных ключей и удаляет из списка рассылки
     *
     * @param sk - ключ, связанный с каналом
     * @throws IOException - если при закрытии прошла ошибка
     */
    private void closeChannel(SelectionKey sk) throws IOException {
        connections.remove(sk);
        SocketChannel socketChannel = (SocketChannel) sk.channel();
        if (socketChannel.isConnected()) {
            socketChannel.close();
        }
        sk.cancel();
    }


    /**
     * входная точка программы
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new ChatServerNIO(45000).run();
    }

}
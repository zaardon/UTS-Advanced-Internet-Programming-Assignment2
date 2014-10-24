package au.edu.uts.aip.detentiontracker.web;


import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class Helper {

    private static byte[] search = "localhost:8081".getBytes();
    private static byte[] replace = "test-api.pin.net.au".getBytes();
    private static int listenPort = 8081;
    private static boolean useSSL = true;
    private static String serverName = "test-api.pin.net.au";
    private static int serverPort = 443;
   
    public static void main(String[] args) throws IOException {
        // Listen for new connections
        ServerSocket ss = new ServerSocket(8081);
        while (true) {
            final Socket s = ss.accept();
            final Socket c;
            if (useSSL)
                c = SSLSocketFactory.getDefault().createSocket(serverName, serverPort);
            else
                c = new Socket(serverName, serverPort);
           
            // Forward requests from the local client to the remote server
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try (InputStream local = new BufferedInputStream(s.getInputStream());
                         OutputStream remote = new BufferedOutputStream(c.getOutputStream())) {
                        System.err.println("Request Open");
                        forward(System.err, local, remote);
                    } catch (IOException ex) {
                        System.out.println("Request Error: " + ex.getMessage());
                    }
                    System.err.println("Request Closed");
                }
            }).start();
           
            // Forward responses from the remote server to the local client
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try (InputStream remote = new BufferedInputStream(c.getInputStream());
                         OutputStream local = new BufferedOutputStream(s.getOutputStream())) {
                        System.out.println("Response Open");
                        forward(System.out, remote, local);
                    } catch (IOException ex) {
                        System.out.println("Response Error: " + ex.getMessage());
                    }
                    System.out.println("Response Closed");
                }
            }).start();
        }
    }
   
    /**
     * Read from the input stream, write the the output stream and the logStream.
     * Also substitute 'search' for 'replace'.
     */
    private static void forward(PrintStream logStream, InputStream in, OutputStream out) throws IOException {
        byte[] check = new byte[search.length];
        int index = 0;
        int length = 0;
       
        int next;
        while ((next = in.read()) != -1) {
          
            check[index] = (byte)next;
            index = (index + 1) % check.length;
            length++;
           
            // Is the buffer full?
            if (length == check.length) {
                // Check if it is a match
                boolean same = true;
                for (int i=0; i<length; i++) {
                    if (check[(index +  i) % check.length] != search[i]) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    // Identical - so write out the replacement
                    out.write(replace);
                    for (byte value : replace)
                        writeClean(logStream, value);
                    length = 0;
                } else {
                    // Different - so just advance one character
                    byte value = check[index];
                    out.write(value);
                    length--;
                    writeClean(logStream, value);
                }
            }
           
            // Is there more data queued up?
            if (in.available() == 0) {
                // How about if we wait a little while?
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    // do nothing
                }
                // Still nothing?
                if (in.available() == 0) {
                    // Then write everything we've got, ignoring the potential
                    // string replacement
                    index = (index - length + check.length) % check.length;
                    for (int i=0; i<length; i++) {
                        byte value = check[(index + i) % check.length];
                        out.write(value);
                        writeClean(logStream, value);
                    }
                    length = 0;
                }
                out.flush();
            }
        }
        // The input stream is closed, so just write out anything queued up
        index = (index - length + check.length) % check.length;
        for (int i=0; i<length; i++) {
            byte value = check[(index + i) % check.length];
            out.write(value);
            writeClean(logStream, value);
        }
        out.flush();
        logStream.flush();
    }
   
    /**
     * Write printable characters to the print stream. Converts 'special'
     * characters into spaces.
     */
    private static void writeClean(PrintStream logStream, int value) {
        if ((value < ' ' || value > '~') && value != '\n')
            logStream.write(' ');
        else
            logStream.write(value);
    }
   
}

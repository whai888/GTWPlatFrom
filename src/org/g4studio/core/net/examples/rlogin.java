package org.g4studio.core.net.examples;

import java.io.IOException;

import org.g4studio.core.net.bsd.RLoginClient;

/***
 * This is an example program demonstrating how to use the RLoginClient
 * class. This program connects to an rlogin daemon and begins to
 * interactively read input from stdin (this will be line buffered on most
 * systems, so don't expect character at a time interactivity), passing it
 * to the remote login process and writing the remote stdout and stderr
 * to local stdout.  If you don't have .rhosts or hosts.equiv files set up,
 * the rlogin daemon will prompt you for a password.
 * <p>
 * On Unix systems you will not be able to use the rshell capability
 * unless the process runs as root since only root can bind port addresses
 * lower than 1024.
 * <p>
 * JVM's using green threads will likely have problems if the rlogin daemon
 * requests a password.  This program is merely a demonstration and is
 * not suitable for use as an application, especially given that it relies
 * on line buffered input from System.in.  The best way to run this example
 * is probably from a Win95 dos box into a Unix host.
 * <p>
 * Example: java rlogin myhost localusername remoteusername vt100
 * <p>
 * Usage: rlogin <hostname> <localuser> <remoteuser> <terminal>
 * <p>
 ***/

// This class requires the IOUtil support class!
public final class rlogin
{

    public static final void main(String[] args)
    {
        String server, localuser, remoteuser, terminal;
        RLoginClient client;

        if (args.length != 4)
        {
            System.err.println(
                "Usage: rlogin <hostname> <localuser> <remoteuser> <terminal>");
            System.exit(1);
            return ; // so compiler can do proper flow control analysis
        }

        client = new RLoginClient();

        server = args[0];
        localuser = args[1];
        remoteuser = args[2];
        terminal = args[3];

        try
        {
            client.connect(server);
        }
        catch (IOException e)
        {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

        try
        {
            client.rlogin(localuser, remoteuser, terminal);
        }
        catch (IOException e)
        {
            try
            {
                client.disconnect();
            }
            catch (IOException f)
            {}
            e.printStackTrace();
            System.err.println("rlogin authentication failed.");
            System.exit(1);
        }


        IOUtil.readWrite(client.getInputStream(), client.getOutputStream(),
                         System.in, System.out);

        try
        {
            client.disconnect();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }

}


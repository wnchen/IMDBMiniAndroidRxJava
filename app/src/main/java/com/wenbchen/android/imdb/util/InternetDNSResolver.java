package com.wenbchen.android.imdb.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetDNSResolver implements Runnable {
    private String domain;
    private InetAddress inetAddr;

    public InternetDNSResolver(String domain) {
        this.domain = domain;
    }

    public void run() {
        try {
            InetAddress addr = InetAddress.getByName(domain);
            set(addr);
        } catch (UnknownHostException e) {
            
        }
    }

    public synchronized void set(InetAddress inetAddr) {
        this.inetAddr = inetAddr;
    }
    public synchronized InetAddress get() {
        return inetAddr;
    }
}

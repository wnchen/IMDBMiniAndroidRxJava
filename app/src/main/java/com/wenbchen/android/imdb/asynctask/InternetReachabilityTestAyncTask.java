package com.wenbchen.android.imdb.asynctask;

import java.net.InetAddress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.wenbchen.android.imdb.util.InternetDNSResolver;

public class InternetReachabilityTestAyncTask extends AsyncTask<Void, Void, Boolean> {
    
    public interface Callback {
        void onReachabilityTestPassed();
        
        void onReachabilityTestFailed();
    }
    
    private Context mContext;
    
    private String mHostname;
    
    
    private Callback mCallback;
    
    public InternetReachabilityTestAyncTask(Context context, String hostname, Callback callback) {
        mContext = context.getApplicationContext(); // Avoid leaking the Activity!
        mHostname = hostname;
        mCallback = callback;
    }
    
    @Override
    protected Boolean doInBackground(Void... args) {
        if (isConnected(mContext)) {
            InetAddress address = isResolvable(mHostname);
            if (address != null) {
               return true;
            }
        }
        return false;
    }
    
    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (mCallback != null) {
            if (result) {
                mCallback.onReachabilityTestPassed();
            }
            else {
                mCallback.onReachabilityTestFailed();
            }
        }
    }
    
    private boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
    
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        
        return false;
    }
    
    private InetAddress isResolvable(String hostname) {
        try {
        	InetAddress inetAddress = null;
        	InternetDNSResolver dnsRes = new InternetDNSResolver(hostname);
        	Thread t = new Thread(dnsRes);
        	t.start();
        	t.join(1000);
        	inetAddress = dnsRes.get();
            return inetAddress;
        }
        catch (InterruptedException e) {
            return null;
        }
    }
    
}

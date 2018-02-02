/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.synconset.cordovahttp;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.cordova.CallbackContext;

import org.json.JSONException;
import org.json.JSONObject;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

import android.net.Network;

class CordovaHttpGet extends CordovaHttp implements Runnable {
    private Network wifiNetwork;

    public CordovaHttpGet(String urlString, Object params, JSONObject headers, int timeout, CallbackContext callbackContext, Network wifiNetwork) {
        super(urlString, params, headers, timeout, callbackContext);
        this.wifiNetwork = wifiNetwork;
    }

    @Override
    public void run() {
        try {
            HttpRequest request = HttpRequest.get(this.getUrlString(), this.getParamsMap(), false, this.wifiNetwork);

            this.prepareRequest(request);
            this.returnResponseObject(request);
        } catch (HttpRequestException e) {
            this.handleHttpRequestException(e);
        } catch (Exception e) {
            this.respondWithError(e.getMessage());
        }
    }
}

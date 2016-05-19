package org.mangosoft.leagoovn.smarthub.model.request;

/**
 * Created by Mango on 4/22/2016.
 *
 * all api will must have a params n,t,o
 * @params n: nonce
 * @params t: timestamp
 * @params o: operation
 */
public class RequestMdl {
    private String n;
    private String t;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

}

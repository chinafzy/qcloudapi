package org.wtb.qcloudapi.base.util;

import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * A signer for qqcloud server api.
 * 
 * @author zeyufang
 * @see https://www.qcloud.com/doc/api/258/4709
 */
public class QcloudSigners {

    public static final String METHOD_GET = "GET", METHOD_POST = "POST";

    private static final String ENCODING = "utf8", HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * 
     * @param host
     * @param path
     * @param method {@link #METHOD_GET}/{@link #METHOD_POST} 
     * @param secretKey
     * @param pars
     * @return
     */
    public static String sign(String host, String path, String method, String secretKey, Map<String, Object> pars) {
        Args.notNullEmpty(host, "host");
        Args.notNullEmpty(path, "path");
        Args.notNullEmpty(method, "method");
        Args.notNullEmpty(secretKey, "secretKey");
        if (pars == null)
            throw new IllegalArgumentException("\"pars\" cannot be null.");

        // adjust method
        if (method.equalsIgnoreCase(METHOD_GET))
            method = METHOD_GET;
        else if (method.equalsIgnoreCase(METHOD_POST))
            method = METHOD_POST;
        else
            throw new IllegalArgumentException("\"method\" can only be GET/POST.");

        return sign0(host, path, method, secretKey, pars);
    }

    /**
     * 
     * @param host
     * @param path
     * @param method
     * @param secretKey
     * @param pars
     * @return
     * @see <a href="https://www.qcloud.com/doc/api/258/4709">https://www.qcloud.com/doc/api/258/4709</a>
     */
    private static String sign0(String host, String path, String method, String secretKey, Map<String, Object> pars) {

        // 2.1 Sort pars by key.
        TreeMap<String, Object> sortedPars = new TreeMap<String, Object>(pars);

        // 2.2 Join pars as a string expression.
        String queryString = join(sortedPars, "=", "&");

        // 2.3 Adding more info.
        //  method + host + path + "?" + query
        String tobeSign = method + host + path + "?" + queryString;

        // 2.4 Sign it and encode with Base64.
        String signed = hmac(tobeSign, secretKey);

        return signed;
    }

    private static String join(Map<String, Object> pars, String kvSep, String fieldSep) {

        StringBuilder ret = new StringBuilder(1024);

        boolean first = true;
        for (Map.Entry<String, Object> ent : pars.entrySet()) {
            if (first)
                first = false;
            else
                ret.append(fieldSep);

            ret.append(ent.getKey()).append(kvSep).append(ent.getValue());
        }

        return ret.toString();
    }

    private static String hmac(String data, String key) {

        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(ENCODING), HMAC_SHA1_ALGORITHM);

            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes(ENCODING));

            // base64-encode the hmac
            return DatatypeConverter.printBase64Binary(rawHmac);

        } catch (Exception e) {
            // fail on any problem.
            //  nothing need to do, because it is the basic service.
            throw new RuntimeException(e);
        }
    }

}

package org.wtb.qcloudapi.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;

import org.wtb.qcloudapi.base.util.Args;
import org.wtb.qcloudapi.base.util.ChainMap;
import org.wtb.qcloudapi.base.util.QcloudSigners;

/**
 * (ThreadSafe)QQ云平台客户端API配置信息 
 * 
 * @author 19781971@qq.com
 */
public class QcloudApiConf {

    private static interface Action {

        String asGet();

        String[] asPost();
    }

    // global inputs.
    private String host, path;
    private String secretId, secretKey;
    private String region;

    // internal temps.
    private Random rnd = new Random();
    private static final int rnd_max = Integer.MAX_VALUE;

    public QcloudApiConf(String host, String path, String secretId, String secretKey, String region) {
        this.host = Args.notNullEmpty(host, "host");
        this.path = Args.notNullEmpty(path, "path");
        this.secretId = Args.notNullEmpty(secretId, "secretId");
        this.secretKey = Args.notNullEmpty(secretKey, "secretKey");
        this.region = Args.notNullEmpty(region, "region");
    }

    public String reqGet(String action, Map<String, Object> pars) {
        Args.notNullEmpty(action, "action");
        Args.notNull(pars, "pars");

        return action(action, pars).asGet();
    }

    public String[] reqPost(String action, Map<String, Object> pars) {
        Args.notNullEmpty(action, "action");
        Args.notNull(pars, "pars");

        return action(action, pars).asPost();
    }

    private Action action(final String action, final Map<String, Object> pars) {

        return new Action() {

            public String asGet() {
                String entity = calEntity("GET");

                return "https://" + host + path + "?" + entity;
            }

            public String[] asPost() {
                String url = "https://" + host + path;
                String entity = calEntity("POST");

                return new String[] {url, entity};
            }

            private String calEntity(String method) {

                ChainMap<String, Object> allPars = new ChainMap<String, Object>(pars) //
                        .add("Action", action) //
                        .add("Timestamp", System.currentTimeMillis() / 1000) //
                        .add("Nonce", rnd.nextInt(rnd_max) + 1) //
                        .add("SecretId", secretId) //
                        .add("Region", region) //
                ;
                String signature = QcloudSigners.sign(host, path, method, secretKey, allPars);
                allPars.put("Signature", signature);

                return join(allPars);
            }

        };
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "utf8");
        } catch (UnsupportedEncodingException e) {
            // fail on such exception.
            throw new RuntimeException(e);
        }
    }

    private static String join(Map<String, Object> pars) {
        StringBuffer ret = new StringBuffer(1024);

        boolean first = true;
        for (Map.Entry<String, Object> ent : pars.entrySet()) {
            if (first)
                first = false;
            else
                ret.append("&");

            ret.append(enc(ent.getKey())).append("=").append(enc("" + ent.getValue()));
        }

        return ret.toString();
    }

}

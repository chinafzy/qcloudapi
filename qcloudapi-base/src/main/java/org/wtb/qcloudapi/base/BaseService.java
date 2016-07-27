package org.wtb.qcloudapi.base;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wtb.qcloudapi.base.tr.BusinessException;
import org.wtb.qcloudapi.base.tr.InvalidJsonException;
import org.wtb.qcloudapi.base.tr.NetworkException;
import org.wtb.qcloudapi.base.util.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public abstract class BaseService {

    private static class DatetimeDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String v = p.getText();

            if (v == null || v.isEmpty())
                return null;

            return Utils.parseDate(v);
        }

    }

    private static final AtomicLong counter = new AtomicLong(0);

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final ObjectMapper om = buildOm();

    protected final QcloudApiConf conf;

    public BaseService(QcloudApiConf conf) {
        super();
        this.conf = conf;
    }

    protected ObjectMapper buildOm() {
        ObjectMapper ret = new ObjectMapper() //
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) //
                .registerModule(new SimpleModule() //
                        .addDeserializer(Date.class, new DatetimeDeserializer()) //
        );

        // use jdk standard annotation instead of fastxml.
        ret.setAnnotationIntrospector(new JaxbAnnotationIntrospector(ret.getTypeFactory()));

        return ret;
    }

    /**
     * 将一个JsonNode转换为java bean对象。 
     * 
     * @param node
     * @param c
     * @return
     */
    protected <T> T parseBean(JsonNode node, Class<T> c) {
        try {
            return om.treeToValue(node, c);
        } catch (JsonProcessingException e) {
            log.error("Cannot transform JsonNode into Class:" + c.getName(), e);
            throw new InvalidJsonException(e);
        }
    }

    /**
     * 发送一个请求给qcloud，返回结果的JsonNode 
     * 
     * @param action
     * @param req
     * @return
     * @throws BusinessException 业务异常，这个是必须要try catch的。 
     * @throws NetworkException 网络异常（服务器故障、Https、网络传输、编码异常），属于运行时异常，调用时无需try catch，建议在调用者的框架底层来处理 
     * @throws InvalidJsonException 不是一个合法的json数据格式（这个一般很难出现），属于运行时异常，调用时无需try catch，建议在调用者的框架底层来处理
     * @author 19781971@qq.com
     */
    protected JsonNode req(String action, BaseReq req) throws BusinessException, NetworkException, InvalidJsonException {
        final long idx = counter.incrementAndGet();
        // use s standalone logger for httpclient(httpreqest) only.
        final Logger hclog = LoggerFactory.getLogger(BaseService.class);

        hclog.info("== {} == Start request ", idx);

        CloseableHttpClient hc = HttpClients.createDefault();

        String url = conf.reqGet(action, req.asPlainMap());
        hclog.info("== {} == Http Get {}", idx, url);

        CloseableHttpResponse res;
        try {
            res = hc.execute(new HttpGet(url));
        } catch (IOException e) {
            hclog.error("== " + idx + " == Fail with request:" + url, e);
            throw new NetworkException(e);
        }

        HttpEntity entity = res.getEntity();
        String s;
        try {
            s = EntityUtils.toString(entity);
        } catch (ParseException e) {
            throw e;
        } catch (IOException e) {
            throw new NetworkException(e);
        } finally {
            EntityUtils.consumeQuietly(entity);
            try {
                hc.close();
            } catch (IOException e) {
                hclog.error("An error occurs while closing HttpClient, you can ignore it.", e);
            }
        }

        hclog.info("== {} == Response \n{}", idx, s);

        String s2 = Utils.unescape(s);

        hclog.info("== {} == Parse Response \n{}", idx, s2);

        JsonNode node;
        try {
            node = om.readTree(s2);
        } catch (JsonParseException e) {
            hclog.error("Invalid json string.", e);
            throw new InvalidJsonException(s2, e);
        } catch (IOException e) {
            // impossible?
            //  how ever, we think something is wrong with net transmission or server side.
            throw new NetworkException(e);
        }

        int code = node.path("code").asInt();
        if (code != 0) {
            String message = node.path("message").asText();
            hclog.error("{} fails with {}({})", url, code, message);
            throw new BusinessException(code, message);
        }

        return node;
    }
}

package org.wtb.qcloudapi.base;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseBean {

    @Override
    public String toString() {
        return new ObjectMapper().convertValue(this, Map.class).toString();
    }

}

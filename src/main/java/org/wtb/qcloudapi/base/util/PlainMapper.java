package org.wtb.qcloudapi.base.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PlainMapper {
    private final Set<Class<?>> primitiveWrappers = new HashSet<Class<?>>(Arrays.<Class<?>> asList( //
            Boolean.class, //
            Byte.class, Short.class, Integer.class, Long.class, //
            Float.class, Double.class, //
            String.class));

    private Map<String, Object> m;
    private boolean skipNull = true;

    public PlainMapper(Map<String, Object> m) {
        super();
        this.m = m;
    }

    private void write(String prefix, Object o) {
        if (o == null) {
            if (!skipNull)
                m.put(prefix, null);

            return;
        }

        // primitives ...
        Class<?> c = o.getClass();
        if (c.isPrimitive() || primitiveWrappers.contains(c)) {
            m.put(prefix, o);
            return;
        }

        // Numbers are treated as primitives..
        //  hmmm, maybe we need to do something more for BigNumber to avoid adding value like 1.08+e30
        if (Number.class.isAssignableFrom(c)) {
            m.put(prefix, o.toString());
            return;
        }
        
        // Date
        if (Date.class.isAssignableFrom(c)){
            m.put(prefix, Utils.formatDate((Date) o));
        }

        // Map
        if (Map.class.isAssignableFrom(c)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> mapo = (Map<String, Object>) o;
            writeMapValue(prefix, mapo);

            return;
        }

        // Collections.
        if (Iterable.class.isAssignableFrom(c)) {
            @SuppressWarnings("unchecked")
            Iterable<Object> io = (Iterable<Object>) o;
            writeMapValue(prefix, asMap(io));

            return;
        }

        // Iterator.
        if (Iterator.class.isAssignableFrom(c)) {
            @SuppressWarnings("unchecked")
            Iterator<Object> io = (Iterator<Object>) o;
            writeMapValue(prefix, asMap(io));

            return;
        }

        // Array
        if (c.isArray()) {
            writeMapValue(prefix, arrayAsMap(o));

            return;
        }

        // Finally, we convert Bean to Map
        Map<String, Object> mapo = beanAsMap(o);
        writeMapValue(prefix, mapo);
    }

    private void writeMapValue(String prefix, Map<String, Object> mapo) {
        for (Map.Entry<String, Object> ent : mapo.entrySet()) {
            String mfield = (prefix == null ? "" : (prefix + ".")) + ent.getKey();
            Object mo = ent.getValue();

            write(mfield, mo);
        }
    }

    /**
     * 将一个Bean对象转换为“平Map”对象（Map里面只包含基础数据内容，不会包含Map、List、Bean这些复合类型）。 <BR />
     * 转换步骤为：<pre>1、对象Map化：
     * a、如果是一个Bean，我们会将其用反射字段的方式转换为Map 
     * b、如果是一个数组/Iterable/Iterator，我们将其以序列号1开始转换为Map
     * c、如果是Map，无需转换
     *2、处理Map每个成员<Key, Value>
     * a、如果成员是primitive或者其包装类型/String/Number，则无需继续转换
     * b、如果是满足#1中的某个条件，将其转换为Map2<Key2, Value2>，将Map2加入到Map中，
     *   newkey = key + "." + key2
     * c 循环#2，一直到全部内容转换为#a
     * </pre>
     * @param value
     * @return
     */
    public static Map<String, Object> asPlainMap(Object value) {
        LinkedHashMap<String, Object> ret = new LinkedHashMap<String, Object>();

        PlainMapper mapper = new PlainMapper(ret);
        mapper.write(null, value);

        return ret;
    }

    /**
     * Convert an Array data to Map data.
     * 
     * @param arr
     * @return
     */
    private static Map<String, Object> arrayAsMap(Object arr) {
        Map<String, Object> ret = new LinkedHashMap<String, Object>();

        for (int i = 0, imax = Array.getLength(arr); i < imax; i++) {
            ret.put("" + (i + 1), Array.get(arr, i));
        }

        return ret;
    }

    /**
     * Convert an Iterable data to Map data.
     * 
     * @param list
     * @return
     */
    private static Map<String, Object> asMap(Iterable<Object> list) {
        Map<String, Object> ret = new LinkedHashMap<String, Object>();

        int pos = 0;
        for (Object o : list) {
            pos++;

            ret.put("" + pos, o);
        }

        return ret;
    }

    /**
     * Convert an Iterator data to Map data.
     * 
     * @param list
     * @return
     */
    private static Map<String, Object> asMap(Iterator<Object> list) {
        Map<String, Object> ret = new LinkedHashMap<String, Object>();

        for (int pos = 1; list.hasNext(); pos++) {
            ret.put("" + pos, list.next());
        }

        return ret;
    }

    /**
     * Convert a bean data to Map data by reflecting fields.
     * 
     * @param bean
     * @return
     */
    private static Map<String, Object> beanAsMap(Object bean) {
        Map<String, Object> ret = new LinkedHashMap<String, Object>();

        for (Class<?> c = bean.getClass(); c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);

                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || Modifier.isVolatile(modifiers))
                    continue;

                Object value;
                try {
                    value = field.get(bean);
                } catch (Exception e) {
                    // Fail over on any failure.
                    throw new RuntimeException(e);
                }

                ret.put(field.getName(), value);
            }
        }

        return ret;
    }

}

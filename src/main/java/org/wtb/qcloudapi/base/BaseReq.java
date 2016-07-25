package org.wtb.qcloudapi.base;

import java.util.Map;

import org.wtb.qcloudapi.base.util.PlainMapper;

/**
 * QcloudAPI的Request基础类。它使用反射field的方式来解析Bean内容，将其转换为“平Map”，以符合QcloudAPI调用的需要。
 * <pre>
 * 1、如何添加字段？
 *   直接定义一个field就可以； 
 * 2、如何给field赋值？
 *   从实用的角度出发，建议这样的俩个规则：
 *   a、如果是必选参数，直接放在构造函数，或者static factory构造方法（建议函数名ins）中，
 *   b、非必选参数，使用jquery风格的赋值表达式
 *   class ChannelCreateReq extends BaseReq{
 *      String channelName; // 必选参数
 *      String channelDescribe; // 可选参数
 *      
 *      public static ChannelCreateReq ins(String channnelName){
 *        ChannelCreateReq ret = new ChannelCreateReq();
 *        ret.channelName = channelName;
 *        return ret;
 *      }
 *      
 *      public ChannelCreateReq channelDescribe(String channelDescribe){
 *        this.channelDescribe = channelDescribe;
 *        return this;
 *      }
 *   }
 * 
 * </pre>
 *
 * @author 19781971@qq.com
 */
public abstract class BaseReq extends BaseBean {

    public Map<String, Object> asPlainMap(){
        return PlainMapper.asPlainMap(this);
    }
}
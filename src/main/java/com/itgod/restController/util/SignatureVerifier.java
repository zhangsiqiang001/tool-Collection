package com.itgod.restController.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SignatureVerifier {

    @Value("${open.api.ak}")
    private String ak;

    @Value("${open.api.sk}")
    private String sk;

    public String verifySignature(Object paras) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == servletRequestAttributes) {
            return null;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        try {
            TreeMap<String, Object> treeMap = new TreeMap();
            Method[] methods = paras.getClass().getMethods();
            for (Method m : methods) {
                String mname = m.getName();
                String keyName = "";
                String value = "";
                if (mname.equals("getClass")) {
                    continue;
                }
                if (mname.length() > 3 && mname.substring(0, 3).equals("get")) {
                    Object v;
                    try {
                        v = m.invoke(paras);

                    } catch (Exception ex) {
                        continue;
                    }
                    if (v == null) {
                        continue;
                    }
                    if (v instanceof String[]
                            || v instanceof int[]
                            || v instanceof float[]
                            || v instanceof double[]
                            || v instanceof long[]
                    ) {
                        value = JSON.toJSONString(v);
                    } else {
                        value = v.toString();
                    }

                    keyName = mname.substring(3, 4).toLowerCase();
                    if (mname.length() > 4) {
                        keyName += mname.substring(4);
                    }
                }
                if (mname.length() > 2 && mname.substring(0, 2).equals("is")) {
                    Object v;
                    try {
                        v = m.invoke(paras);

                    } catch (Exception ex) {
                        continue;
                    }
                    if (v == null) {
                        continue;
                    }

                    value = v.toString();
                    keyName = mname.substring(2, 3).toLowerCase();
                    if (mname.length() > 3) {
                        keyName += mname.substring(3);
                    }
                }
                if (!keyName.equals("")) {
                    treeMap.put(keyName, value);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> next : treeMap.entrySet()) {
                stringBuilder.append(next.getKey()).append(next.
                        getValue());
            }
            return verifySignature(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String verifySignature(String params){
        String secret= this.sk;
        String signatureExpect = DigestUtils.md5Hex
                (secret + params).toUpperCase();
        return signatureExpect;
    }
}

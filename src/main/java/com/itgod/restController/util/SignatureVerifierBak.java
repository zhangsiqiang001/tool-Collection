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
public class SignatureVerifierBak {

    @Value("${open.api.ak}")
    private String ak;

    @Value("${open.api.sk}")
    private String sk;

    private static final String HEADER_NAME_X_SIGNATURE = "X-Signature";
    private static final String PARAMETER_NAME_TIMESTAMP = "timestamp";

    public boolean verifySignature(String ak,String params,long timestamp,String signature){
//        if (Math.abs(System.currentTimeMillis() - timestamp) > 10 * 60
//                * 1000) {
//            return false;
//        }
        if(!this.ak.equals(ak))
            return false;
        String secret= this.sk;


        // build signature and verify
        String signatureExpect = DigestUtils.md5Hex
                (secret + params).toUpperCase();
        if (!signature.equals(signatureExpect)) {

            return false;
        }

        return true;
    }
    public boolean verifySignature(Object paras) {
        // get request context

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == servletRequestAttributes) {
            return false;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String signature =  request.getHeader(HEADER_NAME_X_SIGNATURE);
        if(signature==null)
            return false;
        try {
            // check header info
            String[] signatureArray = signature.split("&");
            if (signatureArray.length != 2) {
                return false;
            }
            String accessKey = signatureArray[0];

            TreeMap<String, Object> treeMap = new TreeMap<>();
//            Method[] methods = paras.getClass().getDeclaredMethods();
            Method[] methods = paras.getClass().getMethods();
            for (Method m : methods) {
                String mname = m.getName();
                String keyName = "";
                String value = "";
                if (mname.equals("getClass"))
                    continue;
                if(mname.length()>3&&mname.substring(0,3).equals("get")){
                    Object v;
                    try {
                        v = m.invoke(paras);

                    }catch (Exception ex){
                        continue;
                    }
                    if (v == null)
                        continue;
                    if(v instanceof String[]
                            || v instanceof int[]
                            ||v instanceof float[]
                            ||v instanceof double[]
                            ||v instanceof long[]
                    ){
                        value = JSON.toJSONString(v);
                    }else{
                        value = v.toString();
                    }

                    keyName = mname.substring(3,4).toLowerCase();
                    if(mname.length()>4){
                        keyName+=mname.substring(4);
                    }
                }
                if(mname.length()>2&&mname.substring(0,2).equals("is")){
                    Object v;
                    try {
                        v = m.invoke(paras);

                    }catch (Exception ex){
                        continue;
                    }
                    if (v == null)
                        continue;

                    value = v.toString();
                    keyName = mname.substring(2,3).toLowerCase();
                    if(mname.length()>3){
                        keyName+=mname.substring(3);
                    }
                }
                if(!keyName.equals("")){
                    treeMap.put(keyName,value);
                }
            }
            long timestamp = Long.parseLong(treeMap.get(PARAMETER_NAME_TIMESTAMP).toString());

            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> next : treeMap.entrySet()) {
                stringBuilder.append(next.getKey()).append(next.
                        getValue());
            }
            return verifySignature(accessKey,stringBuilder.toString(),timestamp,signatureArray[1]);

        } catch (Exception e) {
            return false;
        }
        //return true;
    }
}

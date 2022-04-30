package com.lhc.utils;

import com.lhc.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String replace = s.replace("-", "");
        return replace;
    }

    public static double getLoss(Integer[] a, Integer[] b){
        //b作为标准参照
        if(a.length!=0 && a.length == b.length){
            System.out.println("数组长度为"+a.length);
            double result = 0;
            double bSum = 0;
            for (int i = 0; i < a.length; i++) {
                result+=(double)(a[i]-b[i])*(a[i]-b[i]);
                System.out.println("result为"+result);
                bSum += b[i];
            }
            result = result/a.length;
            System.out.println("result为"+result);
            result = Math.sqrt(result);
            System.out.println("result为"+result);
            System.out.println("bSum为"+bSum);
            bSum = bSum / a.length;
            System.out.println("bSum为"+bSum);

            double loss = result/bSum;

            return loss;
        }
        return 0;
    }
}

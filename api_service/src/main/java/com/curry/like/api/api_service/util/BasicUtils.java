package com.curry.like.api.api_service.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BasicUtils {


    public static boolean isCheckParamsKey(Map<String, Object> params, Object... keys) {
        boolean isHasKey = true;
        Set<String> keySet = params.keySet();
        for (int i = 0; i < keys.length; i++) {
            String needKey = (String) keys[i];
            if (!keySet.contains(needKey)) {
                isHasKey = false;
            }
        }
        return isHasKey;
    }
}

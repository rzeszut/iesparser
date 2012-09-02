package org.iesparser.data;
import java.util.Map;

import com.google.common.collect.Maps;


public enum PhotometricType {
    A(3), B(2), C(1);

    private static Map<Integer, PhotometricType> enumMap;
    static {
        enumMap = Maps.newHashMap();
        enumMap.put(1, C);
        enumMap.put(2, B);
        enumMap.put(3, A);
    }

    private int type;
    private PhotometricType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static PhotometricType fromInt(int type) {
        PhotometricType t = enumMap.get(type);
        if (t != null) {
            return t;
        } else {
            throw new RuntimeException("Invalid int type for PhotometricType");
        }
    }

}

package org.iesparser.data;

import java.util.Map;
import java.util.HashMap;

public enum PhotometricType {
    A(3), B(2), C(1);

    private static Map<Integer, PhotometricType> enumMap;

    private static Map<Integer, PhotometricType> getMap() {
        if (enumMap == null) {
            enumMap = new HashMap<>();
        }
        return enumMap;
    }

    private int type;

    private PhotometricType(int type) {
        this.type = type;
        getMap().put(type, this);
    }

    public int getType() {
        return type;
    }

    public static PhotometricType fromInt(int type) {
        PhotometricType t = enumMap.get(type);
        if (t != null) {
            return t;
        } else {
            throw new IllegalArgumentException();
        }
    }

}

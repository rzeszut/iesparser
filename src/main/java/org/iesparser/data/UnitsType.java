package org.iesparser.data;

import java.util.Map;

import com.google.common.collect.Maps;

public enum UnitsType {
    FEET(1), METERS(2);

    private static Map<Integer, UnitsType> enumMap;
    static {
        enumMap = Maps.newHashMap();
        enumMap.put(1, FEET);
        enumMap.put(2, METERS);
    }

    private int type;
    private UnitsType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static UnitsType fromInt(int type) {
        UnitsType t = enumMap.get(type);
        if (t != null) {
            return t;
        } else {
            throw new RuntimeException("Invalid int type for UnitsType");
        }
    }

}

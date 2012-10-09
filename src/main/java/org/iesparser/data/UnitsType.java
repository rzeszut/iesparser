package org.iesparser.data;

import java.util.HashMap;
import java.util.Map;

public enum UnitsType {
    FEET(1), METERS(2);

    private static Map<Integer, UnitsType> enumMap;

    private static Map<Integer, UnitsType> getMap() {
        if (enumMap == null) {
            enumMap = new HashMap<>();
        }
        return enumMap;
    }

    private int type;

    private UnitsType(int type) {
        this.type = type;
        getMap().put(type, this);
    }

    public int getType() {
        return type;
    }

    public static UnitsType fromInt(int type) {
        UnitsType t = enumMap.get(type);
        if (t != null) {
            return t;
        } else {
            throw new IllegalArgumentException();
        }
    }

}

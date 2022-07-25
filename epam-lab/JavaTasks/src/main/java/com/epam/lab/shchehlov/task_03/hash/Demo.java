package com.epam.lab.shchehlov.task_03.hash;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_03.hash.key.HashFirstFourCharsObject;
import com.epam.lab.shchehlov.task_03.hash.key.HashStringLengthObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demonstration of the order of output of elements using HashMap and LinkedHashMap
 *
 * @author B.Shchehlov
 */
public class Demo {
    private static final Logger log = Logger.getLogger(Demo.class.getName());
    private static final String HASH_MAP = "HashMap";
    private static final String LINKED_HASH_MAP = "LinkedHashMap";
    private static final String HASH_STRING_OBJECT = "HashStringLengthObject";
    private static final String HASH_CHAR_OBJECT = "HashFirstFourCharsObject";
    private static final String BORDER = "------------------------------------------------------------------------";
    private static final String HEAD_FORMAT = "%s with %s keys:";
    private static final String STRING_VALUE_1 = "test";
    private static final String STRING_VALUE_2 = "aa";
    private static final String STRING_VALUE_3 = "0d$r12mb";
    private static final String STRING_VALUE_4 = "@";
    private static final String STRING_VALUE_5 = "FourCharsObject";

    public static void main(String[] args) {

        HashMap<HashStringLengthObject, PowerTool> hashStringMap = new HashMap<>();
        HashMap<HashFirstFourCharsObject, PowerTool> hashCharsMap = new HashMap<>();
        LinkedHashMap<HashStringLengthObject, PowerTool> linkedHashStringMap = new LinkedHashMap<>();
        LinkedHashMap<HashFirstFourCharsObject, PowerTool> linkedHashCharsMap = new LinkedHashMap<>();

        PowerTool tool1 = new Drill(1L, "Bosch 10", 2098, 1000, 2400);
        PowerTool tool2 = new Drill(2L, "Bosch 20", 2598, 1400, 2800);
        PowerTool tool3 = new Drill(3L, "Bosch 30", 3000, 1200, 2900);
        PowerTool tool4 = new Drill(4L, "Bosch 40", 1500, 1000, 2000);
        PowerTool tool5 = new Drill(5L, "Bosch 50", 1098, 800, 1800);

        HashStringLengthObject stringLength1 = new HashStringLengthObject(STRING_VALUE_1);
        HashStringLengthObject stringLength2 = new HashStringLengthObject(STRING_VALUE_2);
        HashStringLengthObject stringLength3 = new HashStringLengthObject(STRING_VALUE_3);
        HashStringLengthObject stringLength4 = new HashStringLengthObject(STRING_VALUE_4);
        HashStringLengthObject stringLength5 = new HashStringLengthObject(STRING_VALUE_5);

        HashFirstFourCharsObject chars1 = new HashFirstFourCharsObject(STRING_VALUE_1);
        HashFirstFourCharsObject chars2 = new HashFirstFourCharsObject(STRING_VALUE_2);
        HashFirstFourCharsObject chars3 = new HashFirstFourCharsObject(STRING_VALUE_3);
        HashFirstFourCharsObject chars4 = new HashFirstFourCharsObject(STRING_VALUE_4);
        HashFirstFourCharsObject chars5 = new HashFirstFourCharsObject(STRING_VALUE_5);

        hashStringMap.put(stringLength1, tool1);
        hashStringMap.put(stringLength2, tool2);
        hashStringMap.put(stringLength3, tool3);
        hashStringMap.put(stringLength4, tool4);
        hashStringMap.put(stringLength5, tool5);

        hashCharsMap.put(chars1, tool1);
        hashCharsMap.put(chars2, tool2);
        hashCharsMap.put(chars3, tool3);
        hashCharsMap.put(chars4, tool4);
        hashCharsMap.put(chars5, tool5);

        linkedHashStringMap.put(stringLength1, tool1);
        linkedHashStringMap.put(stringLength2, tool2);
        linkedHashStringMap.put(stringLength3, tool3);
        linkedHashStringMap.put(stringLength4, tool4);
        linkedHashStringMap.put(stringLength5, tool5);

        linkedHashCharsMap.put(chars1, tool1);
        linkedHashCharsMap.put(chars2, tool2);
        linkedHashCharsMap.put(chars3, tool3);
        linkedHashCharsMap.put(chars4, tool4);
        linkedHashCharsMap.put(chars5, tool5);

        printInfo(hashStringMap, HASH_MAP, HASH_STRING_OBJECT);
        printInfo(hashCharsMap, HASH_MAP, HASH_CHAR_OBJECT);
        printInfo(linkedHashStringMap, LINKED_HASH_MAP, HASH_STRING_OBJECT);
        printInfo(linkedHashCharsMap, LINKED_HASH_MAP, HASH_CHAR_OBJECT);
    }

    /**
     * Outputs map data.
     */
    private static void printInfo(Map<?, PowerTool> map, String mapName, String keyClassName) {
        log.info(String.format(HEAD_FORMAT, mapName, keyClassName));
        log.info(map);
        log.info(BORDER);
    }
}

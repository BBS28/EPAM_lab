package com.epam.lab.shchehlov.task_06.container;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.container.serializer.MultiSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    private static final String MULTI_SERIALIZE_FILE = "src\\main\\resources\\task_06\\containers.txt";

    public static void main(String[] args) {
        List<PowerTool> toolList = new ArrayList<>();
        toolList.add(new Drill(1L, "Drill Bosch 10", 2098, 1000, 2400));
        toolList.add(new Drill(2L, "Drill Bosch 20", 2598, 1400, 2800));
        toolList.add(new Drill(3L, "Drill Makita 30", 3000, 1200, 2900));
        toolList.add(new Drill(4L, "Drill Makita 40", 1500, 1000, 2000));
        toolList.add(new Drill(5L, "Drill DeWalt 50", 1098, 800, 1800));
        toolList.add(new Drill(6L, "Drill DeWalt 60", 3500, 1200, 3200));
        toolList.add(new CordedPowerTool(7L, "Jigsaw Bosch 100", 1000, 800));
        toolList.add(new CordedPowerTool(8L, "Jigsaw Makita 200", 1500, 1200));
        toolList.add(new CordedPowerTool(9L, "Milling Cutter DeWalt 300", 1900, 1400));
        toolList.add(new CordlessPowerTool(10L, "Screwdriver Bosch 30", 1900, 12, 2));

        MultiSerializer serializer = new MultiSerializer(toolList);
        File file = new File(MULTI_SERIALIZE_FILE);

        for (int i = 0; i < 10; i++) {
            serializer.serialize(MULTI_SERIALIZE_FILE, i);
            System.out.printf("Times serialized - %d, file size - %d%n", i, file.length());
        }

        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < 10; i++) {
            serializer.gZipSerialize(MULTI_SERIALIZE_FILE, i);
            System.out.printf("Times serialized - %d, file size - %d%n", i, file.length());
        }
    }
}

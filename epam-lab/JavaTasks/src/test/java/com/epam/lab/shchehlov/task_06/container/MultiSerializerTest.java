package com.epam.lab.shchehlov.task_06.container;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.container.serializer.MultiSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiSerializerTest {
    private MultiSerializer serializer;

    @Before
    public void before() {
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
        serializer = new MultiSerializer(toolList);
    }

    @Test
    public void shouldCreateSerializedGzipFileSmallerThanSimplySerialized() {
        String serializeFilePath = "src\\test\\resources\\task_06\\serialized.srl";
        String gZipSerializeFilePath = "src\\test\\resources\\task_06\\serialized.gzip";

        serializer.serialize(serializeFilePath, 10);
        serializer.gZipSerialize(gZipSerializeFilePath, 10);
        File serializeFile = new File(serializeFilePath);
        File gZipSerializeFile = new File(gZipSerializeFilePath);

        Assert.assertTrue(serializeFile.length() > gZipSerializeFile.length());
    }
}

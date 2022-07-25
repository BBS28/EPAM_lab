package com.epam.lab.shchehlov.task_06.container.serializer;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Class for working with serialization of a list of products inherited from PowerTool .
 *
 * @author B.Shchehlov
 */
public class MultiSerializer {
    private static final String WARNING_SAVE = "Can not save data!";
    private final List<PowerTool> container;

    public MultiSerializer(List<PowerTool> container) {
        this.container = container;
    }

    /**
     * Serializes a list of products to a file at the given path {@param pathFile} given number of times {@param numberTimes}
     */
    public void serialize(String pathFile, int numberTimes) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(pathFile))){
            for (int i = 0; i < numberTimes; i++) {
                outputStream.writeObject(container);
            }
        } catch (IOException e) {
            System.out.println(WARNING_SAVE);
            e.printStackTrace();
        }
    }

    /**
     * Serializes a list of products to a file GZip at the given path {@param pathFile} given number of times {@param numberTimes}
     */
    public void gZipSerialize(String pathFile, int numberTimes) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(pathFile)))){
            for (int i = 0; i < numberTimes; i++) {
                outputStream.writeObject(container);
            }
        } catch (IOException e) {
            System.out.println(WARNING_SAVE);
            e.printStackTrace();
        }
    }
}

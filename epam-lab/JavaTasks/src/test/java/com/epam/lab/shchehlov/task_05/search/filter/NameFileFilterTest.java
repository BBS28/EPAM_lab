package com.epam.lab.shchehlov.task_05.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.filter.NameFileFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameFileFilterTest {
    private static final String FILE_PATH = "src\\test\\resources\\task_05\\folder_1\\file_1.txt";

    private File file;
    private static ByteArrayOutputStream baos;
    private NameFileFilter nameFileFilter;

    @Before
    public void before() {
        file = new File(FILE_PATH);
    }

    @Test
    public void shouldReturnTrueFileNameIsAppropriate() {
        String fileName = "file";
        nameFileFilter = new NameFileFilter(fileName);

        assertTrue(nameFileFilter.isAppropriate(file));
    }

    @Test
    public void shouldReturnFalseFileNameIsAppropriate() {
        String fileName = "test";
        nameFileFilter = new NameFileFilter(fileName);

        assertFalse(nameFileFilter.isAppropriate(file));
    }
}

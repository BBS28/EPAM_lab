package com.epam.lab.shchehlov.task_05.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.filter.SizeFileFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SizeFileFilterTest {
    private static final String FILE_PATH = "src\\test\\resources\\task_05\\folder_1\\file_1.txt";

    private File file;
    private SizeFileFilter sizeFileFilter;

    @Before
    public void before() {
        file = new File(FILE_PATH);
    }

    @Test
    public void shouldReturnTrueFileSizeIsAppropriate() {
        long minSize = 40L;
        long maxSize = 60L;
        sizeFileFilter = new SizeFileFilter(minSize, maxSize);

        assertTrue(sizeFileFilter.isAppropriate(file));
    }

    @Test
    public void shouldReturnFalseFileSizeIsAppropriate() {
        long minSize = 60L;
        long maxSize = 80L;
        sizeFileFilter = new SizeFileFilter(minSize, maxSize);

        assertFalse(sizeFileFilter.isAppropriate(file));
    }
}

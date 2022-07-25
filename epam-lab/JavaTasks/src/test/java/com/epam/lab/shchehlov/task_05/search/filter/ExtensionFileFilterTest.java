package com.epam.lab.shchehlov.task_05.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.filter.ExtensionFileFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExtensionFileFilterTest {
    private static final String FILE_PATH = "src\\test\\resources\\task_05\\folder_2\\file_test.abc";

    private File file;
    private static ByteArrayOutputStream baos;
    private ExtensionFileFilter extensionFileFilter;

    @Before
    public void before() {
        file = new File(FILE_PATH);
    }

    @Test
    public void shouldReturnTrueWhenIsAppropriateRightExtension() {
        String extension = "abc";
        extensionFileFilter = new ExtensionFileFilter(extension);

        assertTrue(extensionFileFilter.isAppropriate(file));
    }

    @Test
    public void shouldReturnFalseWhenIsAppropriateRightExtension() {
        String extension = "qwe";
        extensionFileFilter = new ExtensionFileFilter(extension);

        assertFalse(extensionFileFilter.isAppropriate(file));
    }
}

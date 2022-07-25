package com.epam.lab.shchehlov.task_05.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.filter.ModificationDateFileFilter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModificationDateFileFilterTest {
    private static final String FILE_PATH = "src\\test\\resources\\task_05\\folder_1\\file_1.txt";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private File file;
    private ModificationDateFileFilter modificationDateFileFilter;

    @Before
    public void before() {
        file = new File(FILE_PATH);
    }

    @Test
    public void shouldReturnTrueLastModificationDateIsAppropriate() {
        LocalDateTime date1 = LocalDateTime.parse("2021-11-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2021-12-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        modificationDateFileFilter = new ModificationDateFileFilter(date1, date2);

        assertTrue(modificationDateFileFilter.isAppropriate(file));
    }

    @Test
    public void shouldReturnFalseLastModificationDateIsAppropriate() {
        LocalDateTime date1 = LocalDateTime.parse("2020-11-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2020-12-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        modificationDateFileFilter = new ModificationDateFileFilter(date1, date2);

        assertFalse(modificationDateFileFilter.isAppropriate(file));
    }
}

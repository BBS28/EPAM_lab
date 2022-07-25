package com.epam.lab.shchehlov.task_05.file.search.filter;

import com.epam.lab.shchehlov.task_05.file.search.constant.Constant;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Specific chain element filters by file by last modification date.
 *
 * @author B.Shchehlov
 */
public class ModificationDateFileFilter extends BasicFileFilter {
    LocalDateTime minDate;
    LocalDateTime maxDate;

    public ModificationDateFileFilter(LocalDateTime minDate, LocalDateTime maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    @Override
    public boolean isAppropriate(File file) {
        long date = file.lastModified();
        long date1 = minDate.atZone(ZoneId.of(Constant.UTC)).toInstant().toEpochMilli();
        long date2 = maxDate.atZone(ZoneId.of(Constant.UTC)).toInstant().toEpochMilli();

        if (date1 > date2) {
            throw new IllegalArgumentException();
        }
        return date >= date1 && date <= date2;
    }
}

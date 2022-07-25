package com.epam.shchehlov.util;

import static com.epam.shchehlov.constant.Constant.PAGINATION_DISPLACEMENT;
import static com.epam.shchehlov.constant.Constant.PAGINATION_MAX_SIZE;

public class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * Returns array with pages numbers for displaying pagination.
     *
     * @param pageNumber    current page number.
     * @param numberOfPages amount of all pages.
     * @return array with pages numbers.
     */
    public static int[] getPagesForPagination(int pageNumber, int numberOfPages) {
        int[] pages;
        int firstPage;
        int lastPage;

        if (numberOfPages == 0) {
            numberOfPages = 1;
        }

        if (numberOfPages >= PAGINATION_MAX_SIZE) {
            pages = new int[PAGINATION_MAX_SIZE];
        } else {
            pages = new int[numberOfPages];
        }

        if (numberOfPages >= PAGINATION_MAX_SIZE) {
            if (pageNumber < PAGINATION_MAX_SIZE) {
                firstPage = 1;
            } else if (pageNumber == numberOfPages) {
                firstPage = pageNumber - PAGINATION_DISPLACEMENT;
            } else {
                firstPage = pageNumber - 1;
            }

            lastPage = firstPage + PAGINATION_DISPLACEMENT;
        } else {
            firstPage = 1;
            lastPage = numberOfPages;
        }

        int index = 0;
        for (int i = firstPage; i <= lastPage; i++) {
            pages[index++] = i;
        }

        return pages;
    }
}

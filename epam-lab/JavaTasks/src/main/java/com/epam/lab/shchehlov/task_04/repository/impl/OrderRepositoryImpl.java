package com.epam.lab.shchehlov.task_04.repository.impl;

import com.epam.lab.shchehlov.task_04.repository.OrderRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * An implementation of the {@code OrderRepository} for working with a repository that stores a list of orders made
 *
 * @author B.Shchehlov
 */
public class OrderRepositoryImpl implements OrderRepository {
    private static final String NO_ANY_ORDER = "No order has been created";
    private static final String ORDER_NOT_FOUND = "Order with date %s does not exist";
    private static final String UTC = "UTC";

    private final TreeMap<LocalDateTime, Hashtable<Long, Integer>> orderList;

    public OrderRepositoryImpl() {
        this.orderList = new TreeMap<>();
    }

    public OrderRepositoryImpl(TreeMap<LocalDateTime, Hashtable<Long, Integer>> orderList) {
        this.orderList = orderList;
    }

    @Override
    public void addOne(LocalDateTime date, Hashtable<Long, Integer> order) {
        orderList.put(date, order);
    }

    @Override
    public Hashtable<Long, Integer> getByDate(LocalDateTime date) {
        if (!orderList.containsKey(date)) {
            throw new NoSuchElementException(String.format(ORDER_NOT_FOUND, date));
        }
        return orderList.get(date);
    }

    /**
     * Returns the order's date that is closest to the given one
     *
     * @throws NoSuchElementException if no such order in the list
     */
    @Override
    public Hashtable<Long, Integer> getByClosestDate(LocalDateTime date) {
        Map.Entry<LocalDateTime, Hashtable<Long, Integer>> result;
        Map.Entry<LocalDateTime, Hashtable<Long, Integer>> low = orderList.floorEntry(date);
        Map.Entry<LocalDateTime, Hashtable<Long, Integer>> high = orderList.ceilingEntry(date);
        long dateTime = date.atZone(ZoneId.of(UTC)).toInstant().toEpochMilli();

        if (low == null && high == null) {
            throw new NoSuchElementException(NO_ANY_ORDER);
        }

        if (low == null) {
            result = high;
        } else if (high == null) {
            result = low;
        } else {
            result = dateTime - low.getKey().atZone(ZoneId.of(UTC)).toInstant().toEpochMilli()
                    < high.getKey().atZone(ZoneId.of(UTC)).toInstant().toEpochMilli() - dateTime ? low : high;
        }

        return result.getValue();
    }

    @Override
    public TreeMap<LocalDateTime, Hashtable<Long, Integer>> getAll() {
        return orderList;
    }

    /**
     * Returns the list of orders in the interval between the given dates
     */
    @Override
    public TreeMap<LocalDateTime, Hashtable<Long, Integer>> getOrdersForPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        long date1 = dateFrom.atZone(ZoneId.of(UTC)).toInstant().toEpochMilli();
        long date2 = dateTo.atZone(ZoneId.of(UTC)).toInstant().toEpochMilli();

        TreeMap<LocalDateTime, Hashtable<Long, Integer>> result = new TreeMap<>();
        for (Map.Entry<LocalDateTime, Hashtable<Long, Integer>> entry : orderList.entrySet()) {
            long date = entry.getKey().atZone(ZoneId.of(UTC)).toInstant().toEpochMilli();
            if (date >= date1 && date <= date2) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}

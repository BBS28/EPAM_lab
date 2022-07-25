package com.epam.shchehlov.security.impl;

import com.epam.shchehlov.entity.attribute.Role;
import com.epam.shchehlov.security.AccessService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccessServiceImpl implements AccessService {

    private final Map<String, List<String>> accessMap;

    public AccessServiceImpl(Map<String, List<String>> accessMap) {
        this.accessMap = accessMap;
    }

    @Override
    public boolean isUrlAccessible(Role role, String url) {
        Optional<String> page = accessMap.keySet().stream().filter(url::contains).findFirst();
        if(page.isPresent()) {
            List<String> roles = accessMap.get(page.get());
            return roles.contains(role.toString().toLowerCase());
        }
        return true;
    }

    @Override
    public boolean isLimitedAccess(String url) {
        return accessMap.keySet().stream().anyMatch(url::contains);
    }
}

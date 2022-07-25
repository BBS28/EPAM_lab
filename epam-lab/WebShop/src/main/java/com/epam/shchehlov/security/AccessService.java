package com.epam.shchehlov.security;

import com.epam.shchehlov.entity.attribute.Role;

public interface AccessService {

    boolean isUrlAccessible(Role role, String url);

    boolean isLimitedAccess(String url);
}

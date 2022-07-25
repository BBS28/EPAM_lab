package com.epam.shchehlov.service.impl;

import com.epam.shchehlov.database.transaction.TransactionManager;
import com.epam.shchehlov.database.transaction.impl.JdbcTransactionManager;
import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.exception.ObjectNotFoundException;
import com.epam.shchehlov.repository.UserRepository;
import com.epam.shchehlov.service.UserService;

import java.util.List;

import static com.epam.shchehlov.constant.Constant.USER_LOGIN_NOT_FOUND_FORMAT;

/**
 * Service for working with entity user using JDBC.
 *
 * @author B.Shchehlov.
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TransactionManager transactionManager;

    public UserServiceImpl(UserRepository userRepository, JdbcTransactionManager jdbcTransactionManager) {
        this.userRepository = userRepository;
        this.transactionManager = jdbcTransactionManager;
    }

    @Override
    public List<User> getAllUsers() {
        return transactionManager.doInquiryTransaction(userRepository::getAllUsers);
    }

    @Override
    public User getUser(String login) {
        User user = transactionManager.doInquiryTransaction(() -> userRepository.getUser(login));
        if (user == null) {
            throw new ObjectNotFoundException(String.format(USER_LOGIN_NOT_FOUND_FORMAT, login));
        }
        return user;
    }

    @Override
    public long createUser(User user) {
        return transactionManager.doManipulationTransaction(() -> userRepository.createUser(user));
    }

    @Override
    public long updateUser(User user) {
        return transactionManager.doManipulationTransaction(() -> userRepository.updateUser(user));
    }

    @Override
    public long deleteUser(String login) {
        return transactionManager.doManipulationTransaction(() -> userRepository.deleteUser(login));
    }

    @Override
    public boolean isExistUser(String login) {
        return transactionManager.doInquiryTransaction(() -> userRepository.isExistUser(login));
    }
}

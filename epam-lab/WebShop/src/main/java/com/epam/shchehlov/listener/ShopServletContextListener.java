package com.epam.shchehlov.listener;

import com.epam.shchehlov.capatcha.CaptchaController;
import com.epam.shchehlov.capatcha.CaptchaService;
import com.epam.shchehlov.capatcha.factory.CaptchaServiceFactory;
import com.epam.shchehlov.database.transaction.impl.JdbcTransactionManager;
import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.entity.Order;
import com.epam.shchehlov.entity.OrderedProduct;
import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.exception.UserAvatarException;
import com.epam.shchehlov.repository.OrderRepository;
import com.epam.shchehlov.repository.ProductRepository;
import com.epam.shchehlov.repository.UserRepository;
import com.epam.shchehlov.repository.extractor.impl.CategoryExtractor;
import com.epam.shchehlov.repository.extractor.impl.ManufacturerExtractor;
import com.epam.shchehlov.repository.extractor.impl.ProductExtractor;
import com.epam.shchehlov.repository.extractor.impl.ProductNumbersExtractor;
import com.epam.shchehlov.repository.extractor.impl.UserExtractor;
import com.epam.shchehlov.repository.impl.OrderRepositoryImpl;
import com.epam.shchehlov.repository.impl.ProductRepositoryImpl;
import com.epam.shchehlov.repository.impl.UserRepositoryImpl;
import com.epam.shchehlov.repository.template.JdbcTemplate;
import com.epam.shchehlov.security.AccessService;
import com.epam.shchehlov.security.impl.AccessServiceImpl;
import com.epam.shchehlov.service.OrderService;
import com.epam.shchehlov.service.ProductService;
import com.epam.shchehlov.service.UserService;
import com.epam.shchehlov.service.impl.OrderServiceImpl;
import com.epam.shchehlov.service.impl.ProductServiceImpl;
import com.epam.shchehlov.service.impl.UserServiceImpl;
import com.epam.shchehlov.util.AccessXmlHandler;
import com.epam.shchehlov.util.DBUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.epam.shchehlov.constant.Constant.ACCESS_MAP;
import static com.epam.shchehlov.constant.Constant.AVATAR_DIRECTORY;
import static com.epam.shchehlov.constant.Constant.AVATAR_ENV_VARIABLE;
import static com.epam.shchehlov.constant.Constant.BACKSLASH;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_SET_TIME;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_STRATEGY;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_TIMEOUT;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_TYPE;
import static com.epam.shchehlov.constant.Constant.ORDER_SERVICE;
import static com.epam.shchehlov.constant.Constant.PARAMETER_ACCESS;
import static com.epam.shchehlov.constant.Constant.PRODUCT_SERVICE;
import static com.epam.shchehlov.constant.Constant.USER_SERVICE;

/**
 * Class for receiving notification events about ServletContext lifecycle changes.
 *
 * @author B.Shchehlov.
 */
public class ShopServletContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ShopServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Context initializing started");

        String avatarDirectoryPath = System.getenv(AVATAR_ENV_VARIABLE) + BACKSLASH;
        logger.info("avatar directory ==> " + avatarDirectoryPath);
        checkAvatarDirectory(avatarDirectoryPath);

        ServletContext servletContext = servletContextEvent.getServletContext();
        DataSource dataSource = DBUtils.getDataSource();
        logger.info("datasource created");

        JdbcTransactionManager jdbcTransactionManager = new JdbcTransactionManager(dataSource);
        logger.info("jdbcTransactionManager created");

        UserService userService = getUserService(jdbcTransactionManager);
        logger.info("userService created");

        ProductService productService = getProductService(jdbcTransactionManager);
        logger.info("productService created");

        OrderService orderService = getOrderService(jdbcTransactionManager);
        logger.info("orderService created");

        AccessService accessService = getAccessService(servletContext);
        logger.info("accessService created");

        String captchaTime = servletContext.getInitParameter(CAPTCHA_TIMEOUT);
        logger.info("captcha life time => " + captchaTime);

        CaptchaService captchaService = CaptchaServiceFactory.getCaptcha(servletContextEvent.getServletContext());
        logger.info("captchaService created");

        CaptchaController captchaController = new CaptchaController(captchaService, Long.parseLong(captchaTime));
        logger.info("removeCaptcha created");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        logger.info("scheduledExecutorService created");

        scheduledExecutorService.scheduleWithFixedDelay(captchaController, 0, CAPTCHA_SET_TIME, TimeUnit.MILLISECONDS);

        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(PRODUCT_SERVICE, productService);
        servletContext.setAttribute(ORDER_SERVICE, orderService);
        servletContext.setAttribute(CAPTCHA_STRATEGY, captchaService);
        servletContext.setAttribute(ACCESS_MAP, accessService);
        servletContext.setAttribute(CAPTCHA_TYPE, servletContext.getInitParameter(CAPTCHA_TYPE));
        servletContext.setAttribute(AVATAR_DIRECTORY, avatarDirectoryPath);
        logger.info("Context initializing finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Context destroying started");
        logger.info("Context destroying finished");
    }

    private void checkAvatarDirectory(String avatarDirectoryPath) {
        File avatarSaveDirectory = new File(avatarDirectoryPath);
        if (!avatarSaveDirectory.exists()) {
            logger.info("avatar directory is not exist");
            boolean isCreatedDirectory = avatarSaveDirectory.mkdirs();
            if (!isCreatedDirectory) {
                throw new UserAvatarException("Can't create avatar directory");
            }
        }
    }

    private UserService getUserService(JdbcTransactionManager jdbcTransactionManager) {
        UserExtractor userExtractor = new UserExtractor();
        JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>(userExtractor);
        UserRepository userRepository = new UserRepositoryImpl(jdbcTemplate);

        return new UserServiceImpl(userRepository, jdbcTransactionManager);
    }

    private ProductService getProductService(JdbcTransactionManager jdbcTransactionManager) {
        ProductExtractor productExtractor = new ProductExtractor();
        CategoryExtractor categoryExtractor = new CategoryExtractor();
        ManufacturerExtractor manufacturerExtractor = new ManufacturerExtractor();
        ProductNumbersExtractor productNumbersExtractor = new ProductNumbersExtractor();

        JdbcTemplate<Product> productJdbcTemplate = new JdbcTemplate<>(productExtractor);
        JdbcTemplate<Category> categoryJdbcTemplate = new JdbcTemplate<>(categoryExtractor);
        JdbcTemplate<Manufacturer> manufacturerJdbcTemplate = new JdbcTemplate<>(manufacturerExtractor);
        JdbcTemplate<Integer> productNumberJdbcTemplate = new JdbcTemplate<>(productNumbersExtractor);

        ProductRepository productRepository = new ProductRepositoryImpl(productJdbcTemplate, categoryJdbcTemplate, manufacturerJdbcTemplate, productNumberJdbcTemplate);

        return new ProductServiceImpl(productRepository, jdbcTransactionManager);
    }

    private OrderService getOrderService(JdbcTransactionManager jdbcTransactionManager) {
        JdbcTemplate<Order> orderJdbcTemplate = new JdbcTemplate<>(null);
        JdbcTemplate<OrderedProduct> orderedProductJdbcTemplate = new JdbcTemplate<>(null);

        OrderRepository orderRepository = new OrderRepositoryImpl(orderJdbcTemplate, orderedProductJdbcTemplate);

        return new OrderServiceImpl(orderRepository, jdbcTransactionManager);
    }

    private AccessService getAccessService(ServletContext servletContext) {
        String accessFilePath = servletContext.getInitParameter(PARAMETER_ACCESS);
        Map<String, List<String>> accessMap = AccessXmlHandler.parseXML(accessFilePath);
        logger.info("accessMap ==> " + accessMap);

        return new AccessServiceImpl(accessMap);
    }
}

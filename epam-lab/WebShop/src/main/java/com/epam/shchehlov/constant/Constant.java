package com.epam.shchehlov.constant;

/**
 * Class that stores constants.
 *
 * @author B.Shchehlov.
 */
public class Constant {

    private Constant() {
    }

    public static final String REGEX_USER_NAME = "^[\\w'\\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\\]]]{1,25}$";
    public static final String REGEX_USER_EMAIL = "^\\S+@\\S+\\.\\S+$";
    public static final String REGEX_USER_LOGIN = "^[A-Za-z][A-Za-z0-9_]{7,29}$";
    public static final String REGEX_USER_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    public static final String PAGE_NAME_HOME = "home";
    public static final String PAGE_NAME_REGISTRATION = "userRegistration";

    public static final String METHOD_GET_STARTED = "doGet started";
    public static final String METHOD_POST_STARTED = "doPost started";

    public static final String USER_LOGIN_NOT_FOUND_FORMAT = "User with login %s not found";
    public static final String INVALID_USER_FIRST_NAME = "firstNameIncorrect";
    public static final String INVALID_USER_LAST_NAME = "secondNameIncorrect";
    public static final String INVALID_USER_EMAIL = "emailIncorrect";
    public static final String INVALID_USER_LOGIN = "loginIncorrect";
    public static final String INVALID_USER_PASSWORD = "passwordIncorrect";
    public static final String INVALID_CAPTCHA = "incorrectCaptcha";
    public static final String CAPTCHA_TIME_IS_UP = "Register page time out please update page";

    public static final String MESSAGE_INVALID_INPUT = "Invalid input";
    public static final String MESSAGE_AVATAR_ERROR = "Can not save avatar";
    public static final String MESSAGE_LOGIN_EXISTS = "This login already exists!";
    public static final String MESSAGE_LOGIN_EXISTS_FORMAT = "User \"%s\" successfully created!";
    public static final String MESSAGE_LOGIN_FIELDS_EMPTY = "login or password empty";
    public static final String MESSAGE_LOGIN_WRONG = "wrongLoginPassword";

    public static final String MESSAGE_TRANSACTION_MANIPULATION_FAILED = "Transaction manipulation failed ";
    public static final String MESSAGE_TRANSACTION_INQUIRY_FAILED = "Transaction inquiry failed";
    public static final String MESSAGE_TRANSACTION_ERROR = "Can't execute transaction";
    public static final String MESSAGE_CANT_CLOSE_CONNECTION = "Can't close connection ";
    public static final String MESSAGE_CANT_ROLLBACK = "Can't rollback connection ";
    public static final String MESSAGE_CANT_EXTRACT_ENTITIES = "Can't extract all entity from database";
    public static final String MESSAGE_CANT_EXTRACT_ENTITY = "Can't extract entity from database";
    public static final String MESSAGE_CANT_CREATE_ENTITY = "Can't create entity in database";
    public static final String MESSAGE_CANT_UPDATE_ENTITY = "Can't update entity in database";
    public static final String MESSAGE_CANT_DELETE_ENTITY = "Can't delete entity from database";
    public static final String MESSAGE_CANT_PARSE_XML = "Can't parse XML";
    public static final String MESSAGE_CANT_FIND_AVATAR_DIRECTORY = "Can't find avatar directory";
    public static final String MESSAGE_AVATAR_ENV_VARIABLE_ERROR = "Can't find value for avatar env variable";
    public static final String MESSAGE_CANT_SAVE_AVATAR = "Avatar file can not be saved";
    public static final String MESSAGE_WRONG_QUANTITY = "wrong quantity value";
    public static final String SECURE_PASSWORD_ERROR = "Can't generate secure password ";
    public static final String MESSAGE_GZIP_NOT_SUPPORTED = "gzip is not supported by this method";
    public static final String MESSAGE_PW_OBTAINED = "PrintWriter already obtained";
    public static final String MESSAGE_OS_OBTAINED = "OutputStream already obtained";
    public static final String MESSAGE_NO_PRODUCT = "No products were found for your request!";

    public static final String USER_SERVICE = "userService";
    public static final String PRODUCT_SERVICE = "productService";
    public static final String ORDER_SERVICE = "orderService";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_ID = "id";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MAILING = "mailing";
    public static final String USER_CAPTCHA = "user_captcha";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_ROLE = "role";
    public static final String AVATAR_DIRECTORY = "avatarDirectory";
    public static final String ACCESS_MAP = "accessMap";

    public static final String CURRENT_USER = "currentUser";
    public static final String IMAGE_EXTENSION = "jpeg";
    public static final String DELIMITER_DOT = ".";

    public static final String REFERER = "Referer";

    public static final String INFO_MESSAGE = "message_info";
    public static final String ERROR_MESSAGE = "message_error";
    public static final String LOGIN_ERROR_MESSAGE = "message_login_error";
    public static final String ERROR_FIRST_NAME = "error_message_first_name";
    public static final String ERROR_LAST_NAME = "error_message_last_name";
    public static final String ERROR_EMAIL = "error_message_email";
    public static final String ERROR_LOGIN = "error_message_login";
    public static final String ERROR_PASSWORD = "error_message_password";
    public static final String LOG_IN_ERROR_MESSAGE = "login_message_error";

    public static final String TEMP_FIRST_NAME = "temp_first_name";
    public static final String TEMP_LAST_NAME = "temp_last_name";
    public static final String TEMP_EMAIL = "temp_email";
    public static final String TEMP_LOGIN = "temp_login";

    public static final String CAPTCHA_VALUE = "captcha_value";
    public static final String CAPTCHA_CREATED_TIME = "captcha_created_time";
    public static final String CAPTCHA_TIMEOUT = "captchaTimeout";
    public static final long CAPTCHA_SET_TIME = 10000;

    public static final String COOKIE_CONST = "cookie";
    public static final String FIELD_CONST = "field";
    public static final String SESSION = "session";
    public static final String CAPTCHA_TYPE = "captchaType";
    public static final String HIDDEN_FIELD = "hidden_field";
    public static final String CAPTCHA_STRATEGY = "captchaStrategy";

    public static final int CAPTCHA_IMAGE_WIDTH = 180;
    public static final int CAPTCHA_IMAGE_HEIGHT = 60;
    public static final int CAPTCHA_CIRCLE_NUMBERS = 3;
    public static final int CAPTCHA_LENGTH = 6;
    public static final int CAPTCHA_ELEMENT_BOUND = 10;

    public static final String AVATAR_ENV_VARIABLE = "PT_SHOP_AVA";
    public static final String HOME_PAGE = "/WEB-INF/jsp/home.jsp";
    public static final String PRODUCTS_PAGE = "/WEB-INF/jsp/products.jsp";
    public static final String CART_PAGE = "/WEB-INF/jsp/cart.jsp";
    public static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
    public static final String ORDER_PAGE = "/WEB-INF/jsp/order.jsp";
    public static final String CREATED_ORDER_PAGE = "/WEB-INF/jsp/createdOrder.jsp";
    public static final String ERROR_403_PAGE = "/WEB-INF/jsp/error403.jsp";
    public static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
    public static final String ADMIN_PAGE = "/WEB-INF/jsp/admin.jsp";

    public static final String DEFAULT_AVATAR = "/images/avatar.jpeg";

    public static final String PARAMETER_ACCESS = "access";

    public static final String HASHING = "SHA-256";
    public static final String SALT = "DfgGH2";

    public static final String PARAMETER_MIN_PRICE = "min_price";
    public static final String PARAMETER_MAX_PRICE = "max_price";
    public static final String PARAMETER_PRODUCT_NAME = "product_name";
    public static final String PARAMETER_MANUFACTURER = "manufacturer";
    public static final String PARAMETER_CATEGORY = "category";
    public static final String PARAMETER_PRODUCT_ON_PAGE = "products_on_page";
    public static final String PARAMETER_PAGE_NUMBER = "page_number";
    public static final String PARAMETER_SORT_FIELD = "sort_field";
    public static final String PARAMETER_SORT_DIRECTION = "sort_direction";
    public static final String PARAMETER_QUANTITY = "quantity";
    public static final String PARAMETER_PRODUCT_ID = "productId";
    public static final String PARAMETER_ACTION = "action";

    public static final String ATTRIBUTE_MAX_PRICE = "maxPrice";
    public static final String ATTRIBUTE_MIN_PRICE = "minPrice";
    public static final String ATTRIBUTE_PRODUCT_NAME = "productName";
    public static final String ATTRIBUTE_SORT_FIELD = "sortField";
    public static final String ATTRIBUTE_PAGE_NUMBER = "pageNumber";
    public static final String ATTRIBUTE_PRODUCT_ON_PAGE = "productsOnPage";
    public static final String ATTRIBUTE_IS_ASCENDING = "isAscending";
    public static final String ATTRIBUTE_NUMBER_OF_PAGES = "numberOfPages";
    public static final String ATTRIBUTE_PAGES = "pages";
    public static final String ATTRIBUTE_CATEGORIES = "categories";
    public static final String ATTRIBUTE_MANUFACTURERS = "manufacturers";
    public static final String ATTRIBUTE_PRODUCT_LIST = "productList";
    public static final String ATTRIBUTE_NO_PRODUCTS = "noProducts";
    public static final String ATTRIBUTE_ERROR_INPUT = "error_input";

    public static final String SESSION_ATTRIBUTE_CATEGORIES = "categoryList";
    public static final String SESSION_ATTRIBUTE_MANUFACTURERS = "manufacturerList";
    public static final String SESSION_ATTRIBUTE_CART = "cart";
    public static final String SESSION_ATTRIBUTE_ORDER = "order";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MANUFACTURER_NAME = "manufacturer_name";
    public static final String CATEGORY_NAME = "category_name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String PRODUCT_NUMBER = "products_number";
    public static final String INPUT = "Input";
    public static final String EMPTY = "empty";
    public static final String CLEAR = "clear";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String DELIVERY_PAYMENT = "delivery_payment";
    public static final String PAYMENT_DETAILS= "payment_details";
    public static final String ORDER_CREATED = "order created";

    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_LOCALE = "locale";
    public static final String PROPERTY_TOTAL = "total";
    public static final String PROPERTY_PRODUCT_ID = "productId";
    public static final String PROPERTY_VALUE = "value";
    public static final String PROPERTY_AMOUNT = "amount";

    public static final String FILTER_INIT = "filter init";
    public static final String FILTER_DESTROY = "filter destroy";

    public static final int PRODUCT_ON_PAGE_DEFAULT = 6;
    public static final int MIN_PRICE_DEFAULT = 0;
    public static final int MAX_PRICE_DEFAULT = 10000;
    public static final int PAGINATION_MAX_SIZE = 3;
    public static final int PAGINATION_DISPLACEMENT = 2;
    public static final int QUANTITY_MIN = 0;
    public static final int QUANTITY_MAX = 20;

    public static final String PRODUCT_NAME = "name";
    public static final String FALSE = "false";
    public static final String EMPTY_STRING = "";
    public static final String BACKSLASH = "\\";

    public static final String PARAMETER_LOCALE = "lang";
    public static final String LOCALE = "locale";
    public static final String COOKIE = "cookie";
    public static final String COOKIE_TIME = "cookieTime";
    public static final String DEFAULT_LOCALE = "defaultLocale";
    public static final String DATA_LOCALE = "dataLocale";
    public static final String STORAGE_LOCALE = "storageLocale";

    public static final String CACHE_CONTROL = "Cache-control";
    public static final String EXPIRES = "Expires";
    public static final String PRAGMA = "Pragma";
    public static final String NO_CACHE = "no-cache";
    public static final String NO_STORE = "no-store";
    public static final String MAX_AGE_ZERO = "max-age=0";

    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String GZIP = "gzip";
    public static final String REGEX_IMAGE = ".*(jpeg|jpg|png)";
    public static final String PATH_CAPTCHA = "//WebShop//captcha";
}

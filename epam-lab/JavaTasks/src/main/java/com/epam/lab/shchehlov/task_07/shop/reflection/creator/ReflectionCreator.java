package com.epam.lab.shchehlov.task_07.shop.reflection.creator;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_07.shop.reflection.annotation.FieldName;
import com.epam.lab.shchehlov.task_07.shop.reflection.constant.Constant;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.Initializer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.ManualInitializer;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * Class for creating new product with the help of reflection.
 *
 * @author B.Shchehlov
 */
public class ReflectionCreator {
    private static final Logger log = Logger.getLogger(ReflectionCreator.class.getName());
    private final String className;
    private final Initializer initializer;
    private final ResourceBundle resourceBundle;

    public ReflectionCreator(String className, Initializer initializer, ResourceBundle resourceBundle) {
        this.className = className;
        this.initializer = initializer;
        this.resourceBundle = resourceBundle;
    }

    /**
     * Returns object of PowerTool class created with the help of reflection.
     *
     * @return object of PowerTool class created with the help of reflection.
     */
    public PowerTool create() {
        Object product = null;
        try {
            product = Class.forName(className).getConstructor().newInstance();
            Class productClazz = product.getClass();
            boolean hasSuper = true;

            do {
                for (Field field : productClazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(FieldName.class)) {
                        Method initMethod = getInitMethod(field, initializer);
                        field.setAccessible(true);
                        if (initializer instanceof ManualInitializer) {
                            System.out.println(resourceBundle.getString("ENTER")
                                    .concat(Constant.SPACE)
                                    .concat(resourceBundle.getString(field.getDeclaredAnnotation(FieldName.class).value()))
                                    .concat(Constant.COLON));
                        }
                        field.set(product, initMethod.invoke(initializer));
                    }
                }
                if (productClazz.getSuperclass() != null) {
                    productClazz = productClazz.getSuperclass();
                } else {
                    hasSuper = false;
                }
            } while (hasSuper);

        } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(Constant.INVALID_CREATION, e);
            System.out.println(resourceBundle.getString("IMPOSSIBLE_CREATION"));
        }
        return (PowerTool) product;

    }

    /**
     * Returns the method that is required to initialize the field passed to the parameters
     *
     * @param field       to be initialized
     * @param initializer containing methods for initialization
     * @return method that is required to initialize the field passed to the parameters
     * @throws NoSuchMethodException in case there is no method for initialization
     */
    private static Method getInitMethod(Field field, Initializer initializer) throws NoSuchMethodException {
        for (Method method : initializer.getClass().getDeclaredMethods()) {
            String methodSubName = method.getName().toLowerCase().substring(4);
            if (method.getReturnType().equals(field.getType())
                    && methodSubName.equals(field.getName().toLowerCase())) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }
}

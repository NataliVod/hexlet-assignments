package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
import org.springframework.stereotype.Component;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Object.class);
    private Map<String, String> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        Inspect annotation = bean.getClass().getAnnotation(Inspect.class);
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            beans.put(beanName, annotation.level());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        Object inspectedBean = beans.get(beanName);
        if (inspectedBean != null) {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {

                        String methodName = method.getName();
                        String arguments = Arrays.toString(args);

                        String message = String.format("Was called method: %s() with arguments: %s",
                                methodName, arguments);

                        String level = beans.get(beanName);
                        if (level.equals("info")) {
                            LOGGER.info(message);
                        } else {
                            LOGGER.debug(message);
                        }
                        return method.invoke(bean, args);
                    }
            );
        }
        return bean;

    }
}
// END

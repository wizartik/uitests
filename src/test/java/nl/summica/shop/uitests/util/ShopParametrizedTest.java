package nl.summica.shop.uitests.util;


import nl.summica.shop.uitests.util.selenium.DriverFactory;
import nl.summica.shop.uitests.util.selenium.WebdriverInitCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@ArgumentsSource(TestedPageContextArgumentsProvider.class)
@ExtendWith(WebdriverInitCallback.class)
public @interface ShopParametrizedTest {
    DriverFactory.Browser[] browsers() default {};

    String[] paths() default {};
}

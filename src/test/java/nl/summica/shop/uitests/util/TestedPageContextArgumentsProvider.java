package nl.summica.shop.uitests.util;

import nl.summica.shop.uitests.util.selenium.DriverFactory;
import nl.summica.shop.uitests.util.selenium.TestedPageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static nl.summica.shop.uitests.util.Arguments.*;

public class TestedPageContextArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<ShopParametrizedTest> {

    private DriverFactory.Browser[] browsers;
    private String[] paths;

    public void accept(ShopParametrizedTest source) {
        browsers = source.browsers();
        if (ArrayUtils.isEmpty(source.browsers())) {
            browsers = DriverFactory.Browser.values();
        }
        paths = source.paths();
        if (ArrayUtils.isEmpty(source.paths())) {
//            paths = Site.ALL_SITES;
            paths = new String[]{Site.TEST};
        }
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        final String testMethod = extensionContext.getDisplayName();
        List<Arguments> arguments = new ArrayList<>();
        Arrays.stream(browsers).forEach(browser -> {
            Arrays.stream(paths).forEach(path -> {
                arguments.add(Arguments.of(createContext(browser, path, testMethod)));
            });
        });
        return arguments.stream();
    }

    private TestedPageContext createContext(DriverFactory.Browser browser, String path, String testMethod) {
        TestedPageContext ctx = new TestedPageContext();
        ctx.setBrowser(browser);
        ctx.setServerUrl(SERVER_URL);
        ctx.setPath(path);
        ctx.setTestMethodName(testMethod);
        ctx.setUserLogin(User.LOGIN);
        ctx.setUserPassword(User.PASSWORD);
        return ctx;
    }
}

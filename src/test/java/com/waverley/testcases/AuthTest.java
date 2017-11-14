package com.waverley.testcases;

import com.waverley.model.User;
import com.waverley.utils.CustomAssertions;
import io.github.sskorol.core.DataSupplier;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.waverley.utils.JsonUtils.jsonToEntity;
import static java.util.Optional.ofNullable;

@Slf4j
public class AuthTest {

    @DataSupplier(name = "User provider", flatMap = true)
    public User streamOfData(Method method) {
        return ofNullable(method.getDeclaredAnnotation(DataAnnotation.class))
                .map(DataAnnotation::sourceOfData)
                .map(source -> jsonToEntity(source, User.class))
                .orElseGet(User::fake);
    }

    @BeforeMethod
    public void setUp(ITestContext context, Method method) {
        System.out.println(context.getCurrentXmlTest().getParameter("browser"));
        System.out.println(method.getDeclaredAnnotation(Test.class));
        System.out.println(method.getName());
    }

    @DataAnnotation(sourceOfData = "data.json")
    @Test(dataProvider = "User provider")
    public void userWithValidCredentials(final User user) {
        CustomAssertions.assertThat(user).hasUsername("bashlak");
    }
}

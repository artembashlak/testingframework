package com.waverley.testcases;

import com.waverley.assertions.CustomAssertions;
import com.waverley.data.DataReader;
import com.waverley.model.User;
import io.github.sskorol.core.DataSupplier;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.waverley.utils.JsonUtils.jsonToEntity;
import static java.util.Optional.ofNullable;
import static com.waverley.utils.ServiceLoaderUtils.load;

@Slf4j
public class AuthTest {

  @DataSupplier(name = "User provider", flatMap = true)
  public User streamOfData(Method method) {
    return ofNullable(method.getDeclaredAnnotation(DataAnnotation.class))
        .map(DataAnnotation::sourceOfData)
        .map(source -> jsonToEntity(source, User.class))
        .orElseGet(User::fake);
  }

  public DataReader getReader(final String dataSource) {

    StreamEx.of(load(DataReader.class, ClassLoader.getSystemClassLoader()))
            .findFirst(dataReader -> dataReader.getClass().getSimpleName().matches())

  }

  @BeforeMethod
  public void setUp(ITestContext context, Method method) {
    StreamEx.of(method.getDeclaredAnnotations()).forEach(System.out::println);
    System.out.println(context.getCurrentXmlTest().getParameter("browser"));
  }

  @DataAnnotation(sourceOfData = "data.json")
  @Test(dataProvider = "User provider")
  public void userWithValidCredentials(final User user) {

    CustomAssertions.assertThat(user).hasUsername("bashlak");
  }

  @DataAnnotation(sourceOfData = "data.json")
  @Test
  public void classLoaderTest() {

    System.out.println(
      StreamEx.of(load(DataReader.class, ClassLoader.getSystemClassLoader()))
          .findFirst(dataReader -> dataReader.getDataType("data.json").equals("json"))
          .map(dataReader -> dataReader.readDataFrom("data.json", User.class))
          .orElseThrow(() -> new IllegalArgumentException("Unable to find required implementation.")));git
  }
}

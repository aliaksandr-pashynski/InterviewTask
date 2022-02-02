import com.example.common.BaseTest;
import com.example.utils.ResponseDetails;
import com.example.entities.Person;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonTest extends BaseTest {

    @DataProvider(name = "names")
    public Object[][] provider(){
        return new Object[][] {{Arrays.asList("michael", "matthew", "jane")}};
    }

    @Test(dataProvider = "names")
    public void test(List<String> names) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        ResponseDetails<List<Person>> details = personService.getAll(names);

        //Status code 200 check
        softAssert.assertEquals(details.getStatusCode(), 200);

        //Result number matches number of query params
        List<Person> result = details.getData();
        softAssert.assertEquals(result.size(), names.size());

        //Each query param has single match in json
        names.forEach(n -> softAssert.assertEquals(result.stream().filter(p -> n.equals(p.getName())).count(), 1,
                String.format("Person %s, has more than 1 match in json", n)));

        //Person age > 0
        result.forEach(p -> softAssert.assertTrue(p.getAge() > 0,
                String.format("Person: %s should have age greater than 0, actual value: %s", p.getName(), p.getAge())));

        //Json schema validation
        assertThat(new String(details.getRawData()), matchesJsonSchema(new FileInputStream("src/main/resources/json_scheme_aqa_task.json")));
        softAssert.assertAll();
    }
}

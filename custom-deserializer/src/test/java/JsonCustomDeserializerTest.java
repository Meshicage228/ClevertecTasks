import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.domain.District;
import ru.clevertec.domain.Hospital;
import ru.clevertec.domain.Street;
import ru.clevertec.exception.InvalidJsonException;
import ru.clevertec.exception.KeyMismatchException;
import ru.clevertec.service.JsonCustomDeserializer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings(value = "deprecation")
@DisplayName("Json Custom deserializer tests")
public class JsonCustomDeserializerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Using JsonField annotation correct deserialization")
    public void correctDeserialization() throws Exception {
        Street expected = Street.builder()
                .name("Test")
                .sleepingStreet(true)
                .build();

        String inputJson = mapper.writeValueAsString(expected);

        Street street = deserializeJson(inputJson, Street.class);

        assertThat(street).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    @DisplayName("Invalid key introduced by json")
    public void correctUsingAnnotation() {
        String inputJson = """
                {
                    "streetName" : "Test",
                    "sleepingStreetYuo" : true
                }
                """;

        assertThrows(KeyMismatchException.class, () -> deserializeJson(inputJson, Street.class));
    }

    @Test
    @DisplayName("Invalid json was introduced")
    public void invalidJson() {
        String[] invalidJsons = new String[] {
                """
                {
                   "smth": value
                """,
                """
                {
                   "smth": value
                ]
                """,
                """
                {{
                   "smth": value
                }
                """
        };

        Arrays.stream(invalidJsons)
                .forEach(json ->  assertThrows(InvalidJsonException.class, () -> deserializeJson(json, Street.class)));
    }

    @Test
    @DisplayName("success deserialized with inner objects")
    public void deserializeInnerObjects() throws Exception {
        District expected = District.builder()
                .schoolCount(10)
                .districtName("Urban District")
                .hospital(new Hospital("Hospital", 1984))
                .mainStreet(new Street("Big street", true))
                .build();

        String json = mapper.writeValueAsString(expected);

        Object deserialized = deserializeJson(json, District.class);

        assertThat(deserialized).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    @DisplayName("success deserialized with inner array")
    public void deserializeInnerArrayWithObject() throws Exception {
        Hospital hospital = new Hospital("Hospital1", 1984);
        Hospital hospital1 = new Hospital("Hospital2", 1985);
        Street street = new Street("Big street", true);

        ArrayList<Hospital> hospitals = new ArrayList<>(){{
            add(hospital);
            add(hospital1);
        }};

        ArrayList<Street> streets = new ArrayList<>(){{
            add(street);
        }};

        District expected = District.builder()
                .schoolCount(10)
                .districtName("Urban District")
                .hospitals(hospitals)
                .streets(streets)
                .build();

        String json = mapper.writeValueAsString(expected);

        District deserialized = deserializeJson(json, District.class);

        assertThat(deserialized).isEqualToComparingFieldByFieldRecursively(expected);
    }

    private <T> T deserializeJson(String json, Class<T> clazz) throws Exception {
        JsonCustomDeserializer deserializer = new JsonCustomDeserializer(json, clazz);
        return deserializer.deserialize();
    }

}

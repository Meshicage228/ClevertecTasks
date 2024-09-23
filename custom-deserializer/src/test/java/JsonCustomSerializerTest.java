import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.domain.District;
import ru.clevertec.domain.Hospital;
import ru.clevertec.domain.Street;
import ru.clevertec.service.JsonCustomSerializer;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Json Custom serializer tests")
public class JsonCustomSerializerTest {

    @Test
    @DisplayName("custom json is valid to create instance")
    public void validJson() throws Exception {
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

        String s = serializeObject(expected);

        District district = new ObjectMapper().readValue(s, District.class);

        assertThat(district).isEqualToComparingFieldByFieldRecursively(expected);
    }

    private String serializeObject(Object object) throws Exception {
        JsonCustomSerializer deserializer = new JsonCustomSerializer();
        return deserializer.serialize(object);
    }
}

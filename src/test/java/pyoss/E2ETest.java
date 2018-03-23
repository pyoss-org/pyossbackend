package pyoss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class E2ETest extends MongoTest {

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    public static <T> T asObject(final String str, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(str, clazz);
    }

}

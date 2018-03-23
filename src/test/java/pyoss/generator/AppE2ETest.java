package pyoss.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pyoss.E2ETest;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class AppE2ETest extends E2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppRepository repository;

    @Test
    public void createApp_shouldCreateAppInDB() throws Exception {
        String ownerName = "Reinout";
        String baseUrl = "/apps";

       mockMvc.perform(createAppCreateRequestFor(baseUrl, ownerName))
                .andExpect(status().isOk());


        App app = repository.findByOwner(ownerName).get();
        assertApp(ownerName, app);
    }

    private void assertApp(String expectedName, App app) {
        assertEquals(expectedName, app.getOwnerName());
        assertEquals(36, app.getIdentifier().length());
    }

    private RequestBuilder createAppCreateRequestFor(String baseUrl, String ownerName) throws JsonProcessingException {
        return MockMvcRequestBuilders.post(baseUrl)
                .content("{\"ownerName\": \"" + ownerName + "\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    private RequestBuilder findByOwnerRequestFor(String baseUrl, String ownerName) {
        return MockMvcRequestBuilders.get(baseUrl + "/" + ownerName)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}

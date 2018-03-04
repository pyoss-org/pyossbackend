package pyoss.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pyoss.MongoTest;
import pyoss.agenda.Agenda;
import pyoss.agenda.AgendaRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
public class ConfigE2ETest extends MongoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void getConfig() throws Exception {
        setupOwnerWithDefaultConfig();

        SlotConfiguration initialConfig = doGetSlotConfiguration();
        assertDefaultValues(initialConfig);
    }

    @Test
    public void updateConfig() throws Exception {
        setupOwnerWithDefaultConfig();

        callUpdateConfig(12, 13, 15);
        SlotConfiguration updated = doGetSlotConfiguration();
        assertConfigValuesAreChanged(updated);
    }

    private SlotConfiguration doGetSlotConfiguration() throws Exception {
        MvcResult mvcResult = callGetConfig();
        return marshalResponse(mvcResult);
    }


    private void assertConfigValuesAreChanged(SlotConfiguration updated) {
        Assert.assertEquals(12, updated.getOpeningTime());
        Assert.assertEquals(13, updated.getClosingTime());
        Assert.assertEquals(15, updated.getMinutesPerSlot());
    }

    private void callUpdateConfig(int openingTime, int closingTime, int minutesPerSlot) throws Exception {
        String json = mapper.writeValueAsString(new ChangeConfigRequest(closingTime, openingTime, minutesPerSlot));
        mockMvc.perform(
                post("/config")
                        .contentType(APPLICATION_JSON_UTF8_VALUE)
                        .content(json)
        ).andReturn();
    }

    private MvcResult callGetConfig() throws Exception {
        return mockMvc.perform(get("/config"))
                .andReturn();
    }


    private void assertDefaultValues(SlotConfiguration config) {
        Assert.assertEquals(SlotConfiguration.createDefault(), config);
    }

    private void setupOwnerWithDefaultConfig() {
        mongoTemplate.dropCollection("agendas");

        Agenda agenda = Agenda.createForOwner("kapperX");
        agendaRepository.update(agenda);
    }

    private SlotConfiguration marshalResponse(MvcResult mvcResult) throws java.io.IOException {
        return mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), SlotConfiguration.class);
    }

}

package pyoss.admin.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pyoss.ContextProvider;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConfigApplicationServiceTest {

    @Mock
    private ContextProvider contextProviderMock;

    @Mock
    private ConfigRepository configRepositoryMock;

    @Test
    public void updateConfig_asksRepositoryToUpdate() {
        String owner = "MyOwner";
        ChangeConfigCommand command = createFakeCommand();
        when(contextProviderMock.getOwnerName()).thenReturn(owner);

        ConfigApplicationService service = new ConfigApplicationService(configRepositoryMock, contextProviderMock);

        service.updateConfig(command);

        verify(configRepositoryMock).update(eq(SlotConfiguration.createFor(2, 1, 3)), eq(owner));
    }

    private ChangeConfigCommand createFakeCommand() {
        return ChangeConfigCommand.from(new ChangeConfigRequest(1, 2, 3));
    }
}
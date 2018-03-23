package pyoss.generator;

import org.junit.Test;
import pyoss.exception.NotFoundException;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class AppControllerTest {

    @Test
    public void createApp_usesRepositoryInsert() {
        App app = mock(App.class);
        AppRepository repositoryMock = mock(AppRepository.class);

        AppController controller = new AppController(repositoryMock);
        controller.createApp(app);

        verify(repositoryMock, times(1)).insert(any(App.class));
    }

    @Test
    public void getApp_usesRepositoryFindByOwner() {
        String expectedOwner = "Reinout";

        AppRepository repositoryMock = mock(AppRepository.class);
        App app = mock(App.class);

        when(repositoryMock.findByOwner(expectedOwner)).thenReturn(Optional.of(app));

        AppController controller = new AppController(repositoryMock);
        App actual = controller.getApp(expectedOwner);

        assertEquals(app, actual);
        verify(repositoryMock, times(1)).findByOwner(expectedOwner);
    }

    @Test(expected = NotFoundException.class)
    public void getApp_throwOnEmpty() {
        String expectedOwner = "Reinout";

        AppRepository repositoryMock = mock(AppRepository.class);

        when(repositoryMock.findByOwner(expectedOwner)).thenReturn(Optional.empty());

        AppController controller = new AppController(repositoryMock);
        controller.getApp(expectedOwner);
    }
}
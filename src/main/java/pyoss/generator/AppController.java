package pyoss.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pyoss.exception.NotFoundException;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(AppController.PATH)
public class AppController {
    static final String PATH = "apps";

    private final AppRepository repository;

    @Autowired
    public AppController(AppRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void createApp(@RequestBody App app) {
        repository.insert(App.create(app.getOwnerName()));
    }

    @GetMapping("{ownerName}")
    public App getApp(@PathParam("ownerName") String ownerName) {
        return repository.findByOwner(ownerName)
                .orElseThrow(NotFoundException::new);
    }
}

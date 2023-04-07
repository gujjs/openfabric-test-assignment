package ai.openfabric.api.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${node.api.path}/worker")
public class WorkerController {
    private final DockerClient dockerClient;
    private final Gson gson = new Gson();
    @Autowired
    public WorkerController(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @GetMapping(path = "/list")
    public @ResponseBody String listContainers(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        return gson.toJson(dockerClient.listContainersCmd()
                .withShowAll(true)
                .exec()
                .stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList()));
    }

    @PostMapping(path = "/start")
    public @ResponseBody String startContainer(@RequestParam String containerId) {
        return gson.toJson(dockerClient.startContainerCmd(containerId).exec());
    }

    @PostMapping(path = "/stop")
    public @ResponseBody String stopContainer(@RequestParam String containerId) {
        return gson.toJson(dockerClient.stopContainerCmd(containerId).exec());
    }

    @GetMapping(path = "/info")
    public @ResponseBody String containerInfo(@RequestParam String containerId) {
        return gson.toJson(dockerClient.inspectContainerCmd(containerId).exec());
    }

    @GetMapping(path = "/stats")
    public @ResponseBody String containerStats(@RequestParam String containerId) {
        InvocationBuilder.AsyncResultCallback<Statistics> callback = new InvocationBuilder.AsyncResultCallback<>();
        dockerClient.statsCmd(containerId).exec(callback);
        Statistics stats = new Statistics();

        try {
            stats = callback.awaitResult();
            callback.close();
        } catch (RuntimeException | IOException e) {
            //
        }
        return gson.toJson(stats); // null or invalid if the container has terminated

    }

}

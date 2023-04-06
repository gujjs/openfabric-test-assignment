package ai.openfabric.api.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("${node.api.path}/worker")
public class WorkerController {
    private final DockerClient dockerClient;
    @Autowired
    public WorkerController(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @GetMapping(path = "/list")
    public @ResponseBody String listContainers() {
        return dockerClient.listContainersCmd().exec().toString();
    }

    @GetMapping(path = "/start")
    public @ResponseBody String startContainer(@RequestParam String containerId) {
        return dockerClient.startContainerCmd(containerId).exec().toString();
    }

    @GetMapping(path = "/stop")
    public @ResponseBody String stopContainer(@RequestParam String containerId) {
        return dockerClient.stopContainerCmd(containerId).exec().toString();
    }

    @GetMapping(path = "/info")
    public @ResponseBody String containerInfo(@RequestParam String containerId) {
        return dockerClient.inspectContainerCmd(containerId).exec().toString();
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
            // you may want to throw an exception here
        }
        return stats.toString(); // this may be null or invalid if the container has terminated

    }

}

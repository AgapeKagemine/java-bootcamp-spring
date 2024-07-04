package self.streaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import self.streaming.service.StreamingService;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Slf4j
@RequestMapping("video")
public class StreamingController {

    @Autowired
    private StreamingService service;

    @GetMapping(value = "/{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title,
            @RequestHeader(value = "Range", required = false) String range) {
        log.info("Range in StreamingController: " + range);
        return service.getVideo(title);
    }

}

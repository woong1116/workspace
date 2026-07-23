package org.example.springapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;

@RestController
public class InfoController {

    @GetMapping("/info")
    public String getServerInfo() throws Exception {
        String hostname = InetAddress.getLocalHost().getHostName();
        return "Server: " + hostname + " | Time: " + System.currentTimeMillis();
    }
}
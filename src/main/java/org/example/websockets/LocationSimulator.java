package org.example.websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LocationSimulator {

    private static final Logger logger = LoggerFactory.getLogger(LocationSimulator.class);
    private final SimpMessagingTemplate messagingTemplate;
    private double currentLat = 40.416775;
    private double currentLon = -3.703790;

    public LocationSimulator(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        logger.info("LocationSimulator initialized");
    }

    @Scheduled(fixedRate = 2000)  // Cada 2 segundos
    public void simulateLocation() {
        try {
            LocationData location = new LocationData();

            // Movimiento más notable
            currentLat += (Math.random() - 0.5) * 0.01;  // Incrementado el factor de cambio
            currentLon += (Math.random() - 0.5) * 0.01;

            location.setLat(currentLat);
            location.setLon(currentLon);
            location.setSpeed(Math.random() * 50);
            location.setHeading(Math.random() * 360);

            logger.info("Sending location: lat={}, lon={}", currentLat, currentLon);
            messagingTemplate.convertAndSend("/topic/location", location);
        } catch (Exception e) {
            logger.error("Error sending location", e);
        }
    }
}

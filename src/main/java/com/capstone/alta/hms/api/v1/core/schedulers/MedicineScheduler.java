package com.capstone.alta.hms.api.v1.core.schedulers;

import com.capstone.alta.hms.api.v1.medicines.entities.Medicine;
import com.capstone.alta.hms.api.v1.medicines.repositories.MedicineRepository;
import com.capstone.alta.hms.api.v1.websocket.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicineScheduler {

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Scheduled(fixedDelay = 60000) //1 minute
    public void getMedicineStock() {
        List<Medicine> medicines = medicineRepository.findByStockLessThan((short) 10);

        MessageDTO message = new MessageDTO();

        medicines.forEach(medicine -> {
            message.setType("message");
            message.setTitle("MEDICINE STOCK: " + medicine.getName());
            message.setDescription("Remaining stock is: " + medicine.getStock() + ". Please update stock");
            messagingTemplate.convertAndSend("/topic/messages", message);
        });

    }
}

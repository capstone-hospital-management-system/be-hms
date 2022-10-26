package com.capstone.alta.hms.api.v1.bills.services;

import com.capstone.alta.hms.api.v1.bills.dtos.BillRequestDTO;
import com.capstone.alta.hms.api.v1.bills.dtos.BillResponseDTO;
import com.capstone.alta.hms.api.v1.bills.entities.Bill;
import com.capstone.alta.hms.api.v1.bills.repositories.BillRepository;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.entities.Prescription;
import com.capstone.alta.hms.api.v1.prescriptions.repositories.PrescriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService implements IBillService {
  @Autowired
  BillRepository billRepository;

  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public BaseResponseDTO<BillResponseDTO> createNewBill(BillRequestDTO billRequestDTO) {
    Prescription prescription = prescriptionRepository.findById(billRequestDTO.getPrescriptionId()).get();

    Bill newBill = new Bill();
    newBill.setTotalPrice(billRequestDTO.getTotalPrice());
    newBill.setPrescription(prescription);

    Bill bill = billRepository.save(newBill);

    BillResponseDTO billResponseDTO = new BillResponseDTO();
    billResponseDTO.setId(bill.getId());
    billResponseDTO.setTotalPrice(bill.getTotalPrice());
    billResponseDTO.setPrescription(modelMapper.map(prescription, PrescriptionResponseDTO.class));

    return new BaseResponseDTO<BillResponseDTO>(
            "201",
            HttpStatus.CREATED,
            "successfully creating data",
            billResponseDTO
    );
  }

  @Override
  public PageBaseResponseDTO<List<BillResponseDTO>> getAllBills(Pageable pageable) {
    Page<Bill> bills = billRepository.findAll(pageable);

    String message = "";
    List<BillResponseDTO> billResponseDTOS;

    if (bills.isEmpty()) {
      message = "data is empty";
      billResponseDTOS = Collections.emptyList();
    } else {
      message = "successfully retrieving data";
      billResponseDTOS = bills.stream()
              .map(bill -> modelMapper.map(bill, BillResponseDTO.class))
              .collect(Collectors.toList());
    }

    return new PageBaseResponseDTO<List<BillResponseDTO>>(
            "200",
            HttpStatus.OK,
            message,
            billResponseDTOS,
            new MetaResponseDTO(
                    bills.getNumber() + 1,
                    bills.getSize(),
                    bills.getTotalPages(),
                    bills.getTotalElements()
            )
    );
  }

  @Override
  public BaseResponseDTO<BillResponseDTO> getBillDetails(Integer id) {
    Bill bill = billRepository.findById(id).get();
    return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "success",
            modelMapper.map(bill, BillResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<BillResponseDTO> updateBill(Integer id, BillRequestDTO billRequestDTO) {
    Prescription prescription = prescriptionRepository.findById(billRequestDTO.getPrescriptionId()).get();

    Bill newBill = new Bill();
    newBill.setId(id);
    newBill.setTotalPrice(billRequestDTO.getTotalPrice());
    newBill.setPrescription(prescription);

    Bill bill = billRepository.save(newBill);

    BillResponseDTO billResponseDTO = new BillResponseDTO();
    billResponseDTO.setId(bill.getId());
    billResponseDTO.setTotalPrice(bill.getTotalPrice());
    billResponseDTO.setPrescription(modelMapper.map(prescription, PrescriptionResponseDTO.class));

    return new BaseResponseDTO<BillResponseDTO>(
            "200",
            HttpStatus.OK,
            "successfully updating data",
            billResponseDTO
    );
  }

  @Override
  public BaseResponseDTO<BillResponseDTO> deleteBill(Integer id) {
    billRepository.deleteById(id);

    return new BaseResponseDTO<>(
            "200",
            HttpStatus.OK,
            "successfully deleting data",
            null
    );
  }
}

package com.capstone.alta.hms.api.v1.bills.controllers;

import com.capstone.alta.hms.api.v1.bills.dtos.BillRequestDTO;
import com.capstone.alta.hms.api.v1.bills.dtos.BillResponseDTO;
import com.capstone.alta.hms.api.v1.bills.services.IBillService;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BillController {
  @Autowired
  IBillService billService;

  @PostMapping("/bills")
  public ResponseEntity<BaseResponseDTO<BillResponseDTO>> createNewBill (
          @RequestBody BillRequestDTO billRequestDTO) {
    BaseResponseDTO<BillResponseDTO> response =
            billService.createNewBill(billRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/bills")
  public ResponseEntity<PageBaseResponseDTO<List<BillResponseDTO>>> getAllBills(
          Pageable pageable) {
    PageBaseResponseDTO<List<BillResponseDTO>> response =
            billService.getAllBills(pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/bills/{id}")
  public ResponseEntity<BaseResponseDTO<BillResponseDTO>> getBillDetails(
          @PathVariable Integer id) {
    BaseResponseDTO<BillResponseDTO> response =
            billService.getBillDetails(id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/bills/{id}")
  public ResponseEntity<BaseResponseDTO<BillResponseDTO>> updateBill(
          @PathVariable Integer id,
          @RequestBody BillRequestDTO billRequestDTO) {
    BaseResponseDTO<BillResponseDTO> response =
            billService.updateBill(id, billRequestDTO);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/bills/{id}")
  public ResponseEntity<BaseResponseDTO<BillResponseDTO>> deleteBill(@PathVariable Integer id) {
    BaseResponseDTO<BillResponseDTO> response = billService.deleteBill(id);
    return new ResponseEntity<BaseResponseDTO<BillResponseDTO>>(response, HttpStatus.OK);
  }
}

package com.capstone.alta.hms.api.v1.bills.services;

import com.capstone.alta.hms.api.v1.bills.dtos.BillRequestDTO;
import com.capstone.alta.hms.api.v1.bills.dtos.BillResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillService {
  BaseResponseDTO<BillResponseDTO> createNewBill(BillRequestDTO billRequestDTO);
  PageBaseResponseDTO<List<BillResponseDTO>> getAllBills(Pageable pageable);
  BaseResponseDTO<BillResponseDTO> getBillDetails(Integer id);
  BaseResponseDTO<BillResponseDTO> updateBill(Integer id, BillRequestDTO billRequestDTO);
  BaseResponseDTO<BillResponseDTO> deleteBill(Integer id);
}

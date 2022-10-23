package com.capstone.alta.hms.api.v1.medicines.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineRequestDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMedicineService {
  BaseResponseDTO<MedicineResponseDTO> createNewMedicine(MedicineRequestDTO medicineRequestDTO);
  PageBaseResponseDTO<List<MedicineResponseDTO>> getAllMedicines(Pageable pageable);
  BaseResponseDTO<MedicineResponseDTO> getMedicineDetails(Integer id);
  BaseResponseDTO<MedicineResponseDTO> updateMedicine(Integer id, MedicineRequestDTO medicineRequestDTO);
  BaseResponseDTO<MedicineResponseDTO> deleteMedicine(Integer id);
}

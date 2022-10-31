package com.capstone.alta.hms.api.v1.medicines.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineRequestDTO;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.entities.Medicine;
import com.capstone.alta.hms.api.v1.medicines.repositories.MedicineRepository;
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
public class MedicineService implements IMedicineService {
  @Autowired
  MedicineRepository medicineRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public BaseResponseDTO<MedicineResponseDTO> createNewMedicine(MedicineRequestDTO medicineRequestDTO) {
    Medicine medicine = medicineRepository.save(modelMapper.map(medicineRequestDTO, Medicine.class));
    MedicineResponseDTO medicineResponseDTO = modelMapper.map(medicine, MedicineResponseDTO.class);
    return new BaseResponseDTO<MedicineResponseDTO>(
            "successfully creating data",
            medicineResponseDTO
    );
  }

  @Override
  public PageBaseResponseDTO<List<MedicineResponseDTO>> getAllMedicines(Pageable pageable) {
    Page<Medicine> medicines = medicineRepository.findAll(pageable);

    String message = "";
    List<MedicineResponseDTO> medicineResponseDTOS;

    if (medicines.isEmpty()) {
      message = "data is empty";
      medicineResponseDTOS = Collections.emptyList();
    } else {
      message = "successfully retrieving data";
      medicineResponseDTOS = medicines.stream()
              .map(medicine -> modelMapper.map(medicine, MedicineResponseDTO.class))
              .collect(Collectors.toList());
    }

    return new PageBaseResponseDTO<List<MedicineResponseDTO>>(
            message,
            new MetaResponseDTO(
                    medicines.getNumber() + 1,
                    medicines.getSize(),
                    medicines.getTotalPages(),
                    medicines.getTotalElements()
            ),
            medicineResponseDTOS
    );
  }

  @Override
  public BaseResponseDTO<MedicineResponseDTO> getMedicineDetails(Integer id) {
    Medicine medicine = medicineRepository.findById(id).get();
    return new BaseResponseDTO<>(
            "success",
            modelMapper.map(medicine, MedicineResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<MedicineResponseDTO> updateMedicine(Integer id, MedicineRequestDTO medicineRequestDTO) {
    Medicine medicineUpdate = modelMapper.map(medicineRequestDTO, Medicine.class);
    medicineUpdate.setId(id);

    Medicine medicine = medicineRepository.save(medicineUpdate);

    return new BaseResponseDTO<MedicineResponseDTO>(
            "successfully updating data",
            modelMapper.map(medicine, MedicineResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<MedicineResponseDTO> deleteMedicine(Integer id) {
    medicineRepository.deleteById(id);

    return new BaseResponseDTO<>(
            "successfully deleting data",
            null
    );
  }
}

package com.capstone.alta.hms.api.v1.prescriptions.services;

import com.capstone.alta.hms.api.v1.core.dtos.BaseResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.MetaResponseDTO;
import com.capstone.alta.hms.api.v1.core.dtos.PageBaseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.dtos.DiagnoseResponseDTO;
import com.capstone.alta.hms.api.v1.diagnoses.entities.Diagnose;
import com.capstone.alta.hms.api.v1.diagnoses.repositories.DiagnoseRepository;
import com.capstone.alta.hms.api.v1.medicines.dtos.MedicineResponseDTO;
import com.capstone.alta.hms.api.v1.medicines.entities.Medicine;
import com.capstone.alta.hms.api.v1.medicines.repositories.MedicineRepository;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionRequestDTO;
import com.capstone.alta.hms.api.v1.prescriptions.dtos.PrescriptionResponseDTO;
import com.capstone.alta.hms.api.v1.prescriptions.entities.Prescription;
import com.capstone.alta.hms.api.v1.prescriptions.repositories.PrescriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService implements IPrescriptionService {
  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Autowired
  DiagnoseRepository diagnoseRepository;

  @Autowired
  MedicineRepository medicineRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> createNewPrescription(PrescriptionRequestDTO prescriptionRequestDTO) {
    Diagnose diagnose = diagnoseRepository.findById(prescriptionRequestDTO.getDiagnoseId()).get();
    List<Medicine> medicines = medicineRepository.findByIdIn(prescriptionRequestDTO.getMedicineIds());

    Prescription newPrescription = new Prescription();
    newPrescription.setDiagnose(diagnose);
    newPrescription.setDescription(prescriptionRequestDTO.getDescription());
    newPrescription.setStatus(prescriptionRequestDTO.getStatus());
    newPrescription.setOthers(prescriptionRequestDTO.getOthers());
    newPrescription.setMedicines(medicines);

    Prescription prescription = prescriptionRepository.save(newPrescription);
    PrescriptionResponseDTO response = new PrescriptionResponseDTO();
    response.setId(prescription.getId());
    response.setDiagnose(modelMapper.map(prescription.getDiagnose(), DiagnoseResponseDTO.class));
    response.setDescription(prescription.getDescription());
    response.setStatus(prescription.getStatus());
    response.setOthers(prescription.getOthers());

    List<MedicineResponseDTO> medicineResponseDTOS = newPrescription.getMedicines()
            .stream()
            .map(medicine -> modelMapper.map(medicine, MedicineResponseDTO.class)).toList();

    response.setMedicines(medicineResponseDTOS);

    return new BaseResponseDTO<PrescriptionResponseDTO>(
            "successfully creating data",
            response
    );
  }

  @Override
  public PageBaseResponseDTO<List<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable) {
    Page<Prescription> prescriptions = prescriptionRepository.findAll(pageable);

    String message = "";
    List<PrescriptionResponseDTO> prescriptionResponseDTOS;

    if (prescriptions.isEmpty()) {
      message = "data is empty";
      prescriptionResponseDTOS = Collections.emptyList();
    } else {
      message = "successfully retrieving data";
      prescriptionResponseDTOS = prescriptions.stream()
              .map(prescription -> modelMapper.map(prescription, PrescriptionResponseDTO.class))
              .collect(Collectors.toList());
    }

    return new PageBaseResponseDTO<List<PrescriptionResponseDTO>>(
            message,
            new MetaResponseDTO(
                    prescriptions.getNumber() + 1,
                    prescriptions.getSize(),
                    prescriptions.getTotalPages(),
                    prescriptions.getTotalElements()
            ),
            prescriptionResponseDTOS
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> getPrescriptionDetails(Integer id) {
    Prescription prescription = prescriptionRepository.findById(id).get();
    return new BaseResponseDTO<>(
            "success",
            modelMapper.map(prescription, PrescriptionResponseDTO.class)
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> updatePrescription(Integer id, PrescriptionRequestDTO prescriptionRequestDTO) {
    Diagnose diagnose = diagnoseRepository.findById(prescriptionRequestDTO.getDiagnoseId()).get();
    List<Medicine> medicines = medicineRepository.findByIdIn(prescriptionRequestDTO.getMedicineIds());

    Prescription newPrescription = new Prescription();
    newPrescription.setId(id);
    newPrescription.setDiagnose(diagnose);
    newPrescription.setDescription(prescriptionRequestDTO.getDescription());
    newPrescription.setStatus(prescriptionRequestDTO.getStatus());
    newPrescription.setOthers(prescriptionRequestDTO.getOthers());
    newPrescription.setMedicines(medicines);

    Prescription prescription = prescriptionRepository.save(newPrescription);
    PrescriptionResponseDTO response = new PrescriptionResponseDTO();
    response.setId(prescription.getId());
    response.setDiagnose(modelMapper.map(prescription.getDiagnose(), DiagnoseResponseDTO.class));
    response.setDescription(prescription.getDescription());
    response.setStatus(prescription.getStatus());
    response.setOthers(prescription.getOthers());

    List<MedicineResponseDTO> medicineResponseDTOS = newPrescription.getMedicines()
            .stream()
            .map(medicine -> modelMapper.map(medicine, MedicineResponseDTO.class)).toList();

    response.setMedicines(medicineResponseDTOS);

    return new BaseResponseDTO<PrescriptionResponseDTO>(
            "successfully updating data",
            response
    );
  }

  @Override
  public BaseResponseDTO<PrescriptionResponseDTO> deletePrescription(Integer id) {
    prescriptionRepository.deleteById(id);
    return new BaseResponseDTO<>(
            "successfully deleting data",
            null
    );
  }
}

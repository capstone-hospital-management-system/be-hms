package com.capstone.alta.hms.api.v1.bills.services;

import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentRequestDTO;
import com.capstone.alta.hms.api.v1.appointments.dtos.AppointmentResponseDTO;
import com.capstone.alta.hms.api.v1.appointments.entities.Appointment;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    @Mock
    BillRepository billRepository;

    @Mock
    PrescriptionRepository prescriptionRepository;

    ModelMapper modelMapper = spy(new ModelMapper());

    @InjectMocks
    BillService billService = spy(new BillService());

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(prescriptionRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(prescription()));
    }

    @Test
    public void createNewBill_shouldReturnNewCreatedBill() {
        Bill bill = billEntity();

        when(billRepository.save(any(Bill.class))).thenReturn(bill);
        BillResponseDTO billResponseDTO = modelMapper.map(billEntity(), BillResponseDTO.class);
        billResponseDTO.setPrescription(modelMapper.map(prescription(), PrescriptionResponseDTO.class));

        BaseResponseDTO<BillResponseDTO> expected = new BaseResponseDTO("successfully creating data",
                billResponseDTO);

        BaseResponseDTO<BillResponseDTO> actual = billService.createNewBill(billRequestDTO());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getAllBills_shouldReturnListOfBillsWithPagination() {
        int page = 1;
        int per_page = 1;

        Pageable pageable = PageRequest.of(page, per_page);

        Bill bill = billEntity();

        Page<Bill> billsPage = new PageImpl<>(Collections.singletonList(bill));

        when(billRepository.findAll(pageable)).thenReturn(billsPage);

        List<BillResponseDTO> billResponseDTOS = billsPage.stream().map(billPage -> modelMapper.map(
                billPage, BillResponseDTO.class)).collect(Collectors.toList());

        PageBaseResponseDTO<List<BillResponseDTO>> expected = new PageBaseResponseDTO<>(
                "successfully retrieving data",
                new MetaResponseDTO(1, 1, 1, 1),
                billResponseDTOS);

        PageBaseResponseDTO<List<BillResponseDTO>> actual = billService.getAllBills(pageable);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void getBillDetails_whenCurrentIdOfBillExist_shouldReturnBill() {
        Bill bill = billEntity();

        when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));

        BillResponseDTO billResponseDTO = modelMapper.map(bill, BillResponseDTO.class);

        BaseResponseDTO<BillResponseDTO> expected = new BaseResponseDTO<>("success", billResponseDTO);

        BaseResponseDTO<BillResponseDTO> actual = billService.getBillDetails(bill.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void updateBill_whenCurrentIdOfBillExist_shouldReturnUpdatedBill() {
        Bill billUpdate = billEntity();
        billUpdate.setPrescription(prescription());
        billUpdate.setTotalPrice(BigDecimal.valueOf(100000));

        when(billRepository.save(any(Bill.class))).thenReturn(billUpdate);

        BillRequestDTO updatedBillRequestDTO = modelMapper.map(billUpdate, BillRequestDTO.class);
        BillResponseDTO updatedBillResponseDTO = modelMapper.map(billUpdate, BillResponseDTO.class);

        BaseResponseDTO<BillResponseDTO> expected = new BaseResponseDTO<>("successfully updating data",
                updatedBillResponseDTO);


        BaseResponseDTO<BillResponseDTO> actual = billService.updateBill(billUpdate.getId(),
                updatedBillRequestDTO);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void deleteBill_whenCurrentIdOfBillExist_shouldReturnMessagesSuccessWithNullData() {
        Bill bill = billEntity();

        BaseResponseDTO<BillResponseDTO> expected = new BaseResponseDTO<>("successfully deleting data",
                null);

        BaseResponseDTO<BillResponseDTO> actual = billService.deleteBill(bill.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public BillRequestDTO billRequestDTO() {
        BillRequestDTO billRequestDTO = new BillRequestDTO();
        billRequestDTO.setPrescriptionId(1);
        billRequestDTO.setTotalPrice(BigDecimal.valueOf(5000));
        return billRequestDTO;
    }

    public Bill billEntity() {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setPrescription(prescription());
        bill.setTotalPrice(billRequestDTO().getTotalPrice());
        return bill;
    }

    public Prescription prescription() {
        Prescription prescription = new Prescription();
        prescription.setId(1);
        return prescription;
    }
}

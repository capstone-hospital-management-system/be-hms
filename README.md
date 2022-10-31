
## Apa Itu HMS ?

Hospital Management System pada project ini adalah suatu sistem aplikasi yang digunakan untuk melakukan manajemen proses bisnis pada pusat layanan kesehatan, yaitu manajemen data staff, patient, data kunjungan, diagnosa, treatment, resep, obat, dan tagihan.

## Cara Menjalankan Project

Clone the project BE

```bash
  git clone https://github.com/capstone-hospital-management-system/be-hms.git
```

then you start the project using IntelliJ IDEA / similar IDE


## Feature Project

- [x] Patients Registration
- [x] Appointment / Data kunjungan
- [x] Diagnose
- [x] Treatment
- [x] Prescription
- [x] Bill

## Links

 - [Dokumentasi API](https://documenter.getpostman.com/view/20601351/2s8YRfNbC7)
 - [ERD]( https://drive.google.com/file/d/19nj9QKm_A8KDBHwn-P9xCledWVvTW09M)
 - [FE Vercel ](https://fe-hms.vercel.app/)
 - [BE Heroku](https://hms-capstone-alterra.herokuapp.com)
## API Reference

#### Securities

```http
POST http://localhost:8080/api/v1/auth/sign-in
```

| Parameter | Type     |
| :-------- | :------- |
| `username_or_email` | `string` 
| `password`          | `string` 

#### Account

Create Account
```http
POST http://localhost:8080/api/v1/accounts
```

| Parameter | Type     | 
| :-------- | :------- | 
| `first_name`    | `string` |
| `last_name`     | `string` |
| `username`      | `string` |
| `email`         | `string` |
| `password`      | `string` |
| `role`          | `string` |
| `id_card`       | `string` |
| `phone_number`  | `string` |

Other CRUD Account Works using /api/v1/accounts

#### Medicines

Create Medicines
```http
POST http://localhost:8080/api/v1/medicines
```

| Parameter | Type     | 
| :-------- | :------- | 
| `name`          | `string` |
| `description`   | `string` |
| `price`         | `int` |
| `stock`         | `int` |

Other CRUD Medicines Works using /api/v1/medicines

#### Patient

Create Patient
```http
POST http://localhost:8080/api/v1/patients
```

| Parameter | Type     | 
| :-------- | :------- | 
| `first_name`    | `string` |
| `last_name`     | `string` |
| `id_card`       | `string` |
| `age`           | `int` |
| `gender`        | `string` |
| `address`       | `string` |
| `city`          | `string` |
| `blood_type`    | `string` |
| `phone_number`  | `string` |
| `postal_code`   | `string` |
| `username`      | `string` |
| `bod`           | `string` |
| `register_by`   | `array {"id": [int id] }`|
| `updated_by`    | `array {"id": [int id] }`|

Other CRUD Patient Works using /api/v1/patients

#### Clinics

Create Clinics
```http
POST http://localhost:8080/api/v1/clinics
```

| Parameter | Type     | 
| :-------- | :------- | 
| `name`          | `string` |
| `description`   | `string` |

Other CRUD Clinics Works using /api/v1/clinics

#### Appointment

Create Appointment
```http
POST http://localhost:8080/api/v1/appointments
```

| Parameter | Type     | 
| :-------- | :------- | 
| `appointment_date`  | `string` |
| `patient_id`        | `int` |
| `doctor_id`         | `int` |
| `clinic_id`         | `int` |

Other CRUD Appointment Works using /api/v1/appointments

#### Diagnoses

Create Diagnoses
```http
POST http://localhost:8080/api/v1/diagnoses
```

| Parameter | Type     | 
| :-------- | :------- | 
| `name`            | `string` |
| `description`     | `string` |
| `report`          | `string` |
| `appointment_id`  | `int` |

Other CRUD Diagnoses Works using /api/v1/diagnoses

#### Prescriptions

Create Prescriptions
```http
POST http://localhost:8080/api/v1/prescriptions
```

| Parameter | Type     | 
| :-------- | :------- | 
| `description`  | `string` |
| `status`       | `string` |
| `diagnose_id`  | `int` |
| `medicine_ids` | `[array value of medicine_id]` |

Other CRUD Prescriptions Works using /api/v1/prescriptions

#### Treatment

Create Treatment
```http
POST http://localhost:8080/api/v1/treatments
```

| Parameter | Type     | 
| :-------- | :------- | 
| `status`      | `string` |
| `report`      | `string` |
| `diagnose_id` | `int` |

Other CRUD Treatment Works using /api/v1/treatments

#### Bills

Create Bills
```http
POST http://localhost:8080/api/v1/bills
```

| Parameter | Type     | 
| :-------- | :------- | 
| `prescription_id` | `int` |
| `total_price`     | `int` |

Other CRUD Bills Works using /api/v1/bills

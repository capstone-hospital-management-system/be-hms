
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
| `password` | `string` 

#### Account

Create Account
```http
POST http://localhost:8080/api/v1/accounts
```

| Parameter | Type     | 
| :-------- | :------- | 
| `first_name`      | `string` |
| `last_name`      | `string` |
| `username`      | `string` |
| `email`      | `string` |
| `password`      | `string` |
| `role`      | `string` |
| `id_card`      | `string` |
| `phone_number`      | `string` |

Other CRUD Account Works using /api/v1/accounts

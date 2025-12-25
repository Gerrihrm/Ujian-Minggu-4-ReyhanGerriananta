# API Testing with REST Assured (Product Management)

## Deskripsi Proyek

Proyek ini adalah implementasi pengujian otomatis API menggunakan Java dan library REST Assured. Dibuat sebagai bagian dari tugas pelatihan, proyek ini berfokus pada pengujian fungsionalitas manajemen produk pada API simulasi [FakeStoreAPI](https://fakestoreapi.com/).

Tujuan dari proyek ini adalah untuk memverifikasi bahwa endpoint API bekerja sesuai harapan, baik dari sisi respons status kode maupun struktur data yang dikembalikan.

## Petunjuk Tugas (dari Trainer)

Tugas ini merupakan kelanjutan dari pembelajaran sebelumnya mengenai cara testing API menggunakan REST Assured, khususnya setelah berhasil menguji `create product` (meskipun implementasinya tidak disertakan dalam proyek ini, namun merupakan dasar pemahaman).

Adapun skenario pengujian yang dicakup dalam proyek ini meliputi:

1.  **Test Product Detail** (Get Single Product)
2.  **Test Update Product** (PUT /products/{id})
3.  **Test Delete Product** (DELETE /products/{id})

## Teknologi yang Digunakan

*   **Bahasa Pemrograman**: Java 17
*   **Build Tool**: Apache Maven
*   **Framework Testing**: TestNG
*   **Library API Testing**: REST Assured
*   **JSON Processing**: Jackson Databind

## Struktur Proyek

```
.
├── pom.xml                                 # Konfigurasi Maven dan dependensi proyek
└── src/
    ├── main/
    │   └── java/
    │       └── com/juaracoding/resttest/
    │           └── App.java                # Main class (biasanya kosong atau placeholder)
    └── test/
        ├── java/
        │   └── com/juaracoding/resttest/
        │       ├── api/
        │       │   ├── LoginAPITest.java   # Tes untuk endpoint login
        │       │   └── ProductAPITest.java # Tes untuk endpoint manajemen produk
        │       └── provider/
        │           └── UserProvider.java   # Data provider untuk kredensial user (login)
        └── resources/
            └── testng.xml                  # Konfigurasi TestNG untuk menjalankan suite tes
```

## Skenario Pengujian yang Dicakup

Proyek ini menguji fungsionalitas berikut pada [FakeStoreAPI](https://fakestoreapi.com/):

### 1. Login API (`LoginAPITest.java`)
*   `POST /auth/login`: Menguji autentikasi pengguna dengan berbagai kredensial, memverifikasi respons token.

### 2. Product API (`ProductAPITest.java`)
*   `GET /products`: Menguji pengambilan semua daftar produk, memverifikasi status kode, jumlah item, dan tipe data setiap field.
*   `GET /products/{id}`: Menguji pengambilan detail produk tunggal berdasarkan ID, memverifikasi status kode dan keakuratan data produk yang dikembalikan.
*   `PUT /products/{id}`: Menguji pembaharuan data produk, memverifikasi status kode dan respons yang mengindikasikan suksesnya pembaruan.
*   `DELETE /products/{id}`: Menguji penghapusan produk, memverifikasi status kode dan respons yang mengindikasikan suksesnya penghapusan.

## Cara Menjalankan Tes

1.  **Pastikan Anda memiliki Java Development Kit (JDK) 17** atau yang lebih baru terinstal.
2.  **Pastikan Anda memiliki Apache Maven** terinstal dan terkonfigurasi pada PATH sistem Anda.
3.  Buka terminal atau command prompt di direktori root proyek (`api-test`).
4.  Jalankan perintah berikut:

    ```bash
    mvn test
    ```

    Maven akan mengkompilasi kode, menjalankan semua tes yang terdefinisi dalam `testng.xml`, dan menghasilkan laporan.

## Melihat Laporan Hasil Tes

Setelah tes selesai dijalankan, laporan hasil tes akan dibuat oleh TestNG dan disimpan dalam format HTML.

1.  Arahkan ke direktori: `target/surefire-reports/`.
2.  Buka file `index.html` atau `emailable-report.html` menggunakan browser web favorit Anda untuk melihat rangkuman detail dari semua tes yang telah dijalankan.

## Catatan Penting Mengenai FakeStoreAPI

Perlu diingat bahwa **FakeStoreAPI adalah API simulasi dan tidak memiliki penyimpanan data persisten**. Ini berarti:

*   Operasi `PUT` (update) dan `DELETE` (hapus) akan mengembalikan respons yang menunjukkan keberhasilan, tetapi perubahan tersebut **tidak benar-benar disimpan** di server FakeStoreAPI.
*   Jika Anda mencoba melakukan `GET` setelah `PUT` atau `DELETE`, Anda akan melihat data asli yang belum berubah.
*   Dalam lingkungan pengujian API sungguhan (non-simulasi), verifikasi setelah `PUT` akan melibatkan `GET` untuk memastikan perubahan tersimpan, dan setelah `DELETE` akan melibatkan `GET` yang diharapkan mengembalikan `404 Not Found`.

Meskipun demikian, tes dalam proyek ini tetap valid untuk memverifikasi bahwa aplikasi Anda mampu berinteraksi dengan API sesuai kontrak dan menerima respons yang diharapkan untuk setiap operasi.
"# Ujian-Minggu-4-ReyhanGerriananta" 

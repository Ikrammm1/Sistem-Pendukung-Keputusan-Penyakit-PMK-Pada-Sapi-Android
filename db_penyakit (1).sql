-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Agu 2023 pada 18.02
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penyakit`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_about`
--

CREATE TABLE `tb_about` (
  `id_about` int(11) NOT NULL,
  `about` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_about`
--

INSERT INTO `tb_about` (`id_about`, `about`) VALUES
(1, 'Aplikasi Diagnosa Penyakit adalah sebuah aplikasi yang dirancang untuk membantu pengguna dalam melakukan diagnosa penyakit secara mandiri.Aplikasi ini menyediakan informasi mengenai berbagai gejala penyakit, memberikan saran tentang langkah-langkah pencegahan, dan memberikan rekomendasi tindakan yang tepat. Aplikasi ini tidak menggantikan kunjungan ke dokter atau profesional medis. Tetaplah berkonsultasi dengan tenaga medis yang berkualitas untuk diagnosis yang akurat dan pengobatan yang sesuai. Kami berkomitmen untuk memberikan pengalaman pengguna yang baik dengan aplikasi ini. Jika Anda memiliki pertanyaan, masukan, atau saran, jangan ragu untuk menghubungi kami melalui email atau media sosial yang tersedia. Terima kasih telah menggunakan Aplikasi Diagnosa Penyakit. Semoga aplikasi ini bermanfaat dalam menjaga kesehatan Anda dan keluarga.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_datasapi`
--

CREATE TABLE `tb_datasapi` (
  `id_datasapi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `alternatif` varchar(10) NOT NULL,
  `airliur` enum('Banyak','Normal') NOT NULL,
  `C1` double DEFAULT NULL,
  `kakipincang` enum('Tidak Pincang','Pincang') NOT NULL,
  `C2` double DEFAULT NULL,
  `nafsumakan` enum('Normal','Rendah') NOT NULL,
  `C3` double DEFAULT NULL,
  `lidahbengkak` enum('Tidak Bengkak','Bengkak') NOT NULL,
  `C4` double DEFAULT NULL,
  `postur` enum('Normal','Kurus') NOT NULL,
  `C5` double DEFAULT NULL,
  `status_hitung` enum('Selesai','Belum') NOT NULL DEFAULT 'Belum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_datasapi`
--

INSERT INTO `tb_datasapi` (`id_datasapi`, `id_user`, `alternatif`, `airliur`, `C1`, `kakipincang`, `C2`, `nafsumakan`, `C3`, `lidahbengkak`, `C4`, `postur`, `C5`, `status_hitung`) VALUES
(52, 10, 'C11', 'Banyak', 3, 'Tidak Pincang', 1, 'Normal', 3, 'Tidak Bengkak', 1, 'Normal', 3, 'Selesai'),
(53, 10, 'C12', 'Banyak', 3, 'Tidak Pincang', 1, 'Rendah', 1, 'Tidak Bengkak', 1, 'Kurus', 1, 'Selesai'),
(54, 10, 'C13', 'Normal', 1, 'Tidak Pincang', 1, 'Normal', 3, 'Tidak Bengkak', 1, 'Kurus', 1, 'Selesai'),
(55, 10, 'C15', 'Normal', 1, 'Pincang', 5, 'Normal', 3, 'Tidak Bengkak', 1, 'Normal', 3, 'Selesai'),
(56, 10, 'C14', 'Normal', 1, 'Tidak Pincang', 1, 'Normal', 3, 'Tidak Bengkak', 1, 'Normal', 3, 'Selesai'),
(57, 10, 'C16', 'Banyak', 3, 'Pincang', 5, 'Rendah', 1, 'Bengkak', 5, 'Kurus', 1, 'Selesai'),
(58, 10, 'C12', 'Normal', 1, 'Pincang', 5, 'Rendah', 1, 'Bengkak', 5, 'Kurus', 1, 'Selesai'),
(60, 10, 'C20', 'Normal', 1, 'Pincang', 5, 'Normal', 3, 'Bengkak', 5, 'Normal', 3, 'Selesai'),
(61, 10, 'C21', 'Normal', 1, 'Tidak Pincang', 1, 'Normal', 3, 'Tidak Bengkak', 1, 'Normal', 3, 'Selesai'),
(70, 16, 'U1', 'Normal', 1, 'Pincang', 5, 'Normal', 3, 'Bengkak', 5, 'Normal', 3, 'Selesai'),
(71, 16, 'U2', 'Banyak', 3, 'Tidak Pincang', 1, 'Rendah', 1, 'Tidak Bengkak', 1, 'Kurus', 1, 'Selesai'),
(72, 16, 'U3', 'Banyak', 3, 'Pincang', 5, 'Rendah', 1, 'Bengkak', 5, 'Kurus', 1, 'Selesai'),
(73, 16, 'U4', 'Banyak', 3, 'Pincang', 5, 'Rendah', 1, 'Bengkak', 5, 'Kurus', 1, 'Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_hasil`
--

CREATE TABLE `tb_hasil` (
  `id_hasil` int(11) NOT NULL,
  `id_datasapi` int(11) NOT NULL,
  `nilai` double NOT NULL,
  `kesimpulan` enum('Terjangkit Penyakit','Hampir Terjangkit Penyakit','Tidak Terjangkit Penyakit') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_hasil`
--

INSERT INTO `tb_hasil` (`id_hasil`, `id_datasapi`, `nilai`, `kesimpulan`) VALUES
(135, 52, 0.39, 'Tidak Terjangkit Penyakit'),
(136, 53, 0.52, 'Hampir Terjangkit Penyakit'),
(137, 54, 0.32, 'Tidak Terjangkit Penyakit'),
(138, 55, 0.53, 'Hampir Terjangkit Penyakit'),
(139, 56, 0.25, 'Tidak Terjangkit Penyakit'),
(140, 57, 1, 'Terjangkit Penyakit'),
(141, 58, 0.87, 'Terjangkit Penyakit'),
(142, 60, 0.73, 'Terjangkit Penyakit'),
(143, 61, 0.25, 'Tidak Terjangkit Penyakit'),
(151, 70, 0.73, 'Terjangkit Penyakit'),
(152, 71, 0.52, 'Hampir Terjangkit Penyakit'),
(153, 72, 1, 'Terjangkit Penyakit'),
(154, 73, 1, 'Terjangkit Penyakit');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kriteria`
--

CREATE TABLE `tb_kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `kriteria` varchar(50) NOT NULL,
  `sifat` enum('benefit','cost') NOT NULL,
  `bobot` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_kriteria`
--

INSERT INTO `tb_kriteria` (`id_kriteria`, `kriteria`, `sifat`, `bobot`) VALUES
(6, 'Jumlah Air Liur', 'benefit', 20),
(7, 'Kaki Pincang', 'benefit', 35),
(8, 'Nafsu Makan', 'cost', 10),
(9, 'Mulut dan Lidah Bengkak', 'benefit', 25),
(10, 'Postur Badan', 'cost', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nilaikriteria`
--

CREATE TABLE `tb_nilaikriteria` (
  `id_nilaikriteria` int(11) NOT NULL,
  `id_kriteria` int(11) NOT NULL,
  `nilai_kriteria` float NOT NULL,
  `keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_nilaikriteria`
--

INSERT INTO `tb_nilaikriteria` (`id_nilaikriteria`, `id_kriteria`, `nilai_kriteria`, `keterangan`) VALUES
(13, 6, 1, 'Normal'),
(14, 6, 3, 'Banyak'),
(15, 7, 1, 'Tidak Pincang'),
(16, 7, 5, 'Pincang'),
(17, 9, 1, 'Tidak Bengkak'),
(18, 9, 5, 'Bengkak'),
(19, 8, 3, 'Normal'),
(20, 8, 1, 'Rendah'),
(21, 10, 3, 'Normal'),
(22, 10, 1, 'Kurus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(11) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `level` enum('user','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `nama_user`, `username`, `password`, `level`) VALUES
(10, 'Wisnu', 'WisnuUser', 'Wisnu123', 'user'),
(14, 'UserIkram', 'User1', 'User1', 'user'),
(15, 'UserIkram12', 'User1', 'User1', 'user'),
(16, 'Wisnu Pengguna', 'User12', 'User12', 'user'),
(17, 'Ababil Kiyowo', 'Ababil', 'User123', 'user');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_about`
--
ALTER TABLE `tb_about`
  ADD PRIMARY KEY (`id_about`);

--
-- Indeks untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  ADD PRIMARY KEY (`id_datasapi`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  ADD PRIMARY KEY (`id_hasil`),
  ADD KEY `id_sapi` (`id_datasapi`);

--
-- Indeks untuk tabel `tb_kriteria`
--
ALTER TABLE `tb_kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indeks untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  ADD PRIMARY KEY (`id_nilaikriteria`),
  ADD KEY `id_kriteria` (`id_kriteria`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_about`
--
ALTER TABLE `tb_about`
  MODIFY `id_about` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  MODIFY `id_datasapi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  MODIFY `id_hasil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;

--
-- AUTO_INCREMENT untuk tabel `tb_kriteria`
--
ALTER TABLE `tb_kriteria`
  MODIFY `id_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  MODIFY `id_nilaikriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_datasapi`
--
ALTER TABLE `tb_datasapi`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_hasil`
--
ALTER TABLE `tb_hasil`
  ADD CONSTRAINT `id_sapi` FOREIGN KEY (`id_datasapi`) REFERENCES `tb_datasapi` (`id_datasapi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_nilaikriteria`
--
ALTER TABLE `tb_nilaikriteria`
  ADD CONSTRAINT `id_kriteria` FOREIGN KEY (`id_kriteria`) REFERENCES `tb_kriteria` (`id_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

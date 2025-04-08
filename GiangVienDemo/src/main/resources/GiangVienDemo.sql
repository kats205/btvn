USE GiangVienDemo;
GO

-- Bảng Môn Học
CREATE TABLE MonHoc (
    MaMonHoc NVARCHAR(10) PRIMARY KEY,
    TenMonHoc NVARCHAR(100) NOT NULL,
    SoTinChi INT NOT NULL
);

-- Bảng Bộ Môn
CREATE TABLE BoMon (
    MaBoMon NVARCHAR(10) PRIMARY KEY,
    TenBoMon NVARCHAR(100) NOT NULL
);

-- Bảng Giảng Viên (Chung)
CREATE TABLE GiangVien (
    MaGiangVien NVARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NamSinh INT NOT NULL,
    TrinhDoChuyenMon NVARCHAR(100),
    SoNamCongTac INT,
    MaBoMon NVARCHAR(10),
    LoaiGiangVien NVARCHAR(20) NOT NULL,
    FOREIGN KEY (MaBoMon) REFERENCES BoMon(MaBoMon)
);

-- Bảng Giảng Viên Cơ Hữu
CREATE TABLE GiangVienCoHuu (
    MaGiangVien NVARCHAR(10) PRIMARY KEY,
    HeSoLuong DECIMAL(5,2),
    NamBatDauCongTac INT,
    FOREIGN KEY (MaGiangVien) REFERENCES GiangVien(MaGiangVien)
);

-- Bảng Giảng Viên Thỉnh Giảng
CREATE TABLE GiangVienThinhGiang (
    MaGiangVien NVARCHAR(10) PRIMARY KEY,
    NoiCongTac NVARCHAR(200),
    FOREIGN KEY (MaGiangVien) REFERENCES GiangVien(MaGiangVien)
);

-- Bảng Liên Kết Giảng Viên và Môn Học
CREATE TABLE GiangVienMonHoc (
    MaGiangVien NVARCHAR(10),
    MaMonHoc NVARCHAR(10),
    PRIMARY KEY (MaGiangVien, MaMonHoc),
    FOREIGN KEY (MaGiangVien) REFERENCES GiangVien(MaGiangVien),
    FOREIGN KEY (MaMonHoc) REFERENCES MonHoc(MaMonHoc)
);

-- Dữ liệu mẫu
INSERT INTO BoMon (MaBoMon, TenBoMon) VALUES 
('CNTT', N'Khoa Công Nghệ Thông Tin'),
('NN', N'Khoa Ngoại Ngữ');

INSERT INTO MonHoc (MaMonHoc, TenMonHoc, SoTinChi) VALUES 
('MH001', N'Lập Trình Java', 3),
('MH002', N'Cấu Trúc Dữ Liệu', 4),
('MH003', N'Tiếng Anh Chuyên Ngành', 2);

INSERT INTO GiangVien (MaGiangVien, HoTen, NamSinh, TrinhDoChuyenMon, SoNamCongTac, MaBoMon, LoaiGiangVien) VALUES 
('GV001', N'Nguyễn Văn A', 1975, N'Tiến Sĩ', 15, 'CNTT', N'CoHuu'),
('GV002', N'Trần Thị B', 1985, N'Thạc Sĩ', 8, 'CNTT', N'ThinhGiang'),
('GV003', N'Lê Văn C', 1988, N'Thạc Sĩ', 5, 'NN', N'CoHuu');

INSERT INTO GiangVienCoHuu (MaGiangVien, HeSoLuong, NamBatDauCongTac) VALUES 
('GV001', 4.5, 2005),
('GV003', 4.0, 2010);

INSERT INTO GiangVienThinhGiang (MaGiangVien, NoiCongTac) VALUES 
('GV002', N'Trường Đại Học Bách Khoa');

INSERT INTO GiangVienMonHoc (MaGiangVien, MaMonHoc) VALUES 
('GV001', 'MH001'),
('GV001', 'MH002'),
('GV002', 'MH003'),
('GV003', 'MH001');
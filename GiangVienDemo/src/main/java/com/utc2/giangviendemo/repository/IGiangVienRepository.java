package com.utc2.giangviendemo.repository;

import com.utc2.giangviendemo.model.GiangVien;
import com.utc2.giangviendemo.model.MonHoc;

import java.util.List;
import java.util.Map;

public interface IGiangVienRepository {
    boolean themMonHocChoGiangVien(String maGiangVien, MonHoc monHoc);

    boolean kiemTraGiangVienCoHuuTren50(String maGiangVien);

    boolean kiemTraHaiGiangVienGiongNhau(String maGiangVien1, String maGiangVien2);

    int demSoLuongGiangVienCoHuuSinhTruoc1990(String maBoMon);

    GiangVien timGiangVienCoHuuLonTuoiNhat(String maBoMon);

    boolean kiemTraGiangVienThuocBoMon(String tenGiangVien, String maBoMon);

    List<GiangVien> layDanhSachGiangVienThinhGiangTheoNamSinh(int namSinh);

    List<GiangVien> sapXepGiangVienTrongBoMon(String maBoMon);

    Map<Integer, Integer> thongKeGiangVienTheoNamSinh(String maBoMon);

    List<GiangVien> getAllGiangVien();

    List<GiangVien> timKiemGiangVienTheoBoMon(String boMon);
}
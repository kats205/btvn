package com.utc2.giangviendemo.repository.repositotyImplement;

import com.utc2.giangviendemo.model.GiangVien;
import com.utc2.giangviendemo.model.MonHoc;
import com.utc2.giangviendemo.repository.IGiangVienRepository;

import java.util.List;
import java.util.Map;

public class GiangVienRepository implements IGiangVienRepository {
    private DAOGiangVien daoGiangVien;

    // Constructor
    public GiangVienRepository() {
        this.daoGiangVien = new DAOGiangVien();
    }

    // Constructor with DAO
    public GiangVienRepository(DAOGiangVien daoGiangVien) {
        this.daoGiangVien = daoGiangVien != null ? daoGiangVien : new DAOGiangVien();
    }

    @Override
    public boolean themMonHocChoGiangVien(String maGiangVien, MonHoc monHoc) {
        return daoGiangVien.themMonHocChoGiangVien(maGiangVien, monHoc);
    }

    @Override
    public boolean kiemTraGiangVienCoHuuTren50(String maGiangVien) {
        return daoGiangVien.kiemTraGiangVienCoHuuTren50(maGiangVien);
    }

    @Override
    public boolean kiemTraHaiGiangVienGiongNhau(String maGiangVien1, String maGiangVien2) {
        return daoGiangVien.kiemTraHaiGiangVienGiongNhau(maGiangVien1, maGiangVien2);
    }

    @Override
    public int demSoLuongGiangVienCoHuuSinhTruoc1990(String maBoMon) {
        return daoGiangVien.demSoLuongGiangVienCoHuuSinhTruoc1990(maBoMon);
    }

    @Override
    public GiangVien timGiangVienCoHuuLonTuoiNhat(String maBoMon) {
        return daoGiangVien.timGiangVienCoHuuLonTuoiNhat(maBoMon);
    }

    @Override
    public boolean kiemTraGiangVienThuocBoMon(String tenGiangVien, String maBoMon) {
        return daoGiangVien.kiemTraGiangVienThuocBoMon(tenGiangVien, maBoMon);
    }

    @Override
    public List<GiangVien> layDanhSachGiangVienThinhGiangTheoNamSinh(int namSinh) {
        return daoGiangVien.layDanhSachGiangVienThinhGiangTheoNamSinh(namSinh);
    }

    @Override
    public List<GiangVien> sapXepGiangVienTrongBoMon(String maBoMon) {
        return daoGiangVien.sapXepGiangVienTrongBoMon(maBoMon);
    }

    @Override
    public Map<Integer, Integer> thongKeGiangVienTheoNamSinh(String maBoMon) {
        return daoGiangVien.thongKeGiangVienTheoNamSinh(maBoMon);
    }

    @Override
    public List<GiangVien> getAllGiangVien() {
        return daoGiangVien.getAllGiangVien();
    }

    @Override
    public List<GiangVien> timKiemGiangVienTheoBoMon(String maBoMon) {
        return daoGiangVien.timKiemGiangVienTheoBoMon(maBoMon);
    }

    // Additional utility methods (not required by interface)

    public boolean themGiangVien(GiangVien giangVien) {
        return daoGiangVien.themGiangVien(giangVien);
    }

    public GiangVien getGiangVienByMa(String maGiangVien) {
        return daoGiangVien.getGiangVienByMa(maGiangVien);
    }

    public boolean xoaGiangVien(GiangVien giangVien) {
        return daoGiangVien.xoaGiangVien(giangVien);
    }
}
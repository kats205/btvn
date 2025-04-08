package com.utc2.giangviendemo.repository.repositotyImplement;

import com.utc2.giangviendemo.model.BoMon;
import com.utc2.giangviendemo.model.GiangVien;
import com.utc2.giangviendemo.model.GiangVienCoHuu;
import com.utc2.giangviendemo.model.GiangVienThinhGiang;
import com.utc2.giangviendemo.model.MonHoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DAOGiangVien {
    private List<GiangVien> danhSachGiangVien;
    private List<BoMon> danhSachBoMon;

    public DAOGiangVien() {
        this.danhSachGiangVien = new ArrayList<>();
        this.danhSachBoMon = new ArrayList<>();
        // Initialize with some sample data if needed
    }

    // Constructor with data
    public DAOGiangVien(List<GiangVien> danhSachGiangVien, List<BoMon> danhSachBoMon) {
        this.danhSachGiangVien = danhSachGiangVien != null ? danhSachGiangVien : new ArrayList<>();
        this.danhSachBoMon = danhSachBoMon != null ? danhSachBoMon : new ArrayList<>();
    }

    public List<GiangVien> getAllGiangVien() {
        return new ArrayList<>(danhSachGiangVien);
    }

    public GiangVien getGiangVienByMa(String maGiangVien) {
        return danhSachGiangVien.stream()
                .filter(gv -> gv.getMaGiangVien().equals(maGiangVien))
                .findFirst()
                .orElse(null);
    }

    public BoMon getBoMonByMa(String maBoMon) {
        return danhSachBoMon.stream()
                .filter(bm -> bm.getMaBoMon().equals(maBoMon))
                .findFirst()
                .orElse(null);
    }

    public boolean themMonHocChoGiangVien(String maGiangVien, MonHoc monHoc) {
        GiangVien giangVien = getGiangVienByMa(maGiangVien);
        if (giangVien != null) {
            return giangVien.themMonHoc(monHoc);
        }
        return false;
    }

    public boolean kiemTraGiangVienCoHuuTren50(String maGiangVien) {
        GiangVien giangVien = getGiangVienByMa(maGiangVien);
        if (giangVien != null) {
            return giangVien.kiemTraCoHuuTren50();
        }
        return false;
    }

    public boolean kiemTraHaiGiangVienGiongNhau(String maGiangVien1, String maGiangVien2) {
        GiangVien giangVien1 = getGiangVienByMa(maGiangVien1);
        GiangVien giangVien2 = getGiangVienByMa(maGiangVien2);

        if (giangVien1 != null && giangVien2 != null) {
            return giangVien1.kiemTraGiongNhau(giangVien2);
        }
        return false;
    }

    public int demSoLuongGiangVienCoHuuSinhTruoc1990(String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.demSoLuongGiangVienCoHuuSinhTruoc1990();
        }
        return 0;
    }

    public GiangVien timGiangVienCoHuuLonTuoiNhat(String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.timGiangVienCoHuuLonTuoiNhat();
        }
        return null;
    }

    public boolean kiemTraGiangVienThuocBoMon(String tenGiangVien, String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.kiemTraGiangVienThuocBoMon(tenGiangVien);
        }
        return false;
    }

    public List<GiangVien> layDanhSachGiangVienThinhGiangTheoNamSinh(int namSinh) {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienThinhGiang && gv.getNamSinh() == namSinh)
                .collect(Collectors.toList());
    }

    public List<GiangVien> sapXepGiangVienTrongBoMon(String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.sapXepGiangVienTrongBoMon();
        }
        return new ArrayList<>();
    }

    public Map<Integer, Integer> thongKeGiangVienTheoNamSinh(String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.thongKeGiangVienTheoNamSinh();
        }
        return new HashMap<>();
    }

    public List<GiangVien> timKiemGiangVienTheoBoMon(String maBoMon) {
        BoMon boMon = getBoMonByMa(maBoMon);
        if (boMon != null) {
            return boMon.getDanhSachGiangVien();
        }
        return new ArrayList<>();
    }

    // Additional utility methods for managing data
    public boolean themGiangVien(GiangVien giangVien) {
        if (!danhSachGiangVien.contains(giangVien)) {
            danhSachGiangVien.add(giangVien);

            // Add lecturer to department if associated
            if (giangVien.getBoMon() != null) {
                giangVien.getBoMon().themGiangVien(giangVien);
            }
            return true;
        }
        return false;
    }

    public boolean themBoMon(BoMon boMon) {
        if (!danhSachBoMon.contains(boMon)) {
            danhSachBoMon.add(boMon);
            return true;
        }
        return false;
    }

    public boolean xoaGiangVien(GiangVien giangVien) {
        if (giangVien.getBoMon() != null) {
            giangVien.getBoMon().xoaGiangVien(giangVien);
        }
        return danhSachGiangVien.remove(giangVien);
    }

    public boolean xoaBoMon(BoMon boMon) {
        // Remove department only if it has no lecturers
        if (boMon.getDanhSachGiangVien().isEmpty()) {
            return danhSachBoMon.remove(boMon);
        }
        return false;
    }
}
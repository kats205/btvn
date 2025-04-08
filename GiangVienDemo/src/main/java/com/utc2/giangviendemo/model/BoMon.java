package com.utc2.giangviendemo.model;

import java.util.*;
import java.util.stream.Collectors;

public class BoMon {
    private String maBoMon;
    private String tenBoMon;
    private List<GiangVien> danhSachGiangVien;

    // Constructor
    public BoMon(String maBoMon, String tenBoMon) {
        this.maBoMon = maBoMon;
        this.tenBoMon = tenBoMon;
        this.danhSachGiangVien = new ArrayList<>();
    }

    // Thêm giảng viên vào bộ môn
    public boolean themGiangVien(GiangVien giangVien) {
        if (danhSachGiangVien == null) {
            danhSachGiangVien = new ArrayList<>();
        }

        if (!danhSachGiangVien.contains(giangVien)) {
            danhSachGiangVien.add(giangVien);
            return true;
        }
        return false;
    }

    // Xóa giảng viên khỏi bộ môn
    public boolean xoaGiangVien(GiangVien giangVien) {
        if (danhSachGiangVien != null) {
            return danhSachGiangVien.remove(giangVien);
        }
        return false;
    }

    // Kiểm tra giảng viên có trong bộ môn không
    public boolean kiemTraGiangVienThuocBoMon(GiangVien giangVien) {
        return danhSachGiangVien != null && danhSachGiangVien.contains(giangVien);
    }

    // Kiểm tra giảng viên có tên cho trước có trong bộ môn không
    public boolean kiemTraGiangVienThuocBoMon(String tenGiangVien) {
        return danhSachGiangVien != null &&
                danhSachGiangVien.stream()
                        .anyMatch(gv -> gv.getHoTen().equals(tenGiangVien));
    }

    // Đếm số lượng giảng viên trong bộ môn
    public int demSoLuongGiangVien() {
        return danhSachGiangVien != null ? danhSachGiangVien.size() : 0;
    }

    // Đếm số lượng giảng viên cơ hữu sinh trước 1990
    public int demSoLuongGiangVienCoHuuSinhTruoc1990() {
        return (int) danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu && gv.getNamSinh() < 1990)
                .count();
    }

    // Tìm giảng viên cơ hữu lớn tuổi nhất
    public GiangVienCoHuu timGiangVienCoHuuLonTuoiNhat() {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu)
                .map(gv -> (GiangVienCoHuu) gv)
                .min(Comparator.comparingInt(GiangVien::getNamSinh))
                .orElse(null);
    }

    // Sắp xếp giảng viên trong bộ môn
    public List<GiangVien> sapXepGiangVienTrongBoMon() {
        return danhSachGiangVien.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    // Thống kê số lượng giảng viên theo năm sinh
    public Map<Integer, Integer> thongKeGiangVienTheoNamSinh() {
        return danhSachGiangVien.stream()
                .collect(Collectors.groupingBy(
                        GiangVien::getNamSinh,
                        Collectors.reducing(0, e -> 1, Integer::sum)
                ));
    }

    // Getters and Setters
    public String getMaBoMon() {
        return maBoMon;
    }

    public void setMaBoMon(String maBoMon) {
        this.maBoMon = maBoMon;
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

    public List<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien != null ?
                new ArrayList<>(danhSachGiangVien) :
                new ArrayList<>();
    }

    public void setDanhSachGiangVien(List<GiangVien> danhSachGiangVien) {
        this.danhSachGiangVien = danhSachGiangVien;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoMon boMon = (BoMon) o;
        return Objects.equals(maBoMon, boMon.maBoMon) &&
                Objects.equals(tenBoMon, boMon.tenBoMon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maBoMon, tenBoMon);
    }

    // ToString
    @Override
    public String toString() {
        return "BoMon{" +
                "maBoMon='" + maBoMon + '\'' +
                ", tenBoMon='" + tenBoMon + '\'' +
                ", soLuongGiangVien=" + (danhSachGiangVien != null ? danhSachGiangVien.size() : 0) +
                '}';
    }
}
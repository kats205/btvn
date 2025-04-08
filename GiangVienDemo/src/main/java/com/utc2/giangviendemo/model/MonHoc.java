package com.utc2.giangviendemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MonHoc {
    private String maMonHoc;
    private String tenMonHoc;
    private int soTinChi;
    private List<GiangVien> danhSachGiangVien;

    // Constructor
    public MonHoc(String maMonHoc, String tenMonHoc, int soTinChi) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.danhSachGiangVien = new ArrayList<>();
    }

    // Thêm giảng viên vào môn học
    public boolean themGiangVien(GiangVien giangVien) {
        if (danhSachGiangVien == null) {
            danhSachGiangVien = new ArrayList<>();
        }

        if (!danhSachGiangVien.contains(giangVien)) {
            danhSachGiangVien.add(giangVien);
            // Đồng thời thêm môn học vào danh sách môn học của giảng viên
            giangVien.themMonHoc(this);
            return true;
        }
        return false;
    }

    // Xóa giảng viên khỏi môn học
    public boolean xoaGiangVien(GiangVien giangVien) {
        if (danhSachGiangVien != null) {
            boolean removed = danhSachGiangVien.remove(giangVien);
            if (removed) {
                // Xóa môn học khỏi danh sách môn học của giảng viên
                giangVien.getDanhSachMonHoc().remove(this);
            }
            return removed;
        }
        return false;
    }

    // Kiểm tra giảng viên có dạy môn học này không
    public boolean kiemTraGiangVienDay(GiangVien giangVien) {
        return danhSachGiangVien != null && danhSachGiangVien.contains(giangVien);
    }

    // Kiểm tra giảng viên có tên cho trước có dạy môn học này không
    public boolean kiemTraGiangVienDay(String tenGiangVien) {
        return danhSachGiangVien != null &&
                danhSachGiangVien.stream()
                        .anyMatch(gv -> gv.getHoTen().equals(tenGiangVien));
    }

    // Đếm số lượng giảng viên dạy môn học
    public int demSoLuongGiangVien() {
        return danhSachGiangVien != null ? danhSachGiangVien.size() : 0;
    }

    // Đếm số lượng giảng viên cơ hữu dạy môn học
    public int demSoLuongGiangVienCoHuu() {
        return (int) danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu)
                .count();
    }

    // Tìm giảng viên cơ hữu dạy môn học
    public List<GiangVienCoHuu> timGiangVienCoHuu() {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu)
                .map(gv -> (GiangVienCoHuu) gv)
                .collect(Collectors.toList());
    }

    // Sắp xếp giảng viên dạy môn học
    public List<GiangVien> sapXepGiangVien() {
        return danhSachGiangVien.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    // Thống kê giảng viên theo năm sinh
    public List<GiangVien> thongKeGiangVienTheoNamSinh(int namSinh) {
        return danhSachGiangVien.stream()
                .filter(gv -> gv.getNamSinh() == namSinh)
                .collect(Collectors.toList());
    }

    // Getters and Setters
    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
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
        MonHoc monHoc = (MonHoc) o;
        return soTinChi == monHoc.soTinChi &&
                Objects.equals(maMonHoc, monHoc.maMonHoc) &&
                Objects.equals(tenMonHoc, monHoc.tenMonHoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMonHoc, tenMonHoc, soTinChi);
    }

    // ToString
    @Override
    public String toString() {
        return "MonHoc{" +
                "maMonHoc='" + maMonHoc + '\'' +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                ", soTinChi=" + soTinChi +
                ", soLuongGiangVien=" + (danhSachGiangVien != null ? danhSachGiangVien.size() : 0) +
                '}';
    }
}
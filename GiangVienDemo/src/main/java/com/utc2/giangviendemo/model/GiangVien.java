package com.utc2.giangviendemo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GiangVien implements Comparable<GiangVien> {
    protected String maGiangVien;
    protected String hoTen;
    protected int namSinh;
    protected String trinhDoChuyenMon;
    protected int soNamCongTac;
    protected BoMon boMon;
    protected List<MonHoc> danhSachMonHoc;

    public GiangVien(String maGiangVien, String hoTen, int namSinh, String trinhDoChuyenMon, int soNamCongTac, BoMon boMon) {
        this.maGiangVien = maGiangVien;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.trinhDoChuyenMon = trinhDoChuyenMon;
        this.soNamCongTac = soNamCongTac;
        this.boMon = boMon;
        this.danhSachMonHoc = new ArrayList<>();
    }

    public boolean themMonHoc(MonHoc monHoc) {
        if (danhSachMonHoc == null) {
            danhSachMonHoc = new ArrayList<>();
        }
        if (!danhSachMonHoc.contains(monHoc)) {
            return danhSachMonHoc.add(monHoc);
        }
        return false;
    }

    public boolean kiemTraCoHuuTren50() {
        return false;
    }

    public boolean kiemTraGiongNhau(GiangVien other) {
        if (other == null) return false;
        return this.getClass() == other.getClass() &&
                this.soNamCongTac == other.soNamCongTac;
    }

    @Override
    public int compareTo(GiangVien other) {
        int hoTenCompare = this.hoTen.compareTo(other.hoTen);
        if (hoTenCompare != 0) return hoTenCompare;
        return Integer.compare(other.namSinh, this.namSinh);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiangVien giangVien = (GiangVien) o;
        return namSinh == giangVien.namSinh &&
                soNamCongTac == giangVien.soNamCongTac &&
                Objects.equals(maGiangVien, giangVien.maGiangVien) &&
                Objects.equals(hoTen, giangVien.hoTen) &&
                Objects.equals(trinhDoChuyenMon, giangVien.trinhDoChuyenMon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maGiangVien, hoTen, namSinh, trinhDoChuyenMon, soNamCongTac);
    }

    // Getters and Setters
    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getTrinhDoChuyenMon() {
        return trinhDoChuyenMon;
    }

    public void setTrinhDoChuyenMon(String trinhDoChuyenMon) {
        this.trinhDoChuyenMon = trinhDoChuyenMon;
    }

    public int getSoNamCongTac() {
        return soNamCongTac;
    }

    public void setSoNamCongTac(int soNamCongTac) {
        this.soNamCongTac = soNamCongTac;
    }

    public BoMon getBoMon() {
        return boMon;
    }

    public void setBoMon(BoMon boMon) {
        this.boMon = boMon;
    }

    public List<MonHoc> getDanhSachMonHoc() {
        return danhSachMonHoc;
    }

    public void setDanhSachMonHoc(List<MonHoc> danhSachMonHoc) {
        this.danhSachMonHoc = danhSachMonHoc;
    }
}
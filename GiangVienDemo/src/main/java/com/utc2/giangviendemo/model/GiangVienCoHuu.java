package com.utc2.giangviendemo.model;

public class GiangVienCoHuu extends GiangVien {
    private double heSoLuong;
    private int namBatDauCongTac;

    public GiangVienCoHuu(String maGiangVien, String hoTen, int namSinh,
                          String trinhDoChuyenMon, int soNamCongTac,
                          BoMon boMon, double heSoLuong, int namBatDauCongTac) {
        super(maGiangVien, hoTen, namSinh, trinhDoChuyenMon, soNamCongTac, boMon);
        this.heSoLuong = heSoLuong;
        this.namBatDauCongTac = namBatDauCongTac;
    }

    @Override
    public boolean kiemTraCoHuuTren50() {
        return 2021 - namSinh > 50;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getNamBatDauCongTac() {
        return namBatDauCongTac;
    }

    public void setNamBatDauCongTac(int namBatDauCongTac) {
        this.namBatDauCongTac = namBatDauCongTac;
    }

    @Override
    public String toString() {
        return "GiangVienCoHuu{" +
                "maGiangVien='" + maGiangVien + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh=" + namSinh +
                ", trinhDoChuyenMon='" + trinhDoChuyenMon + '\'' +
                ", soNamCongTac=" + soNamCongTac +
                ", heSoLuong=" + heSoLuong +
                ", namBatDauCongTac=" + namBatDauCongTac +
                '}';
    }
}
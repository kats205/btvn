package com.utc2.giangviendemo.model;

public class GiangVienThinhGiang extends GiangVien {
    private String noiCongTac;

    public GiangVienThinhGiang(String maGiangVien, String hoTen, int namSinh,
                               String trinhDoChuyenMon, int soNamCongTac,
                               BoMon boMon, String noiCongTac) {
        super(maGiangVien, hoTen, namSinh, trinhDoChuyenMon, soNamCongTac, boMon);
        this.noiCongTac = noiCongTac;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    @Override
    public String toString() {
        return "GiangVienThinhGiang{" +
                "maGiangVien='" + maGiangVien + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh=" + namSinh +
                ", trinhDoChuyenMon='" + trinhDoChuyenMon + '\'' +
                ", soNamCongTac=" + soNamCongTac +
                ", noiCongTac='" + noiCongTac + '\'' +
                '}';
    }
}
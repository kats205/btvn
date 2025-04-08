package com.utc2.giangviendemo.controller;

import com.utc2.giangviendemo.model.*;
import com.utc2.giangviendemo.repository.repositotyImplement.GiangVienRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GiangVienController {
    @FXML private ComboBox<String> cbBoMon;
    @FXML private TableView<GiangVien> tableGiangVien;
    @FXML private TableColumn<GiangVien, String> colMaGV;
    @FXML private TableColumn<GiangVien, String> colHoTen;
    @FXML private TableColumn<GiangVien, Integer> colNamSinh;
    @FXML private TableColumn<GiangVien, String> colLoai;
    @FXML private TableColumn<GiangVien, String> colTrinhDo;
    @FXML private TableColumn<GiangVien, Integer> colSoNamCT;
    @FXML private TableColumn<GiangVien, String> colMonHoc;
    @FXML private TableColumn<GiangVien, String> colThongTinThem;

    @FXML private TextArea txtKetQua;
    @FXML private TextField txtGV1;
    @FXML private TextField txtGV2;
    @FXML private TextField txtTenGV;
    @FXML private TextField txtNamSinh;

    private GiangVienRepository giangVienRepository;

    public GiangVienController() {
        giangVienRepository = new GiangVienRepository();
    }

    @FXML
    private void initialize() {
        // Cấu hình các cột cho bảng
        colMaGV.setCellValueFactory(new PropertyValueFactory<>("maGiangVien"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        colNamSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        colLoai.setCellValueFactory(cellData -> {
            GiangVien gv = cellData.getValue();
            return new SimpleStringProperty(
                    gv instanceof GiangVienCoHuu ? "Cơ hữu" : "Thỉnh giảng"
            );
        });
        colTrinhDo.setCellValueFactory(new PropertyValueFactory<>("trinhDoChuyenMon"));
        colSoNamCT.setCellValueFactory(new PropertyValueFactory<>("soNamCongTac"));
        colMonHoc.setCellValueFactory(cellData -> {
            GiangVien gv = cellData.getValue();
            return new SimpleStringProperty(
                    gv.getDanhSachMonHoc().stream()
                            .map(MonHoc::getTenMonHoc)
                            .collect(Collectors.joining(", "))
            );
        });
        colThongTinThem.setCellValueFactory(cellData -> {
            GiangVien gv = cellData.getValue();
            if (gv instanceof GiangVienCoHuu coHuu) {
                return new SimpleStringProperty(
                        String.format("Hệ số lương: %.2f, Năm bắt đầu: %d",
                                coHuu.getHeSoLuong(), coHuu.getNamBatDauCongTac())
                );
            } else if (gv instanceof GiangVienThinhGiang thinhGiang) {
                return new SimpleStringProperty(
                        "Nơi công tác: " + thinhGiang.getNoiCongTac()
                );
            }
            return new SimpleStringProperty("");
        });

        // Set handler for table row selection
        tableGiangVien.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        hienThiThongTinGiangVien(newSelection);
                    }
                }
        );

        // Load danh sách giảng viên ban đầu
        loadDanhSachGiangVien();
    }

    private void loadDanhSachGiangVien() {
        // Lấy danh sách tất cả giảng viên từ repository
        List<GiangVien> danhSachGiangVien = giangVienRepository.getAllGiangVien();

        // Cập nhật dữ liệu cho bảng
        tableGiangVien.setItems(FXCollections.observableArrayList(danhSachGiangVien));
    }

    private void hienThiThongTinGiangVien(GiangVien giangVien) {
        if (giangVien == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("THÔNG TIN GIẢNG VIÊN\n\n");
        sb.append("Mã GV: ").append(giangVien.getMaGiangVien()).append("\n");
        sb.append("Họ tên: ").append(giangVien.getHoTen()).append("\n");
        sb.append("Năm sinh: ").append(giangVien.getNamSinh()).append("\n");
        sb.append("Trình độ chuyên môn: ").append(giangVien.getTrinhDoChuyenMon()).append("\n");
        sb.append("Số năm công tác: ").append(giangVien.getSoNamCongTac()).append("\n");

        if (giangVien.getBoMon() != null) {
            sb.append("Bộ môn: ").append(giangVien.getBoMon().getTenBoMon()).append("\n");
        }

        sb.append("Các môn học giảng dạy: ").append(
                giangVien.getDanhSachMonHoc().stream()
                        .map(MonHoc::getTenMonHoc)
                        .collect(Collectors.joining(", "))
        ).append("\n\n");

        if (giangVien instanceof GiangVienCoHuu coHuu) {
            sb.append("Loại: Giảng viên cơ hữu\n");
            sb.append("Hệ số lương: ").append(coHuu.getHeSoLuong()).append("\n");
            sb.append("Năm bắt đầu công tác: ").append(coHuu.getNamBatDauCongTac()).append("\n");
            boolean tren50 = coHuu.kiemTraCoHuuTren50();
            sb.append("Tuổi: ").append(tren50 ? "Trên 50 tuổi" : "Không quá 50 tuổi").append("\n");
        } else if (giangVien instanceof GiangVienThinhGiang thinhGiang) {
            sb.append("Loại: Giảng viên thỉnh giảng\n");
            sb.append("Nơi công tác: ").append(thinhGiang.getNoiCongTac()).append("\n");
        }

        txtKetQua.setText(sb.toString());
    }

    @FXML
    private void timKiemBoMon(ActionEvent event) {
        String boMon = cbBoMon.getValue();
        if (boMon == null || boMon.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn bộ môn!");
            return;
        }

        // Thực hiện tìm kiếm giảng viên theo bộ môn
        List<GiangVien> danhSachGiangVien = giangVienRepository.timKiemGiangVienTheoBoMon(boMon);

        // Cập nhật dữ liệu cho bảng
        tableGiangVien.setItems(FXCollections.observableArrayList(danhSachGiangVien));

        txtKetQua.setText("Đã tìm thấy " + danhSachGiangVien.size() + " giảng viên thuộc bộ môn " + boMon);
    }

    @FXML
    private void soSanhGiangVien(ActionEvent event) {
        String maGV1 = txtGV1.getText();
        String maGV2 = txtGV2.getText();

        if (maGV1.isEmpty() || maGV2.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập mã giảng viên cần so sánh!");
            return;
        }

        boolean ketQua = giangVienRepository.kiemTraHaiGiangVienGiongNhau(maGV1, maGV2);

        txtKetQua.setText(ketQua ?
                "Hai giảng viên có cùng loại và số năm công tác." :
                "Hai giảng viên khác nhau về loại hoặc số năm công tác."
        );
    }

    @FXML
    private void kiemTraGiangVien(ActionEvent event) {
        String tenGV = txtTenGV.getText();
        String boMon = cbBoMon.getValue();

        if (tenGV.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập tên giảng viên!");
            return;
        }

        if (boMon == null || boMon.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn bộ môn!");
            return;
        }

        boolean ketQua = giangVienRepository.kiemTraGiangVienThuocBoMon(tenGV, boMon);

        txtKetQua.setText(ketQua ?
                "Giảng viên \"" + tenGV + "\" thuộc bộ môn \"" + boMon + "\"." :
                "Giảng viên \"" + tenGV + "\" KHÔNG thuộc bộ môn \"" + boMon + "\"."
        );
    }

    @FXML
    private void timGiangVienThinhGiang(ActionEvent event) {
        try {
            if (txtNamSinh.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng nhập năm sinh!");
                return;
            }

            int namSinh = Integer.parseInt(txtNamSinh.getText());

            List<GiangVien> danhSach = giangVienRepository.layDanhSachGiangVienThinhGiangTheoNamSinh(namSinh);

            // Cập nhật bảng và kết quả
            tableGiangVien.setItems(FXCollections.observableArrayList(danhSach));
            txtKetQua.setText("Đã tìm thấy " + danhSach.size() + " giảng viên thỉnh giảng sinh năm " + namSinh);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Năm sinh phải là số nguyên!");
        }
    }

    @FXML
    private void sapXepGiangVien(ActionEvent event) {
        String boMon = cbBoMon.getValue();

        if (boMon == null || boMon.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn bộ môn!");
            return;
        }

        List<GiangVien> danhSach = giangVienRepository.sapXepGiangVienTrongBoMon(boMon);

        // Cập nhật bảng và kết quả
        tableGiangVien.setItems(FXCollections.observableArrayList(danhSach));
        txtKetQua.setText("Đã sắp xếp " + danhSach.size() + " giảng viên theo tên (và năm sinh nếu trùng tên)");
    }

    @FXML
    private void thongKeGiangVien(ActionEvent event) {
        String boMon = cbBoMon.getValue();

        if (boMon == null || boMon.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn bộ môn!");
            return;
        }

        Map<Integer, Integer> thongKe = giangVienRepository.thongKeGiangVienTheoNamSinh(boMon);

        if (thongKe.isEmpty()) {
            txtKetQua.setText("Không có dữ liệu thống kê cho bộ môn này.");
            return;
        }

        // Hiển thị thống kê
        StringBuilder ketQua = new StringBuilder("THỐNG KÊ GIẢNG VIÊN THEO NĂM SINH - BỘ MÔN " + boMon + "\n\n");
        thongKe.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        ketQua.append(String.format("Năm %d: %d giảng viên\n", entry.getKey(), entry.getValue()))
                );

        txtKetQua.setText(ketQua.toString());
    }

    @FXML
    private void suaThongTin(ActionEvent event) {
        GiangVien selected = tableGiangVien.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn giảng viên cần sửa!");
            return;
        }

        // Logic hiển thị form chỉnh sửa thông tin giảng viên
        // Đây có thể là một cửa sổ mới hoặc một dialog để cập nhật thông tin
        showAlert(Alert.AlertType.INFORMATION, "Thông báo",
                "Chức năng đang được phát triển: Sửa thông tin cho giảng viên " + selected.getHoTen());
    }

    @FXML
    private void xoaGiangVien(ActionEvent event) {
        GiangVien selected = tableGiangVien.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Cảnh báo", "Vui lòng chọn giảng viên cần xóa!");
            return;
        }

        Optional<ButtonType> result = showConfirmation(
                "Xác nhận xóa",
                "Bạn có chắc chắn muốn xóa giảng viên " + selected.getHoTen() + "?"
        );

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = giangVienRepository.xoaGiangVien(selected);
            if (success) {
                loadDanhSachGiangVien();
                txtKetQua.setText("Đã xóa giảng viên " + selected.getHoTen() + " thành công.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa giảng viên.");
            }
        }
    }

    @FXML
    private void themGiangVien(ActionEvent event) {
        // Logic hiển thị form thêm giảng viên mới
        // Đây có thể là một cửa sổ mới hoặc một dialog để nhập thông tin
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Chức năng đang được phát triển: Thêm giảng viên mới");
    }

    // Utility methods for UI
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
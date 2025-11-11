/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Model.KegiatanModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class KegiatanController implements Initializable {

    @FXML
    private TableView<KegiatanModel> tvKegiatan;
    @FXML
    private TableColumn<KegiatanModel, String> colTanggal;
    @FXML
    private TableColumn<KegiatanModel, String> colKegiatan;
    @FXML
    private TableColumn<KegiatanModel, String> colTempat;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtKegiatan;
    @FXML
    private TextField txtTempat;
    private ObservableList<KegiatanModel> dataKegiatan;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataKegiatan = FXCollections.observableArrayList();  
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        colKegiatan.setCellValueFactory(new PropertyValueFactory<> ("namaKegiatan"));
        colTempat.setCellValueFactory(new PropertyValueFactory<>("tempatKegiatan"));
        tvKegiatan.setItems(dataKegiatan);      
        tvKegiatan.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showKegiatanModelDetails(newValue));
    }    

    @FXML
    private void handleAddKegiatan(ActionEvent event) {
        LocalDate Tanggal = txtDate.getValue();
        String namaKegiatan = txtKegiatan.getText();
        String tempatKegiatan = txtTempat.getText();

        if (Tanggal == null || namaKegiatan.isEmpty() || tempatKegiatan.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Data tidak boleh kosong.");
            return;
            }

        KegiatanModel baru = new KegiatanModel(Tanggal, namaKegiatan, tempatKegiatan);
        dataKegiatan.add(baru);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Kegiatan berhasil ditambahkan.");
    }

    @FXML
    private void handleEditKegiatan(ActionEvent event) {
        KegiatanModel selectedkgn = tvKegiatan.getSelectionModel().getSelectedItem();

        if (selectedkgn != null) {
            selectedkgn.setTanggal(txtDate.getValue());
            selectedkgn.setNamaKegiatan(txtKegiatan.getText()); 
            selectedkgn.setTempatKegiatan(txtTempat.getText()); 

            tvKegiatan.refresh();
            
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Kegiatan berhasil diubah.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Kegiatan yang ingin diubah.");
        }

    }

    @FXML
    private void handleDeleteKegiatan(ActionEvent event) {
        KegiatanModel selectedkgn = tvKegiatan.getSelectionModel().getSelectedItem();

        if (selectedkgn != null) {
            dataKegiatan.remove(selectedkgn);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Kegiatan berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Kegiatan yang ingin dihapus.");
        }

    }

    private void showKegiatanModelDetails(KegiatanModel kgn) {
        if (kgn != null) {
            txtKegiatan.setText(kgn.getNamaKegiatan());
            txtTempat.setText(kgn.getTempatKegiatan());
        } else {
            txtKegiatan.setText("");
            txtTempat.setText("");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        txtKegiatan.clear();
        txtTempat.clear();
        tvKegiatan.getSelectionModel().clearSelection();
    }
    
}

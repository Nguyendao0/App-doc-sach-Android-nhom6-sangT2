package com.example.helloworldjava.presenter;


import com.example.helloworldjava.model.DAO.TheLoaiSachDAO;
import com.example.helloworldjava.model.entity.TheLoaiSach;


import java.util.List;

public class TheLoaiSachPresenter {
    private TheLoaiSachDAO theLoaiSachDAO;

    public TheLoaiSachPresenter(TheLoaiSachDAO theLoaiSachDAO) {
        this.theLoaiSachDAO = theLoaiSachDAO;
    }

    public List<TheLoaiSach> getAllTheLoaiSach() {
        return theLoaiSachDAO.getAllTheLoaiSach();
    }

    public void addTheLoaiSach(TheLoaiSach theLoaiSach) {
        theLoaiSachDAO.insertTheLoaiSach(theLoaiSach);
    }
}

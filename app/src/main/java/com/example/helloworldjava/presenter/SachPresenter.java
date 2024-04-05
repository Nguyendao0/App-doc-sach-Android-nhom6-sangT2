package com.example.helloworldjava.presenter;

import com.example.helloworldjava.model.DAO.SachDAO;
import com.example.helloworldjava.model.entity.Sach;

import java.util.List;

public class SachPresenter {
    private SachDAO sachDAO;

    public SachPresenter(SachDAO sachDAO) {
        this.sachDAO = sachDAO;
    }

    public List<Sach> getAllSach() {
        return sachDAO.getAllSach();
    }

    public void addSach(Sach sach) {
        sachDAO.insertSach(sach);
    }
}

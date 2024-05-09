package com.example.helloworldjava.view.Search;

import static com.example.helloworldjava.R.drawable.sach02;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;

import java.util.ArrayList;

public class HistorySearch {
   ArrayList<Sach> arraySach;

    public ArrayList<Sach> getArraySach() {
        return arraySach;
    }

    public void setArrayBook(ArrayList<Sach> arrayBook) {
        this.arraySach = arraySach;
    }

    public HistorySearch() {
        ArrayList<Sach> array = new ArrayList<Sach>();
//        array.add(new Sach("Watanabe Kazuko","Hạnh Phúc Hay Không Do Ta Quyết Định", String.valueOf(R.drawable.sach01)));
//        array.add(new Sach("Be Kind","Hãy có lòng tốt",String.valueOf(sach02)));
//        array.add(new Sach("Minh Niệm","Làm Như Chơi ",String.valueOf(R.drawable.sach03)));
//        array.add(new Sach("Nhà xuất bản Trẻ","Tư vấn tâm lý học đường. ",String.valueOf(R.drawable.sach04)));

        this.arraySach = array;
    }
}



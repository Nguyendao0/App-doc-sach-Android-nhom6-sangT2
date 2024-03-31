package com.example.helloworldjava.model;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Book;

import java.util.ArrayList;

public class HistorySearch {
   ArrayList<Book> arrayBook;

    public ArrayList<Book> getArrayBook() {
        return arrayBook;
    }

    public void setArrayBook(ArrayList<Book> arrayBook) {
        this.arrayBook = arrayBook;
    }

    public HistorySearch() {
        ArrayList<Book> array = new ArrayList<Book>();
        array.add(new Book("Watanabe Kazuko","Hạnh Phúc Hay Không Do Ta Quyết Định", R.drawable.sach01));
        array.add(new Book("Be Kind","Hãy có lòng tốt",R.drawable.sach03));
        array.add(new Book("Minh Niệm","Làm Như Chơi ",R.drawable.sach03));
        array.add(new Book("Nhà xuất bản Trẻ","Tư vấn tâm lý học đường. ",R.drawable.sach04));

        this.arrayBook = array;
    }
}



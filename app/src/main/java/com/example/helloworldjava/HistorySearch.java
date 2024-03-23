package com.example.helloworldjava;

import static com.example.helloworldjava.R.drawable.sach02;

import com.example.helloworldjava.mvp.model.Book;

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
        array.add(new Book("Be Kind","Hãy có lòng tốt",sach02));
        array.add(new Book("Minh Niệm","Làm Như Chơi ",R.drawable.sach03));
        array.add(new Book("Nhà xuất bản Trẻ","Tư vấn tâm lý học đường. ",R.drawable.sach04));

        this.arrayBook = array;
    }
}



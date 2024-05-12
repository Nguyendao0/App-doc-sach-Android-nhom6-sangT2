package com.example.helloworldjava.presenter.Library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.Realm.DAO.Interface_Success_Fail;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.UserService;
import com.example.helloworldjava.model.Realm.DAO.SachDAO;
import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;
import com.example.helloworldjava.model.Realm.Sach;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentReadingPresenter implements CurrentReadingContract.Presenter {
    private CurrentReadingContract.View CurrentReadingView;
    private  SachService sachService;
    private UserService userService;
    private ChuongService chuongService;

    public CurrentReadingPresenter(CurrentReadingContract.View currentReadingView) {
        CurrentReadingView = currentReadingView;
        sachService = ServiceBuilder.buildService(SachService.class);
        userService = ServiceBuilder.buildService(UserService.class);
        chuongService = ServiceBuilder.buildService(ChuongService.class);
    }

    @Override
    public void readSach() {

        Call<List<ThuVienSachCaNhan>> request = userService.findAll("wVtlXbDWiRmCmETfixgd");
        request.enqueue(new Callback<List<ThuVienSachCaNhan>>() {
            @Override
            public void onResponse(Call<List<ThuVienSachCaNhan>> call, Response<List<ThuVienSachCaNhan>> response) {

                List<ThuVienSachCaNhan> thuVienSachCaNhanList = response.body();
                ArrayList<Sach> listSach = new ArrayList<>();
                for (ThuVienSachCaNhan tvs: thuVienSachCaNhanList)
                {
                    listSach.add(tvs.getSach());
                    setChuongForSach(tvs.getSach(), "wVtlXbDWiRmCmETfixgd");
                }
                CurrentReadingView.setSachList((ArrayList<Sach>) listSach);
            }

            @Override
            public void onFailure(Call<List<ThuVienSachCaNhan>> call, Throwable throwable) {
                System.out.println("Lỗi main" + throwable.getMessage());
            }
        });


    }

    private void setChuongForSach(Sach sach, String userID)
    {
        Call<List<Chuong>> request = sachService.getChuongList(sach.getID(),userID);
        request.enqueue(new Callback<List<Chuong>>() {
            @Override
            public void onResponse(Call<List<Chuong>> call, Response<List<Chuong>> response) {

                List<Chuong> chuongList = response.body();

                RealmList chuongRealm = new RealmList<>();
                for (Chuong c: chuongList)
                {
                    chuongRealm.add(c);
                }
                sach.setChuong_Items(chuongRealm);
            }

            @Override
            public void onFailure(Call<List<Chuong>> call, Throwable throwable) {
                System.out.println("Lỗi main" + throwable.getMessage());
            }
        });
    }

    @Override
    public void readSachOffline() {

        ArrayList<Sach> listsach = new ArrayList<>();
        SachDAO sachDAO = new SachDAO();
        for (Sach s : sachDAO.getAll())
        {
            listsach.add(s);
        }
        CurrentReadingView.setSachOfflineList(listsach);

    }

    @Override
    public void addBookOffline(Sach sach, Context context) {
        SachDAO sachDAO = new SachDAO();
        DowloadFile(sach.getImg(), sach, context);
        sachDAO.add(sach, new Interface_Success_Fail() {
            @Override
            public void onSuccess() {

                ArrayList<Sach> list = new ArrayList<>();
                for (Sach s:sachDAO.getAll())
                {
                    list.add(s);
                }

                CurrentReadingView.setSachOfflineList(list);
            }

            @Override
            public void onFail(Throwable error) {

            }
        });
    }

    private void DowloadFile(String imageUrl, Sach sach, Context context)
    {

// Tạo một đối tượng File để lưu trữ ảnh từ truyện được tải xuống
        String fileName = Uri.parse(imageUrl).getLastPathSegment();
        File file = new File(context.getFilesDir(), fileName);

        Picasso.get()
                .load(imageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        try {
                            // Lưu ảnh từ truyện xuống thiết bị
                            FileOutputStream outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                            outputStream.flush();
                            outputStream.close();

                            sach.setImg(file.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            System.out.println("LỖI TẢI ẢNH: " + e);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("LỖI TẢI ẢNH: " + e);
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }

                    // ...
                });
    }
    @Override
    public void removeBookOffline(String IDSach) {
        SachDAO sachDAO = new SachDAO();
        DeteleFile(sachDAO.getById(IDSach).getImg());
        sachDAO.delete(IDSach, new Interface_Success_Fail() {
            @Override
            public void onSuccess() {
                ArrayList<Sach> list = new ArrayList<>();
                for (Sach s:sachDAO.getAll())
                {
                    list.add(s);
                }
                CurrentReadingView.setSachOfflineList(list);

            }

            @Override
            public void onFail(Throwable error) {

            }
        });
    }

    private void DeteleFile(String imagePath)
    {
        File imageFile = new File(imagePath);

        // Kiểm tra xem tệp tồn tại trước khi xóa
        if (imageFile.exists()) {
            // Xóa tệp
            boolean isDeleted = imageFile.delete();

            if (isDeleted) {
                System.out.println("Ảnh đã được xóa thành công.");
            } else {
                System.out.println("Không thể xóa ảnh.");
            }
        } else {
            System.out.println("Ảnh không tồn tại.");
        }
    }
    @Override
    public void addBooksOffline(List<Sach> sachList, Context context) {
        SachDAO sachDAO = new SachDAO();
        for(Sach sach : sachList)
        {
            DowloadFile(sach.getImg(), sach, context);
        }
        sachDAO.addBooks(sachList, new Interface_Success_Fail() {
            @Override
            public void onSuccess() {
                List<Sach> updatedSachList = sachDAO.getAll();
                CurrentReadingView.setSachOfflineList(new ArrayList<>(updatedSachList));
            }

            @Override
            public void onFail(Throwable error) {

            }
        });
    }

    @Override
    public void removesBookOffline(List<String> sachIDs) {
        SachDAO sachDAO = new SachDAO();
        for (String id : sachIDs) {
            DeteleFile(sachDAO.getById(id).getImg());
        }

        sachDAO.deleteBooks(sachIDs, new Interface_Success_Fail() {
            @Override
            public void onSuccess() {
                List<Sach> updatedSachList = sachDAO.getAll();
                CurrentReadingView.setSachOfflineList(new ArrayList<>(updatedSachList));
            }

            @Override
            public void onFail(Throwable error) {
                // Xử lý lỗi nếu cần thiết
            }
        });
    }

    @Override
    public void removeBooksLibrary(List<String> sachIDs) {
        int totalRequests = sachIDs.size();
        AtomicInteger completedRequests = new AtomicInteger(0);

        for (String id : sachIDs) {
            Call<Void> request = userService.deleteSach("wVtlXbDWiRmCmETfixgd", id);
            request.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (completedRequests.incrementAndGet() == totalRequests) {
                        readSach();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable throwable) {
                    System.out.println("Lỗi curPresenter" + throwable.getMessage());
                }
            });
        }
    }
}
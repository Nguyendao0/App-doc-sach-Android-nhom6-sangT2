package com.example.helloworldjava.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helloworldjava.model.entity.NguoiDung;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

public class NguoiDungModel {
    public static String COLLECTION_PATH = "NguoiDung";
    private FirebaseFirestore db;

    public NguoiDungModel() {
        db = FirebaseFirestore.getInstance();
    }

    public interface GetNguoiDungCallback {
        void onNguoiDungLoaded(NguoiDung nguoiDung);

        void onNguoiDungNotFound();

        void onError(Exception e);
    }

    public void getNguoiDungByGoogleId(String googleId, GetNguoiDungCallback callback) {
        db.collection(COLLECTION_PATH).whereEqualTo("googleId", googleId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        Log.d(COLLECTION_PATH, "No such document");
                        callback.onNguoiDungNotFound();
                    } else {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        NguoiDung nguoiDung = document.toObject(NguoiDung.class);
                        Log.d(COLLECTION_PATH, document.getId() + " => " + document.getData());
                        callback.onNguoiDungLoaded(nguoiDung);
                    }
                } else {
                    Log.d(COLLECTION_PATH, "Error getting documents: ", task.getException());
                    callback.onError(task.getException());
                }
            }
        });
    }

    public void getNguoiDungById(String id, GetNguoiDungCallback callback) {
        db.collection(COLLECTION_PATH).document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                NguoiDung nguoiDung = document.toObject(NguoiDung.class);
                                nguoiDung.setId(document.getId());
                                callback.onNguoiDungLoaded(nguoiDung);
                                Log.d(COLLECTION_PATH, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(COLLECTION_PATH, "No such document");
                                callback.onNguoiDungNotFound();
                            }
                        } else {
                            Log.d(COLLECTION_PATH, "get failed with ", task.getException());
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public void getNguoiDungByEmail(String email, GetNguoiDungCallback callback) {
        db.collection(COLLECTION_PATH).whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        Log.d(COLLECTION_PATH, "No such document");
                        callback.onNguoiDungNotFound();
                    } else {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        NguoiDung nguoiDung = document.toObject(NguoiDung.class);
                        Log.d(COLLECTION_PATH, document.getId() + " => " + document.getData());
                        callback.onNguoiDungLoaded(nguoiDung);
                    }
                } else {
                    Log.d(COLLECTION_PATH, "Error getting documents: ", task.getException());
                    callback.onError(task.getException());
                }
            }
        });
    }

    public interface CreateNguoiDungCallback {
        void onNguoiDungCreated(NguoiDung nguoiDung);

        void onError(Exception e);
    }

    private String getRandomString(int sizeOfPasswordString){
        String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfPasswordString);

        for(int i=0;i<sizeOfPasswordString;++i){
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        }
        return sb.toString();
    }

    public void createNguoiDung(NguoiDung nguoiDung, CreateNguoiDungCallback callback) {
        DocumentReference newNguoiDungRef = db.collection(COLLECTION_PATH).document();
        nguoiDung.setId(newNguoiDungRef.getId());
        nguoiDung.setMatKhau(getRandomString(8));

        newNguoiDungRef.set(nguoiDung).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(COLLECTION_PATH, "DocumentSnapshot written with ID: " + nguoiDung.getId());
                callback.onNguoiDungCreated(nguoiDung);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(COLLECTION_PATH, "Error adding document", e);
                callback.onError(e);
            }
        });
    }

}

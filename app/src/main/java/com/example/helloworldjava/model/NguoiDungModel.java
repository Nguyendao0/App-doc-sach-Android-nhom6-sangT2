package com.example.helloworldjava.model;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class NguoiDungModel {
    public static String COLLECTION_PATH = "NguoiDung";
    private FirebaseFirestore db;
    public NguoiDungModel() {
        db = FirebaseFirestore.getInstance();
    }

    public interface GetNguoiDungCallback {
        void onNguoiDungLoaded(NguoiDung nguoiDung);

        void onError(Exception e);
    }

    public void getNguoiDungByGoogleId(String googleId, GetNguoiDungCallback callback) {
        db.collection(COLLECTION_PATH).whereEqualTo("googleId", googleId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
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
                                nguoiDung.setID(document.getId());
                                callback.onNguoiDungLoaded(nguoiDung);
                                Log.d(COLLECTION_PATH, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(COLLECTION_PATH, "No such document");
                            }
                        } else {
                            Log.d(COLLECTION_PATH, "get failed with ", task.getException());
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public interface CreateNguoiDungCallback {
        void onNguoiDungCreated(NguoiDung nguoiDung);

        void onError(Exception e);
    }

    public void createNguoiDung(NguoiDung nguoiDung, CreateNguoiDungCallback callback) {
        db.collection(COLLECTION_PATH).add(nguoiDung).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(COLLECTION_PATH, "DocumentSnapshot written with ID: " + documentReference.getId());
                nguoiDung.setID(documentReference.getId());
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

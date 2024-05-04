package com.example.helloworldjava.model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helloworldjava.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserModel {
    public interface GetUserCallback {
        void onUserLoaded(User user);

        void onError(Exception e);
    }

    public interface UpdateUserCallback {
        void onUserUpdated(User user);

        void onError(Exception e);
    }

    private FirebaseFirestore db;

    public UserModel() {
        db = FirebaseFirestore.getInstance();
    }

    public void getUsers(GetUserCallback callback) {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                callback.onUserLoaded(user);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.onError(task.getException());
                        }
                    }
                });

    }

    public void getUserById(String id, GetUserCallback callback) {
        db.collection("users").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = document.toObject(User.class);
                                user.setId(document.getId());
                                callback.onUserLoaded(user);
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                            callback.onError(task.getException());
                        }
                    }
                });
    }

    public void updateUser(User user, UpdateUserCallback callback) {
        db.collection("users").document(user.getId())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onUserUpdated(user);
                        } else {
                            callback.onError(task.getException());
                        }
                    }
                });
    }
}

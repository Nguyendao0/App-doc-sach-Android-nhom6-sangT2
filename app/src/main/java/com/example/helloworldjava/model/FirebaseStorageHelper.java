package com.example.helloworldjava.model;

import android.net.Uri;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class FirebaseStorageHelper {
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();

    public FirebaseStorage getStorage() {
        return storage;
    }

    public StorageReference getReference() {
        return storage.getReference();
    }

    public StorageReference getReference(String path) {
        return storage.getReference(path);
    }

    public Task<Uri> uploadImageAndGetDownloadUrl(byte[] byteArray, StorageReference imagesRef, UploadImageCallback callback) {
        UploadTask uploadTask = imagesRef.putBytes(byteArray);
        return uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            // Continue with the task to get the download URL
            return imagesRef.getDownloadUrl().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    Uri downloadUri = task1.getResult();
                    callback.onUploadSuccess(downloadUri);
                } else {
                    callback.onUploadFailure(task1.getException());
                }
            });
        });
    }

    public interface UploadImageCallback {
        void onUploadSuccess(Uri uri);
        void onUploadFailure(Exception e);
    }

}

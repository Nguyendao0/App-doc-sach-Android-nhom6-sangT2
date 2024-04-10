package com.example.helloworldjava.model;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    public FirebaseFirestore db;
    public FirebaseHelper() {
        // Initialize Firestore instance
        db  = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}

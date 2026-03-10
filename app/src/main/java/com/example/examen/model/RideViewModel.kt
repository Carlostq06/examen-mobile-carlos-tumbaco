package com.example.examen.model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.collections.emptyList


class RideViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var rideHistory by mutableStateOf<List<Ride>>(emptyList())
        private set

    fun loadMyRides() {
        val user = auth.currentUser ?: return

        viewModelScope.launch {
            try {
                val snapshot = db.collection("rides")
                    .whereEqualTo("userId", user.uid)
                    .get()
                    .await()

                val rides = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Ride::class.java)?.copy(firestoreId = doc.id)
                }

                rideHistory = rides

            } catch (e: Exception) {
                rideHistory = emptyList()
            }
        }
    }
}
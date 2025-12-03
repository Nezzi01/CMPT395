package com.cmpt395.deferralapp.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.cmpt395.deferralapp.model.*
import com.example.deferalapp.model.ChatThread
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class DeferralAppState {

    private val database = Firebase.database
    private val usersRef = database.getReference("users")

    private val _users = mutableStateListOf<User>()

    private val initialUsers = listOf(
        User("u1", "student1", "pass123", "Viraj", "Randeria", UserRole.STUDENT, "Computer Science", "**** **** **** 1234"),
        User("u2", "proctor1", "protpass", "Dr.", "Smith", UserRole.PROCTOR),
        User("u3", "admin1", "adminpass", "Admin", "Office", UserRole.ADMIN)
    )

    private val classes = listOf(
        ClassItem("c1", "COMP 101 - Intro to Programming", "u2", "Dr. Smith", "2025-12-15", "10:00 AM", "Room A101"),
        ClassItem("c2", "MATH 201 - Calculus II",        "u2", "Dr. Smith", "2025-12-18", "2:00 PM",  "Room B202"),
        ClassItem("c3", "STAT 160 - Statistics I",       "u2", "Prof. Green", "2025-12-20", "9:00 AM", "Room C303")
    )

    private val _deferrals = mutableStateListOf<DeferralRequest>()
    val deferrals: List<DeferralRequest> get() = _deferrals
    

    val chatThreads = listOf(
        ChatThread("t1", "Dr. Smith"),
        ChatThread("t2", "Admin Office"),
        ChatThread("t3", "Prof. Green")
    )

    var currentUser by mutableStateOf<User?>(null)
        private set

    init {
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val loadedUsers = mutableListOf<User>()
                for (child in snapshot.children) {
                    child.getValue(User::class.java)?.let { loadedUsers.add(it) }
                }

                if (loadedUsers.isEmpty()) {
                    initialUsers.forEach {
                        usersRef.child(it.id).setValue(it)
                    }
                } else {
                    _users.clear()
                    _users.addAll(loadedUsers)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        val student = initialUsers.first { it.id == "u1" }

        _deferrals.add(
            DeferralRequest(
                id = "d1",
                student = student,
                classItem = classes.first { it.id == "c1" },
                reason = "I have a medical appointment on the exam day.",
                status = DeferralStatus.PENDING
            )
        )

        _deferrals.add(
            DeferralRequest(
                id = "d2",
                student = student,
                classItem = classes.first { it.id == "c2" },
                reason = "I will be travelling with family.",
                status = DeferralStatus.PENDING
            )
        )

        _deferrals.add(
            DeferralRequest(
                id = "d3",
                student = student,
                classItem = classes.first { it.id == "c3" },
                reason = "Schedule conflict with another exam.",
                status = DeferralStatus.PENDING
            )
        )
    }

    fun login(role: UserRole, username: String, password: String): Boolean {
        // If users are not loaded yet, fallback to initialUsers or check _users
        // Ideally we should wait, but for now let's check both or rely on _users if loaded.
        // Since initialUsers are written to DB if empty, eventually _users will have them.
        // But for immediate login during development/testing with sync code:
        val source = if (_users.isEmpty()) initialUsers else _users
        val user = source.find { it.username == username && it.password == password && it.role == role }
        currentUser = user
        return user != null
    }

    fun logout() {
        currentUser = null
    }

    fun getStudentClasses(): List<ClassItem> = classes

    fun getClassById(id: String): ClassItem? = classes.find { it.id == id }

    fun createDeferral(classId: String, reason: String) {
        val student = currentUser ?: return
        val cls = getClassById(classId) ?: return
        _deferrals.add(
            DeferralRequest(
                id = "d${_deferrals.size + 1}",
                student = student,
                classItem = cls,
                reason = reason,
                status = DeferralStatus.PENDING
            )
        )
    }

    fun deferralsForProctor(): List<DeferralRequest> {
        val proctor = currentUser ?: return emptyList()
        return _deferrals.filter { it.classItem.proctorId == proctor.id }
    }

    fun allDeferralsForAdmin(): List<DeferralRequest> = _deferrals

    fun updateDeferralStatus(id: String, newStatus: DeferralStatus) {
        val index = _deferrals.indexOfFirst { it.id == id }
        if (index != -1) {
            _deferrals[index] = _deferrals[index].copy(status = newStatus)
        }
    }
}

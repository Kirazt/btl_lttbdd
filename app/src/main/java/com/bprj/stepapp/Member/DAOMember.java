package com.bprj.stepapp.Member;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class DAOMember
{
    private DatabaseReference databaseReference;
    public DAOMember()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(member.class.getSimpleName());
    }
    public Task<Void> add(member mem)
    {
        return databaseReference.push().setValue(mem);
    }
}

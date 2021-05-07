package com.example.teamspartans;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EncryptedDialog extends AppCompatDialogFragment {
    private EditText code;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.dialoglayout,null);
        builder.setView(view).setTitle("Encryption Code").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 String tempcode=code.getText().toString();
                 String checker="220747";
                 if(tempcode.equals(checker)){
                     Intent intent = new Intent(getActivity(),EditingActivity.class);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(getContext(),"Wrong Encryption Code",Toast.LENGTH_SHORT).show();
                 }
            }
        });
        code=view.findViewById(R.id.encryption);
                return builder.create();
    }
}

package com.haissam.whatsapp

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutMsg;
    private TextInputLayout inputLayoutTel;
    private TextInputEditText inputTextMsg;
    private TextInputEditText inputTextTel;

    private Button buttonEnviarMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLayoutMsg = findViewById(R.id.edit_layout_msg);
        inputLayoutTel = findViewById(R.id.edit_layout_tel);
        inputTextMsg = findViewById(R.id.mensagem);
        inputTextTel = findViewById(R.id.telefone);
        buttonEnviarMsg = findViewById(R.id.enviarButton);

        buttonEnviarMsg.setOnClickListener(v -> {
        EnviaMensagem();
    });


    }

    private void EnviaMensagem() {
        String mensagem = inputTextMsg.getText().toString();
        String telefone = inputTextTel.getText().toString();

        if(mensagem.isEmpty()) {
            inputLayoutMsg.setError("Insira uma mensagem");
            return;
        }

        if(mensagem.length() > 255){
            inputLayoutMsg.setError("Mensagem é muito grande");
            return;
        }

        if (telefone.length() < 11){
            inputLayoutTel.setError("O numero que você inseriu  não é um numero de telefone");
            return;
        }

        String link = "https://wa.me/" + telefone + "?text=" + mensagem;

        Uri webpage = Uri.parse(link);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        startActivity(webIntent);

    }
}
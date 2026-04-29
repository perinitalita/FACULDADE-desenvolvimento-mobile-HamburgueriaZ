package com.example.hamburgueriaz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int quantidade = 1;
    private EditText edtNome;
    private CheckBox cbBacon, cbQueijo, cbOnion;
    private TextView txtQuantidade, txtResumoPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views once
        edtNome = findViewById(R.id.edt_nome);
        cbBacon = findViewById(R.id.cb_bacon);
        cbQueijo = findViewById(R.id.cb_queijo);
        cbOnion = findViewById(R.id.cb_onion_rings);
        txtQuantidade = findViewById(R.id.txt_quantidade);
        txtResumoPreco = findViewById(R.id.txt_resumo_preco);

        findViewById(R.id.btn_mais).setOnClickListener(v -> somar());
        findViewById(R.id.btn_menos).setOnClickListener(v -> subtrair());
        findViewById(R.id.btn_enviar).setOnClickListener(v -> enviarPedido());

        cbBacon.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarResumoPreco());
        cbQueijo.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarResumoPreco());
        cbOnion.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarResumoPreco());

        atualizarResumoPreco();
    }

    private void somar() {
        if (quantidade < 10) {
            quantidade++;
            exibirQuantidade();
            atualizarResumoPreco();
        }
    }

    private void subtrair() {
        if (quantidade > 0) {
            quantidade--;
            exibirQuantidade();
            atualizarResumoPreco();
        }
    }

    @SuppressLint("DefaultLocale")
    private void atualizarResumoPreco() {
        int preco = calcularPreco(cbBacon.isChecked(), cbQueijo.isChecked(), cbOnion.isChecked());
        txtResumoPreco.setText(String.format("R$ %d", preco));
    }

    private void exibirQuantidade() {
        txtQuantidade.setText(String.valueOf(quantidade));
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void enviarPedido() {
        String nome = edtNome.getText().toString().trim();

        if (nome.isEmpty()) {
            edtNome.setError(getString(R.string.erro_nome));
            return;
        }

        boolean temBacon = cbBacon.isChecked();
        boolean temQueijo = cbQueijo.isChecked();
        boolean temOnion = cbOnion.isChecked();
        int precoFinal = calcularPreco(temBacon, temQueijo, temOnion);

        String sim = getString(R.string.sim);
        String nao = getString(R.string.nao);

        String resumo = "Nome: " + nome +
                "\nTem Bacon? " + (temBacon ? sim : nao) +
                "\nTem Queijo? " + (temQueijo ? sim : nao) +
                "\nTem Onion Rings? " + (temOnion ? sim : nao) +
                "\nQuantidade: " + quantidade +
                "\nPreço final: R$ " + precoFinal;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.assunto_email, nome));
        intent.putExtra(Intent.EXTRA_TEXT, resumo);

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            android.widget.Toast.makeText(this, R.string.erro_email, android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    private int calcularPreco(boolean temBacon, boolean temQueijo, boolean temOnion) {
        int precoBase = 20;
        if (temBacon) precoBase += 2;
        if (temQueijo) precoBase += 2;
        if (temOnion) precoBase += 3;
        return precoBase * quantidade;
    }
}
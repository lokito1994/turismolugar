package com.example.turismolugar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<nombrelugar> lugares = new ArrayList<>();
    private ArrayList<nombrelugar> lugaresFiltrados = new ArrayList<>();
    private RecyclerView recyclerView;
    private adaptadorlugar adapter;
    private TextView textViewNombreLugar;
    private TextView textViewTelefonoLugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lugares.add(new nombrelugar(
                "Aroma de Cacao\n " +
                        "Rancho Galaxi\n" +
                        "Bravo GrandHotel\n" + "Hotel Costa del Sol \n", "Alojamiento", "0985666771\n" + "0987442132\n" +"0987551139\n"));

        lugares.add(new nombrelugar("Cafe & Tinto\n"+ "Fancy Mint \n" + "Sweet Land\n" +"Toro Café\n", "Alimentación y bebidas", "987654321\n"+"098315177\n"+"09877741359\n"+"986774419\n"));
        lugares.add(new nombrelugar("Ronda Quevedeña\n"+ "Iglesia San José\n" + "Igl.San Cristobal\n" +"Igl.SantTrinidad\n", "Actractivos Culturales", "987654321\n"+"0986774112\n"+"0986584763\n"+"098325726\n"));
        lugares.add(new nombrelugar("Boho Garden\n"+ "Coco Fresh\n" + "El Container\n" +"Paradise Resto Bar\n", "Esparcimiento", "0987411113\n"+"0986663215\n"+"0986554723\n"+"0935156778\n"));
        lugares.add(new nombrelugar("Boho Garden\n"+ "Coco Fresh\n" + "El Container\n" +"Paradise Resto Bar\n", "Compras", "0978895213\n"+"0986663215\n"+"0986554723\n"+"0935156778\n"));

        adapter = new adaptadorlugar(lugaresFiltrados);

        Spinner spinner = findViewById(R.id.spinner);
        String[] nombresCategorias = obtenerNombresCategorias();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresCategorias);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada = parent.getItemAtPosition(position).toString();
                filtrarLugaresPorCategoria(categoriaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        textViewNombreLugar = findViewById(R.id.textViewNombreLugar);
        textViewTelefonoLugar = findViewById(R.id.textViewTelefonoLugar);
    }

    private String[] obtenerNombresCategorias() {
        return new String[]{"Escoja la categoría", "Alojamiento", "Alimentación y bebidas", "Actractivos Culturales", "Esparcimientos", "Compras", "Transportes", "Rutas", "Agencias de Viajes"};
    }

    private void filtrarLugaresPorCategoria(String categoria) {
        lugaresFiltrados.clear();
        if (categoria.equals("Escoja la categoría")) {
            lugaresFiltrados.addAll(lugares);
            limpiarTextViews();
        } else {
            for (nombrelugar nombrelugar : lugares) {
                if (nombrelugar.getCategoria().equals(categoria)) {
                    lugaresFiltrados.add(nombrelugar);
                }
            }
            mostrarInformacionLugarSeleccionado();
        }
        adapter.notifyDataSetChanged();
    }

    private void limpiarTextViews() {
        textViewNombreLugar.setText("");
        textViewTelefonoLugar.setText("");
    }

    private void mostrarInformacionLugarSeleccionado() {
        if (!lugaresFiltrados.isEmpty()) {
            nombrelugar nombrelugarSeleccionado = lugaresFiltrados.get(0);
            textViewNombreLugar.setText("Nombre:\n" + nombrelugarSeleccionado.getNombre());
            textViewTelefonoLugar.setText("Teléfono\n" + nombrelugarSeleccionado.getTelefono());
        }
    }
}

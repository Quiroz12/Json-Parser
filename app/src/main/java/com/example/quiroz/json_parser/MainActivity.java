package com.example.quiroz.json_parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {
    private Context context;
    private static String url = "http://resources.260mb.net/prestamos.json";


    static final String KEY_CLAVE_PRESTAMO = "clave_prestamo";
    static final String KEY_FECHA = "fecha";
    static final String KEY_NOMBRE_SOL = "nombre_sol";
    static final String KEY_AREA_SOL = "area_sol";
    static final String KEY_DESC = "descripcion";
    static final String KEY_REC = "recibido";
    static final String KEY_ENT = "entregado";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /** application context. */
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item,new String[] { KEY_CLAVE_PRESTAMO, KEY_NOMBRE_SOL, KEY_FECHA, KEY_AREA_SOL, KEY_DESC , KEY_REC, KEY_ENT}, new int[] {
                    R.id.id_v_prestamo, R.id.v_nombre,R.id.v_fecha, R.id.v_area_sol, R.id.v_descripcion, R.id.v_recibido, R.id.v_entregado});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // getting values from selected ListItem
                    String tx_calvepres = ((TextView) view.findViewById(R.id.id_v_prestamo)).getText().toString();
                    String tx_nom = ((TextView) view.findViewById(R.id.v_nombre)).getText().toString();
                    String txt_fe=((TextView) view.findViewById(R.id.v_fecha)).getText().toString();
                    String txtarea=((TextView) view.findViewById(R.id.v_area_sol)).getText().toString();
                    String txt_des=((TextView) view.findViewById(R.id.v_descripcion)).getText().toString();
                    String txt_rec=((TextView) view.findViewById(R.id.v_recibido)).getText().toString();
                    String txt_ent=((TextView) view.findViewById(R.id.v_entregado)).getText().toString();


                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), vista_indiv.class);
                    in.putExtra(KEY_CLAVE_PRESTAMO, tx_calvepres);
                    in.putExtra(KEY_NOMBRE_SOL, tx_nom);
                    in.putExtra(KEY_FECHA, txt_fe);
                    in.putExtra(KEY_AREA_SOL, txtarea);
                    in.putExtra(KEY_DESC, txt_des);
                    in.putExtra(KEY_REC, txt_rec);
                    in.putExtra(KEY_ENT, txt_ent);


                    startActivity(in);

                }
            });

        }


                protected Boolean doInBackground(final String... args) {

                    JSONParser jParser = new JSONParser();

                    // getting JSON string from URL
                    JSONArray json = jParser.getJSONFromUrl(url);

                    for (int i = 0; i < json.length(); i++) {

                        try {
                            JSONObject c = json.getJSONObject(i);
                            String cv_prest = c.getString(KEY_CLAVE_PRESTAMO);

                            String nom_sol = c.getString(KEY_NOMBRE_SOL);
                            String fecha = c.getString(KEY_FECHA);
                            String area_sol = c.getString(KEY_AREA_SOL);
                            String descr = c.getString(KEY_DESC);
                            String rec = c.getString(KEY_REC);
                            String ent = c.getString(KEY_ENT);


                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(KEY_CLAVE_PRESTAMO, cv_prest);
                            map.put(KEY_NOMBRE_SOL, nom_sol);
                            map.put(KEY_FECHA, fecha);
                            map.put(KEY_AREA_SOL, area_sol);
                            map.put(KEY_DESC, descr);
                            map.put(KEY_REC, rec);
                            map.put(KEY_ENT, ent);
                            jsonlist.add(map);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    return null;

                }

            }
        }










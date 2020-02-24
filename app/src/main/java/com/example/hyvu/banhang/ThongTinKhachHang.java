package com.example.hyvu.banhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hyvu.banhang.util.CheckConnection;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {
    EditText edt_name,edt_sdt,edt_diachi,edt_email;
    Button btn_Xacnhan,btn_TroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        Mapping();
        if(new CheckConnection().haveConnection(getApplicationContext())){
            ButtonXacNhan();
        }
        else{
            new CheckConnection().ToastConnection(getApplicationContext(),"Kiểm tra kết nối internet");
        }
    }

    private void ButtonXacNhan() {
        btn_Xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten=edt_name.getText().toString();
                final String diachi=edt_diachi.getText().toString();
                final String sdt=edt_sdt.getText().toString();
                final String email=edt_email.getText().toString();
                RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                StringRequest request=new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.length()>0){

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("tenkhachhang",ten);
                        params.put("sodienthoai",sdt);
                        params.put("diachi",diachi);
                        params.put("email",email);
                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });
    }

    private void Mapping() {
        edt_name=findViewById(R.id.id_thongtinkhachhang_edt_tenkh);
        edt_diachi=findViewById(R.id.id_thongtinkhachhang_edt_diachi);
        edt_email=findViewById(R.id.id_thongtinkhachhang_edt_email);
        edt_sdt=findViewById(R.id.id_thongtinkhachhang_edt_sdt);
        btn_TroVe=findViewById(R.id.id_thongtinkhachhang_btn_trove);
        btn_Xacnhan=findViewById(R.id.id_thongtinkhachhang_btn_xacnhan);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

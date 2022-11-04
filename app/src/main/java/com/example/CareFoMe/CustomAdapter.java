package com.example.CareFoMe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ListViewHolder> {
   Bundle bundle;

    Context context;
    List<UserData> dataList = new ArrayList<>();
    LayoutInflater inflater;
    Intent apt;
    String username,email;
    private static long idCounter = 0;
    DBHelper dbHelper;
    public static String mail ;
    public CustomAdapter(Context context, List dataList1,String u,String e) {

        this.context = context;
        this.dataList = dataList1;
        inflater = LayoutInflater.from(context);
        username=u;
        email=e;




    }


    public static synchronized String createID()
    {
        return String.valueOf(idCounter++);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.linear_layout, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {


        final String doc_name = dataList.get(position).name;
        holder.iv_book.setTag(position);
        holder.iv_whitefav.setTag(position);
        holder.tv_name.setText(doc_name);
        holder.tv_spec.setText(dataList.get(position).speciality);
       //bundle.putString("Name",dataList.get(position).name);

        holder.iv_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apt = new Intent(context,Appointment.class);
                apt.putExtra("username",username);
                apt.putExtra("Email",email);
                apt.putExtra("Doc Name",doc_name);


mail=email;

                context.startActivity(apt);

            }
        });



        holder.iv_whitefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String[]> returnList = new ArrayList();

                for (int i =1; i < returnList.size(); i++)
                {

                    DoctorData Doc = new DoctorData();
                    Doc.id=Integer.parseInt(returnList.get(i)[0]);
                    Doc.name= doc_name;
                    Doc.email=mail;
                    dbHelper.insertFavDocDetail(Doc);
                }


                apt = new Intent(context,MainActivity.class);

                apt.putExtra("username",username);
                apt.putExtra("Email",email);
                apt.putExtra("Doc Name",doc_name);





                context.startActivity(apt);





            }
        });



    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_spec;
        ImageView iv_book;
        ImageView iv_whitefav;

        public ListViewHolder(View itemView) {
            super(itemView);

            context = (Context)  itemView.getContext();
            tv_name = (TextView) itemView.findViewById(R.id.textViewTV);
            tv_spec = (TextView) itemView.findViewById(R.id.textViewSpec);
            iv_book= (ImageView) itemView.findViewById(R.id.imageViewbook);
            iv_whitefav= (ImageView) itemView.findViewById(R.id.imageViewWhiteFav);
        }
    }


}

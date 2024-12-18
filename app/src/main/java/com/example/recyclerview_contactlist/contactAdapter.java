package com.example.recyclerview_contactlist;

import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ContactviewHolder> {
    private ArrayList<String> contacts= new ArrayList<>();
    private static final String TAG= "ContactAdapter";
    private ItemEventListener itemEventListener;
    public contactAdapter(ItemEventListener itemEventListener){
        this.itemEventListener=itemEventListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("Felipe Bradtke");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Mahshid Shirazi");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");
    }
    public void addNewContact(String fullName){
        contacts.add(0,fullName);
        notifyItemInserted(0);

    }
    public void updateContact(String fullName, int position){
        contacts.set(position,fullName);
        notifyItemChanged(position);
    }
    @NonNull
    @Override
    public ContactviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_items,parent,false);
        return new ContactviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactviewHolder holder, int position) {
          holder.bindContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactviewHolder extends RecyclerView.ViewHolder{
        private TextView tv_character;
        private TextView tv_fullName;

        public ContactviewHolder(@NonNull View itemView) {
            super(itemView);
            tv_character=itemView.findViewById(R.id.tv_contact_first_character);
            tv_fullName=itemView.findViewById(R.id.tv_contact_fullName);
        }
        public void bindContact (String fullName){
            tv_fullName.setText(fullName);
            tv_character.setText(fullName.substring(0,1));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemEventListener.onItemClick(fullName, getBindingAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contacts.remove(getAbsoluteAdapterPosition());
                    notifyItemRemoved(getBindingAdapterPosition());
                    return false;
                }
            });
        }

    }
    public interface ItemEventListener{
         void onItemClick(String fullName, int position);
    }

}

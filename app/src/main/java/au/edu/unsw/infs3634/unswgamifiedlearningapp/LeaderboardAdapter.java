package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>  {
    private List<User> users;
    private UserListener mUserListener;


    public LeaderboardAdapter(List<User> users) {
        this.users = users;
        //this.mUserListener = listener;
    }

    public interface UserListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_leaderboard, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
        User user = users.get(position);

        String name = user.getFirstName();
        System.out.println("username is " + name);
        String level = String.valueOf(user.getLevel());

        holder.txtName.setText(name);
        holder.txtLevel.setText("Level " + level);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        UserListener userListener;
        TextView txtRank;
        TextView txtName;
        TextView txtLevel;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            this.userListener = userListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            userListener.onClick(getAdapterPosition());
        }
    }

    public void setUser(List<User> data) {
        users.clear();
        users.addAll(data);
        notifyDataSetChanged();
    }

}

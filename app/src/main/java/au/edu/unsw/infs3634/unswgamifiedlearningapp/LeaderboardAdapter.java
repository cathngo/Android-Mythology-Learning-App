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
import java.util.Collections;
import java.util.Comparator;
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

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String name = firstName + " " + lastName;
        System.out.println("username is " + name);
        String level = String.valueOf(user.getLevel());

        holder.txtName.setText(name);
        holder.txtLevel.setText("Level " + level+".");

        holder.txtRank.setText(String.valueOf(position + 1));


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
            txtRank = itemView.findViewById(R.id.txtRank);
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

    public void sortByRank() {
        if (users.size() > 0) {
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o2.getLevel(), o1.getLevel());
                }
            });
        }
        notifyDataSetChanged();
    }

}

package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

//Reference for adapter: Week 4 Tutorial Covid Tracker: https://github.com/INFS3634/Covid19Tracker
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>  {
    private List<User> users;

    public LeaderboardAdapter(List<User> users) {
        this.users = users;
    }

    public interface UserListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_leaderboard, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
        //Set the views with the respective user's information including their name, level
        //username, rank.
        User user = users.get(position);
        //only display top 5
        if (position < 5) {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String name = firstName + " " + lastName;
            System.out.println("username is " + name);
            String level = String.valueOf(user.getLevel());
            String username = user.getUsername();
            holder.txtName.setText(name);
            holder.txtLevel.setText("Level " + level);
            holder.txtUsername.setText(username);
            holder.txtRank.setText(String.valueOf(position + 1 + "."));

        } else {
            holder.txtName.setText("");
            holder.txtLevel.setText("");
            holder.txtUsername.setText("");
            holder.txtRank.setText("");
            holder.imgProfile.setImageDrawable(null);
        }

    }

    //Return size of the users list
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Initialise UI elements
        UserListener userListener;
        TextView txtRank;
        TextView txtName;
        TextView txtLevel;
        TextView txtUsername;
        ImageView imgProfile;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //Find the UI elements id
            txtName = itemView.findViewById(R.id.txtName);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            txtRank = itemView.findViewById(R.id.txtRank);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            imgProfile = itemView.findViewById(R.id.imgProfile);
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

    //Method that sorts the user based on their current level
    public void sortByRank() {
        //Check if the user list is empty
        if (users.size() > 0) {
            //Sort users by their level
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

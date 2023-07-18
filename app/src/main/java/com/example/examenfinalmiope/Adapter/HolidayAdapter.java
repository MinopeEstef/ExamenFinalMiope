package com.example.examenfinalmiope.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalmiope.Entity.Holiday;
import com.example.examenfinalmiope.R;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {
    private List<Holiday> holidayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;
        public TextView tvName;
        public TextView tvCountry;

        public ViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvName = view.findViewById(R.id.tvName);
            tvCountry = view.findViewById(R.id.tvCountry);
        }
    }

    public HolidayAdapter(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holiday, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Holiday holiday = holidayList.get(position);
        holder.tvDate.setText(holiday.getDate());
        holder.tvName.setText(holiday.getLocalName());
        holder.tvCountry.setText(holiday.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }
}


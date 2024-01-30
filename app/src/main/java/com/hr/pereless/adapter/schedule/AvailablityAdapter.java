package com.hr.pereless.adapter.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.hr.pereless.R;
import com.hr.pereless.activities.schedule.CreateScheduleActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.fragment.scheduleNew.AvailabilityFragment;
import com.hr.pereless.model.schedule.AvailabilityModel;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class AvailablityAdapter extends SectioningAdapter {

    static final String TAG = AvailablityAdapter.class.getSimpleName();
    static final boolean USE_DEBUG_APPEARANCE = false;
    Context _context ;

    private class Section {
        int index;
        int copyCount;
        int allDate;
        String header;
        String footer;
        ArrayList<String> items = new ArrayList<>();
        public Section duplicate() {
            Section c = new Section();
            c.index = this.index;
            c.copyCount = this.copyCount + 1;
            c.header = c.index + " copy " + c.copyCount;
            c.footer = this.footer;
            for (String i : this.items) {
                c.items.add(i + " copy " + c.copyCount);
            }

            return c;
        }

        public void duplicateItem(int item) {
            String itemCopy = items.get(item) + " copy";
            items.add(item + 1, itemCopy);
        }

    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        TextView edt_starttime,edt_endtime;
        ImageView txv_delete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            edt_starttime = itemView.findViewById(R.id.edt_starttime);
            edt_endtime = itemView.findViewById(R.id.edt_endtime);
            txv_delete = itemView.findViewById(R.id.txv_delete);
            edt_starttime.setOnClickListener(this);
            edt_endtime.setOnClickListener(this);
            txv_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            final int section = AvailablityAdapter.this.getSectionForAdapterPosition(adapterPosition);
            final int item = AvailablityAdapter.this.getPositionOfItemInSection(section, adapterPosition);

            switch (v.getId()){
                case R.id.edt_starttime:
                    Calendar now = Calendar.getInstance();
                    TimePickerDialog tpd = TimePickerDialog.newInstance(
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                    _roomDatas.get(preArrayCounts(section) + item).setShiftfrom(String.valueOf(hourOfDay * 3600 + minute*60));
                                    initData();
                                }
                            },
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            false
                    );
                    tpd.setAccentColor("#1461b9");
                    // If you're calling this from a support Fragment
                    tpd.show(availabilityFragment.getChildFragmentManager(), "Datepickerdialog");

                    break;
                case R.id.edt_endtime:
                    now = Calendar.getInstance();
                    tpd = TimePickerDialog.newInstance(
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                    _roomDatas.get(preArrayCounts(section) + item).setShiftto(String.valueOf(hourOfDay * 3600 + minute*60));
                                    initData();
                                }
                            },
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            false
                    );
                    tpd.setAccentColor("#1461b9");
                    // If you're calling this from a support Fragment
                    tpd.show(availabilityFragment.getChildFragmentManager(), "Datepickerdialog");

                    break;
                case R.id.txv_delete:
                    AvailabilityModel availabilityModel = _roomDatas.get(preArrayCounts(section) + item);
                    if(availabilityModel.getSch_id().length()>0){
                        deleteIds += availabilityModel.getSch_id() + ",";
                    }
                    _roomDatas.remove(preArrayCounts(section) + item);
                    initData();
                    break;
            }
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder  {
        TextView txt_availability;
        ImageView add_item;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            txt_availability =  itemView.findViewById(R.id.txt_availability);
            add_item = itemView.findViewById(R.id.add_item);

        }


    }

    public class FooterViewHolder extends SectioningAdapter.FooterViewHolder {
        TextView textView;
        TextView adapterPositionTextView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            adapterPositionTextView = (TextView) itemView.findViewById(R.id.adapterPositionTextView);

            if (!AvailablityAdapter.this.showAdapterPositions) {
                adapterPositionTextView.setVisibility(View.GONE);
            }
        }
    }


    ArrayList<Section> sections = new ArrayList<>();
    boolean showModificationControls;
    boolean showCollapsingSectionControls;
    boolean showAdapterPositions;
    boolean hasFooters;
    public ArrayList<AvailabilityModel> _roomDatas = new ArrayList<>();
    public String deleteIds = "";

    AvailabilityFragment availabilityFragment;
    public AvailablityAdapter(AvailabilityFragment availabilityFragment , Context context, boolean hasFooters, boolean showModificationControls, boolean showCollapsingSectionControls, boolean showAdapterPositions, ArrayList<AvailabilityModel> data) {
        this.availabilityFragment = availabilityFragment;
        this._context = context;
        this.showModificationControls = showModificationControls;
        this.showCollapsingSectionControls = showCollapsingSectionControls;
        this.showAdapterPositions = showAdapterPositions;
        this.hasFooters = hasFooters;
        _roomDatas = data;
        initData();
    }
    void initData(){
        Collections.sort(_roomDatas, new Comparator<AvailabilityModel>() {
            public int compare(AvailabilityModel o1, AvailabilityModel o2) {
                return o1.getAllDate().compareTo(o2.getAllDate());
            }
        });
        sections.clear();
        int index = 0 ,allDate = -1 , count = 0;
        for(int i =0;i<_roomDatas.size();i++){
            AvailabilityModel availabilityModel =_roomDatas.get(i);
            if(Integer.parseInt(availabilityModel.getAllDate()) != allDate){
                if(count >0){
                    appendSection(index, allDate , count);
                    index++;
                    count = 0;
                }
                allDate = Integer.parseInt(availabilityModel.getAllDate());

            }
            count ++;
        }
        if(count >0){
            appendSection(index, allDate , count);
        }
        notifyAllSectionsDataSetChanged();

        notifyDataSetChanged();
    }


    void appendSection(int index, int  alldate,int itemCount) {
        Section section = new Section();
        section.index = index;
        section.copyCount = 0;
        section.header = "test";
        section.allDate = alldate;
        for (int j = 0; j < itemCount; j++) {
            section.items.add(index + "/" + j);
        }
        sections.add(section);
    }


    @Override
    public int getNumberOfSections() {
        return sections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return sections.get(sectionIndex).items.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return !TextUtils.isEmpty(sections.get(sectionIndex).header);
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return !TextUtils.isEmpty(sections.get(sectionIndex).footer);
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_availability_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int footerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_footer, parent, false);
        return new FooterViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        Section s = sections.get(sectionIndex);
        int itemPosstion = preArrayCounts(sectionIndex);

        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        itemPosstion += itemIndex;
        AvailabilityModel availabilityModel = _roomDatas.get(itemPosstion);
        ivh.edt_starttime.setText(Commons.HoursFromSecond(Integer.parseInt( availabilityModel.getShiftfrom())));
        ivh.edt_endtime.setText(Commons.HoursFromSecond(Integer.parseInt( availabilityModel.getShiftto())));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        Section s = sections.get(sectionIndex);
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(_context.getResources().getStringArray(R.array.days)));
        hvh.txt_availability.setText(arrayList.get(s.allDate));
        hvh.txt_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
                selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        hvh.txt_availability.setText(arrayList.get(selectPosstion));
                        changeAvailability(sectionIndex,selectPosstion);
                    }
                },arrayList,1);
                selectJobOneDialog.show(availabilityFragment.getChildFragmentManager(), "DeleteMessage");
            }
        });
        hvh.add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addModel(s.allDate);
            }
        });
    }
    public void addModel(int day){
        AvailabilityModel availabilityModel = new AvailabilityModel();
        availabilityModel.setShiftfrom("64800");
        availabilityModel.setShiftto("68400");
        availabilityModel.setAllDate(String.valueOf(day));
        _roomDatas.add(availabilityModel);
        initData();
    }

    int preArrayCounts(int sectionIndex){
        int itemPosstion = 0;
        for(int i = 0 ;i<sectionIndex ; i ++){
            itemPosstion += sections.get(i).items.size();
        }
        return itemPosstion;
    }

    void changeAvailability(int sectionIndex,int selectPosstion){
        Section s = sections.get(sectionIndex);
        int counts = preArrayCounts(sectionIndex);

        ArrayList<AvailabilityModel> data = new ArrayList<>();
        for(int i =0 ;i<s.items.size();i++){
            int index = counts + i;
            _roomDatas.get(index).setAllDate(String.valueOf(selectPosstion));
        }
        initData();
    }

    @Override
    public void onBindGhostHeaderViewHolder(SectioningAdapter.GhostHeaderViewHolder viewHolder, int sectionIndex) {
        if (USE_DEBUG_APPEARANCE) {
            viewHolder.itemView.setBackgroundColor(0xFF9999FF);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindFooterViewHolder(SectioningAdapter.FooterViewHolder viewHolder, int sectionIndex, int footerType) {
//        Section s = sections.get(sectionIndex);
//        FooterViewHolder fvh = (FooterViewHolder) viewHolder;
//        fvh.textView.setText(s.footer);
//        fvh.adapterPositionTextView.setText(Integer.toString(getAdapterPositionForSectionFooter(sectionIndex)));
    }


}
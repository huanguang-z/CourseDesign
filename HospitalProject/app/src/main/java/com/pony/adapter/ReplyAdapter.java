package com.pony.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.community.R;
import com.pony.config.Consts;
import com.pony.model.ReviewBean;
import com.pony.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * 评论适配器
 */
public class ReplyAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReviewBean> list_result;

    public ReplyAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public ReplyAdapter(Context mContext, List<ReviewBean> list_msg) {
        super();
        this.mContext = mContext;
        this.list_result = list_msg;
        notifyDataSetChanged();
    }

    public List<ReviewBean> setData(List<ReviewBean> list_str) {
        return list_result = list_str;

    }

    @Override
    public int getCount() {
        return list_result.size();
    }

    @Override
    public Object getItem(int position) {
        return list_result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_reply, null);
            vh = new ViewHolder();
            vh.message_name = (TextView) convertView.findViewById(R.id.message_name);
            vh.message_state = (TextView) convertView.findViewById(R.id.message_state);
            vh.messageContent = (TextView) convertView.findViewById(R.id.messageContent);
            vh.mtvReplyContent = (TextView) convertView.findViewById(R.id.mtvReplyContent);
            vh.mtvContent = (TextView) convertView.findViewById(R.id.mtvContent);
            vh.musicImage = (CircleImageView) convertView.findViewById(R.id.musicImage);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.message_state.setText(list_result.get(position).getReviewTime());
        vh.mtvContent.setText(list_result.get(position).getReviewContent());
        vh.message_name.setText(list_result.get(position).getReviewUserName());


        if (!TextUtils.isEmpty(list_result.get(position).getUserImage())) {
            Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position).getUserImage()).placeholder(R.drawable.default_drawable_show_pictrue).into(vh.musicImage);
        }

        return convertView;
    }

    class ViewHolder {
        private CircleImageView musicImage;
        TextView message_name;
        TextView message_state;
        TextView messageContent;
        TextView mtvContent;
        TextView mtvReplyContent;
    }
}
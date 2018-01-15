package com.dryseed.dryseedapp.widget.dragPhotoView;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageBean implements Parcelable {
    int top;
    int left;
    int width;
    int height;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.top);
        dest.writeInt(this.left);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.top = in.readInt();
        this.left = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}

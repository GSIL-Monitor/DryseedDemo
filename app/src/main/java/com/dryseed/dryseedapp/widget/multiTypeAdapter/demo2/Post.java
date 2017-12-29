/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dryseed.dryseedapp.widget.multiTypeAdapter.demo2;

import android.support.annotation.NonNull;

import com.dryseed.dryseedapp.widget.multiTypeAdapter.demo.Bean;
import com.dryseed.dryseedapp.widget.suspensionRecyclerView.ISuspensionInterface;

/**
 * @author drakeet
 */
public class Post extends Bean implements ISuspensionInterface{

    public int coverResId;
    public
    @NonNull
    String title;


    public Post(int coverResId, @NonNull final String title) {
        this.coverResId = coverResId;
        this.title = title;
    }

    public int getCoverResId() {
        return coverResId;
    }

    public void setCoverResId(int coverResId) {
        this.coverResId = coverResId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }

    @Override
    public String getSuspensionTag() {
        return this.title;
    }
}

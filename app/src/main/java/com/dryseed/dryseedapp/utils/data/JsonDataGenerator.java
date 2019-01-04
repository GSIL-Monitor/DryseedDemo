package com.dryseed.dryseedapp.utils.data;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Category;
import com.dryseed.dryseedapp.widget.recyclerView.multiTypeAdapter.bean.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class JsonDataGenerator {
    private static final String PREFIX = "这是一条长长的达到两行的标题文字";

    public static List generateListData() {
        Post post00 = new Post(R.drawable.img_00, PREFIX + "post00");
        Post post01 = new Post(R.drawable.img_01, PREFIX + "post01");
        Post post02 = new Post(R.drawable.img_10, PREFIX + "post02");
        Post post03 = new Post(R.drawable.img_11, PREFIX + "post03");

        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            post00.setTitle("post00" + "-" + i);
            post01.setTitle("post01" + "-" + i);
            post02.setTitle("post02" + "-" + i);
            post03.setTitle("post03" + "-" + i);
            postList.add(post00);
            postList.add(post01);
            postList.add(post02);
            postList.add(post03);
        }

        return postList;
    }

    public static List generateListDataWithTitle() {
        Post post0 = new Post(R.drawable.img_00, PREFIX + "post00");
        Post post1 = new Post(R.drawable.img_01, PREFIX + "post02");
        Post post2 = new Post(R.drawable.img_10, PREFIX + "post03");
        Post post3 = new Post(R.drawable.img_11, PREFIX + "post04");
        Category category0 = new Category("title0");

        List list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            post0.setTitle("post00" + "-" + i);
            post1.setTitle("post01" + "-" + i);
            post2.setTitle("post02" + "-" + i);
            post3.setTitle("post03" + "-" + i);
            list.add(category0);
            list.add(post0);
            list.add(post1);
            list.add(post2);
            list.add(post3);
        }

        return list;
    }
}

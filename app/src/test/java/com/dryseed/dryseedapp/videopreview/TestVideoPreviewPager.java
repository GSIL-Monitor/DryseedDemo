package com.dryseed.dryseedapp.videopreview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class TestVideoPreviewPager {
    @Test
    public void test() throws Exception {
        VideoPreviewPager videoPreviewPager = new VideoPreviewPager();

        videoPreviewPager.pushData(generateData());
        videoPreviewPager.setOriginIndex(22);

        List<AbsRowModel> list1 = videoPreviewPager.getNextPageData();
        printInfo("getNextPageData", list1, videoPreviewPager);

        List<AbsRowModel> list2 = videoPreviewPager.getPrePageData();
        printInfo("getPrePageData", list2, videoPreviewPager);
        List<AbsRowModel> list3 = videoPreviewPager.getPrePageData();
        printInfo("getPrePageData", list3, videoPreviewPager);

    }

    private static void printInfo(String TAG, List<AbsRowModel> list, VideoPreviewPager videoPreviewPager) {
        if (list == null || list.isEmpty()) {
            System.out.println(TAG + " null");
        } else {
            System.out.println(String.format(TAG + " : [firstData:%d][lastData:%d][firstIndex:%d][lastIndex:%d] ",
                    list.get(0).id,
                    list.get(list.size() - 1).id,
                    videoPreviewPager.getFirstIndex(),
                    videoPreviewPager.getLastIndex()));
        }
    }

    private static List<AbsRowModel> generateData() {
        ArrayList<AbsRowModel> list = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            AbsRowModel absRowModel = new AbsRowModel(i);
            list.add(absRowModel);
        }
        return list;
    }
}

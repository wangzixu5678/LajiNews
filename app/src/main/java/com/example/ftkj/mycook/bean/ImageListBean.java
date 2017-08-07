package com.example.ftkj.mycook.bean;

import java.util.List;

/**
 * Created by FTKJ on 2017/6/5.
 */

public class ImageListBean {

    /**
     * status : true
     * total : 65
     * tngou : [{"count":3321,"fcount":0,"galleryclass":2,"id":874,"img":"/ext/160807/2a6c28e45afbd966be231d40d899b79c.jpg","rcount":0,"size":15,"time":1470570564000,"title":"高挑美女模特Kaylar紧身包裙诱人性感美腿"},{"count":4967,"fcount":0,"galleryclass":2,"id":873,"img":"/ext/160807/5132c502b51e14b981207bc366051997.jpg","rcount":0,"size":9,"time":1470570496000,"title":"性感护士美女脱丝袜制服魅惑"},{"count":8499,"fcount":0,"galleryclass":2,"id":841,"img":"/ext/160801/e50cbb3175079e3a211af14e7feb63cf.jpg","rcount":0,"size":17,"time":1470058009000,"title":"腿模林瑞瑜sara粉色可爱女仆裙性感"},{"count":6587,"fcount":0,"galleryclass":2,"id":837,"img":"/ext/160801/71eba4c05fd6bc8779c4a4267ad7b831.jpg","rcount":0,"size":10,"time":1470057769000,"title":"马来西亚槟城之花韩小涩 翁依玲图片"},{"count":4520,"fcount":0,"galleryclass":2,"id":829,"img":"/ext/160729/d20a5cd187567885234986dd2f4743d9.jpg","rcount":0,"size":9,"time":1469799074000,"title":"Panadol雅蓝色制服高跟美腿"}]
     */

    private boolean status;
    private int total;
    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        /**
         * count : 3321
         * fcount : 0
         * galleryclass : 2
         * id : 874
         * img : /ext/160807/2a6c28e45afbd966be231d40d899b79c.jpg
         * rcount : 0
         * size : 15
         * time : 1470570564000
         * title : 高挑美女模特Kaylar紧身包裙诱人性感美腿
         */

        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

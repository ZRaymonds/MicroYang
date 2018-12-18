package com.app.microyang.bean;

import java.util.List;

public class MatchBean {


    /**
     * code : 200
     * data : {"page_num":1,"page_size":20,"size":2,"total":2,"pages":1,"list":[{"id":2,"user_id":11,"theme":"app设计大赛","content":"发生的合法的身份是否会但是客户发的顺丰","organizer":"学生会"},{"id":1,"user_id":2,"theme":"吃不吃","content":"扔个如果葛瑞格如果隔热隔热广东省韶关市有二太太威特沃特","organizer":"吃吧吃吧"}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page_num : 1
         * page_size : 20
         * size : 2
         * total : 2
         * pages : 1
         * list : [{"id":2,"user_id":11,"theme":"app设计大赛","content":"发生的合法的身份是否会但是客户发的顺丰","organizer":"学生会"},{"id":1,"user_id":2,"theme":"吃不吃","content":"扔个如果葛瑞格如果隔热隔热广东省韶关市有二太太威特沃特","organizer":"吃吧吃吧"}]
         */

        private int page_num;
        private int page_size;
        private int size;
        private int total;
        private int pages;
        private List<ListBean> list;

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * user_id : 11
             * theme : app设计大赛
             * content : 发生的合法的身份是否会但是客户发的顺丰
             * organizer : 学生会
             */

            private int id;
            private int user_id;
            private String theme;
            private String content;
            private String organizer;

            public ListBean(String theme, String organizer) {
                this.theme = theme;
                this.organizer = organizer;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOrganizer() {
                return organizer;
            }

            public void setOrganizer(String organizer) {
                this.organizer = organizer;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", user_id=" + user_id +
                        ", theme='" + theme + '\'' +
                        ", content='" + content + '\'' +
                        ", organizer='" + organizer + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "page_num=" + page_num +
                    ", page_size=" + page_size +
                    ", size=" + size +
                    ", total=" + total +
                    ", pages=" + pages +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}

package com.app.microyang.bean;

public class UserBean {


    /**
     * msg : 登录成功
     * code : 200
     * data : {"id":5,"username":"user3","status":{"value":1,"desc":"学生","role":"guest"},"studentid":201630621112,"sex":"男","school":"阳职","systemtype":"信工","classes":"1班"}
     * authc_token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1Iiwic3ViIjoidXNlcjMiLCJleHAiOjE1NDM4MDM1NDIsInJvbGVzIjoiZ3Vlc3QiLCJpYXQiOjE1NDM4MDE3NDJ9.ENOtJiVIjyae1dgmdE9aWV6Me1aDV7UwIuVLQmD-IJc
     */

    private String msg;
    private int code;
    private DataBean data;
    private String authc_token;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public String getAuthc_token() {
        return authc_token;
    }

    public void setAuthc_token(String authc_token) {
        this.authc_token = authc_token;
    }

    public static class DataBean {
        /**
         * id : 5
         * username : user3
         * status : {"value":1,"desc":"学生","role":"guest"}
         * studentid : 201630621112
         * sex : 男
         * school : 阳职
         * systemtype : 信工
         * classes : 1班
         */

        private int id;
        private String username;
        private StatusBean status;
        private long studentid;
        private String sex;
        private String school;
        private String systemtype;
        private String classes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public long getStudentid() {
            return studentid;
        }

        public void setStudentid(long studentid) {
            this.studentid = studentid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getSystemtype() {
            return systemtype;
        }

        public void setSystemtype(String systemtype) {
            this.systemtype = systemtype;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public static class StatusBean {
            /**
             * value : 1
             * desc : 学生
             * role : guest
             */

            private int value;
            private String desc;
            private String role;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }
    }
}

package com.app.microyang.bean;

public class UserBean {


    /**
     * msg : {"id":4,"username":"zraymond","status":"1","studentid":2018,"sex":"男","school":"阳江职业技术学院","systemtype":"信息工程系","classes":"安卓班"}
     * code : 200
     * status : 登陆成功
     */

    private MsgBean msg;
    private int code;
    private String status;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MsgBean {
        /**
         * id : 4
         * username : zraymond
         * status : 1
         * studentid : 2018
         * sex : 男
         * school : 阳江职业技术学院
         * systemtype : 信息工程系
         * classes : 安卓班
         */

        private int id;
        private String username;
        private String status;
        private int studentid;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
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

        @Override
        public String toString() {
            return "MsgBean{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", status='" + status + '\'' +
                    ", studentid=" + studentid +
                    ", sex='" + sex + '\'' +
                    ", school='" + school + '\'' +
                    ", systemtype='" + systemtype + '\'' +
                    ", classes='" + classes + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "msg=" + msg +
                ", code=" + code +
                ", status='" + status + '\'' +
                '}';
    }
}

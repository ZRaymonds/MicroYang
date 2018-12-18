package com.app.microyang.bean;

public class UserBean {


    /**
     * msg : 登录成功
     * code : 200
     * data : {"id":1,"username":"admin","status":{"value":1,"desc":"学生","role":"guest"},"student_id":201630621263,"sex":"男","school":"阳江职业技术学院","system_type":"信息工程系","classes":"计算机应用技术2班"}
     * authc_token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJleHAiOjE1NDQ2NzEyNjYsInJvbGVzIjoiZ3Vlc3QiLCJpYXQiOjE1NDQ2Njk0NjZ9.SapRZVv1FjO0qNPMkATdnHme6xyoBQ2Hd0hkDGCmCyE
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
         * id : 1
         * username : admin
         * status : {"value":1,"desc":"学生","role":"guest"}
         * student_id : 201630621263
         * sex : 男
         * school : 阳江职业技术学院
         * system_type : 信息工程系
         * classes : 计算机应用技术2班
         */

        private int id;
        private String username;
        private StatusBean status;
        private long student_id;
        private String sex;
        private String school;
        private String system_type;
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

        public long getStudent_id() {
            return student_id;
        }

        public void setStudent_id(long student_id) {
            this.student_id = student_id;
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

        public String getSystem_type() {
            return system_type;
        }

        public void setSystem_type(String system_type) {
            this.system_type = system_type;
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

            @Override
            public String toString() {
                return "StatusBean{" +
                        "value=" + value +
                        ", desc='" + desc + '\'' +
                        ", role='" + role + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", status=" + status +
                    ", student_id=" + student_id +
                    ", sex='" + sex + '\'' +
                    ", school='" + school + '\'' +
                    ", system_type='" + system_type + '\'' +
                    ", classes='" + classes + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", authc_token='" + authc_token + '\'' +
                '}';
    }
}

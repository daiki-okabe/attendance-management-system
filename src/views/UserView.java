package views;

import java.sql.Timestamp;

public class UserView {

     private Integer user_id;

        private String user_name;

        private Integer dept_id;

        private Integer user_role;

        private Integer user_class;

        private String inp_user;

        private Timestamp inp_date;

        private String upd_user;

        private Timestamp upd_date;

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Integer getDept_id() {
            return dept_id;
        }

        public void setDept_id(Integer dept_id) {
            this.dept_id = dept_id;
        }

        public Integer getUser_role() {
            return user_role;
        }

        public void setUser_role(Integer user_role) {
            this.user_role = user_role;
        }

        public Integer getUser_class() {
            return user_class;
        }

        public void setUser_class(Integer user_class) {
            this.user_class = user_class;
        }

        public String getInp_user() {
            return inp_user;
        }

        public void setInp_user(String inp_user) {
            this.inp_user = inp_user;
        }

        public Timestamp getInp_date() {
            return inp_date;
        }

        public void setInp_date(Timestamp inp_date) {
            this.inp_date = inp_date;
        }

        public String getUpd_user() {
            return upd_user;
        }

        public void setUpd_user(String upd_user) {
            this.upd_user = upd_user;
        }

        public Timestamp getUpd_date() {
            return upd_date;
        }

        public void setUpd_date(Timestamp upd_date) {
            this.upd_date = upd_date;
        }

}
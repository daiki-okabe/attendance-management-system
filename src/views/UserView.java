package views;

import java.time.LocalDateTime;

public class UserView {

     private Integer user_id;

        private String user_name;

        private Integer dept_id;

        private Integer user_role;

        private Integer user_class;

        private String password;

        private String inp_user;

        private LocalDateTime inp_date;

        private String upd_user;

        private LocalDateTime upd_date;

        private Integer del_flg;

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

        public LocalDateTime getInp_date() {
            return inp_date;
        }

        public void setInp_date(LocalDateTime now) {
            this.inp_date = now;
        }

        public String getUpd_user() {
            return upd_user;
        }

        public void setUpd_user(String upd_user) {
            this.upd_user = upd_user;
        }

        public LocalDateTime getUpd_date() {
            return upd_date;
        }

        public void setUpd_date(LocalDateTime now) {
            this.upd_date = now;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getDel_flg() {
            return del_flg;
        }

        public void setDel_flg(Integer del_flg) {
            this.del_flg = del_flg;
        }

}
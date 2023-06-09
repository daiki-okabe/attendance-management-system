package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllUsers",
        query = "SELECT u FROM User AS u WHERE u.del_flg=0 ORDER BY u.user_id ASC"
    ),
    @NamedQuery(
            name = "loginQuely",
            query = "SELECT u FROM User AS u WHERE u.del_flg=0 AND u.user_id=:id AND u.password=:password ORDER BY u.user_id ASC"
    ),
    @NamedQuery(
            name = "selectUser_UserId",
            query = "SELECT u FROM User AS u WHERE u.del_flg=0 AND u.user_id=:id ORDER BY u.user_id ASC"
    ),
    @NamedQuery(
            name = "selectUser_UserName",
            query = "SELECT u FROM User AS u WHERE u.del_flg=0 AND u.user_name=:name ORDER BY u.user_id ASC"
            )
})
@Table(name = "M_USER")
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "USER_NAME", length = 200, nullable = false)
    private String user_name;

    @Column(name = "DEPT_ID", nullable = false)
    private Integer dept_id;

    @Column(name = "USER_RANK", nullable = false)
    private Integer user_rank;

    @Column(name = "USER_ROLE", nullable = false)
    private Integer user_role;

    @Column(name = "USER_CLASS", nullable = false)
    private Integer user_class;

    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;

    @Column(name = "INP_USER", length = 200)
    private String inp_user;

    @Column(name = "INP_DATE")
    private LocalDateTime inp_date;

    @Column(name = "UPD_USER", length = 200)
    private String upd_user;

    @Column(name = "UPD_DATE")
    private LocalDateTime upd_date;

    @Column(name = "DEL_FLG")
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

    public void setInp_date(LocalDateTime inp_date) {
        this.inp_date = inp_date;
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

    public void setUpd_date(LocalDateTime upd_date) {
        this.upd_date = upd_date;
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

    public Integer getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(Integer user_rank) {
        this.user_rank = user_rank;
    }
}
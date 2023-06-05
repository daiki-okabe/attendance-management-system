package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllRequest",
        query = "SELECT r FROM Request AS r WHERE r.del_flg=0 ORDER BY r.user_id ASC"
    ),
    @NamedQuery(
            name = "selectRequest_UserId",
            query = "SELECT r FROM Request AS r WHERE r.del_flg=0 AND r.user_id=:id ORDER BY r.paper_id ASC"
    ),
    @NamedQuery(
            name = "selectRequest_RequestId",
            query = "SELECT r FROM Request AS r WHERE r.del_flg=0 AND r.request_id=:id ORDER BY r.paper_id ASC"
    )
})
@Table(name = "T_REQUEST")
public class Request {
    @Id
    @Column(name = "REQUEST_ID", length = 10)
    private Integer request_id;

    @Column(name = "USER_ID", length = 10, nullable = false)
    private Integer user_id;

    @Column(name = "PAPER_ID", nullable = false, length = 10)
    private Integer paper_id;

    @Column(name = "PROGRESS", nullable = false, length = 2)
    private Integer progress;

    @Column(name = "AGAIN_FLG", nullable = false, length = 1)
    private Integer again_flg;

    @Column(name = "OK_FLG", nullable = false, length = 1)
    private Integer ok_flg;

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

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getAgain_flg() {
        return again_flg;
    }

    public void setAgain_flg(Integer again_flg) {
        this.again_flg = again_flg;
    }

    public Integer getOk_flg() {
        return ok_flg;
    }

    public void setOk_flg(Integer ok_flg) {
        this.ok_flg = ok_flg;
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

    public Integer getDel_flg() {
        return del_flg;
    }

    public void setDel_flg(Integer del_flg) {
        this.del_flg = del_flg;
    }

}
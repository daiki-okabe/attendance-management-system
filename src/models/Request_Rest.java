package models;

import java.time.LocalDate;
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
        name = "getAllRequest_Rest",
        query = "SELECT u FROM Request_Rest AS u WHERE u.del_flg=0 ORDER BY u.request_id ASC"
    ),
    @NamedQuery(
            name = "selectRequestRest_RequestId",
            query = "SELECT r FROM Request_Rest AS r WHERE r.del_flg=0 AND r.request_rest_id=:id ORDER BY r.request_id ASC"
    ),
    @NamedQuery(
            name = "selectRequestRest_UserId",
            query = "SELECT r FROM Request_Rest AS r WHERE r.del_flg=0 AND r.user_id=:id ORDER BY r.request_id ASC"
    )
})
@Table(name = "TR_REST")
public class Request_Rest {
    @Id
    @Column(name = "REQUEST_REST_ID", length = 10)
    private Integer request_rest_id;

    @Column(name = "REQUEST_ID", length = 10)
    private Integer request_id;

    @Column(name = "USER_ID", length = 10, nullable = false)
    private Integer user_id;

    @Column(name = "USER_NAME", length = 200)
    private String user_name;

    @Column(name = "FROM_DATE")
    private LocalDate from_date;

    @Column(name = "TO_DATE")
    private LocalDate to_date;

    @Column(name = "REST_CLASS", length = 10, nullable = false)
    private Integer rest_class;

    @Column(name = "COMMENT", length = 200)
    private String comment;

    @Column(name = "RETURN_REASON", length = 200)
    private String return_reason;

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

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public Integer getRest_class() {
        return rest_class;
    }

    public void setRest_class(Integer rest_class) {
        this.rest_class = rest_class;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReturn_reason() {
        return return_reason;
    }

    public void setReturn_reason(String return_reason) {
        this.return_reason = return_reason;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getRequest_rest_id() {
        return request_rest_id;
    }

    public void setRequest_rest_id(Integer request_rest_id) {
        this.request_rest_id = request_rest_id;
    }

}
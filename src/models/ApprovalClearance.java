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
        name = "getAllApprovalClearance",
        query = "SELECT u FROM User AS u WHERE u.del_flg=0 ORDER BY u.user_id ASC"
    ),
    @NamedQuery(
            name = "selectClearance_PaperIdAndProgress",
            query = "SELECT u FROM User AS u WHERE u.del_flg=0 AND u.user_id=:id ORDER BY u.user_id ASC"
    )
})
@Table(name = "M_APPROVAL_CLEARANCE")
public class ApprovalClearance {
    @Id
    @Column(name = "PAPER_ID", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paper_id;

    @Column(name = "PROGRESS", nullable = false, length = 2)
    private Integer progress;

    @Column(name = "APPROVAL_DEPT_ID", nullable = false, length = 1)
    private Integer approval_dept_id;

    @Column(name = "APPROVAL_CLEARANCE", nullable = false, length = 1)
    private Integer approval_clearance;

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

    public Integer getApproval_dept_id() {
        return approval_dept_id;
    }

    public void setApproval_dept_id(Integer approval_dept_id) {
        this.approval_dept_id = approval_dept_id;
    }

    public Integer getApproval_clearance() {
        return approval_clearance;
    }

    public void setApproval_clearance(Integer approval_clearance) {
        this.approval_clearance = approval_clearance;
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
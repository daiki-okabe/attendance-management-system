package models;

import java.time.LocalDate;
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
        name = "getAllAttendance",
        query = "SELECT a FROM Attendance AS a WHERE a.del_flg=0 ORDER BY a.user_id ASC"
    ),
    @NamedQuery(
            name = "selectAttendance_UserId",
            query = "SELECT a FROM Attendance AS a WHERE a.del_flg=0 AND a.user_id=:id ORDER BY a.user_id ASC"
    ),
    @NamedQuery(
            name = "selectAttendance_Today",
            query = "SELECT a FROM Attendance AS a WHERE a.del_flg=0 AND a.user_id=:id AND a.date=:date ORDER BY a.user_id ASC"
    )
})
//勤怠トラン
@Table(name = "T_ATTENDANCE")
public class Attendance {
    @Id
    @Column(name = "ATTENDANCE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendance_id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "USER_ID", length = 10)
    private Integer user_id;

    @Column(name = "START_DATE")
    private LocalDateTime start_date;

    @Column(name = "END_DATE")
    private LocalDateTime end_date;

    @Column(name = "WORK_CLASS", length = 1)
    private Integer work_class;

    @Column(name = "WORK_HOLIDAY_FLG", length = 1)
    private Integer work_holiday_flg;

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

    public Integer getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(Integer attendance_id) {
        this.attendance_id = attendance_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Integer getWork_class() {
        return work_class;
    }

    public void setWork_class(Integer work_class) {
        this.work_class = work_class;
    }

    public Integer getWork_holiday_flg() {
        return work_holiday_flg;
    }

    public void setWork_holiday_flg(Integer work_holiday_flg) {
        this.work_holiday_flg = work_holiday_flg;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
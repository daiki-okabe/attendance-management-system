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
        name = "getAllDepartment",
        query = "SELECT d FROM Department AS d WHERE d.del_flg=0 ORDER BY d.division_id ASC"
    ),
    @NamedQuery(
            name = "getDepartment_deptId",
            query = "SELECT d FROM Department AS d WHERE d.del_flg=0 AND d.department_id=:dept_id ORDER BY d.department_id ASC"
    )
})
@Table(name = "M_DEPARTMENT")
public class Department {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DEPARTMENT_ID")
    private Integer department_id;

    @Column(name = "DEPARTMENT_NAME", length = 200, nullable = false)
    private String department_name;

    @Column(name = "DIVIDION_ID", length = 3, nullable = false)
    private Integer division_id;

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

    public Integer getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
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

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}